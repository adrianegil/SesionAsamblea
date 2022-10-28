package cu.desoft.sesionasamblea.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import cu.desoft.sesionasamblea.data.entity.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface Note_Dao {

    @get:Query("SELECT * FROM note")
    val getAllNote: LiveData<List<Note?>?>?

    @Query("SELECT * FROM note")
    fun getAllNote(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id =:id")
    fun getNoteById(id: Long): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllNote(noteList: List<Note?>?)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveNote(note: Note?)

    @Update
    fun updateNote(note: Note?)

    @Delete
    fun deleteNote(note: Note?)

    @Query("DELETE FROM note WHERE id = :id")
    fun deleteNoteByID(id: Long)

    @Query("DELETE FROM note")
    fun deleteAll()
}