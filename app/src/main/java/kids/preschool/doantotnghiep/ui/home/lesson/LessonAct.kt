package kids.preschool.doantotnghiep.ui.home.lesson

import androidx.recyclerview.widget.GridLayoutManager
import io.strongapp.gymworkout.base.BaseActivity
import kids.preschool.doantotnghiep.R
import kids.preschool.doantotnghiep.data.models.LessonEntity
import kids.preschool.doantotnghiep.databinding.ActivityLessonBinding
import kids.preschool.doantotnghiep.ui.home.lesson.adapter.LessonAdapter

class LessonAct: BaseActivity<ActivityLessonBinding>() {
	private val listLesson  = listOf(
		LessonEntity(R.string.lesson1_1,R.color.lessonColor1,R.drawable.bangchucai),
		LessonEntity(R.string.lesson1_2,R.color.lessonColor1,R.drawable.bangchucai),
		LessonEntity(R.string.lesson2,R.color.lessonColor2,R.drawable.sodem),
		LessonEntity(R.string.lesson3,R.color.lessonColor3,R.drawable.mausac),
		LessonEntity(R.string.lesson4,R.color.lessonColor4,R.drawable.hinhdang),
		LessonEntity(R.string.lesson5,R.color.lessonColor5,R.drawable.xeco),
		LessonEntity(R.string.lesson6,R.color.lessonColor6,R.drawable.dongvat),
		LessonEntity(R.string.lesson7,R.color.lessonColor7,R.drawable.raucu),
		LessonEntity(R.string.lesson8,R.color.lessonColor8,R.drawable.thoitiet),
	)

	override fun initView() {
		binding.rcvLesson.layoutManager = GridLayoutManager(this, 4)
		val lessonAdapter = LessonAdapter(this,listLesson)
		binding.rcvLesson.adapter = lessonAdapter
	}

	override fun initAction() {
		binding.btnBack.setOnClickListener {
			finish()
		}
	}

	override fun getContentView(): Int {
		return R.layout.activity_lesson
	}

	override fun bindViewModel() {

	}
}