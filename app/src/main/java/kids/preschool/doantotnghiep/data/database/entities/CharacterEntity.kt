package kids.preschool.doantotnghiep.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "character_table",
	foreignKeys = [
		ForeignKey(
			entity = UserEntity::class,
			parentColumns = ["id"],
			childColumns = ["idUser"],
			onDelete = ForeignKey.CASCADE,
			onUpdate = ForeignKey.CASCADE
		)
	],
	indices = [
		Index("idUser")
	]
)
data class CharacterEntity (
	@PrimaryKey
	@ColumnInfo("id")val id : Long ,
	@ColumnInfo("name")val name : String ,
	@ColumnInfo("age")val age : Int ,
	@ColumnInfo("image")val image : String,
	@ColumnInfo("idUser")val idUser : Long
)