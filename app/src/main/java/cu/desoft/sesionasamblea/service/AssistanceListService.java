package cu.desoft.sesionasamblea.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AssistanceListService {

    @GET("diputados")
    Call<JsonObject> getAssistance(@Query("presentes") String date,@Header("Authorization") String authHeader);

    @GET("diputados")
    Call<JsonObject> getOut(@Query("ausentes") String date, @Header("Authorization") String authHeader);



}
