package de.fh_zwickau.ciapp.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.fh_zwickau.ciapp.MainViewModel;
import de.fh_zwickau.ciapp.R;
import de.fh_zwickau.ciapp.common.CIsAdapter;
import de.fh_zwickau.ciapp.models.CI;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class CIListFragment extends Fragment {

    private static final String IS_FAVORITE = "is_favorite";
    private Boolean isFavorite;
    private MainViewModel mViewModel;

    public static CIListFragment newInstance(String isFavorite) {
        CIListFragment fragment = new CIListFragment();
        Bundle args = new Bundle();
        args.putString(IS_FAVORITE, isFavorite);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @SuppressLint("CheckResult")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ci_list, container, false);

        isFavorite = this.getArguments().getString(IS_FAVORITE) == "true";

        EditText search = view.findViewById(R.id.search);
        RecyclerView rv = view.findViewById(R.id.recycler_view);
        CIsAdapter adapter = new CIsAdapter(new ArrayList<CI>());

        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        mViewModel = MainViewModel.getViewModel();

        adapter.setOnDetailsBtnClickedListener(new CIsAdapter.OnDetailsBtnClickedListener() {
            @Override
            public void onClicked(int id) {
                mViewModel.replaceFragment(CIDetailsFragment.newInstance(id), getActivity().getSupportFragmentManager(), true);
            }
        });

        adapter.setOnFavoriteBtnListener(new CIsAdapter.OnFavoriteBtnClickedListener() {
            @Override
            public void onClicked(CI ci, int position) {
                ci.setIsFavorite(!ci.getIsFavorite());
                mViewModel.updateCI(ci).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
                if (isFavorite) {
                    adapter.removeItemFromDataList(position);
                } else {
                    adapter.notifyItemChanged(position);
                }
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // No action needed
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Filter the CI list based on the search query
                String searchQuery = charSequence.toString().trim();
                updateCIList(searchQuery, adapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // No action needed
            }
        });

        updateCIList(null,adapter);

        return view;
    }


    @SuppressLint("CheckResult")
    private void updateCIList(String searchQuery, CIsAdapter adapter) {
        if (isFavorite) {
            mViewModel.getFavoriteCIs()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(cis -> {
                        if (searchQuery== null) adapter.updateDataList(cis);

                        ArrayList<CI> filteredCIs = new ArrayList<>();
                        for (CI ci : cis) {


                            if (ci.getTitle().toLowerCase().contains(searchQuery.toLowerCase())) {
                                filteredCIs.add(ci);
                            }
                        }
                        adapter.updateDataList(filteredCIs);
                    }, error -> {
                        Log.e("Update CI List --->", error.getMessage());
                    });
        } else {
            mViewModel.getAllCis()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(cis -> {
                        if (searchQuery== null) adapter.updateDataList(cis);
                        ArrayList<CI> filteredCIs = new ArrayList<>();
                        for (CI ci : cis) {


                            if (ci.getTitle().toLowerCase().contains(searchQuery.toLowerCase())) {
                                filteredCIs.add(ci);
                            }
                        }
                        adapter.updateDataList(filteredCIs);
                    }, error -> {
                        Log.e("Update CI List --->", error.getMessage());
                    });
        }
    }




}