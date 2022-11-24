package cu.desoft.sesionasamblea.data.dao

import androidx.room.*
import cu.desoft.sesionasamblea.data.entity.Document
import kotlinx.coroutines.flow.Flow


@Dao
interface DocumentDao {

    @Query("SELECT * FROM document")
    fun getAllDocument(): Flow<List<Document>>

    @Query("SELECT * FROM document WHERE documentID = (:documentID)")
    fun getDocumentsByID(documentID: Int): Document

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDocument(document: Document)

    @Query("DELETE FROM document WHERE documentID = (:documentID)")
    suspend fun deleteDocumentFromID(documentID: Int)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateDocument(document: Document)
}