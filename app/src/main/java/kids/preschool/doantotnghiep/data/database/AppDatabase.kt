package kids.preschool.doantotnghiep.data.models


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kids.preschool.doantotnghiep.data.database.daos.CharacterDao
import kids.preschool.doantotnghiep.data.database.daos.ClearDataDao
import kids.preschool.doantotnghiep.data.database.daos.ScoreTestDao
import kids.preschool.doantotnghiep.data.database.daos.UserDao
import kids.preschool.doantotnghiep.data.database.entities.CharacterEntity
import kids.preschool.doantotnghiep.data.database.entities.ScoreCharacterEntity
import kids.preschool.doantotnghiep.data.database.entities.UserEntity


@Database(
	entities = [UserEntity::class,CharacterEntity::class,ScoreCharacterEntity::class],
	version = 1
)
abstract class AppDatabase : RoomDatabase() {
	abstract fun userDao() : UserDao
	abstract fun characterDao() : CharacterDao
	abstract fun scoreTestDao() : ScoreTestDao
	abstract fun clearDataDao() : ClearDataDao
	companion object {

		@Volatile
		private var instance: AppDatabase? = null
		fun getDatabase(context: Context) = instance ?: synchronized(this) {
			instance ?: buildDatabase(context).also { instance = it }
		}

		private fun buildDatabase(context: Context) = Room.databaseBuilder(
			context.applicationContext, AppDatabase::class.java, "recent_database"
		).build()
	}
}