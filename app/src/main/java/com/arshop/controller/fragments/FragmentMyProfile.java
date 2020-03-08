package com.arshop.controller.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arshop.controller.activities.LoggedUser;
import com.arshop.controller.R;
import com.arshop.model.User;


/**
 * FragmentMyProfile. This is an fragment class responsible to show all the user profile data.
 * This can be accessed through user profile settings.
 */
public class FragmentMyProfile extends Fragment implements View.OnClickListener {


    // Variables which deals with the user profile information.
    private View myProfileFragmentView;

    // Variables to deal with the layout elements.
    private EditText userNameField, userEmailField, userCpfField, userBornDateField, userAgeField,
            userContactField;
    private Button editMyDataButton, editCancelEditDataButton;


    public FragmentMyProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        myProfileFragmentView =
                inflater.inflate(R.layout.fragment_my_profile, container, false);

        // Get layout elements.
        getLayoutElements();

        // Load user information data.
        loadUserData();

        // Lock edition.
        lockElements();

        return myProfileFragmentView;
    }


    /**
     * Load user information data.
     */
    private void loadUserData() {
        User logged_user = ((LoggedUser) getActivity().getApplication()).getUser();

        userNameField.setText(logged_user.getName());
        userEmailField.setText(logged_user.getEmail());
        userCpfField.setText(logged_user.getCpf());
        userBornDateField.setText(logged_user.getBornDate());
    }


    /**
     * Lock all EditText elements.
     */
    private void lockElements() {
        userNameField.setEnabled(false);
        userEmailField.setEnabled(false);
        userCpfField.setEnabled(false);
        userBornDateField.setEnabled(false);
        userAgeField.setEnabled(false);
        userContactField.setEnabled(false);
    }


    /**
     * Unlock all fields to user edit any information wanted..
     *
     * @param view
     */
    public void editMyData(View view){

        if (editMyDataButton.getText().equals("Editar Meus Dados")) {
            userNameField.setEnabled(true);
            userEmailField.setEnabled(true);
            userCpfField.setEnabled(true);
            userBornDateField.setEnabled(true);
            userAgeField.setEnabled(true);
            userContactField.setEnabled(true);

            // Change Button text and show Cancel option.
            editMyDataButton.setText("Salvar");
            editCancelEditDataButton.setVisibility(View.VISIBLE);


        } else {
            Toast.makeText(getActivity(), "Ação realizada.",
                    Toast.LENGTH_SHORT ).show();
            lockElements();
            editMyDataButton.setText("Editar Meus Dados");
            editCancelEditDataButton.setVisibility(View.INVISIBLE);

        }
    }


    /**
     * Associates each element of the Layout (from the xml) to a variable.
     */
    public void getLayoutElements() {
        userNameField = myProfileFragmentView.findViewById(R.id.userProfileName);
        userEmailField = myProfileFragmentView.findViewById(R.id.userProfileEmail);
        userCpfField = myProfileFragmentView.findViewById(R.id.userProfileCpf);
        userBornDateField = myProfileFragmentView.findViewById(R.id.userProfileBornDate);
        userAgeField = myProfileFragmentView.findViewById(R.id.userProfileAge);
        userContactField = myProfileFragmentView.findViewById(R.id.userProfileContact);

        editMyDataButton = myProfileFragmentView.findViewById(R.id.editMyDataButton);
        editMyDataButton.setOnClickListener(this);
        editCancelEditDataButton = myProfileFragmentView.findViewById(R.id.canceEditDataButton);
        editCancelEditDataButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        editMyData(v);
    }
}
