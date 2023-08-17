package de.fh_zwickau.ciapp.repository;

import java.util.List;

import de.fh_zwickau.ciapp.models.CI;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public interface CIsRepository {

    Flowable<List<CI>> getAllCIs ();
    Flowable<List<CI>> getFavoriteCIs();
    Single<CI> getCIbyId  (int id);
    Completable addCI (CI newCI);

    Completable insertAll(List<CI> list);

    Completable deleteById(int id);
    Completable deleteAll();

    Completable updateCI(CI ci);

}