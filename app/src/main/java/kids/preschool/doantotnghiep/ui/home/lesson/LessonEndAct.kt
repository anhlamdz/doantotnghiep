package kids.preschool.doantotnghiep.ui.home.lesson

import android.content.Intent
import android.os.Handler
import android.os.Looper
import io.strongapp.gymworkout.base.BaseActivity
import kids.preschool.doantotnghiep.R
import kids.preschool.doantotnghiep.databinding.LessonEndBinding
import kids.preschool.doantotnghiep.ui.home.HomeAct

class LessonEndAct : BaseActivity<LessonEndBinding>() {
	private val handler = Handler(Looper.getMainLooper())
	override fun initView() {
		val intent = Intent(this , HomeAct::class.java)
		handler.postDelayed({ startActivity(intent) },5000)
	}

	override fun initAction() {

	}

	override fun getContentView(): Int {
		return R.layout.lesson_end
	}

	override fun bindViewModel() {

	}
}