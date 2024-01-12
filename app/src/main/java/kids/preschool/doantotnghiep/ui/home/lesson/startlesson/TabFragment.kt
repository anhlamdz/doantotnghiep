package kids.preschool.doantotnghiep.ui.home.lesson.startlesson

import android.media.MediaPlayer
import android.os.Bundle
import io.strongapp.gymworkout.base.BaseFragment
import kids.preschool.doantotnghiep.R
import kids.preschool.doantotnghiep.databinding.FragmentTabLessonBinding

class TabFragment : BaseFragment<FragmentTabLessonBinding>() {
	private var mediaPlayer: MediaPlayer? = null
	override fun getLayoutRes(): Int {
		return R.layout.fragment_tab_lesson
	}

	override fun initAction() {

	}

	override fun initView() {
		val imageLesson = arguments?.getInt(ARG_IMAGE_RES_ID,0)?: 0
		val stringLesson = arguments?.getString(ARG_TEXT)?: ""
		val soundLessonAct = arguments?.getInt(ARG_SOUND_RES_ID,0)?:0

		binding.image.setImageResource(imageLesson)
		binding.name.text = stringLesson
		binding.sound.setOnClickListener {
			mediaPlayer = MediaPlayer.create(context, soundLessonAct)
			mediaPlayer?.start()
			mediaPlayer?.setOnCompletionListener {
				it.release()
			}
		}
	}

	companion object {
		private const val ARG_IMAGE_RES_ID = "image_res_id"
		private const val ARG_TEXT = "text"
		private const val ARG_SOUND_RES_ID = "sound_res_id"

		fun newInstance(imageResId: Int,text : String,soundResInt: Int): TabFragment {
			val fragment = TabFragment()
			val args = Bundle()
			args.putInt(ARG_IMAGE_RES_ID, imageResId)
			args.putString(ARG_TEXT,text)
			args.putInt(ARG_SOUND_RES_ID,soundResInt)
			fragment.arguments = args
			return fragment
		}
	}
}