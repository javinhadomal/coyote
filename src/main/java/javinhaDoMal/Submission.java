package javinhaDoMal;
import java.io.IOException;
import java.nio.*;
import java.nio.file.Files;
import java.text.DateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.apache.commons.codec.binary.Base64;
import java.io.*;
import java.nio.file.Paths;
import java.util.Date;

public class Submission {

    private Date dateAndHourExecution;
    private String nameFile;
    private String idProblem;
    private String sourceCode;
    private String status;

    public Submission(String nameFile, String idProblem, String sourceCode) {
        this.nameFile = nameFile;
        this.idProblem = idProblem;
        this.sourceCode = sourceCode;
    }

    public Date getDateAndHourExecution() {
        return dateAndHourExecution;
    }

    public void setDateAndHourExecution(Date dateAndHourExecution) {
        this.dateAndHourExecution = dateAndHourExecution;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getIdProblem() {
        return idProblem;
    }

    public void setIdProblem(String idProblem) {
        this.idProblem = idProblem;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public void setStatus(String status) {this.status = status; }

    public String getStatus() {return this.status; }

    public boolean runSourceCode() throws IOException, InterruptedException {
        // creating py file
        Files.write( Paths.get(this.nameFile) ,   java.util.Base64.getDecoder().decode(this.sourceCode) );

        // Creating string variables name's file
        String namePyFile = this.nameFile;
        //String entry = "";
        //if (this.nameFile.equals("mergulho.py"))  entry =  "mergulhoTestCase1.txt";
        //else  entry = "arqEntradaCasoTeste2.txt";

        ArrayList<String> TestFiles = this.TestCases();
        ArrayList<String> OutputFiles = new ArrayList<String>();

        for (Integer i = 0 ; i < TestFiles.size(); i++){
            // build string outputfile
            String saidaFileName = new String("saida"+String.format(this.nameFile.replace(".py", i.toString()+".txt")));
            OutputFiles.add(saidaFileName);

            // running python
            ProcessBuilder p = new ProcessBuilder("python3" , namePyFile);
            p.redirectInput(new File( TestFiles.get(i) ) );
            p.redirectOutput(new File(saidaFileName));
            p.start().waitFor();

        }


        if(this.dateAndHourExecution == null) this.dateAndHourExecution = Calendar.getInstance().getTime();

        boolean success = verifyOutPutFiles(OutputFiles);
        if(success) this.status = "SUCCESS";
        else this.status = "FAIL";
        return success;
    }

    private ArrayList<String> TestCases(){
        ArrayList<String> testFiles =  new ArrayList<String>();
        if (this.nameFile.equals("mergulho.py")){
            testFiles.add("mergulhoTestCase1.txt");
            testFiles.add("mergulhoTestCase2.txt");
            return testFiles;
        }else{
            testFiles.add("zerinhoTestCase1.txt");
            testFiles.add("zerinhoTestCase2.txt");
            testFiles.add("zerinhoTestCase3.txt");
            return testFiles;
        }
    }

    private boolean verifyOutPutFiles(ArrayList<String> OutputFiles) throws  IOException{
        try{

            if(this.nameFile.equals("mergulho.py")){

                // Rotina de correção 1
                BufferedReader Reader0 = new BufferedReader(new FileReader(OutputFiles.get(0) ));
                BufferedReader Reader1 = new BufferedReader(new FileReader(OutputFiles.get(1) ));
                String line1 = Reader0.readLine();
                String line2 = Reader1.readLine();
                if ( line1.equals("2 4") && line2.equals("*")){
                    return true;
                }
            }else{
                BufferedReader Reader0 = new BufferedReader(new FileReader(OutputFiles.get(0) ));
                BufferedReader Reader1 = new BufferedReader(new FileReader(OutputFiles.get(1) ));
                BufferedReader Reader2 = new BufferedReader(new FileReader(OutputFiles.get(2) ));
                String line1 = Reader0.readLine();
                String line2 = Reader1.readLine();
                String line3 = Reader2.readLine();
                if (line1.equals("C") && line2.equals("*") && line3.equals("A")){
                    return true;
                }
            }

        }catch (Exception e){
            return false;
        }
        return false;
    }

}
