package cu.desoft.sesionasamblea.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "deputy")
class Deputy {

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Long = 0

    @ColumnInfo(name = "url")
    @SerializedName("url")
    var url: String = ""

    @ColumnInfo(name = "register")
    @SerializedName("registro")
    var register: Long = 0

    @ColumnInfo(name = "name")
    @SerializedName("nombre")
    var name: String = ""

    @ColumnInfo(name = "ci")
    @SerializedName("ci")
    var ci: String = ""

    @ColumnInfo(name = "province")
    @SerializedName("provincia")
    var province: String = ""

    /*@ColumnInfo(name = "photo")
    @SerializedName("foto")
    var photo: String? = null*/

    @ColumnInfo(name = "organization")
    @SerializedName("organismo")
    var organization: String = ""

    @ColumnInfo(name = "folio")
    @SerializedName("folio")
    var folio: String = ""

}