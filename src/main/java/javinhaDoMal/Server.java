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

        Historical historico = new Historical();


        On.post("/maratona").json( (req, resp) -> {
            req.async();

            byte[] bytes = req.body();

            JSONObject data = new JSONObject(new String(bytes));

            Submition newSubmition = new Submition(data.getString("filename"), data.getString("problem"), data.getString("sourcecode"));

            boolean what = newSubmition.runSourceCode();
            historico.appendSubmition(newSubmition);

            return resp.code(200).result("DEU BOM!").done();
        });


    }

}


