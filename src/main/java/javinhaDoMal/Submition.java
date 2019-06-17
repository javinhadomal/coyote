package javinhaDoMal;
import java.io.IOException;
import java.nio.*;
import java.nio.file.Files;
import java.util.*;

import org.apache.commons.codec.binary.Base64;
import java.io.*;
import java.nio.file.Paths;
import java.util.Date;

public class Submition{

    private Date dateAndHourExecution;
    private String nameFile;
    private String idProblem;
    private String sourceCode;

    public Submition(String nameFile, String idProblem, String sourceCode) {
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

    public boolean runSourceCode() throws IOException {
        // creating py file

        String absolutePath = new File("any").getAbsolutePath().replace("any", "") + this.nameFile;
        String absoluteAEntry = new File("any").getAbsolutePath().replace("any", "")+"arqEntradaCasoTeste1.txt";

        Files.write( Paths.get(this.nameFile) ,   java.util.Base64.getDecoder().decode(this.sourceCode) );
        Process p = new ProcessBuilder("python3" , absolutePath , String.format("<"+absoluteAEntry)).start();


        this.writeOutPutInTextFile(p);

        return false;
    }

    // Classe que escreverá o arquivo com o output do processo gerado pela classe runSourceCode
    private void writeOutPutInTextFile(Process p) throws IOException {

        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(p.getInputStream()));

        BufferedWriter writerOutPutFile = new BufferedWriter(new FileWriter("arqSaidaCasoTeste1.txt"));

        String lineOutPutProcess;

        while ( (  lineOutPutProcess = stdInput.readLine() ) != null ) {
            writerOutPutFile.write( lineOutPutProcess );
        }

        writerOutPutFile.close();

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(p.getErrorStream()));

        while ( ( lineOutPutProcess = stdError.readLine() ) != null ) {
            System.out.println(lineOutPutProcess);
        }
    }
}
