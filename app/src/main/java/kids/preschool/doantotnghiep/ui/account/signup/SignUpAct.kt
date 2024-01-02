package kids.preschool.doantotnghiep.ui.account.signup

import android.content.Intent
import android.util.Patterns
import android.widget.Toast
import io.strongapp.gymworkout.base.BaseActivity
import kids.preschool.doantotnghiep.R
import kids.preschool.doantotnghiep.databinding.ActivitySignupBinding
import kids.preschool.doantotnghiep.ui.account.login.LoginAct
import kids.preschool.doantotnghiep.ui.account.pass.PassAct

class SignUpAct : BaseActivity<ActivitySignupBinding>() {
	override fun initView() {

	}

	override fun initAction() {
		binding.btnBack.setOnClickListener {
			finish()
		}
		binding.btnLogin.setOnClickListener {
			val intent = Intent(this, LoginAct::class.java)
			startActivity(intent)
		}
		binding.btnContinue.setOnClickListener {
			when(isValidPhoneNumber(binding.numberPhone.text.toString())){
				true -> {
					val intent = Intent(this,PassAct::class.java)
					intent.putExtra("username",binding.numberPhone.text.toString())
					startActivity(intent)
				}
				false -> {
					Toast.makeText(this,resources.getText(R.string.warning_number_phone),Toast.LENGTH_SHORT).show()
				}
			}

		}
	}

	override fun getContentView(): Int {
		return R.layout.activity_signup
	}

	override fun bindViewModel() {

	}
	fun isValidPhoneNumber(phoneNumber: String): Boolean {
		val pattern = Patterns.PHONE
		return pattern.matcher(phoneNumber).matches()
	}



}