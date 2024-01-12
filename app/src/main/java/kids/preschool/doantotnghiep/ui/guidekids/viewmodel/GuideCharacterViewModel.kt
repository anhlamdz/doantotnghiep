package kids.preschool.doantotnghiep.ui.guidekids.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kids.preschool.doantotnghiep.data.database.entities.CharacterEntity
import kids.preschool.doantotnghiep.data.database.entities.UserAndCharacter
import kids.preschool.doantotnghiep.data.database.entities.UserEntity
import kids.preschool.doantotnghiep.data.models.AppDatabase
import kids.preschool.doantotnghiep.data.repository.CharacterRepository
import kids.preschool.doantotnghiep.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class GuideCharacterViewModel(application: Application) : AndroidViewModel(application) {
	val characterRepository : CharacterRepository
	val userRepository : UserRepository
	private val _name = MutableLiveData<String>()
	private val _age = MutableLiveData<Int>()
	private val _image = MutableLiveData<String>()

	val name : LiveData<String>
		get() = _name
	val age : LiveData<Int>
		get() = _age
	val image : LiveData<String>
		get() = _image

	init {
		val characterDao = AppDatabase.getDatabase(application).characterDao()
		val userDao = AppDatabase.getDatabase(application).userDao()

		characterRepository = CharacterRepository(characterDao)
		userRepository = UserRepository(userDao)
	}
	fun setName(nameCharacter : String){
		_name.value = nameCharacter
	}
	fun setAge(age : Int){
		_age.value = age
	}
	fun setImage(image : String) {
		_image.value = image
	}


	fun insertCharacter (characterEntity: CharacterEntity) = viewModelScope.launch {
		characterRepository.insert(characterEntity)
	}
	fun deleteCharacter (characterEntity: CharacterEntity) = viewModelScope.launch {
		characterRepository.delete(characterEntity)
	}
	fun updateCharacter(characterEntity: CharacterEntity) = viewModelScope.launch {
		characterRepository.update(characterEntity)
	}
	fun userAndCharacter(idUser : String) : LiveData<UserAndCharacter>{
		return characterRepository.userAndCharacter(idUser)
	}
	suspend fun getInfo(): UserEntity {
		return viewModelScope.async(Dispatchers.IO) {
			userRepository.getInfo()
		}.await()
	}
}