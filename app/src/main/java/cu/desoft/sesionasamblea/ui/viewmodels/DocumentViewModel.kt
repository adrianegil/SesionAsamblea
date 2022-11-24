package cu.desoft.sesionasamblea.ui.viewmodels

import android.os.Environment
import androidx.lifecycle.*
import cu.desoft.sesionasamblea.data.entity.Document
import cu.desoft.sesionasamblea.data.entity.Note
import cu.desoft.sesionasamblea.repository.DocumentRepository
import cu.desoft.sesionasamblea.ui.activities.Documents
import cu.desoft.sesionasamblea.utils.LoaderManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class DocumentViewModel(private val repository: DocumentRepository) : ViewModel() {

    val allDocument = repository.getAllDocument().asLiveData()

//    fun  getDocById(id : Int): Document{
//        return  repository.getDocumentsById(id)
//    }


    fun addNote(document: Document ) = viewModelScope.launch(Dispatchers.IO){repository.updateTicket(document)}

    fun addddddNote(document: Document ) = viewModelScope.launch(Dispatchers.IO){repository.insertDocument(document)}


    class DocumentViewModelFactory(private val repository: DocumentRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DocumentViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DocumentViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

