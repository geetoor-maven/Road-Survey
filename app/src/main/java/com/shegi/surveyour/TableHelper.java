package com.shegi.surveyour;

import android.content.Context;
import com.shegi.surveyour.model.spaceCrafts;
import java.util.ArrayList;

public class TableHelper{

    Context c;
    public   String []spaceProbeHeaders={"no","masuk","exp"};
    private String[][] spaceProbes;



    public TableHelper(Context c){
        this.c = c;
    }
    public String []getSpaceProbeHeaders(){

        return spaceProbeHeaders;
    }

    public String[][] returnSpaceProbesArray(ArrayList<spaceCrafts> spacecrafts){
        spaceProbes= new String[spacecrafts.size()][3];
        spaceCrafts s;




        for (int i=0;i<spacecrafts.size();i++) {
            s = spacecrafts.get(i);
            spaceProbes[i][0] = s.getId();
            spaceProbes[i][1] = s.getTgl_masuk();
            spaceProbes[i][2] = s.getTgl_exp();




        }

        return spaceProbes;
    }
}
