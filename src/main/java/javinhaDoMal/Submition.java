package javinhaDoMal;

import org.apache.commons.codec.binary.Base64;
import org.json.*;

import java.util.Date;

public class Submition{

    private Date dateAndHourExecution;
    private String nameFile;
    private String idProblem;
    private byte[] sourceCode;

    public Submition(String nameFile, String idProblem, byte[] sourceCode) {
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

    public byte[] getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(byte[] sourceCode) {
        this.sourceCode = sourceCode;
    }
}
