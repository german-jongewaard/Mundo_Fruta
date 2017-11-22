package dev.com.jongewaard.mundo_fruta.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import dev.com.jongewaard.mundo_fruta.R;
import dev.com.jongewaard.mundo_fruta.adapters.FruitAdapter;
import dev.com.jongewaard.mundo_fruta.models.Fruit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private FruitAdapter adapter;

    private List<Fruit> fruits;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fruits = this.getAllFruits();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        /*
        *   Observa como pasamos el activity , con this. Podríamos declarar
        *   Activity o Context en el constructory funcionaria pasando el mismo valor, this.
        *
        * */

        adapter = new FruitAdapter(fruits, R.layout.recycler_view_item, new FruitAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Fruit fruits, int position) {

               deleteFruit(position);
            }
        });

        // Lo usamos en caso de que sepamos que el layout no va a cambiar de tamaño, mejorando la performance
        mRecyclerView.setHasFixedSize(true);
        // Añade un efecto por defecto, si le pasamos null lo desactivamos por completo
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // Enlazamos el layout manager y adaptor directamente al recycler view
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_fruit:
                //this.addFruit(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private List<Fruit> getAllFruits(){
        return new ArrayList<Fruit>(){{
            add(new Fruit("Strawberry", "Strawberry description", R.drawable.strawberry_bg, R.mipmap.ic_strawberry, 0));
            add(new Fruit("Orange", "Orange description", R.drawable.orange_bg, R.mipmap.ic_orange, 0));
            add(new Fruit("Apple", "Apple description", R.drawable.apple_bg, R.mipmap.ic_apple, 0));
            add(new Fruit("Banana", "Banana description", R.drawable.banana_bg, R.mipmap.ic_banana, 0));
            add(new Fruit("Cherry", "Cherry description", R.drawable.cherry_bg, R.mipmap.ic_cherry, 0));
            add(new Fruit("Pear", "Pear description", R.drawable.pear_bg, R.mipmap.ic_pear, 0));
            add(new Fruit("Raspberry", "Raspberry description", R.drawable.raspberry_bg, R.mipmap.ic_raspberry, 0));

        }};
    }

    private void addFruit(int position) {
        fruits.add(position, new Fruit("New fruit " + (++counter), R.drawable.newmovie));
        // Notificamos de un nuevo item insertado en nuestra colección
        adapter.notifyItemInserted(position);
        // Hacemos scroll hacia lo posición donde el nuevo elemento se aloja
        mLayoutManager.scrollToPosition(position);
    }

    private void deleteFruit(int position){

        fruits.remove(position);
        // Notificamos de un item borrado en nuestra colección
        adapter.notifyItemRemoved(position);
    }
}
