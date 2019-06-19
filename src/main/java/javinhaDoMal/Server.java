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

            // transformando a requisição em json e criando a nova Submission
            JSONObject data = new JSONObject(new String(req.body()));

            Submission newSubmission = new Submission(
                    data.getString("filename"),
                    data.getString("problem"),
                    data.getString("sourcecode")
            );

            newSubmission.runSourceCode();
            historico.appendSubmition(newSubmission);

            return resp.code(200).result(newSubmission).done();
        });

        On.get("/sub").json((reque, resp) -> {
            JSONObject metadata = new JSONObject(new String(reque.body()));

            Submission newSubmission = new Submission(
                    metadata.getString("status"),
                    metadata.getString("idProblem"),
                    metadata.getString("dateAndHourExecution")
            );
            return historico;
        });

    }
}