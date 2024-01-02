package kids.preschool.doantotnghiep.ui.guidekids

import android.content.Intent
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import io.strongapp.gymworkout.base.BaseActivity
import kids.preschool.doantotnghiep.R
import kids.preschool.doantotnghiep.databinding.ActivityGuideAgeBinding
import kids.preschool.doantotnghiep.ui.guidekids.viewmodel.GuideCharacterViewModel

class GuideAgeAct : BaseActivity<ActivityGuideAgeBinding>() {
	private lateinit var guideCharacterViewModel: GuideCharacterViewModel
	private var age : Int = 0
	private var selectedAgeTextView: TextView? = null
	private var name : String = ""
	override fun initView() {
		guideCharacterViewModel = ViewModelProvider(this)[GuideCharacterViewModel::class.java]
		name = intent.getStringExtra("name") as String
	}

	override fun initAction() {
		binding.btnBack.setOnClickListener {
			finish()
		}
		binding.btnContinue.setOnClickListener {
			if (age == 0){
				Toast.makeText(this,resources.getText(R.string.warning_age),Toast.LENGTH_SHORT).show()
			}else{
				val intent = Intent(this,GuideImageAct::class.java)
				intent.putExtra("name", name)
				intent.putExtra("age",age)
				startActivity(intent)
			}
		}
		binding.age6.setOnClickListener {
			resetAllAges()
			selectAge(binding.age6)
			age = binding.age6.text.toString().toInt()
			ifCheck()
		}
		binding.age7.setOnClickListener {
			resetAllAges()
			selectAge(binding.age7)
			age = binding.age7.text.toString().toInt()
			ifCheck()}
		binding.age8.setOnClickListener {
			resetAllAges()
			selectAge(binding.age8)
			age = binding.age8.text.toString().toInt()
			ifCheck()}
		binding.age9.setOnClickListener {
			resetAllAges()
			selectAge(binding.age9)
			age = binding.age9.text.toString().toInt()
			ifCheck()}
		binding.age10.setOnClickListener {
			resetAllAges()
			selectAge(binding.age10)
			age = binding.age10.text.toString().toInt()
			ifCheck()}


	}

	override fun getContentView(): Int {
		return R.layout.activity_guide_age
	}

	override fun bindViewModel() {

	}
	private fun selectAge(ageTextView: TextView) {
		selectedAgeTextView?.post {
			selectedAgeTextView?.setBackgroundResource(R.drawable.circle_age_checked)
			selectedAgeTextView?.setTextColor(resources.getColor(R.color.white))
		}

		ageTextView.post {
			ageTextView.setBackgroundResource(R.drawable.circle_age_checked)
			ageTextView.setTextColor(resources.getColor(R.color.white))
		}

		selectedAgeTextView = ageTextView

	}

	private fun resetAllAges() {
		binding.age6.post {
			binding.age6.setBackgroundResource(R.drawable.circle_age)
			binding.age6.setTextColor(resources.getColor(R.color.black))
		}

		binding.age7.post {
			binding.age7.setBackgroundResource(R.drawable.circle_age)
			binding.age7.setTextColor(resources.getColor(R.color.black))
		}

		binding.age8.post {
			binding.age8.setBackgroundResource(R.drawable.circle_age)
			binding.age8.setTextColor(resources.getColor(R.color.black))
		}

		binding.age9.post {
			binding.age9.setBackgroundResource(R.drawable.circle_age)
			binding.age9.setTextColor(resources.getColor(R.color.black))
		}

		binding.age10.post {
			binding.age10.setBackgroundResource(R.drawable.circle_age)
			binding.age10.setTextColor(resources.getColor(R.color.black))
		}
	}
	fun ifCheck() {
		if(age != null) binding.btnContinue.setBackgroundResource(R.drawable.bg_btn_selected)
		else binding.btnContinue.setBackgroundResource(R.drawable.bg_btn_unselected)
	}

}