package com.example.task1.db.Room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.task1.db.modules.Cart.Cart_product;
import com.example.task1.db.modules.Favorite;

import java.util.List;


public class Repo {
    private Dao_favorite dao_favorite;
    private Dao_cart dao_cart;
    private LiveData<List<Favorite>> allFavorites;
    private LiveData<List<Cart_product>> allCartProducts;


    /**
     * =Application is subclass of Context
     * =create instance of RoomDatabase here
     * =connect with noteDao abstract method in NoteDatabase.class
     * normally we cannot call abstract methods because it didn't have body
     * but since we build our database instance builder auto_generate needed code as a body for abstract method
     */
    public Repo(Application application) {
        mDatabase mDatabase = com.example.task1.db.Room.mDatabase.getInstance(application);
        dao_favorite = mDatabase.dao_favorite();
        dao_cart = mDatabase.dao_cart();
        allFavorites = dao_favorite.getAllFavorite();
        allCartProducts = dao_cart.getAllCartProducts();
    }

    /**
     * then create methods for our Dao every method
     * = room execute all database operations in background thread so you don't have take care with this
     * but for anther database operations we have execute code by our self in background because ROOM doesn't
     * allow database operations in Main Thread since it Freeze our app
     * if you do this without using async task your app will crash.
     */
    public void insert(Favorite favorite) {
        new InsertAsyncTask(dao_favorite).execute(favorite);
    }

    public void delete(Favorite favorite) {
        new DeleteAsyncTask(dao_favorite).execute(favorite);
    }

    public void deleteAllFavorites() {
        new DeleteAllSourcesAsyncTask(dao_favorite).execute();
    }

    public LiveData<List<Favorite>> getAllFavorites() {
        return allFavorites;
    }

    /**
     * =make NoteDao Member we need it to make our Database operations
     * =because of our inner class is static we can use NoteDao from class Repository
     */
    private static class InsertAsyncTask extends AsyncTask<Favorite, Void, Void> {

        private Dao_favorite dao_favorite;

        private InsertAsyncTask(Dao_favorite dao_favorite) {
            this.dao_favorite = dao_favorite;
        }

        @Override
        protected Void doInBackground(Favorite... favorites) {
            dao_favorite.insert(favorites[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Favorite, Void, Void> {

        private Dao_favorite dao_favorite;

        private DeleteAsyncTask(Dao_favorite dao_favorite) {
            this.dao_favorite = dao_favorite;
        }

        @Override
        protected Void doInBackground(Favorite... favorites) {
            dao_favorite.delete(favorites[0]);
            return null;
        }
    }

    private static class DeleteAllSourcesAsyncTask extends AsyncTask<Void, Void, Void> {

        private Dao_favorite dao_favorite;

        private DeleteAllSourcesAsyncTask(Dao_favorite dao_favorite) {
            this.dao_favorite = dao_favorite;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao_favorite.deleteAllFavorites();
            return null;
        }
    }

    public void insertCart(Cart_product cart_product) {
        new InsertCartAsyncTask(dao_cart).execute(cart_product);
    }

    public void deleteCart(Cart_product cart_product) {
        new DeleteCartAsyncTask(dao_cart).execute(cart_product);
    }

    public void deleteAllCartProducts() {
        new DeleteCartAllSourcesAsyncTask(dao_cart).execute();
    }

    public LiveData<List<Cart_product>> getAllCartProducts() {
        return allCartProducts;
    }

    /**
     * =make NoteDao Member we need it to make our Database operations
     * =because of our inner class is static we can use NoteDao from class Repository
     */
    private static class InsertCartAsyncTask extends AsyncTask<Cart_product, Void, Void> {

        private Dao_cart dao_cart;

        private InsertCartAsyncTask(Dao_cart dao_cart) {
            this.dao_cart = dao_cart;
        }

        @Override
        protected Void doInBackground(Cart_product... cart_products) {
            dao_cart.insert(cart_products[0]);
            return null;
        }
    }

    private static class DeleteCartAsyncTask extends AsyncTask<Cart_product, Void, Void> {

        private Dao_cart dao_cart;

        private DeleteCartAsyncTask(Dao_cart dao_cart) {
            this.dao_cart = dao_cart;
        }

        @Override
        protected Void doInBackground(Cart_product... cart_products) {
            dao_cart.delete(cart_products[0]);
            return null;
        }
    }

    private static class DeleteCartAllSourcesAsyncTask extends AsyncTask<Void, Void, Void> {

        private Dao_cart dao_cart;

        private DeleteCartAllSourcesAsyncTask(Dao_cart dao_cart) {
            this.dao_cart = dao_cart;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao_cart.getAllCartProducts();
            return null;
        }
    }
}
