package com.arshop.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.arshop.controller.LoggedUser;
import com.arshop.controller.R;
import com.arshop.model.Address;

import java.util.List;


/**
 * FragmentMyAddress. This is an fragment class responsible to show all the user addresses.
 * This can be accessed through user profile settings.
 */
public class FragmentMyAddress extends Fragment {


    // Variables which deals with the user profile information.
    private View myAddressFragmentView;
    private List<Address> addressesList;

    // Variables to deal with the layout elements.
    private EditText addressName, addressNumber, addressComplement, addressCep, addressNeighborhood;


    public FragmentMyAddress() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        myAddressFragmentView =
                inflater.inflate(R.layout.fragment_my_address, container, false);

        // Get layout elements.
        getLayoutElements();

        // Load user information data.
        loadUserAddress();

        return myAddressFragmentView;
    }


    /**
     * Load user address data.
     */
    private void loadUserAddress() {
        addressesList = ((LoggedUser) getActivity().getApplication()).getUser().getAddresses();

        addressName.setText(addressesList.get(0).getAddressName());
        addressNumber.setText(String.valueOf(addressesList.get(0).getAddress_number()));
        addressComplement.setText(addressesList.get(0).getAddress_complement());
        addressCep.setText(addressesList.get(0).getCEP());
        addressNeighborhood.setText(addressesList.get(0).getNeighborhood());

    }


    /**
     * Associates each element of the Layout (from the xml) to a variable.
     */
    private void getLayoutElements() {
        addressName = myAddressFragmentView.findViewById(R.id.userProfileAddressName);
        addressNumber = myAddressFragmentView.findViewById(R.id.userProfileAddressNumber);
        addressComplement = myAddressFragmentView.findViewById(R.id.userProfileAddressCompliment);
        addressCep = myAddressFragmentView.findViewById(R.id.userProfileAddressCep);
        addressNeighborhood = myAddressFragmentView.findViewById(R.id.userProfileAddressNeighborhood);

    }

}
