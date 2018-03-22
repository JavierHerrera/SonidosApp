package myapps.jherrera.sonidosapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import myapps.jherrera.sonidosapp.adapters.SonidoAdapter;
import myapps.jherrera.sonidosapp.interfaces.CallbackClick;

public class MainActivity extends AppCompatActivity implements CallbackClick{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadList();
    }


    private void loadList(){

         RecyclerView recycler;
         RecyclerView.Adapter adapter;
         RecyclerView.LayoutManager lManager;

        List items = new ArrayList();

        String tuitepath = "https://sonidos-test.herokuapp.com/static/images/1519808153417tuite.png";
        items.add(new Sonido("primero",tuitepath , tuitepath));
        items.add(new Sonido("segundo",tuitepath , tuitepath));
        items.add(new Sonido("tercero",tuitepath , tuitepath));


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
    public void onClick(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
