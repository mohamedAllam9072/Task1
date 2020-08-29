package com.example.task1.db.Room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.task1.db.modules.Favorite;

import java.util.List;


public class Repo {
    private Dao_favorite dao_favorite;
    private LiveData<List<Favorite>> allFavorites;

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
        allFavorites = dao_favorite.getAllFavorite();
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


}
