package kids.preschool.doantotnghiep.ui.home.lesson.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import kids.preschool.doantotnghiep.data.models.ExInLessonEntity
import kids.preschool.doantotnghiep.ui.home.lesson.startlesson.TabFragment

class TabAdapter(
	private val list : List<ExInLessonEntity>,
	fragmentActivity: FragmentActivity) :
FragmentStateAdapter(fragmentActivity){
	override fun getItemCount(): Int {
		return list.size
	}

	override fun createFragment(position: Int): Fragment {
		val pageModel = list[position]
		return TabFragment.newInstance(pageModel.image,pageModel.text,pageModel.sound)
	}
}