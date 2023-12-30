package kids.preschool.doantotnghiep.ui.account

import android.content.Intent
import io.strongapp.gymworkout.base.BaseActivity
import kids.preschool.doantotnghiep.R
import kids.preschool.doantotnghiep.databinding.ActivityAccountBinding
import kids.preschool.doantotnghiep.ui.account.login.LoginAct
import kids.preschool.doantotnghiep.ui.account.signup.SignUpAct

class AccountAct : BaseActivity<ActivityAccountBinding>() {
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
			val intent = Intent(this, SignUpAct::class.java)
			startActivity(intent)
		}
	}

	override fun getContentView(): Int {
		return R.layout.activity_account
	}

	override fun bindViewModel() {

	}
}