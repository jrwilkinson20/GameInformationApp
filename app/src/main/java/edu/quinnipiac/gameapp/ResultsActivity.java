package edu.quinnipiac.gameapp;

/*
@authors: Victoria Gorski and Julia Wilkinson
@date: 3 - 31 - 20
@description: The ResultsActivity class is used to display the name and release data of the selected game.
 */

// Imports

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// Constructor
public class ResultsActivity extends AppCompatActivity {
    private String url;
    TextView view;
    String urlAddition;

    // Creates the activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        // Allow the fragment to be added to the activity
        getSupportFragmentManager().findFragmentById(R.id.ResultsFragment);
        view = findViewById(R.id.viewText);
        Intent intent = getIntent();
        // Connect to the url and find the information
        urlAddition = intent.getStringExtra("gameName");
        url = "https://rawg-video-games-database.p.rapidapi.com/games/" + urlAddition;
        new FetchResults().execute(url);
    }

    // Async process that fetches the results while the app is running
    private class FetchResults extends AsyncTask<String, Void, String> {
        ResultsHandler resultsHandler = new ResultsHandler();
        // In the background, grab the url and the results of the selected game
        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String results;
            // Try to get the url that was declared in the instance variables
            try {
                URL url = new URL(params[0]);
                // Get a connection to the API
                urlConnection = (HttpURLConnection) url.openConnection();
                // Ask to get some information
                urlConnection.setRequestMethod("GET");
                // Key to prove the user is authentic
                urlConnection.setRequestProperty("X-RapidAPI-Key", "292bfbd148msha9961407c7c9467p1d51c2jsn68561fad6b2c");
                // Connect to the API
                urlConnection.connect();
                // If the results do not exist, do not return anything
                InputStream in = urlConnection.getInputStream();
                if (in == null) {
                    return null;
                }
                // Get the string from the buffer
                reader = new BufferedReader(new InputStreamReader(in));
                results = getStringFromBuffer(reader).toString();
                // If there's an error, report this message
            }catch(Exception e){
                    return null;
                    // If the url does not exist, disconnect from the API
                }finally{
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                    // If there's nothing in the reader, do not report anything
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            //Log.e(LOG_TAG, "Error" + e.getMessage());
                            return null;
                        }
                    }
                }
                return results;
            }

            // Execute this method to get the desired results
            protected void onPostExecute (String result) {

                // If there are results, print them
                if (result != null) {
                    try {
                        String displayString = new ResultsHandler().getGameInfo(result);
                        view.setText(displayString);
                    }catch(JSONException e){
                        e.printStackTrace();
                    }
                }
            }

            // Get the string from the buffer
            private StringBuffer getStringFromBuffer (BufferedReader bufferedReader) throws Exception {
                StringBuffer buffer = new StringBuffer();
                String line;

                // While the buffer is not empty, keep printing out information
                while ((line = bufferedReader.readLine()) != null) {
                    buffer.append(line + '\n');

                }

                if(buffer.length() == 0){
                    return null;
                }
                return buffer;
            }
        }
    }

