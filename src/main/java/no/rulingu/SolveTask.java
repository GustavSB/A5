package no.rulingu;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SolveTask {

    String currentSessionID;
    private String BASE_URL; // Base URL (address) of the server

    public SolveTask(String host, int port) {
        BASE_URL = "http://" + host + ":" + port + "/";
    }

    public void task(String SessionID, String response, int taskNr) {
        currentSessionID = SessionID;

        if (taskNr == 1) {
            task1();
        }
        if (taskNr == 2) {
            task2(response);
        } else if (taskNr == 3) {
            task3(response);
        } else if (taskNr == 4) {
            task4();
        }
    }

    private void task1() {
        JSONObject json = new JSONObject();
        String msg = "Hello";
        json.put("sessionId", currentSessionID);
        json.put("msg", msg);
        sendPost("dkrest/solve", json);
    }

    //todo fix her  we are getting response from suc task 1 /v2
    private void task2(String response) {
        JSONObject json = new JSONObject();
        json.put("sessionId", currentSessionID);
        String jsonObjectString = response;
        System.out.println(jsonObjectString);
        JSONObject jObj = new JSONObject(jsonObjectString);
        //JSONArray jsonArray = jObj.getJSONArray("arguments");
        //json.put("msg", jsonArray.toString());
        sendPost("dkrest/solve", json);
    }

    private void task3(String response) {
        JSONObject json = new JSONObject();
        json.put("sessionId", currentSessionID);
        String jsonObjectString = response;
        JSONObject jObj = new JSONObject(jsonObjectString);
        System.out.println(jObj.toString());
        //JSONArray jsonArray = jObj.getJSONArray("arguments");
        int result = 0;
        //for (int i = 0; i <= jsonArray.length(); i++) {
        //    result = result * i;
        //}
        json.put("result", result);
        sendPost("dkrest/solve", json);
    }

    private void task4() {
        String password = "123456";

        /*boolean state = false;
        if (!state) {
            for (int i = 0; i < 9; i++) {
                String answear = ("000" + i);
                if (answear == expected) {
                    state = true;
                }
            }
        }
        for (int i=0; false; i++) {
            if (sb == argument) {

            }
        }*/

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        System.out.println(sb.toString());

    }

    public void sendPost(String path, JSONObject jsonData) {
        try {
            String url = BASE_URL + path;
            URL urlObj = new URL(url);
            System.out.println("Sending HTTP POST to " + url);
            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            OutputStream os = con.getOutputStream();
            os.write(jsonData.toString().getBytes());
            os.flush();

            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                System.out.println("Server reached");

                // Response was OK, read the body (data)
                InputStream stream = con.getInputStream();

                String responseBody = convertStreamToString(stream);
                stream.close();
                System.out.println("Response from the server:");
                System.out.println(responseBody);

            } else {
                String responseDescription = con.getResponseMessage();
                System.out.println("Request failed, response code: " + responseCode + " (" + responseDescription + ")");
            }
        } catch (ProtocolException e) {
            System.out.println("Protocol not supported by the server");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void sendGet(String path) {
        try {
            String url = BASE_URL + path;
            URL urlObj = new URL(url);
            System.out.println("Sending HTTP GET to " + url);
            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                System.out.println("Server reached");
                // Response was OK, read the body (data)
                InputStream stream = con.getInputStream();
                String responseBody = convertStreamToString(stream);
                stream.close();
                System.out.println("Response from the server:");
                System.out.println(responseBody);
            } else {
                String responseDescription = con.getResponseMessage();
                System.out.println("Request failed, response code: " + responseCode + " (" + responseDescription + ")");
            }
        } catch (ProtocolException e) {
            System.out.println("Protocol not supported by the server");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Read the whole content from an InputStream, return it as a string
     *
     * @param is Inputstream to read the body from
     * @return The whole body as a string
     */
    private String convertStreamToString(InputStream is) {
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder();
        try {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
                response.append('\n');
            }
        } catch (IOException ex) {
            System.out.println("Could not read the data from HTTP response: " + ex.getMessage());
        }
        return response.toString();
    }
}
