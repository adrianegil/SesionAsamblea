package cu.desoft.sesionasamblea.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import cu.desoft.sesionasamblea.data.entity.Note

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("Select * from notesTable order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>
}