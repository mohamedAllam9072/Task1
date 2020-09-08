package com.example.task1.ui.Cart;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.task1.db.Room.Repo;
import com.example.task1.db.modules.Cart.Cart_product;

import java.util.List;

public class CartViewModel extends AndroidViewModel {
    private Repo repo;
    private LiveData<List<Cart_product>> allCartProducts;

    public CartViewModel(Application application) {
        super(application);
        repo = new Repo(application);
        allCartProducts = repo.getAllCartProducts();

    }

    public void insert(Cart_product cart_product) {
        repo.insertCart(cart_product);
    }

    public void delete(Cart_product cart_product) {
        repo.deleteCart(cart_product);
    }

    public void deleteAllCartProducts() {
        repo.deleteAllCartProducts();
    }

    public LiveData<List<Cart_product>> getAllCartProducts() {
        return allCartProducts;
    }
}
