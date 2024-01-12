package kids.preschool.doantotnghiep.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable


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
	@ColumnInfo("id")val id : Long = 0L ,
	@ColumnInfo("name")val name : String = "",
	@ColumnInfo("age")val age : Int = 0,
	@ColumnInfo("image")val image : String ="",
	@ColumnInfo("idUser")val idUser : String =""
) : Serializable {
	companion object{
		fun fromString(characterString: String): CharacterEntity {
			val parts = characterString.split("|")
			return CharacterEntity(
				id = parts[0].toLong(),
				name = parts[1],
				age = parts[2].toInt(),
				image = parts[3],
				idUser = parts[4]
			)
		}
		fun toString(character: CharacterEntity) : String {
			return "${character.id}|${character.name}|${character.age}|${character.image}|${character.idUser}"
		}
	}
}