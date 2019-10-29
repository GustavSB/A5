package no.rulingu;

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
        GetTask getTask = new GetTask("dataKOMM.WORK", 80);
        post.sendauhorizationinformation();
    }
}
