package kids.preschool.doantotnghiep.ui.account.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kids.preschool.doantotnghiep.data.database.entities.CharacterEntity
import kids.preschool.doantotnghiep.data.database.entities.UserEntity
import kids.preschool.doantotnghiep.data.models.AppDatabase
import kids.preschool.doantotnghiep.data.repository.CharacterRepository
import kids.preschool.doantotnghiep.data.repository.ClearRepository
import kids.preschool.doantotnghiep.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AccountViewModel(application: Application) : AndroidViewModel(application) {
	val userRepository : UserRepository
	val characterRepository : CharacterRepository
	val clearRepository : ClearRepository
	init {
		val userDao = AppDatabase.getDatabase(application).userDao()
		val characterDao = AppDatabase.getDatabase(application).characterDao()
		val clearDataDao = AppDatabase.getDatabase(application).clearDataDao()

		userRepository = UserRepository(userDao)
		characterRepository = CharacterRepository(characterDao)
		clearRepository = ClearRepository(clearDataDao)
	}

	fun insertAccount (userEntity: UserEntity) = viewModelScope.launch {
		userRepository.insert(userEntity)
	}
	fun insertCharacter (characterEntity: CharacterEntity) = viewModelScope.launch {
		characterRepository.insert(characterEntity)
	}
	suspend fun getInfo(): UserEntity {
		return viewModelScope.async(Dispatchers.IO) {
			userRepository.getInfo()
		}.await()
	}
	fun updateAccount (userEntity: UserEntity) = viewModelScope.launch {
		userRepository.update(userEntity)
	}
	fun clearData(){
		viewModelScope.launch {
			clearRepository.clearAllData()
		}
	}

}