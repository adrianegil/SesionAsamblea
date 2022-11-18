package cu.desoft.sesionasamblea.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import cu.desoft.sesionasamblea.data.entity.Document
import cu.desoft.sesionasamblea.repository.DocumentRepository
import kotlinx.coroutines.launch

class DocumentViewModel(private val repository: DocumentRepository) : ViewModel() {

    val allDocument = repository.getAllDocument().asLiveData()

    fun getDocumenttById(documentID: Int): Document {
        return repository.getDocumentsById(documentID)
    }

    fun insertDocument(document: Document) {
        viewModelScope.launch {
            repository.insertDocument(document)
        }
    }

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

