package cu.desoft.sesionasamblea.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cu.desoft.sesionasamblea.data.converter.DateConverter
import cu.desoft.sesionasamblea.data.dao.Deputy_Dao
import cu.desoft.sesionasamblea.data.dao.DocumentDao
import cu.desoft.sesionasamblea.data.dao.Note_Dao
import cu.desoft.sesionasamblea.data.entity.Deputy
import cu.desoft.sesionasamblea.data.entity.Document
import cu.desoft.sesionasamblea.data.entity.Note
import kotlinx.coroutines.CoroutineScope

@Database(
    entities = [Note::class, Document::class, Deputy::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(
    DateConverter::class
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun note_dao(): Note_Dao?
    abstract fun documentDao(): DocumentDao
    abstract fun deputyDao(): Deputy_Dao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}