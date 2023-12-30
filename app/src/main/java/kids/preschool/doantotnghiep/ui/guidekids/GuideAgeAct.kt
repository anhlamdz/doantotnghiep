package kids.preschool.doantotnghiep.ui.guidekids

import io.strongapp.gymworkout.base.BaseActivity
import kids.preschool.doantotnghiep.R
import kids.preschool.doantotnghiep.databinding.ActivityGuideAgeBinding

class GuideAgeAct : BaseActivity<ActivityGuideAgeBinding>() {
	override fun initView() {

	}

	override fun initAction() {
		binding.btnBack.setOnClickListener {
			finish()
		}
		binding.btnContinue.setOnClickListener {

		}
	}

	override fun getContentView(): Int {
		return R.layout.activity_guide_age
	}

	override fun bindViewModel() {

	}
}