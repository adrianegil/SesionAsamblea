package cu.desoft.sesionasamblea

import android.app.Application
import cu.desoft.sesionasamblea.data.AppDatabase
import cu.desoft.sesionasamblea.repository.Deputy_Repository
import cu.desoft.sesionasamblea.repository.DocumentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class SesionAsambleaApp : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { DocumentRepository(database.documentDao()) }
    val deputyRepository by lazy { Deputy_Repository(database.deputyDao()) }

}