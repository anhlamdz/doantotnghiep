package kids.preschool.doantotnghiep.data.database.daos

import androidx.room.Dao
import androidx.room.Query
@Dao
interface ClearDataDao {
	@Query("Delete From user_table")
	suspend fun clearTableUser()

	@Query("Delete From character_table")
	suspend fun clearTableCharacter()

	@Query("Delete From score_test_table")
	suspend fun clearTableScore()
}