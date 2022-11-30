package cu.desoft.sesionasamblea.service;

import com.google.gson.JsonObject;

import cu.desoft.sesionasamblea.data.entity.Login;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("api-token-auth/")
    Call<JsonObject> login(@Body Login params);

}
