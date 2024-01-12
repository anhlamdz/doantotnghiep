package kids.preschool.doantotnghiep.ui.home

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import com.bumptech.glide.Glide
import io.strongapp.gymworkout.base.BaseActivity
import kids.preschool.doantotnghiep.R
import kids.preschool.doantotnghiep.data.database.entities.CharacterEntity
import kids.preschool.doantotnghiep.databinding.ActivityHomeBinding
import kids.preschool.doantotnghiep.ui.account.setting.SettingAct
import kids.preschool.doantotnghiep.ui.home.lesson.LessonAct
import kids.preschool.doantotnghiep.ui.home.test.TestAct

class HomeAct : BaseActivity<ActivityHomeBinding>() {
	override fun initView() {
		loadCharacter()?.let { loadAvatar(it) }
	}

	override fun initAction() {
		binding.btnLesson.setOnClickListener {
			val intent = Intent(this, LessonAct::class.java)
			startActivity(intent)
		}
		binding.btnAudio.setOnClickListener {

		}
		binding.btnTest.setOnClickListener {
			val intent = Intent(this,TestAct::class.java)
			startActivity(intent)
		}
		binding.btnSound.setOnClickListener {  }
		binding.btnSetting.setOnClickListener {
			val intent = Intent(this, SettingAct::class.java)
			startActivity(intent)
		}
	}

	override fun getContentView(): Int {
		return R.layout.activity_home
	}

	override fun bindViewModel() {

	}
	private fun loadCharacter(): CharacterEntity {
		val pref = this.getSharedPreferences("character", MODE_PRIVATE)
		val characterString = pref.getString("character", "")?:""
		return CharacterEntity.fromString(characterString)
	}
	fun loadAvatar(character:CharacterEntity) {
		Glide.with(this)
			.asBitmap()
			.load(character.image)
			.into(binding.avatar)
	}
}