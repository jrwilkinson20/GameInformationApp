package edu.quinnipiac.gameapp;

/*
@authors: Victoria Gorski and Julia Wilkinson
@date: 3 - 31 - 20
@description: The OpeningFragment class is used to inflate the container and populate the fragment portion of the splash screen.
 */

// Imports

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// Constructor
public class OpeningFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Once the fragment is created, match it to the appropriate layout
        return inflater.inflate(R.layout.fragment_opening, container, false);
    }
}
