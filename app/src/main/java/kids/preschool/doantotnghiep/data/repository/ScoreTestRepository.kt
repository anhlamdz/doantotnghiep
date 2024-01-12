package kids.preschool.doantotnghiep.data.repository

import androidx.lifecycle.LiveData
import kids.preschool.doantotnghiep.data.database.daos.ScoreTestDao
import kids.preschool.doantotnghiep.data.database.entities.ScoreCharacterEntity

class ScoreTestRepository(private val scoreTestDao: ScoreTestDao) {
	suspend fun saveScoreTest(scoreCharacterEntity: ScoreCharacterEntity){
		scoreTestDao.saveScore(scoreCharacterEntity)
	}
	suspend fun deleteScore(scoreCharacterEntity: ScoreCharacterEntity){
		scoreTestDao.delete(scoreCharacterEntity)
	}
	fun getListScore(idCharacterEntity : Long) : LiveData<List<ScoreCharacterEntity>> {
		return scoreTestDao.getListScore(idCharacterEntity)
	}
}