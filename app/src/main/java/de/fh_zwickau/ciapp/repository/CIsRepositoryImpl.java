package de.fh_zwickau.ciapp.repository;

import android.app.Application;

import java.util.List;

import de.fh_zwickau.ciapp.models.CI;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public class CIsRepositoryImpl implements CIsRepository {

    private final CIDao ciDao;

    public CIsRepositoryImpl(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        ciDao = db.cIDao();
    }

    @Override
    public Flowable<List<CI>> getAllCIs() {
        return ciDao.getAll();
    }

    @Override
    public Single<CI> getCIbyId(int id) {
        return ciDao.getCIByID(id);
    }

    @Override
    public Completable addCI(CI newCI) {
        return ciDao.insertCI(newCI);
    }

    @Override
    public Completable insertAll(List<CI> list) {
        return ciDao.insertCIs(list);
    }

    @Override
    public Completable deleteById(int id) {
        return ciDao.deleteById(id);
    }

    @Override
    public Completable deleteAll() {
        return ciDao.deleteAll();
    }

    @Override
    public Completable updateCI(CI ci) {
        return ciDao.updateCI(ci);
    }

    @Override
    public Flowable<List<CI>> getFavoriteCIs() {
        return ciDao.getFavoriteCIs();
    }

}
