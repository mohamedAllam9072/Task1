package com.example.task1.db.Room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.task1.db.modules.Cart.Cart_product;
import com.example.task1.db.modules.Favorite;


@Database(entities = {Favorite.class, Cart_product.class}, version = 2, exportSchema = true)
public abstract class mDatabase extends RoomDatabase {

    /**
     * Singleton
     * <p>
     * comment by mohamed_allam
     * this line means that no more than ONE object created in same time only one object can
     * connect with Database ==> that is the MAGIC of ROOMING
     */
    private static mDatabase instance;
    /**
     * how to populate ROOM Database using Callback
     * issue --> app starts with table empty or have elements
     * SQLiteOpenHelper class do this on_create method because it called when create Database
     * but in any time else we call callback method and in it call on_create again
     */
    private static Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(instance).execute();
        }
    };

    /**
     * == here create our only Instance and call from outside a method to create an instance
     * == synchronized --> means only one thread at time
     * == check if instance created or not if not generate new one else didn't create new one
     * and use current instance
     */

    public static synchronized mDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext()
                    , mDatabase.class, "m_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    /**
     * connecting Dao
     * now we create Abstract method ,we don't need to write method body here
     */
    public abstract Dao_favorite dao_favorite();

    public abstract Dao_cart dao_cart();


    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void> {
        private Dao_favorite dao_favorite;
        private Dao_cart dao_cart;


        public PopulateDBAsyncTask(mDatabase db) {
            dao_favorite = db.dao_favorite();
            dao_cart = db.dao_cart();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }


}
