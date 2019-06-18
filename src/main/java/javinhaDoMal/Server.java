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

            JSONObject jsonresponse = new JSONObject();
            jsonresponse.put("filename", data.getString("filename"));
            jsonresponse.put("problem", data.getString("problem"));
            if (what){
                jsonresponse.put("status","SUCCESS");
                return resp.code(500).result(jsonresponse.toString()).done();
            }
            jsonresponse.put("status","FAIL");
            return resp.code(500).result(jsonresponse.toString()).done();

        });


    }

}


