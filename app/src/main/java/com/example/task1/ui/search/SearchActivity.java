package com.example.task1.ui.search;

import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task1.R;
import com.example.task1.adapters.SearchAdapter;
import com.example.task1.db.modules.home.h_Product;
import com.example.task1.ui.home.HomeViewModel;

import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView rv_search;
    private SearchAdapter searchAdapter;
    private HomeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView();
        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        viewModel.getHomeData();
        viewModel.getProducts().observe(this, new Observer<List<h_Product>>() {
            @Override
            public void onChanged(List<h_Product> h_products) {
                searchAdapter.setList(h_products);
            }
        });

        rv_search = findViewById(R.id.rv_search);
        searchAdapter = new SearchAdapter(getApplicationContext());
        rv_search.setLayoutManager(new LinearLayoutManager(this));
        rv_search.setAdapter(searchAdapter);
    }

    private void searchView() {
        SearchView searchView = findViewById(R.id.search_view);
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setQueryHint("search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchAdapter.getFilter().filter(s);
                return false;
            }
        });
    }
}