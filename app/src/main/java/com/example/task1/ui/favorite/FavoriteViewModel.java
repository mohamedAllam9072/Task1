package com.example.task1.ui.favorite;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.task1.db.Room.Repo;
import com.example.task1.db.modules.Favorite;

import java.util.List;

public class FavoriteViewModel extends AndroidViewModel {
    private Repo repo;
    private LiveData<List<Favorite>> allFavorites;

    public FavoriteViewModel(Application application) {
        super(application);
        repo = new Repo(application);
        allFavorites = repo.getAllFavorites();

    }

    public void insert(Favorite favorite) {
        repo.insert(favorite);
    }

    public void delete(Favorite favorite) {
        repo.delete(favorite);
    }

    public void deleteAllFavorites() {
        repo.deleteAllFavorites();
    }

    public LiveData<List<Favorite>> getAllFavorites() {
        return allFavorites;
    }
}
