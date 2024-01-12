package kids.preschool.doantotnghiep.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "score_test_table")
data class ScoreCharacterEntity (
	@PrimaryKey
	val id : Long = 0L,
	val name : String = "",
	val score : Int = 0,
	val idCharacter : Long =0L
)