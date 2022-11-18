package cu.desoft.sesionasamblea.repository

import cu.desoft.sesionasamblea.data.dao.DocumentDao
import cu.desoft.sesionasamblea.data.entity.Document

import kotlinx.coroutines.flow.Flow

class DocumentRepository(private  val documentDao: DocumentDao) {
    fun getAllDocument(): Flow<List<Document>> {
        return documentDao.getAllDocument()
    }

    fun getDocumentsById(documentID: Int): Document {
        return documentDao.getDocumentsByID(documentID)
    }

    suspend fun insertDocument(document: Document){
        documentDao.insertDocument(document)
    }

    suspend fun updateTicket(document: Document){
        documentDao.updateTicket(document)
    }

    suspend fun deleTicket(documentID: Int){
        documentDao.deleteDocumentFromID(documentID)
    }
}