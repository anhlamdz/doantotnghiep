package kids.preschool.doantotnghiep.ui.account.login

import android.content.Intent
import io.strongapp.gymworkout.base.BaseActivity
import kids.preschool.doantotnghiep.R
import kids.preschool.doantotnghiep.databinding.ActivityLoginBinding
import kids.preschool.doantotnghiep.ui.account.signup.SignUpAct
import kids.preschool.doantotnghiep.ui.guidekids.GuideNameAct

class LoginAct : BaseActivity<ActivityLoginBinding>() {
	override fun initView() {

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
			val intent = Intent(this, GuideNameAct::class.java)
			startActivity(intent)
			finish()
		}
	}

	override fun getContentView(): Int {
		return R.layout.activity_login
	}

	override fun bindViewModel() {

	}
}