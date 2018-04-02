package myapps.jherrera.sonidosapp;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.File;
import java.util.ArrayList;

import myapps.jherrera.sonidosapp.interfaces.ApiInterface;
import myapps.jherrera.sonidosapp.interfaces.CallbackResponse;
import myapps.jherrera.sonidosapp.myutil.FileByPath;
import myapps.jherrera.sonidosapp.objects.Sonido;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiResponse {

    private CallbackResponse callbackResponse;

    public ApiResponse(CallbackResponse callbackResponse ){
        this.callbackResponse = callbackResponse;
    }

    public void get(int id){

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<JsonObject> call = apiInterface.get(id);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call,
                                   Response<JsonObject> response) {

                ArrayList<Sonido> list = new ArrayList<>();

                if (response.body().getAsJsonArray("data").size() > 0) {
                    list.add(createSonido((JsonObject) response.body().getAsJsonArray("data").get(0)));
                    callbackResponse.response(list);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("xxx", ":( "+t.getMessage());
            }
        });
    }

    public void getAll(){

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<JsonObject> call = apiInterface.getAll();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call,
                                   Response<JsonObject> response) {

                if (response.body().getAsJsonArray("data").size() > 0) {
                    callbackResponse.response(createSonidosList(response.body().getAsJsonArray("data")));
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("xxx", ":( "+t.getMessage());
            }
        });
    }

    public void add(Context context){

        File file = FileByPath.getFile(context);

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("image/*"),file);

        MultipartBody.Part body = MultipartBody.Part.createFormData("data",file.getName(),requestFile);

        Call<JsonObject> call = apiInterface.add( body);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call,
                                   Response<JsonObject> response) {
                Log.v("xxx", "success");
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("xxx", ":( "+t.getMessage());
            }
        });
    }

    private Sonido createSonido(JsonObject jsonObject){

        String nombre = String.valueOf(jsonObject.get("titulo"));
        String iconoPath = String.valueOf(jsonObject.get("icono"));
        String audioPath = String.valueOf(jsonObject.get("sonido"));

        Sonido sonido = new Sonido(nombre, iconoPath, audioPath);
        return sonido;
    }

    private ArrayList<Sonido> createSonidosList(JsonArray data){

        ArrayList<Sonido> list = new ArrayList<>();

        for (int i = 0; data.size() > i; i++){
            list.add(createSonido((JsonObject)data.get(i)));
        }
        return list;
    }
}
