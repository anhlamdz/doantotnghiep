package kids.preschool.doantotnghiep.data.repository

import kids.preschool.doantotnghiep.data.database.daos.UserDao
import kids.preschool.doantotnghiep.data.database.entities.UserEntity

class UserRepository(private val userDao: UserDao) {
	suspend fun insert(userEntity: UserEntity) : Long {
		return userDao.insert(userEntity)
	}
	fun getInfo(): UserEntity {
		return userDao.getUser()
	}
	suspend fun update(userEntity: UserEntity) {
		userDao.update(userEntity)
	}
}