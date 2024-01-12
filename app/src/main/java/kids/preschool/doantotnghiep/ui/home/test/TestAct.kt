package kids.preschool.doantotnghiep.ui.home.test

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import io.strongapp.gymworkout.base.BaseActivity
import kids.preschool.doantotnghiep.R
import kids.preschool.doantotnghiep.data.database.entities.CharacterEntity
import kids.preschool.doantotnghiep.data.database.entities.ScoreCharacterEntity
import kids.preschool.doantotnghiep.data.database.entities.UserEntity
import kids.preschool.doantotnghiep.databinding.ActivityTestBinding
import kids.preschool.doantotnghiep.ui.home.test.adapter.TestAdapter
import kids.preschool.doantotnghiep.ui.home.test.dialog.DialogHistoryScore
import kids.preschool.doantotnghiep.ui.home.test.starttest.StartTestAct
import kids.preschool.doantotnghiep.ui.home.test.viewmodel.TestViewModel

class TestAct : BaseActivity<ActivityTestBinding>(), TestAdapter.OnClickListener {
	private lateinit var list: List<String>
	private lateinit var testViewModel: TestViewModel
	private lateinit var listHistoryScore : List<ScoreCharacterEntity>
	override fun initView() {
		testViewModel = ViewModelProvider(this)[TestViewModel::class.java]
		testViewModel.getListScore(loadCharacter().id).observe(this,{
			listHistoryScore = it
		})
		list = listOf(
			resources.getString(R.string.test1),
			resources.getString(R.string.test2),
			resources.getString(R.string.test3),
			resources.getString(R.string.test4),
			resources.getString(R.string.test5),
			resources.getString(R.string.test6),
			resources.getString(R.string.test7),
			resources.getString(R.string.test8),
			resources.getString(R.string.test9),
			resources.getString(R.string.test10),
		)
		binding.rcvTest.layoutManager = LinearLayoutManager(this)
		val testAdapter = TestAdapter(this, list, this)
		binding.rcvTest.adapter = testAdapter
	}

	override fun initAction() {
		binding.btnBack.setOnClickListener {
			finish()
		}
		binding.btnSave.setOnClickListener {
			val dialogHistoryScore  = DialogHistoryScore(this)
			dialogHistoryScore.show(listHistoryScore)
		}
	}

	override fun getContentView(): Int {
		return R.layout.activity_test
	}

	override fun bindViewModel() {

	}

	override fun onClick(test: String) {
		val intent = Intent(this, StartTestAct::class.java)
		intent.putExtra("nameTest", test)
		startActivity(intent)
	}
	private fun loadCharacter(): CharacterEntity {
		val pref = this.getSharedPreferences("character", MODE_PRIVATE)
		val characterString = pref.getString("character", "")?:""
		return CharacterEntity.fromString(characterString)
	}
}