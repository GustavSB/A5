package no.rulingu;

import jdk.nashorn.internal.parser.JSONParser;
import org.json.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class JSONParse {

    public static JSONObject stringParser(String respons) {

        String jsonObjectString = respons;
        JSONObject jsonObject = new JSONObject(jsonObjectString);
        System.out.println(respons);
        // Let's try to parse it as a JSON object
        try {

            if (jsonObject.has("taskNr")) {
                int TaskNr = jsonObject.getInt("taskNr");
                System.out.println("The object contains field 'taskNr' with value "
                        + TaskNr);
            }
            if (jsonObject.has("description")) {
                String description = jsonObject.getString("description");
                System.out.println("The object contains field 'description' with value "
                        + description);
            }

        }
        catch (JSONException e) {
            // It is important to always wrap JSON parsing in try/catch
            // If the string is suddenly not in the expected format,
            // an exception will be generated
            System.out.println("Got exception in JSON parsing: " + e.getMessage());
        }

        System.out.println("");
        return jsonObject;
    }
    public static JSONArray arrayParser(String respons){

        String jsonObjectArray = respons;
        JSONArray jsonArray = new JSONArray(jsonObjectArray);
        try {
            if (jsonArray.toString().contains("arguments")) {
                JSONObject jObj = new JSONObject(jsonObjectArray);
                jsonArray = jObj.getJSONArray("arguments");
                System.out.println("The object contains field 'arguments' with value "
                        + jsonArray.toString());
            }
        }
        catch (JSONException e) {
            // It is important to always wrap JSON parsing in try/catch
            // If the string is suddenly not in the expected format,
            // an exception will be generated
            System.out.println("Got exception in JSON parsing: " + e.getMessage());
        }
        return jsonArray;
    }



}
