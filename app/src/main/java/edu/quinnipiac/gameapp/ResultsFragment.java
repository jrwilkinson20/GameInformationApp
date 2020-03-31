package edu.quinnipiac.gameapp;

/*
@authors: Victoria Gorski and Julia Wilkinson
@date: 3 - 31 - 20
@description: The ResultsFragment class is used to display the fragment for the results screen.
 */

// Imports
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// Constructor
public class ResultsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Match the inflater to the appropriate fragment
        return inflater.inflate(R.layout.fragment_results, container, false);
    }
}
