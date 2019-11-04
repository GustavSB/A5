package no.rulingu;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Hello world!
 */
public class App
{
    public static void main( String[] args )
    {
        App app = new App ();
        app.go();
    }

    public void go(){
        POST post = new POST("datakomm.work", 80);
        post.sendauhorizationinformation();

        GetTask getTask = new GetTask("datakomm.work", 80);

        SolveTask solveTask = new SolveTask("datakomm.work", 80);
        for (int i = 1; i <= 4; i++) {
            getTask.doGet(post.SessionID, i);

            JSONObject stringParser = getTask.stringParser;
            JSONArray arrayParser = getTask.arrayParser;
            solveTask.task(post.SessionID, stringParser, arrayParser, i);
        }
    }
}
