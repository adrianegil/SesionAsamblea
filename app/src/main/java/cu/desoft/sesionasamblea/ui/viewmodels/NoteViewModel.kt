package cu.desoft.sesionasamblea.ui.viewmodels

import android.app.Application
import androidx.lifecycle.*
import cu.desoft.sesionasamblea.data.AppDatabase
import cu.desoft.sesionasamblea.data.entity.Note
import cu.desoft.sesionasamblea.repository.Note_Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModal(application: Application) : AndroidViewModel(application) {

    val allNotes: LiveData<List<Note>>
    val repository: Note_Repository

    init {
        val dao = AppDatabase.getDatabase(application, viewModelScope).note_dao()
        repository = dao?.let { Note_Repository(it) }!!
        allNotes = repository.allNote
    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) { repository.delete(note) }
    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) { repository.update(note) }
    fun addNote(note: Note) = viewModelScope.launch(Dispatchers.IO) { repository.insert(note) }

}