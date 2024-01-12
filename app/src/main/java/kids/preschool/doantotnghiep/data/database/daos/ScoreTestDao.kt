package kids.preschool.doantotnghiep.data.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kids.preschool.doantotnghiep.data.database.entities.ScoreCharacterEntity
import kids.preschool.doantotnghiep.data.database.entities.UserAndCharacter
@Dao
interface ScoreTestDao {
	@Insert
	suspend fun saveScore(scoreCharacterEntity: ScoreCharacterEntity)

	@Delete
	suspend fun delete(scoreCharacterEntity: ScoreCharacterEntity)

	@Query("Select * From score_test_table where idCharacter = :idCharacter")
	fun getListScore(idCharacter: Long) : LiveData<List<ScoreCharacterEntity>>
}