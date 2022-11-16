package cu.desoft.sesionasamblea.ui.notepad

import android.app.Application
import androidx.lifecycle.*
import cu.desoft.sesionasamblea.data.entity.Note
import cu.desoft.sesionasamblea.data.entity.NoteDatabase
import cu.desoft.sesionasamblea.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModal(application: Application) : AndroidViewModel(application) {

    val allNotes: LiveData<List<Note>>
    val repository: NoteRepository


    init {
        val dao = NoteDatabase.getDatabase(application).getNotesDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNote
    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO){repository.delete(note)}
    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO){repository.update(note)}
    fun addNote(note: Note) = viewModelScope.launch(Dispatchers.IO){repository.insert(note)}


}