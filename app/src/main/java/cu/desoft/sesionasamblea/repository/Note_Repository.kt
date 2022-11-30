package cu.desoft.sesionasamblea.repository

import androidx.lifecycle.LiveData
import cu.desoft.sesionasamblea.data.dao.Note_Dao
import cu.desoft.sesionasamblea.data.entity.Note

class Note_Repository(private val noteDao: Note_Dao) {


    val allNote: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }

    suspend fun update(note: Note) {
        noteDao.update(note)
    }

}