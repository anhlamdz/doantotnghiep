package kids.preschool.doantotnghiep.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kids.preschool.doantotnghiep.data.database.entities.UserEntity

@Dao
interface UserDao {
	@Insert
	suspend fun insert(userEntity: UserEntity): Long

	@Update
	suspend fun update(userEntity: UserEntity)

	@Query("select * from user_table")
	fun getUser() : UserEntity
}
