package kids.preschool.doantotnghiep.ui.guidekids

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import io.strongapp.gymworkout.base.BaseActivity
import kids.preschool.doantotnghiep.R
import kids.preschool.doantotnghiep.databinding.ActivityGuidenameBinding
import kids.preschool.doantotnghiep.ui.guidekids.viewmodel.GuideCharacterViewModel

class GuideNameAct:BaseActivity<ActivityGuidenameBinding>() {
	private lateinit var guideCharacterViewModel: GuideCharacterViewModel
	override fun initView() {
	guideCharacterViewModel = ViewModelProvider(this)[GuideCharacterViewModel::class.java]
	}

	override fun initAction() {
		binding.btnContinue.setOnClickListener {
			if (!getName().isNullOrEmpty()){
				guideCharacterViewModel.setName(getName())
				val intent = Intent(this,GuideAgeAct::class.java)
					intent.putExtra("name",getName())
				startActivity(intent)
			}else{
				Toast.makeText(this, resources.getText(R.string.warning_name),Toast.LENGTH_SHORT).show()
			}
		}
	}

	override fun getContentView(): Int {
		return R.layout.activity_guidename
	}

	override fun bindViewModel() {

	}
	fun getName() : String {
		return binding.name.text.toString().trim()
	}
}