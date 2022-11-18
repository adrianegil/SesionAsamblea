package cu.desoft.sesionasamblea.managers;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import cu.desoft.sesionasamblea.service.AssistanceListService;
import cu.desoft.sesionasamblea.ssl.UnsafeOkHttpClient;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AssistanceListManager {

    public AssistanceListManager() {
    }

    public Call<JsonObject> getAssistance(String date,String token) {
        try {
            OkHttpClient clients = UnsafeOkHttpClient.getUnsafeOkHttpClient();
            Retrofit service = new Retrofit.Builder()
                    .client(clients)
                    .baseUrl("https://asamblea-ws2.hab.desoft.cu/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            AssistanceListService restService = service.create(AssistanceListService.class);
            return restService.getAssistance(date,token);
        } catch (Exception e) {
            Log.e("TAG", e.getMessage());
            return null;
        }
    }

    public Call<JsonObject> getOut(String date, String token) {
        try {
            OkHttpClient clients = UnsafeOkHttpClient.getUnsafeOkHttpClient();
            Retrofit service = new Retrofit.Builder()
                    .baseUrl("https://asamblea-ws2.hab.desoft.cu/api/")
                    .client(clients)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            AssistanceListService restService = service.create(AssistanceListService.class);
            return restService.getOut(date,token);
        } catch (Exception e) {
            Log.e("TAG", e.getMessage());
            return null;
        }
    }


}
