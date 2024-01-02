package kids.preschool.doantotnghiep.data.database.entities

import androidx.room.Embedded
import androidx.room.Relation


data class UserAndCharacter(
	@Embedded
	val userEntity: UserEntity,
	@Relation(
		parentColumn = "id",
		entityColumn = "idUser",
		entity = CharacterEntity::class
	)
	val character : List<CharacterEntity>
)
