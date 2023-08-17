package de.fh_zwickau.ciapp.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import de.fh_zwickau.ciapp.MainViewModel;
import de.fh_zwickau.ciapp.R;
import de.fh_zwickau.ciapp.models.CI;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class CIDetailsFragment extends Fragment {

    private static final String ID_OF_CI = "ci_id";
    private MainViewModel mViewModel;

    public static CIDetailsFragment newInstance(int id) {
        CIDetailsFragment fragment = new CIDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ID_OF_CI, Integer.toString(id));
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
        View view = inflater.inflate(R.layout.fragment_ci_details, container, false);

        mViewModel = MainViewModel.getViewModel();

        TextView title = view.findViewById(R.id.ciTitle);
        TextView text = view.findViewById(R.id.ciText);
        TextView place = view.findViewById(R.id.placeView);
        TextView author = view.findViewById(R.id.authorView);
        TextView perspective = view.findViewById(R.id.perspectiveView);
        int ciId = Integer.parseInt(getArguments().getString(ID_OF_CI));

        mViewModel.getCiById(ciId).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<CI>() {
                    @Override
                    public void accept(CI ci) throws Throwable {
                        title.setText(ci.getId() + "\n\n" + ci.getTitle());
                        text.setText("Story:\n" + ci.getStory() );
                        place.setText("Ort: " + ci.getPlace());
                        author.setText("Author: " + ci.getAuthor());
                        perspective.setText("Perspective: " + ci.getPerspective());
                    }
                });


        return view;
    }
}