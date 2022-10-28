package cu.desoft.sesionasamblea.ui.notepad

import androidx.lifecycle.*
import cu.desoft.sesionasamblea.data.entity.Note
import cu.desoft.sesionasamblea.repository.Note_Repository
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: Note_Repository) : ViewModel() {

    val allWords: LiveData<List<Note>> = repository.allNote.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(note: Note) = viewModelScope.launch {
        repository.insert(note)
    }

    class NoteViewModelFactory(private val repository: Note_Repository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return NoteViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}