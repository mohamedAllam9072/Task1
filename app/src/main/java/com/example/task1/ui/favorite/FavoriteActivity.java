package com.example.task1.ui.favorite;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task1.R;
import com.example.task1.adapters.FavoriteAdapter;
import com.example.task1.db.modules.Favorite;

import java.util.List;

public class FavoriteActivity extends AppCompatActivity {
    private FavoriteViewModel viewModel;
    private RecyclerView rv_favorite;
    private FavoriteAdapter favoriteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);
        rv_favorite = findViewById(R.id.rv_favorites);
        rv_favorite.setLayoutManager(new GridLayoutManager(this, 2));
        favoriteAdapter = new FavoriteAdapter(this);
        viewModel.getAllFavorites().observe(this, new Observer<List<Favorite>>() {
            @Override
            public void onChanged(List<Favorite> favorites) {
                favoriteAdapter.setList(favorites);
            }
        });
        rv_favorite.setAdapter(favoriteAdapter);
        delete_item();

    }

    private void delete_item() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.delete(favoriteAdapter.getFavoriteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getApplicationContext(), "delete from Favorites", Toast.LENGTH_SHORT).show();


            }
        }).attachToRecyclerView(rv_favorite);


    }
}