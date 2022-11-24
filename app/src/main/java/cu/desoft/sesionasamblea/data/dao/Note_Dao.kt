package cu.desoft.sesionasamblea.data.dao

import androidx.annotation.Nullable
import androidx.lifecycle.LiveData
import androidx.room.*
import cu.desoft.sesionasamblea.data.entity.Note

@Dao
interface Note_Dao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insert(note: Note)

    @Update
     fun update(note: Note)

    @Delete
     fun delete(note: Note)

    @Query("Select * from notesTable order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>
}