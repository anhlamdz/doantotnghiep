package kids.preschool.doantotnghiep.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "user_table")
data class UserEntity (
	@PrimaryKey
	@ColumnInfo("id")val id : String = "",
	@ColumnInfo("email") val email : String = "",
	@ColumnInfo("pass")val pass : String = "",
	@ColumnInfo("number_phone")val numberPhone  : String = "",
	@ColumnInfo("nameParent") val nameParent :String = ""
): Serializable
