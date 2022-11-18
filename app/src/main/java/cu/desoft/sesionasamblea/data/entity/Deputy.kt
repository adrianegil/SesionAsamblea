package cu.desoft.sesionasamblea.data.entity

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

class Deputy {

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Long = 0

    @ColumnInfo(name = "url")
    @SerializedName("url")
    var url: String = ""

    @ColumnInfo(name = "register")
    @SerializedName("registro")
    var register: Long? = null

    @ColumnInfo(name = "name")
    @SerializedName("nombre")
    var name: String? = null

    @ColumnInfo(name = "ci")
    @SerializedName("ci")
    var ci: String? = null

    @ColumnInfo(name = "province")
    @SerializedName("provincia")
    var province: String? = null

    /*@ColumnInfo(name = "photo")
    @SerializedName("foto")
    var photo: String? = null*/

    @ColumnInfo(name = "organization")
    @SerializedName("organismo")
    var organization: String? = null

    @ColumnInfo(name = "folio")
    @SerializedName("folio")
    var folio: String? = null
}