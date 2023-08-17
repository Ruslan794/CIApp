package de.fh_zwickau.ciapp.repository;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import de.fh_zwickau.ciapp.models.CI;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface CIDao {
    @Query("SELECT * FROM ci")
    Flowable<List<CI>> getAll();

    @Query("SELECT * FROM ci WHERE is_favorite LIKE 1")
    Flowable<List<CI>> getFavoriteCIs();

    @Query("SELECT * FROM ci WHERE id LIKE :id LIMIT 1")
    Single<CI> getCIByID(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertCI(CI newCI);

    @Query("DELETE FROM ci WHERE id LIKE :id")
    Completable deleteById(int id);

    @Query("DELETE FROM ci")
    Completable deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertCIs(List<CI> users);
    @Update
    Completable updateCI(CI ci);
}
