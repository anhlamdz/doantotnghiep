package kids.preschool.doantotnghiep.ui.account.pass

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
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
	override fun initView() {
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
					val id  = Random.nextLong()
					val newUser = UserEntity(id,username,binding.pass.text.toString())
					accountViewModel.insertAccount(newUser)
					val intent = Intent(this, GuideNameAct::class.java)
					startActivity(intent)
					finish()
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
	fun validateCredentials(username: String, password: String, rePassword : String): Boolean {
		if (username.isEmpty() || password.isEmpty()){
			return false
		}
		val passwordRegex = "^(?=.*[0-9])(?=.*[a-z]).{6,}$".toRegex()
		if (!password.matches(passwordRegex)) {
			Toast.makeText(this,resources.getText(R.string.warning_pass),Toast.LENGTH_SHORT).show()
			return false
		}
		if (password != rePassword){
			return false
			Toast.makeText(this,resources.getText(R.string.warning_pass),Toast.LENGTH_SHORT).show()
		}
		return true
	}
}