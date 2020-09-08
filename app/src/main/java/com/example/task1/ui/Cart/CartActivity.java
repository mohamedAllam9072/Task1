package com.example.task1.ui.Cart;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task1.R;
import com.example.task1.databinding.ActivityCartBinding;
import com.example.task1.db.modules.Cart.Cart_product;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    private ActivityCartBinding binding;
    private CartAdapter cartAdapter;
    private CartViewModel viewModel;
    private RecyclerView rv_cart;
    private TextView tv_items_number, tv_total_price;
    private int items_number = 0;
    private Double total_price = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_cart);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cart);
        // Specify the current activity as the lifecycle owner.
        binding.setLifecycleOwner(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewModel = ViewModelProviders.of(this).get(CartViewModel.class);
        rv_cart = findViewById(R.id.rv_cart);
        tv_items_number = findViewById(R.id.tv_items_number);
        tv_total_price = findViewById(R.id.tv_total_price);
        rv_cart.setLayoutManager(new GridLayoutManager(this, 2));
        cartAdapter = new CartAdapter(this);
        viewModel.getAllCartProducts().observe(this, new Observer<List<Cart_product>>() {
            @Override
            public void onChanged(List<Cart_product> cart_products) {
                cartAdapter.setList(cart_products);
            }
        });
        rv_cart.setAdapter(cartAdapter);

        delete_item();
        cartAdapter.setOnCartButtonClicked(new CartAdapter.onCartClicked() {
            @Override
            public void m_onClick(Cart_product cart_product) {
                items_number++;
                total_price = Double.parseDouble(cart_product.getPrice());
                tv_items_number.setText("items = " + items_number);
                tv_total_price.setText("total = " + total_price);
            }
        });

    }

    private void delete_item() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.delete(cartAdapter.getCartProductAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getApplicationContext(), "delete from Favorites", Toast.LENGTH_SHORT).show();


            }
        }).attachToRecyclerView(rv_cart);


    }
}