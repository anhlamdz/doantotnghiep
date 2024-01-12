package kids.preschool.doantotnghiep.data.repository

import kids.preschool.doantotnghiep.data.database.daos.ClearDataDao

class ClearRepository(private val clearDataDao: ClearDataDao) {
	suspend fun clearAllData(){
		clearDataDao.clearTableScore()
		clearDataDao.clearTableUser()
		clearDataDao.clearTableCharacter()
	}
}