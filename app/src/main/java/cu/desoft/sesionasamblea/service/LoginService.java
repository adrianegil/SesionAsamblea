package cu.desoft.sesionasamblea.service;

import com.google.gson.JsonObject;

import cu.desoft.sesionasamblea.data.entity.Login;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginService {

    @POST("api-token-auth/")
    Call<JsonObject> login(@Body Login params);



}
