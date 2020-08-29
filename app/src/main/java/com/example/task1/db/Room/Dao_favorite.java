package com.example.task1.db.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.task1.db.modules.Favorite;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;


@Dao
public interface Dao_favorite {
    @Insert(onConflict = REPLACE)
    void insert(Favorite favorite);

    @Delete
    void delete(Favorite favorite);

    @Query("DELETE FROM favorite_table")
    void deleteAllFavorites();

    @Query("SELECT * FROM favorite_table")
    LiveData<List<Favorite>> getAllFavorite();
}
