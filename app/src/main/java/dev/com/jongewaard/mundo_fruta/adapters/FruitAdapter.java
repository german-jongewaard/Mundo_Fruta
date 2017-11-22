package dev.com.jongewaard.mundo_fruta.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import dev.com.jongewaard.mundo_fruta.R;
import dev.com.jongewaard.mundo_fruta.models.Fruit;

/**
 * Created by german on 20-11-17.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<Fruit> fruits;
    private int layout;
    private OnItemClickListener itemClickListener;

    private Context context;


    public FruitAdapter(List<Fruit> fruits, int layout, OnItemClickListener listener) {
        this.fruits = fruits;
        this.layout = layout;
        this.itemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflamos el layout y se lo pasamos al constructor del ViewHolder, donde manejaremos
        // toda la lógica como extraer los datos, referencias...
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);

        //Rellenando el Contexto para la libreria Picasso
        context = parent.getContext();

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Llamamos al método Bind del ViewHolder pasándole objeto y listener
        holder.bind(fruits.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Elementos UI a rellenar (declarados)
        public TextView textViewName;
        public ImageView imageViewPoster;

        public ViewHolder(View itemView) {
            // Recibe la View completa. La pasa al constructor padre y enlazamos referencias UI
            // con nuestras propiedades ViewHolder declaradas justo arriba.
            super(itemView);
            //(instanciados)
            textViewName = (TextView) itemView.findViewById(R.id.textViewTitle);
            imageViewPoster = (ImageView) itemView.findViewById(R.id.imageViewPoster);

        }

        public void bind(final Fruit movie, final OnItemClickListener listener) {
            // Procesamos los datos a renderizar (aquí lo procesamos, los relleno con nuestros valores!)
            textViewName.setText(movie.getName());

            //Libreria Picasso                     con fit() extiendo la imagen en to do el cuadrado
            //                 con into() es donde vamos a cargarla, seria, dentro del "imageViewPoster"
            Picasso.with(context).load(movie.getPoster()).fit().into(imageViewPoster);
            //imageViewPoster.setImageResource(movie.getPoster());

            // Definimos que por cada elemento de nuestro recycler view, tenemos un click listener
            // que se comporta de la siguiente manera...
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // ... pasamos nuestro objeto modelo (este caso String) y posición
                    listener.onItemClick(movie, getAdapterPosition());

                }
            });
        }
    }

    // Declaramos nuestra interfaz con el/los método/s a implementar
    public interface OnItemClickListener {
        void onItemClick(Fruit movies, int position);
    }


}
