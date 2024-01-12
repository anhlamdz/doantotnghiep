package kids.preschool.doantotnghiep.ui.account.pass

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.strongapp.gymworkout.base.BaseActivity
import kids.preschool.doantotnghiep.R
import kids.preschool.doantotnghiep.data.database.entities.UserEntity
import kids.preschool.doantotnghiep.databinding.ActivityPassBinding
import kids.preschool.doantotnghiep.ui.account.viewmodel.AccountViewModel
import kids.preschool.doantotnghiep.ui.guidekids.GuideNameAct
import kotlin.random.Random


class PassAct : BaseActivity<ActivityPassBinding>() {
	private lateinit var username: String
	private lateinit var accountViewModel: AccountViewModel
	private lateinit var dbRef : DatabaseReference
	private lateinit var mAuth : FirebaseAuth
	override fun initView() {
		dbRef = FirebaseDatabase.getInstance().getReference("User")
		mAuth = FirebaseAuth.getInstance()
		accountViewModel = ViewModelProvider(this)[AccountViewModel::class.java]
		username = intent.getStringExtra("username")!!
	}

	override fun initAction() {
		binding.btnBack.setOnClickListener {
			finish()
		}
		binding.btnLogin.setOnClickListener {
			when(validateCredentials(username,binding.pass.text.toString(),binding.returnPass.text.toString())){
				true -> {
					register(username,binding.pass.text.toString())

				}
				false -> {
					if (binding.pass.text.toString() != binding.returnPass.text.toString()) {
						Toast.makeText(this,resources.getText(R.string.warning_pass),Toast.LENGTH_SHORT).show()
					}
				}
			}

		}

	}

	override fun getContentView(): Int {
		return R.layout.activity_pass
	}

	override fun bindViewModel() {

	}
	fun validateCredentials(username: String, password: String,returnPass : String): Boolean {
		if (username.isEmpty() || password.isEmpty() || returnPass.isEmpty()){
			return false
		}
		val emailRegex = "^[a-zA-Z0-9._-]+@gmail.com$".toRegex()
		if (!username.matches(emailRegex)) {
			return false
		}
		val passwordRegex = "^(?=.*[0-9])(?=.*[a-z]).{6,}$".toRegex()
		if (!password.matches(passwordRegex)) {
			return false
		}
		if (!returnPass.matches(passwordRegex)) {
			return false
		}
		return true
	}
	fun register(username: String, password: String) {
		mAuth.createUserWithEmailAndPassword(username,password)
			.addOnCompleteListener {
				val id  = mAuth.currentUser!!.uid
				val newUser = UserEntity(id,username,binding.pass.text.toString(),"","")
				dbRef.child(id).setValue(newUser)
					.addOnCompleteListener{
						val intent = Intent(this, GuideNameAct::class.java)
						startActivity(intent)
						finish()
						Toast.makeText(this,resources.getText(R.string.sign_success),Toast.LENGTH_SHORT).show()
					}
					.addOnFailureListener {
						Toast.makeText(this,"Lỗi ${it.message}",Toast.LENGTH_SHORT).show()
						Log.i("hahaha", "failded ${it.message}")
					}

				accountViewModel.insertAccount(newUser)

			}
			.addOnFailureListener {
				Toast.makeText(this,"Lỗi ${it.message}",Toast.LENGTH_SHORT).show()
			}
	}
}