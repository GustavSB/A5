package no.rulingu;

import org.json.*;
import org.json.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLOutput;


public class JSONParse {

    public JSONParse() {}

        public void fullparse(String respons){

            String jsonObjectString = respons;

            System.out.println(respons);
            // Let's try to parse it as a JSON object
            try {
                JSONObject jsonObject = new JSONObject(jsonObjectString);
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
                if (jsonObject.has("arguments")) {
                    JSONObject jObj = new JSONObject(jsonObjectString);
                    JSONArray jsonArray = jObj.getJSONArray("arguments");
                    System.out.println("The object contains field 'arguments' with value "
                            + jsonArray.toString());
                }


            } catch (JSONException e) {
                // It is important to always wrap JSON parsing in try/catch
                // If the string is suddently not in the expected format,
                // an exception will be generated
                System.out.println("Got exception in JSON parsing: " + e.getMessage());
            }

            System.out.println("");
        }

        public String[] parseArgs(String respons){
            String jsonObjectString = respons;
            String[] retArgs;
            retArgs[0] = "";

            System.out.println(respons);
            // Let's try to parse it as a JSON object
            try {
                JSONObject jsonObject = new JSONObject(jsonObjectString);
                if(jsonObject.has("arguments")) {
                    JSONObject jObj = new JSONObject(jsonObjectString);
                    JSONArray jsonArray = jObj.getJSONArray("arguments");
                    String[] arguments = new String[jsonArray.length()];
                    for(int i=0; i<arguments.length; i++) {
                        arguments[i]=jsonArray.optString(i);
                    }
                    retArgs = arguments;
                    System.out.println("object contains 'args' with value " + jsonArray.toString());
                }
            }

                catch (JSONException e) {
                    System.out.println("got exception in JSON parsing: " + e.getMessage());
                }
            return retArgs;
        }
    }

