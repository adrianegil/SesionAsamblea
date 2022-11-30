package cu.desoft.sesionasamblea.api

import cu.desoft.sesionasamblea.data.model.Login_Model
import cu.desoft.sesionasamblea.utils.Login
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Login_API {

    @POST("/api-token-auth/")
    fun LogIn(@Body loginCredential: Login): Call<Login_Model>

}