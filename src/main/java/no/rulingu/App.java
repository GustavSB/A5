package no.rulingu;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        App app = new App();
        app.go();
    }

    public void go() {
        POST post = new POST("datakomm.work", 80);
        post.sendauhorizationinformation();

        GetTask getTask = new GetTask("datakomm.work", 80);

        SolveTask solveTask = new SolveTask("datakomm.work", 80);
        for (int i = 1; i <= 4; i++) {
            String parseString = getTask.doGet(post.SessionID, i);

            //JSONObject stringParser = JSONParse.stringParser(parseString);
            JSONArray arrayParser = JSONParse.arrayParser(parseString);
            solveTask.task(post.SessionID, /*stringParser,*/ arrayParser, i);
        }
    }
}
