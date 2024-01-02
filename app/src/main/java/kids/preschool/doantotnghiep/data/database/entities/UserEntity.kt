package kids.preschool.doantotnghiep.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class UserEntity (
	@PrimaryKey
	@ColumnInfo("id")val id : Long = 0L,
	@ColumnInfo("username")val username : String = "",
	@ColumnInfo("pass")val pass : String = ""
)
