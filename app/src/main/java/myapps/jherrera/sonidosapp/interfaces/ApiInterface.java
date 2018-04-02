package myapps.jherrera.sonidosapp.interfaces;

import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("sounds/")
    Call<JsonObject> getAll();

    @GET("sounds/{id}")
    Call<JsonObject> get(@Path("id") int id);

    @Multipart
    @POST("sounds/")
    Call<JsonObject> add(@Part MultipartBody.Part body);
}