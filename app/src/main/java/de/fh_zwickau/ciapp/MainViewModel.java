package de.fh_zwickau.ciapp;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import de.fh_zwickau.ciapp.models.CI;
import de.fh_zwickau.ciapp.repository.CIsRepositoryImpl;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel {

    private static volatile MainViewModel INSTANCE;
    private CIsRepositoryImpl repository;
    private BottomNavigationView bottomNav;

    public static MainViewModel getViewModel() {
        if (INSTANCE == null) {
            INSTANCE = new MainViewModel();
        }
        return INSTANCE;
    }

    public void initialiseDB(Application application) {
        repository = new CIsRepositoryImpl(application);
    }

    public void initializeBottomNavBar(BottomNavigationView bottomNav) {
        this.bottomNav = bottomNav;
    }

    public void replaceFragment(Fragment destinationFragment, FragmentManager fragmentManager, Boolean clearFocusOnBottomNavBar) {
        if (clearFocusOnBottomNavBar) {
            bottomNav.setSelectedItemId(R.id.empty);
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_activity_container, destinationFragment);
        fragmentTransaction.commit();
    }

    @SuppressLint("CheckResult")
    public Flowable<List<CI>> getAllCis() {
        return repository.getAllCIs();
    }


    public Single<CI> getCiById(int id) {
        return repository.getCIbyId(id);
    }

    public Completable updateCI(CI ci) {
        return repository.updateCI(ci);
    }

    public void insertCI(CI ci) {
        repository.addCI(ci).subscribeOn(Schedulers.io()).subscribe();
    }

    public void clearDb() {
        repository.deleteAll().subscribeOn(Schedulers.io()).subscribe();
    }

    public void insertListOfCIs(List<CI> cis) {
        repository.insertAll(cis).subscribeOn(Schedulers.io()).subscribe();
    }

    @SuppressLint("CheckResult")
    public Flowable<List<CI>> getFavoriteCIs() {
        return repository.getFavoriteCIs();
    }

}