package kids.preschool.doantotnghiep.ui.guidekids

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import io.strongapp.gymworkout.base.BaseActivity
import kids.preschool.doantotnghiep.R
import kids.preschool.doantotnghiep.data.database.entities.CharacterEntity
import kids.preschool.doantotnghiep.data.database.entities.UserEntity
import kids.preschool.doantotnghiep.databinding.ActivityCharaterBinding
import kids.preschool.doantotnghiep.ui.guidekids.adapter.CharacterAdapter
import kids.preschool.doantotnghiep.ui.guidekids.viewmodel.GuideCharacterViewModel
import kids.preschool.doantotnghiep.ui.home.HomeAct
import kotlinx.coroutines.launch

class CharacterAct : BaseActivity<ActivityCharaterBinding>(), CharacterAdapter.OnClickListener {
	private lateinit var characterViewModel: GuideCharacterViewModel
	private lateinit var userEntity: UserEntity
	private var mode : String = ""
	override fun initView() {
		characterViewModel = ViewModelProvider(this)[GuideCharacterViewModel::class.java]
		binding.rcvCharater.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
		mode = intent.getStringExtra("mode")?:"default"
		when(mode){
			"edit" -> {
				binding.btnBack.visibility = View.VISIBLE
				lifecycleScope.launch {
					userEntity = characterViewModel.getInfo()
					characterViewModel.userAndCharacter(userEntity.id).observe(this@CharacterAct,{
						it?.let {
							val characterAdapter = CharacterAdapter(this@CharacterAct, it.character,mode,this@CharacterAct)
							binding.rcvCharater.adapter = characterAdapter
						}
					})
				}
			}
			"default"-> {
				binding.btnBack.visibility = View.GONE
				lifecycleScope.launch {
					userEntity = characterViewModel.getInfo()
					characterViewModel.userAndCharacter(userEntity.id).observe(this@CharacterAct,{
						it?.let {
							val characterAdapter = CharacterAdapter(this@CharacterAct, it.character,mode,this@CharacterAct)
							binding.rcvCharater.adapter = characterAdapter
						}
					})
				}
			}
		}
	}

	override fun initAction() {
		binding.btnBack.setOnClickListener {
			finish()
		}
		binding.btnCreate.setOnClickListener {
			val intent = Intent(this,GuideNameAct::class.java)
			intent.putExtra("mode","create")
			startActivity(intent)
		}
	}

	override fun getContentView(): Int {
		return R.layout.activity_charater
	}

	override fun bindViewModel() {

	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_EDIT_CUSTOM) {
			lifecycleScope.launch {
				userEntity = characterViewModel.getInfo()
				characterViewModel.userAndCharacter(userEntity.id).observe(this@CharacterAct,{
					if (it.character == null){
						val characterAdapter = CharacterAdapter(this@CharacterAct, it.character,mode,this@CharacterAct)
						binding.rcvCharater.adapter = characterAdapter
					}else {
						val intent = Intent(this@CharacterAct , GuideNameAct::class.java)
						startActivity(intent)
					}
				})
			}
		}
	}

	override fun onClick(character: CharacterEntity) {
		when(mode){
			"default" -> {
				val intent = Intent(this, HomeAct::class.java)
				startActivity(intent)
			}
			"edit" -> {
				val intent = Intent(this, EditCharacterAct::class.java)
				intent.putExtra("character",character)
				startActivityForResult(intent, REQUEST_EDIT_CUSTOM)
			}
		}
	}
	companion object {
		const val REQUEST_EDIT_CUSTOM = 1
	}
}