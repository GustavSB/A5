package no.rulingu;

import javafx.application.Application;
import org.json.JSONObject;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class POST {
    GetTask task;

    private String BASE_URL; // Base URL (address) of the server
    public String SessionID; //ID that is used when confirming tasks

    /**
     * Create an HTTP POST example
     *
     * @param host Will send request to this host: IP address or domain
     * @param port Will use this port
     */
    public POST(String host, int port) {
        BASE_URL = "http://" + host + ":" + port + "/";
        task = new GetTask("datakomm.work", 80);
    }

    /**
     * Post three random numbers to a specific path on the web server
     */
    public void sendauhorizationinformation() {
        // TODO: Change email and phone before sending in;
        String email = "gustavsb@stud.ntnu.no";
        String phone = "90050394";

        JSONObject json = new JSONObject();
        json.put("email", email);
        json.put("phone", phone);
        System.out.println("Posting this JSON data to server");
        System.out.println(json.toString());
        sendPost("dkrest/auth", json);
    }

    /**
     * Send HTTP POST
     *
     * @param path     Relative path in the API.
     * @param jsonData The data in JSON format that will be posted to the server
     */
    private void sendPost(String path, JSONObject jsonData) {
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
                GetSessionID(responseBody);

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
        task.doGet(1, SessionID);
    }

    /**
     * Read the whole content from an InputStream, return it as a string
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

    public void GetSessionID(String responsB) {
        String[] splitRespons = responsB.split(" ");
        String[] doublesplit = splitRespons[16].split(",");
        SessionID = doublesplit[0];
        }
    }
