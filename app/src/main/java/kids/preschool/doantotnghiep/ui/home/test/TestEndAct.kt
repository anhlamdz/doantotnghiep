package kids.preschool.doantotnghiep.ui.home.test

import android.content.Intent
import android.os.Handler
import android.os.Looper
import io.strongapp.gymworkout.base.BaseActivity
import kids.preschool.doantotnghiep.R
import kids.preschool.doantotnghiep.databinding.ActivityEndTestBinding
import kids.preschool.doantotnghiep.ui.home.HomeAct

class TestEndAct : BaseActivity<ActivityEndTestBinding>() {
	private val handler = Handler(Looper.getMainLooper())
	override fun initView() {
		val score = intent.getIntExtra("score",0)
		binding.score.text = "${score}đ"


		val intent = Intent(this , HomeAct::class.java)
		handler.postDelayed({ startActivity(intent) },5000)
	}

	override fun initAction() {

	}

	override fun getContentView(): Int {
		return R.layout.activity_end_test
	}

	override fun bindViewModel() {
	}
}