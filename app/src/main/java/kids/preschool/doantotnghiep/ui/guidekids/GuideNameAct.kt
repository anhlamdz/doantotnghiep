package kids.preschool.doantotnghiep.ui.guidekids

import android.content.Intent
import io.strongapp.gymworkout.base.BaseActivity
import kids.preschool.doantotnghiep.R
import kids.preschool.doantotnghiep.databinding.ActivityGuidenameBinding

class GuideNameAct:BaseActivity<ActivityGuidenameBinding>() {
	override fun initView() {

	}

	override fun initAction() {
		binding.btnContinue.setOnClickListener {
			val intent = Intent(this,GuideAgeAct::class.java)
			startActivity(intent)
		}
	}

	override fun getContentView(): Int {
		return R.layout.activity_guidename
	}

	override fun bindViewModel() {

	}
}