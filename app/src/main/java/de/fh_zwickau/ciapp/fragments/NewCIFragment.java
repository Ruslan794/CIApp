package de.fh_zwickau.ciapp.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.List;

import de.fh_zwickau.ciapp.MainViewModel;
import de.fh_zwickau.ciapp.R;
import de.fh_zwickau.ciapp.common.ConstantsObject;
import de.fh_zwickau.ciapp.models.CI;


public class NewCIFragment extends Fragment {


    private MainViewModel mViewModel;
    private int id = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_ci, container, false);


        TextView titleField = view.findViewById(R.id.titleTextField);
        TextView storyField = view.findViewById(R.id.descrTextField);
        TextView placeField = view.findViewById(R.id.placeTextField);
        TextView authorTextField = view.findViewById(R.id.authorTextField);
        RadioButton firstPersonButton = view.findViewById(R.id.firstPersonButton);
        RadioButton thirdPersonButton = view.findViewById(R.id.thirdPersonButton);
        Button saveButton = view.findViewById(R.id.saveButton);
        Button cancelButton = view.findViewById(R.id.clearButton);



        cancelButton.setOnClickListener(l -> {
            titleField.setText("");
            storyField.setText("");
            placeField.setText("");
            authorTextField.setText("");
            titleField.clearFocus();
            storyField.clearFocus();
            placeField.clearFocus();
            authorTextField.clearFocus();
            firstPersonButton.setChecked(false);
            thirdPersonButton.setChecked(false);
        });

        saveButton.setOnClickListener((l) -> {
            String title = titleField.getText().toString();
            String story = storyField.getText().toString();
            String place = placeField.getText().toString();
            String author = authorTextField.getText().toString();

            if (title.isEmpty() || story.isEmpty() || place.isEmpty() || author.isEmpty()
                    || (!firstPersonButton.isChecked() && !thirdPersonButton.isChecked())) {
                Toast.makeText(getActivity(), "Fill all fields before saving!", Toast.LENGTH_LONG).show();
                return;
            }

            String perspective = "";
            if (firstPersonButton.isChecked()) {
                perspective = "First Person";
            } else if (thirdPersonButton.isChecked()) {
                perspective = "Third Person";
            } else {
                throw new IllegalStateException();
            }


            mViewModel = MainViewModel.getViewModel();
            List<CI> cis = mViewModel.getAllCis().blockingFirst();
            for (CI c : cis) {
                if (id == c.getId()) {
                    //Increase the id by one if it already exists. This should ensure, that there will be no id-collisions
                    id++;
                }
            }

            CI ci = new CI(id, title, story, place, perspective, false, author);

            MainViewModel mViewModel = MainViewModel.getViewModel();
            mViewModel.insertCI(ci);

            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(ConstantsObject.ADDED_CIS_NUMBER, MODE_PRIVATE);
            int existingValue = sharedPreferences.getInt(ConstantsObject.ADDED_CIS_NUMBER, 0);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(ConstantsObject.ADDED_CIS_NUMBER, existingValue += 1);
            editor.apply();

            Toast.makeText(getActivity(), "CI added successfully!", Toast.LENGTH_SHORT).show();
            mViewModel.replaceFragment(CIListFragment.newInstance("false"), getActivity().getSupportFragmentManager(), false);
        });

        return view;
    }

}