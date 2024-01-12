package kids.preschool.doantotnghiep.ui.account.login

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import io.strongapp.gymworkout.base.BaseActivity
import kids.preschool.doantotnghiep.R
import kids.preschool.doantotnghiep.data.database.entities.CharacterEntity
import kids.preschool.doantotnghiep.data.database.entities.UserEntity
import kids.preschool.doantotnghiep.databinding.ActivityLoginBinding
import kids.preschool.doantotnghiep.ui.account.signup.SignUpAct
import kids.preschool.doantotnghiep.ui.account.viewmodel.AccountViewModel
import kids.preschool.doantotnghiep.ui.guidekids.CharacterAct
import kids.preschool.doantotnghiep.ui.guidekids.GuideNameAct
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginAct : BaseActivity<ActivityLoginBinding>() {
	private val firebaseDatabase = FirebaseDatabase.getInstance()
	private lateinit var mAuth : FirebaseAuth
	private lateinit var accountViewModel: AccountViewModel
	override fun initView() {
		mAuth = FirebaseAuth.getInstance()
		accountViewModel = ViewModelProvider(this)[AccountViewModel::class.java]
	}

	override fun initAction() {
		binding.btnBack.setOnClickListener {
			finish()
		}
		binding.btnSignup.setOnClickListener {
			val intent = Intent(this, SignUpAct::class.java)
			startActivity(intent)
		}
		binding.btnLogin.setOnClickListener {
			when(validateCredentials(binding.username.text.toString(),binding.pass.text.toString())){
				true->{
					mAuth.signInWithEmailAndPassword(binding.username.text.toString(),binding.pass.text.toString())
						.addOnCompleteListener {
							loadUserDataToRoom()
						}
						.addOnFailureListener {
							Toast.makeText(this,"Lỗi ${it.message}", Toast.LENGTH_SHORT).show()
						}
				}
				false ->{
					Toast.makeText(this,resources.getText(R.string.is_empty),Toast.LENGTH_SHORT).show()
				}
			}
		}
	}

	override fun getContentView(): Int {
		return R.layout.activity_login
	}

	override fun bindViewModel() {

	}
	fun validateCredentials(username: String, password: String): Boolean {
		if (username.isEmpty() || password.isEmpty()){
			return false
		}
		return true
	}
	private fun loadUserDataToRoom() {
		firebaseDatabase.getReference("User").addListenerForSingleValueEvent(object : ValueEventListener {
			override fun onDataChange(snapshot: DataSnapshot) {
				for (userSnap in snapshot.children) {
					val email = userSnap.child("email").getValue(String::class.java)
					if (binding.username.text.toString() == email) {
						val userData = userSnap.getValue(UserEntity::class.java)
						userData?.let {
							lifecycleScope.launch {
								accountViewModel.insertAccount(userData)
							}
						}
						break
					}
				}

			}

			override fun onCancelled(error: DatabaseError) {
				Log.d("FirebaseData", "lỗi ${error.message}")
			}
		})
		firebaseDatabase.getReference("Character").addListenerForSingleValueEvent(object : ValueEventListener {
			override fun onDataChange(snapshot: DataSnapshot) {
				for (userSnap in snapshot.children) {
					val idUser = userSnap.child("idUser").getValue(String::class.java)
					if (mAuth.currentUser!!.uid == idUser ) {
						val data = userSnap.getValue(CharacterEntity::class.java)
						Log.d("FirebaseData", "userData: $data")
						lifecycleScope.launch {
							data?.let { accountViewModel.insertCharacter(it) }
							goToHome()

						}
						val storageReference = FirebaseStorage.getInstance().getReference().child("${data!!.id}.png")
						storageReference.downloadUrl.addOnSuccessListener { uri ->

						}
						break
					}
				}

			}

			override fun onCancelled(error: DatabaseError) {
				Log.d("FirebaseData", "lỗi ${error.message}")
			}
		})

	}
	fun goToHome(){
		val intent = Intent(this, CharacterAct::class.java)
		startActivity(intent)
	}
}