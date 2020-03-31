package edu.quinnipiac.gameapp;

/*
@authors: Victoria Gorski and Julia Wilkinson
@date: 2 - 29 - 20
@description: The SpinnerActivity class is used to control the spinner and the games in it. It connects to the API and then passes its data to the ResultsHandler
class in order to be converted into Strings the user can read.
 */

// Imports
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.ShareActionProvider;
import android.widget.Spinner;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;

// Constructor
public class SpinnerActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    // Instance variables
    private final String LOG_TAG = SpinnerActivity.class.getSimpleName();
    public ResultsHandler resultsHandler = new ResultsHandler();
    boolean userSelect = false;
    public RelativeLayout layout;
    ListView list;


    String listSelectedValue;
    private ShareActionProvider provider;

    // Used to create the options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.mainmenu,menu);
        MenuItem shareItem = menu.findItem(R.id.action_share);
        // Allow the share button to access other apps
        provider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        layout = findViewById(R.id.spinnerActivity);
        return super.onCreateOptionsMenu(menu);
    }

    // Attach the options to some action
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        // Depending on which option is selected, match it to the corresponding action
        switch (id) {
            // Access settings
            case R.id.action_settings:
                // Hint for the user to change the background
                Toast.makeText(this, "You can change the background!", Toast.LENGTH_SHORT).show();
                return true;
            // Access share menu
            case R.id.action_share:
                // Allow the user to send a message using other apps
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "This is a message for you");
                provider.setShareIntent(intent);
                return true;
            // Access help menu
            case R.id.action_help:
                // Display a pop - up
                AlertDialog.Builder help = new AlertDialog.Builder(this);
                help.setTitle("How to Use");
                // Explain what the app does
                help.setMessage("This app is designed to retrieve information from a video game API and display it to the user. To use this app, select a game from the spinner in order to learn about its release date.");
                // If the user clicks outside the box, remove the pop - up
                help.setCancelable(true);
                help.show();
                return true;
            // Change background to background1.jpg
            case R.id.awesomeBackground:
                layout.setBackgroundResource(R.drawable.background1);
                return true;
            // Change background to background2.jpg
            case R.id.coolBackground:
                layout.setBackgroundResource(R.drawable.background2);
                return true;
            // Change background to background3.jpg
            case R.id.cuteBackground:
                layout.setBackgroundResource(R.drawable.background3);
                return true;
            // If no options are selected, return the menu
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Create the spinner and the Async process
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        SpinnerFragment spinner = (SpinnerFragment)getSupportFragmentManager().findFragmentById(R.id.SpinnerFragment);
        getIntent();



        // Connect the layout with the activity
        // Spinner spinner = findViewById(R.id.spinner);
        list = findViewById(R.id.list);
        list.setOnItemClickListener(this);
        String[] items = {"Fallout ","Battlefield" ,"Galaga", "Sonic", "Yoshi","Minecraft", "Castlevania", "Tetris", "Fortnite"};

        // Create the spinner drop - down menu

            ArrayAdapter<String> resultsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,items);
            list.setAdapter(resultsAdapter);
            // Throw this if the spinner doesn't work


        // Get the game the user selects





    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String selected = list.getItemAtPosition(position).toString();
        selected.replaceAll(" ","");
        Intent intent = new Intent(this, ResultsActivity.class);
        intent.putExtra("gameName", selected);
        startActivity(intent);


    }

    // If a user clicks something, make sure the item is selected
    @Override
    public void onUserInteraction(){
        super.onUserInteraction();
        userSelect = true;
    }

}
