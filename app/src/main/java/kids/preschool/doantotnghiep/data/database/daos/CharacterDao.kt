package kids.preschool.doantotnghiep.data.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kids.preschool.doantotnghiep.data.database.entities.CharacterEntity
import kids.preschool.doantotnghiep.data.database.entities.UserAndCharacter

@Dao
interface CharacterDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(characterEntity: CharacterEntity) : Long

	@Update
	suspend fun update(characterEntity: CharacterEntity)

	@Delete
	suspend fun delete(characterEntity: CharacterEntity)

	@Query("Select * From user_table Where id = :idUser")
	@Transaction
	fun observerCharacter(idUser : String) : LiveData<UserAndCharacter>

}