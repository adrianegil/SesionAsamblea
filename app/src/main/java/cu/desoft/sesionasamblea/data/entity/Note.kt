package cu.desoft.sesionasamblea.data.entity

import androidx.annotation.IntegerRes
import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "notesTable")
class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "title") val noteTitle: String,
    @ColumnInfo(name = "description") val noteDescription: String,
    @ColumnInfo(name = "timeStamp") val timeStamp: String
)
