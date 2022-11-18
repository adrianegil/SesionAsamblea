package cu.desoft.sesionasamblea.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "document")
class Document (
    @PrimaryKey(autoGenerate = true)
    val documentID: Int,
    @ColumnInfo(name = "name") val nameDocuments: String?,
)