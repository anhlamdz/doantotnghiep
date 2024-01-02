package kids.preschool.doantotnghiep.ui.guidekids

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import io.strongapp.gymworkout.base.BaseActivity
import kids.preschool.doantotnghiep.R
import kids.preschool.doantotnghiep.data.database.entities.UserEntity
import kids.preschool.doantotnghiep.data.repository.CharacterRepository
import kids.preschool.doantotnghiep.databinding.ActivityCharaterBinding
import kids.preschool.doantotnghiep.ui.guidekids.adapter.CharacterAdapter
import kids.preschool.doantotnghiep.ui.guidekids.viewmodel.GuideCharacterViewModel
import kotlinx.coroutines.launch

class CharaterAct : BaseActivity<ActivityCharaterBinding>() {
	private lateinit var characterViewModel: GuideCharacterViewModel
	private lateinit var userEntity: UserEntity
	override fun initView() {
		characterViewModel = ViewModelProvider(this)[GuideCharacterViewModel::class.java]
		binding.rcvCharater.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
		lifecycleScope.launch {
			userEntity = characterViewModel.getInfo()
			characterViewModel.userAndCharacter(userEntity.id).observe(this@CharaterAct,{
				it?.let {
					val characterAdapter = CharacterAdapter(this@CharaterAct, it.character)
					binding.rcvCharater.adapter = characterAdapter
				}
			})

		}
	}

	override fun initAction() {

	}

	override fun getContentView(): Int {
		return R.layout.activity_charater
	}

	override fun bindViewModel() {

	}
}