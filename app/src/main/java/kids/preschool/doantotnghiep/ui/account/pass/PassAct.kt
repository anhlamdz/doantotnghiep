package kids.preschool.doantotnghiep.ui.account.pass

import android.content.Intent
import io.strongapp.gymworkout.base.BaseActivity
import kids.preschool.doantotnghiep.R
import kids.preschool.doantotnghiep.databinding.ActivityPassBinding
import kids.preschool.doantotnghiep.ui.guidekids.GuideNameAct

class PassAct : BaseActivity<ActivityPassBinding>() {
	override fun initView() {

	}

	override fun initAction() {
		binding.btnBack.setOnClickListener {
			finish()
		}
		binding.btnLogin.setOnClickListener {
			val intent = Intent(this, GuideNameAct::class.java)
			startActivity(intent)
			finish()
		}

	}

	override fun getContentView(): Int {
		return R.layout.activity_pass
	}

	override fun bindViewModel() {

	}
}