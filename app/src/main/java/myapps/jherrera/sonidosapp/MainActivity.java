package myapps.jherrera.sonidosapp;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import myapps.jherrera.sonidosapp.adapters.SonidoAdapter;
import myapps.jherrera.sonidosapp.interfaces.CallbackClick;
import myapps.jherrera.sonidosapp.interfaces.CallbackResponse;
import myapps.jherrera.sonidosapp.objects.Sonido;

public class MainActivity extends AppCompatActivity implements CallbackClick, CallbackResponse{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiResponse apiResponse = new ApiResponse(this);
        apiResponse.getAll();

    }
    private void loadList(List<Sonido> sonidos){

        List items = sonidos;

         RecyclerView recycler;
         RecyclerView.Adapter adapter;
         RecyclerView.LayoutManager lManager;

// Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);

// Usar un administrador para LinearLayout
        lManager = new GridLayoutManager(this,3);
        recycler.setLayoutManager(lManager);

// Crear un nuevo adaptador
        adapter = new SonidoAdapter(items,this);
        recycler.setAdapter(adapter);
    }

    @Override
    public void response(ArrayList<Sonido> sonido) {
        Log.d("xxx", sonido.get(0).getIconoPath());
        loadList(sonido);
    }

    @Override
    public void onClickPlay(String message) {

        try {
            MediaPlayer player = new MediaPlayer();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(message);
            player.prepare();
            player.start();

        } catch (Exception e) {
            // TODO: handle exception
        }


    }

}
