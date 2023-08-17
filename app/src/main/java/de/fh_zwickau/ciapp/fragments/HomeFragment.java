package de.fh_zwickau.ciapp.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import de.fh_zwickau.ciapp.MainViewModel;
import de.fh_zwickau.ciapp.R;
import de.fh_zwickau.ciapp.common.CIGenerator;
import de.fh_zwickau.ciapp.common.ConstantsObject;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class HomeFragment extends Fragment {

    private MainViewModel mViewModel;
    private CIGenerator ciGenerator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint({"CheckResult", "DefaultLocale"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mViewModel = MainViewModel.getViewModel();
        ciGenerator = new CIGenerator(getActivity());


        TextView cisNumber = view.findViewById(R.id.cis_number);
        TextView addedCisNumber = view.findViewById(R.id.added_cis_number);
        Button setDataBtn = view.findViewById(R.id.set_default_data_btn);
        Button generateDataBtn = view.findViewById(R.id.generate_cis_btn);
        Button clearDbBtn = view.findViewById(R.id.cleat_db_btn);

        mViewModel.getAllCis().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(cis -> {
            if (cis.isEmpty()) {
                cisNumber.setText(String.format("Es gibt %d CIs in Database.", 0));
            } else {
                cisNumber.setText(String.format("Es gibt %d CIs in Database", cis.size()));
            }
        });

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(ConstantsObject.ADDED_CIS_NUMBER, MODE_PRIVATE);
        addedCisNumber.setText(String.format("Du hast %d CIs selbst eingefügt", sharedPreferences.getInt(ConstantsObject.ADDED_CIS_NUMBER, 0)));

        setDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.insertListOfCIs(ciGenerator.getDefaultData());
                Toast.makeText(getActivity(), "CIs added successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        generateDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.insertListOfCIs(ciGenerator.generateTenCIs());
                Toast.makeText(getActivity(), "CIs added successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        clearDbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.clearDb();
                Toast.makeText(getActivity(), "Database cleared!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        ciGenerator.saveCounterValue();
    }
}