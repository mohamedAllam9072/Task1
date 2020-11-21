package com.example.task1.db.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.task1.db.modules.Cart.Cart_product;
import com.example.task1.db.modules.Favorite;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;


@Dao
public interface Dao_cart {
    @Insert(onConflict = REPLACE)
    void insert(Cart_product cart_product);

    @Delete
    void delete(Cart_product cart_product);

    @Query("DELETE FROM cart_products_table")
    void deleteAllCart();

    @Query("SELECT * FROM cart_products_table")
    LiveData<List<Cart_product>> getAllCartProducts();
}
