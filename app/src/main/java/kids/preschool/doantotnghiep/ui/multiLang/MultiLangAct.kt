package kids.preschool.doantotnghiep.ui.multiLang

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.view.View
import io.strongapp.gymworkout.base.BaseActivity
import kids.preschool.doantotnghiep.R
import kids.preschool.doantotnghiep.databinding.ActivityMultiLangBinding
import kids.preschool.doantotnghiep.ui.account.AccountAct
import java.util.Locale

class MultiLangAct : BaseActivity<ActivityMultiLangBinding>() {
	private var selectedLang: String? = null
	private var myLocale: Locale? = null
	override fun initView() {
		setLocale(this)
	}

	override fun initAction() {
		binding.btnEng.setOnClickListener {
			selectedLang = "en"
			updateLangViews()
		}
		binding.btnVn.setOnClickListener {
			selectedLang = "vi"
			updateLangViews()
		}
		binding.btnSave.setOnClickListener {
			selectedLang?.let { it1 -> savePositionLang(it1) }
			setLocale(this)
			val intent = Intent(this, AccountAct::class.java)
			startActivity(intent)

		}
	}

	override fun getContentView(): Int {
		return R.layout.activity_multi_lang
	}

	override fun bindViewModel() {

	}
	private fun savePositionLang(language : String) {
		val pref = this.getSharedPreferences(
			"lang", MODE_PRIVATE
		)
		val editor = pref.edit()
		editor.putString("positionLang", language)
		editor.commit()
	}
	fun getLang() : String{
		val pref = this.getSharedPreferences("lang", MODE_PRIVATE)
		val lang =  pref.getString("positionLang","") ?:"vi"
		return lang

	}

	fun setLocale(context: Context) {
		val language = getLang()
		if (language == "") {
			val config = Configuration()
			val locale = Locale.getDefault()
			Locale.setDefault(locale)
			config.locale = locale
			context.resources
				.updateConfiguration(config, context.resources.displayMetrics)
		} else {
			changeLang(language, context)
		}
	}

	fun changeLang(lang: String?, context: Context) {
		if (lang.equals("", ignoreCase = true)) return
		myLocale = lang?.let { Locale(it) }
		lang?.let { savePositionLang(it) }
		myLocale?.let { Locale.setDefault(it) }
		val config = Configuration()
		config.locale = myLocale
		context.resources.updateConfiguration(config, context.resources.displayMetrics)
	}
	private fun updateLangViews() {
		binding.eng.isSelected = selectedLang == "en"
		binding.vn.isSelected = selectedLang == "vi"
		binding.btnEng.background =
			if (selectedLang == "en") resources.getDrawable(R.drawable.bg_border_item_lang)
			else resources.getDrawable(R.drawable.bg_border_item_lang_unselect)
		binding.btnVn.background =
			if (selectedLang == "vi") resources.getDrawable(R.drawable.bg_border_item_lang)
			else resources.getDrawable(R.drawable.bg_border_item_lang_unselect)
		// Kiểm tra xem có giới tính được chọn hay không và cập nhật màu sắc của nút "Next"
		if (!selectedLang.isNullOrBlank()) {
			binding.btnSave.visibility = View.VISIBLE
		} else {
			binding.btnSave.visibility = View.GONE
		}
	}

}