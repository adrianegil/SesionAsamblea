package cu.desoft.sesionasamblea.repository

import androidx.annotation.WorkerThread
import cu.desoft.sesionasamblea.data.dao.Note_Dao
import cu.desoft.sesionasamblea.data.entity.Note
import kotlinx.coroutines.flow.Flow


class Note_Repository(private val noteDao: Note_Dao) {

    val allNote: Flow<List<Note>> = noteDao.getAllNote()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(note: Note) {
        noteDao.saveNote(note)
    }

}