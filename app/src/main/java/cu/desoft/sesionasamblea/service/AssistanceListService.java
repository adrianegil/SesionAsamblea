package cu.desoft.sesionasamblea.service;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AssistanceListService {

    @GET("diputados")
    Call<JsonArray> getAssistance(@Query("presentes") String date,@Header("Authorization") String authHeader);

    @GET("diputados")
    Call<JsonArray> getOut(@Query("ausentes") String date,@Header("Authorization") String authHeader);



}
