package cu.desoft.sesionasamblea.utils

import com.google.gson.annotations.SerializedName
import cu.desoft.sesionasamblea.data.entity.Deputy

class Login {

    @SerializedName("username")
    var register: Long = 0

    @SerializedName("password")
    var folio: String? = null

    constructor(register: Long, folio: String?) {
        this.register = register
        this.folio = folio
    }
}