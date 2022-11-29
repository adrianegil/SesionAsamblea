package cu.desoft.sesionasamblea.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import cu.desoft.sesionasamblea.data.entity.Deputy
import cu.desoft.sesionasamblea.data.entity.Document
import cu.desoft.sesionasamblea.repository.Deputy_Repository
import cu.desoft.sesionasamblea.repository.DocumentRepository
import kotlinx.coroutines.launch

class DeputyViewModel(private val repository: Deputy_Repository) : ViewModel() {

     fun getDeputyByRegister(deputyRegister: Long): LiveData<Deputy> {
        return repository.getDeputyByRegister(deputyRegister)
    }

    fun insertDeputy(deputy: Deputy) {
        viewModelScope.launch {
            repository.insertDeputy(deputy)
        }
    }

    fun updateDeputy(deputy: Deputy) {
        viewModelScope.launch {
            repository.updateDeputy(deputy)
        }
    }

    fun deleteAllDeputies() {
        viewModelScope.launch {
            repository.deleteAllDeputies()
        }
    }

    fun deleteDeputyByRegister(register: Long) {
        viewModelScope.launch {
            repository.getDeputyByRegister(register)
        }
    }

    class DeputyViewModelFactory(private val repository: Deputy_Repository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DeputyViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DeputyViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}