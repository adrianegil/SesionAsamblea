package cu.desoft.sesionasamblea.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import cu.desoft.sesionasamblea.data.entity.Deputy

@Dao
interface Deputy_Dao {

    @Query("SELECT * FROM deputy WHERE register = (:id)")
    fun getDeputyByRegister(id: Long): LiveData<Deputy>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeputy(deputy: Deputy)

    @Update
    suspend fun updateDeputy(deputy: Deputy)

    @Query("DELETE FROM deputy WHERE register = (:register)")
    suspend fun deleteDeputyByRegister(register: Long)

    @Query("DELETE FROM deputy")
    suspend fun deleteAllDeputies()
}