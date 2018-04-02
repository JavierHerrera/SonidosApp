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
import myapps.jherrera.sonidosapp.interfaces.CallbackClick;
import myapps.jherrera.sonidosapp.myutil.DownloadImageTask;
import myapps.jherrera.sonidosapp.objects.Sonido;

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

                    mListener.onClickPlay(items.get(getAdapterPosition()).getAudioPath());
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

       setImageViewFromUrl(viewHolder.imagen, items.get(i).getIconoPath());
        viewHolder.nombre.setText(items.get(i).getNombre());

    }

    private void setImageViewFromUrl(ImageView imageView, String url){
        new DownloadImageTask(imageView).execute(url);
    }
}