package edu.quinnipiac.gameapp;

/*
@authors: Victoria Gorski and Julia Wilkinson
@date: 3 - 31 - 20
@description: The SpinnerFragment class is used to connect the appropriate fragment for the spinner and allows it to be used by the SpinnerActivity class.
 */

// Imports
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// Constructor
public class SpinnerFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Match the fragment with its layout
        return inflater.inflate(R.layout.fragment_spinner, container, false);
    }
}
