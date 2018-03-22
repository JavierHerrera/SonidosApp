package myapps.jherrera.sonidosapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import myapps.jherrera.sonidosapp.R;
import myapps.jherrera.sonidosapp.Sonido;
import myapps.jherrera.sonidosapp.interfaces.CallbackClick;

public class SonidoAdapter extends RecyclerView.Adapter<SonidoAdapter.SonidoViewHolder> {

    private static List<Sonido> items;
    private static CallbackClick mListener;

    public static class SonidoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.nombre) TextView nombre;
        @BindView(R.id.imagen) ImageView imagen;

        public SonidoViewHolder(View v) {
            super(v);
            ButterKnife.bind(this,v);

            v.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View view) {

                    mListener.onClick(items.get(getAdapterPosition()).getNombre());
                }
            });
        }
    }

    public SonidoAdapter(List<Sonido> items, CallbackClick callbackClick) {
        this.items = items;
        mListener = callbackClick;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public SonidoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.sonido_card, viewGroup, false);
        return new SonidoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SonidoViewHolder viewHolder, int i) {

        //Configurar la vista de la imagen aaqui abajo
        //viewHolder.imagen.setImageResource(items.get(i).getIconoPath());

        viewHolder.nombre.setText(items.get(i).getNombre());

    }
}