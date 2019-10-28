package no.rulingu;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        POST post = new POST("datakomm.work", 80);
        post.sendauhorizationinformation();
    }
}
