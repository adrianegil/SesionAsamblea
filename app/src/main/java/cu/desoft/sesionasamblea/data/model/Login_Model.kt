package cu.desoft.sesionasamblea.data.model

import com.google.gson.annotations.SerializedName
import cu.desoft.sesionasamblea.data.entity.Deputy

class Login_Model {

    @SerializedName("token")
    var token: String = ""

    @SerializedName("diputado")
    var deputy: Deputy? = null
}