package cu.desoft.sesionasamblea.repository

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import cu.desoft.sesionasamblea.data.dao.NotesDao
import cu.desoft.sesionasamblea.data.entity.Note


class NoteRepository(private val noteDao: NotesDao) {


    val allNote: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun delete(note: Note){
        noteDao.delete(note)
    }

    suspend fun update(note: Note) {
        noteDao.update(note)
    }

}