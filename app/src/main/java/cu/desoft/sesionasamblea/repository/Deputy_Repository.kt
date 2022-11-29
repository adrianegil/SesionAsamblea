package cu.desoft.sesionasamblea.repository

import androidx.lifecycle.LiveData
import cu.desoft.sesionasamblea.data.dao.Deputy_Dao
import cu.desoft.sesionasamblea.data.entity.Deputy

class Deputy_Repository(private val deputyDao: Deputy_Dao) {

    fun getDeputyByRegister(deputyRegister: Long): LiveData<Deputy> {
        return deputyDao.getDeputyByRegister(deputyRegister)
    }

    suspend fun insertDeputy(deputy: Deputy) {
        deputyDao.insertDeputy(deputy)
    }

    suspend fun deleteAllDeputies() {
        deputyDao.deleteAllDeputies()
    }

    suspend fun updateDeputy(deputy: Deputy) {
        deputyDao.updateDeputy(deputy)
    }

    suspend fun deleteDeputyByRegister(deputyRegister: Long) {
        deputyDao.deleteDeputyByRegister(deputyRegister)
    }
}