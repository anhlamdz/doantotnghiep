package kids.preschool.doantotnghiep.data.repository

import androidx.lifecycle.LiveData
import kids.preschool.doantotnghiep.data.database.daos.CharacterDao
import kids.preschool.doantotnghiep.data.database.entities.CharacterEntity
import kids.preschool.doantotnghiep.data.database.entities.UserAndCharacter

class CharacterRepository (private val characterDao: CharacterDao) {
	suspend fun insert(characterEntity: CharacterEntity) : Long {
		return characterDao.insert(characterEntity)
	}
	suspend fun update(characterEntity: CharacterEntity){
		return characterDao.update(characterEntity)
	}
	suspend fun delete(characterEntity: CharacterEntity){
		return characterDao.delete(characterEntity)
	}
	fun userAndCharacter(idUser : Long) : LiveData<UserAndCharacter> {
		return characterDao.observerCharacter(idUser)
	}
}