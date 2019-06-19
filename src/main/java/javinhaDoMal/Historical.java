package javinhaDoMal;

import java.util.ArrayList;

public class Historical {
    private ArrayList<Submission> allSubmissions = new ArrayList<Submission>();

    public void appendSubmition( Submission e){
        this.allSubmissions.add(e);
    }
}
