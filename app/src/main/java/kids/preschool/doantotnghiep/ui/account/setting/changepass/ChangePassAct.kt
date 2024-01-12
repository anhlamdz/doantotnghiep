package kids.preschool.doantotnghiep.ui.account.setting.changepass

import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.strongapp.gymworkout.base.BaseActivity
import kids.preschool.doantotnghiep.R
import kids.preschool.doantotnghiep.data.database.entities.UserEntity
import kids.preschool.doantotnghiep.databinding.ActivityChangePassBinding
import kids.preschool.doantotnghiep.ui.account.viewmodel.AccountViewModel

class ChangePassAct  : BaseActivity<ActivityChangePassBinding>(){
	private lateinit var userEntity : UserEntity
	private lateinit var accountViewModel: AccountViewModel
	private lateinit var dbRef : DatabaseReference
	private lateinit var auth: FirebaseAuth
	override fun initView() {
		auth = Firebase.auth
		dbRef = FirebaseDatabase.getInstance().getReference("User")
		accountViewModel = ViewModelProvider(this)[AccountViewModel::class.java]
		userEntity = intent.getSerializableExtra("user") as UserEntity

	}

	override fun initAction() {
		binding.btnBack.setOnClickListener {
			finish()
		}
		binding.btnUpdatePass.setOnClickListener {
			when(checkPassOld(binding.passOld.text.toString())){
				true -> {
					when(checkPassNew(binding.passNew.text.toString(), binding.returnPassNew.text.toString())){
						true ->{
							when (binding.passNew.text.toString().equals(binding.returnPassNew.text.toString())){
								true -> {
									val newUser = UserEntity(userEntity.id,userEntity.email,binding.passNew.text.toString(),"",userEntity.nameParent)
									dbRef.child(userEntity.id).setValue(newUser)
										.addOnCompleteListener {
											accountViewModel.updateAccount(newUser)
											Toast.makeText(this,resources.getText(R.string.update_user),Toast.LENGTH_LONG).show()
										}
										.addOnFailureListener {
											Toast.makeText(this,"${it.message}",Toast.LENGTH_LONG).show()
										}
									setResult(Activity.RESULT_OK,intent)
									finish()
								}
								false -> {
									Toast.makeText(this,resources.getText(R.string.warning_pass_equal),Toast.LENGTH_SHORT).show()
								}
							}
						}
						false ->{
							Toast.makeText(this,resources.getText(R.string.warning_pass_new),Toast.LENGTH_SHORT).show()
						}
					}
				}
				false -> {
					Toast.makeText(this,resources.getText(R.string.warning_pass_old),Toast.LENGTH_SHORT).show()
				}
			}

		}
	}

	override fun getContentView(): Int {
		return R.layout.activity_change_pass
	}

	override fun bindViewModel() {

	}
	fun checkPassOld(pass : String) : Boolean {
		if (!pass.equals(userEntity.pass)) {
			return false
		}
		return true
	}
	fun checkPassNew(passNew : String, returnPassNew : String) : Boolean {
		val passwordRegex = "^(?=.*[0-9])(?=.*[a-z]).{6,}$".toRegex()
		if (!passNew.matches(passwordRegex) && !returnPassNew.matches(passwordRegex)) {
			return false
		}
		return true
	}
}