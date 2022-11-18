package cu.desoft.sesionasamblea.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//SINGLENTON DEL OBJETO RETROFIT
class RetrofitClient {

    companion object {
        const val BASE_URL = "https://asamblea-ws2.hab.desoft.cu"
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var retrofit: Retrofit? = null

        fun getRetrofit():Retrofit{
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .callTimeout(3, TimeUnit.SECONDS)
                .build()
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
            }
            return retrofit!!
            }
        }
    }

