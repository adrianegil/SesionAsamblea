package cu.desoft.sesionasamblea.managers;

import android.util.Log;

import com.google.gson.JsonArray;

import cu.desoft.sesionasamblea.service.AssistanceListService;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AssistanceListManager {

    public AssistanceListManager() {
    }

    public Call<JsonArray> getAssistance(String date,String token) {
        try {
            Retrofit service = new Retrofit.Builder()
                    .baseUrl("http://asamblea-ws2.hab.desoft.cu/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            AssistanceListService restService = service.create(AssistanceListService.class);
            return restService.getAssistance(date,token);
        } catch (Exception e) {
            Log.e("TAG", e.getMessage());
            return null;
        }
    }

    public Call<JsonArray> getOut(String date,String token) {
        try {
            Retrofit service = new Retrofit.Builder()
                    .baseUrl("http://asamblea-ws2.hab.desoft.cu/api/")
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
