package kids.preschool.doantotnghiep.ui.account.signup

import android.content.Intent
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
		binding.btnSignup.setOnClickListener {
			val intent = Intent(this,PassAct::class.java)
			startActivity(intent)
		}
	}

	override fun getContentView(): Int {
		return R.layout.activity_signup
	}

	override fun bindViewModel() {

	}
}