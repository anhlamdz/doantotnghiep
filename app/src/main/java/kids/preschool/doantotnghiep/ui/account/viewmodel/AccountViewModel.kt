package kids.preschool.doantotnghiep.ui.account.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kids.preschool.doantotnghiep.data.database.entities.CharacterEntity
import kids.preschool.doantotnghiep.data.database.entities.UserEntity
import kids.preschool.doantotnghiep.data.models.AppDatabase
import kids.preschool.doantotnghiep.data.repository.CharacterRepository
import kids.preschool.doantotnghiep.data.repository.UserRepository
import kotlinx.coroutines.launch

class AccountViewModel(application: Application) : AndroidViewModel(application) {
	val userRepository : UserRepository
	val characterRepository : CharacterRepository

	init {
		val userDao = AppDatabase.getDatabase(application).userDao()
		val characterDao = AppDatabase.getDatabase(application).characterDao()

		userRepository = UserRepository(userDao)
		characterRepository = CharacterRepository(characterDao)

	}

	fun insertAccount (userEntity: UserEntity) = viewModelScope.launch {
		userRepository.insert(userEntity)
	}

}