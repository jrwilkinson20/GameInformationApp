package edu.quinnipiac.gameapp;

/*
@authors: Victoria Gorski and Julia Wilkinson
@date: 3 - 31 - 20
@description: The ResultsHandler class is used to get the data from the API and return it to the ResultsActivity class.
 */

// Imports
import org.json.JSONException;
import org.json.JSONObject;

// Constructor
public class ResultsHandler {

    // Required constructor
    public ResultsHandler(){

    }

    // Get the name of the game
    public String getGameInfo(String gameInfo) throws JSONException {
       String game_name, game_released, game_Description;
       JSONObject gameData = new JSONObject(gameInfo);
       game_name = gameData.getString("name");
       game_released = gameData.getString("released");
       game_Description = gameData.getString("description");
       game_Description = game_Description.replaceAll("</p>","");
       game_Description = game_Description.replaceAll("<p>", "");
       game_Description = game_Description.replaceAll("<br/>","");
       game_Description = game_Description.replaceAll("<br>", "");


       String printString = "Name: " + game_name + "\nRelease Date: " + game_released + "\nDescription: " + game_Description;

        return printString;
    }



}