package kids.preschool.doantotnghiep.ui.home.lesson.startlesson

import android.content.Intent
import android.util.Log
import androidx.fragment.app.Fragment
import io.strongapp.gymworkout.base.BaseActivity
import kids.preschool.doantotnghiep.R
import kids.preschool.doantotnghiep.data.models.ExInLessonEntity
import kids.preschool.doantotnghiep.databinding.ActivityStartLessonBinding
import kids.preschool.doantotnghiep.ui.home.lesson.LessonEndAct
import kids.preschool.doantotnghiep.ui.home.lesson.adapter.TabAdapter

class StartLessonAct : BaseActivity<ActivityStartLessonBinding>() {
	private lateinit var name : String
	override fun initView() {
		name = intent.getStringExtra("lesson")?: ""
		val lesson = TabAdapter(getList(name),this)
		binding.viewpager2.adapter = lesson
	}

	override fun initAction() {
		binding.btnContinue.setOnClickListener {
			if (binding.viewpager2.currentItem == getList(name).size-1){
				val intent = Intent(this, LessonEndAct::class.java)
				startActivity(intent)
			}else {
				binding.viewpager2.currentItem = binding.viewpager2.currentItem +1
			}
		}
		binding.btnPrevious.setOnClickListener {
			binding.viewpager2.currentItem = binding.viewpager2.currentItem -1
		}
		binding.btnBack.setOnClickListener {
			finish()
		}
	}

	override fun getContentView(): Int {
		return R.layout.activity_start_lesson
	}

	override fun bindViewModel() {

	}
	fun getList(name : String) : List<ExInLessonEntity> {
		when(name){
			resources.getString(R.string.lesson1_1) -> {
				return listOf(
					ExInLessonEntity(R.drawable.a,"A",R.raw.a),
					ExInLessonEntity(R.drawable.b,"B",R.raw.b),
					ExInLessonEntity(R.drawable.c,"C",R.raw.c),
					ExInLessonEntity(R.drawable.d,"D",R.raw.d),
					ExInLessonEntity(R.drawable.e,"E",R.raw.e),
					ExInLessonEntity(R.drawable.f,"F",R.raw.f),
					ExInLessonEntity(R.drawable.g,"G",R.raw.g),
					ExInLessonEntity(R.drawable.h,"H",R.raw.h),
					ExInLessonEntity(R.drawable.i,"I",R.raw.i),
					ExInLessonEntity(R.drawable.j,"J",R.raw.j),
					ExInLessonEntity(R.drawable.k,"K",R.raw.k),
					ExInLessonEntity(R.drawable.l,"L",R.raw.l),
					ExInLessonEntity(R.drawable.m,"M",R.raw.m)
				)
			}
			resources.getString(R.string.lesson1_2) -> {
				return listOf(
					ExInLessonEntity(R.drawable.n,"N",R.raw.n),
					ExInLessonEntity(R.drawable.o,"O",R.raw.o),
					ExInLessonEntity(R.drawable.p,"P",R.raw.p),
					ExInLessonEntity(R.drawable.q,"Q",R.raw.q),
					ExInLessonEntity(R.drawable.r,"R",R.raw.r),
					ExInLessonEntity(R.drawable.s,"S",R.raw.s),
					ExInLessonEntity(R.drawable.t,"T",R.raw.t),
					ExInLessonEntity(R.drawable.u,"U",R.raw.u),
					ExInLessonEntity(R.drawable.v,"V",R.raw.v),
					ExInLessonEntity(R.drawable.w,"W",R.raw.w),
					ExInLessonEntity(R.drawable.x,"X",R.raw.x),
					ExInLessonEntity(R.drawable.y,"Y",R.raw.y),
					ExInLessonEntity(R.drawable.z,"Z",R.raw.z)
				)
			}
			resources.getString(R.string.lesson2) -> {
				return listOf(
					ExInLessonEntity(R.drawable.one,"One",R.raw.one),
					ExInLessonEntity(R.drawable.two,"Two",R.raw.two),
					ExInLessonEntity(R.drawable.three,"Three",R.raw.three),
					ExInLessonEntity(R.drawable.four,"Four",R.raw.four),
					ExInLessonEntity(R.drawable.five,"Five",R.raw.five),
					ExInLessonEntity(R.drawable.six,"Six",R.raw.six),
					ExInLessonEntity(R.drawable.seven,"Seven",R.raw.seven),
					ExInLessonEntity(R.drawable.eight,"Eight",R.raw.eight),
					ExInLessonEntity(R.drawable.nine,"Nine",R.raw.nine),
					ExInLessonEntity(R.drawable.ten,"Ten",R.raw.ten),
				)
			}
		}
		return emptyList()
	}
}