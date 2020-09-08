package com.example.task1.db.modules.Cart;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cart_products_table")
public class Cart_product {
    @PrimaryKey
    private int id;
    private String name;
    private String image;
    private String price;

    public Cart_product(int id, String name, String image, String price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getPrice() {
        return price;
    }
}
