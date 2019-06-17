package javinhaDoMal;

import java.util.ArrayList;

public class Historical {
    private ArrayList<Submition> AllSubmitions = new ArrayList<Submition>();

    public void appendSubmition( Submition e){
        this.AllSubmitions.add(e);
    }
}
