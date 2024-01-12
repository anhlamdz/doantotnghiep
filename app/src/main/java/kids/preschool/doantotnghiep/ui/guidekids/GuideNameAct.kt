package kids.preschool.doantotnghiep.ui.guidekids

import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import io.strongapp.gymworkout.base.BaseActivity
import kids.preschool.doantotnghiep.R
import kids.preschool.doantotnghiep.databinding.ActivityGuidenameBinding
import kids.preschool.doantotnghiep.ui.guidekids.viewmodel.GuideCharacterViewModel
import kids.preschool.doantotnghiep.ui.home.test.TestEndAct

class GuideNameAct:BaseActivity<ActivityGuidenameBinding>() {
	override fun initView() {
		if (intent.hasExtra("mode")){
			binding.btnBack.visibility = View.VISIBLE
		}
		else {
			binding.btnBack.visibility = View.GONE
		}
	}

	override fun initAction() {
		binding.btnContinue.setOnClickListener {
			if (!getName().isNullOrEmpty()){
				val intent = Intent(this,GuideAgeAct::class.java)
					intent.putExtra("name",getName())
				startActivity(intent)
			}else{
				Toast.makeText(this, resources.getText(R.string.warning_name),Toast.LENGTH_SHORT).show()
			}
		}
		binding.btnBack.setOnClickListener {
			val builder = AlertDialog.Builder(this)
			builder.setMessage(resources.getString(R.string.cancell_create))

			builder.setPositiveButton(resources.getString(R.string.btn_yes)) { dialogInterface: DialogInterface, i: Int ->
				finish()
			}
			builder.setNegativeButton(resources.getString(R.string.btn_no)) { dialogInterface: DialogInterface, i: Int ->
				dialogInterface.dismiss()
			}
			builder.show()
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