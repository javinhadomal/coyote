package javinhaDoMal;
import org.rapidoid.setup.On;
import org.json.*;


/**
 * Hello world!
 *
 */
public class Server
{
    public static void main( String[] args )
    {

        On.post("/maratona").json( (req, resp) -> {
            req.async();

            byte[] bytes = req.body();




            return resp;
        });


    }
}


