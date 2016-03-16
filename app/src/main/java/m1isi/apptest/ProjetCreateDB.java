package m1isi.apptest;

import java.sql.SQLException;

/**
 * Created by Godefroy on 14/03/2016.
 */
public class ProjetCreateDB extends AbstractDatabaseClass {
    private enum functionCalled{eAddProjet}
    final static boolean _DEBUG = true;

    ProjetCreate caller;

    private functionCalled fc;

    public ProjetCreateDB(ProjetCreate caller){
        this.caller = caller;
    }

    public void eAddProjet(ItemProjet myProject){
        query = "INSERT INTO t_projet (pro_titre, pro_desc, pro_dateD, pro_dateFP, pro_budgetD, pro_budgetFP) ";
        query = query + " VALUES (";
        query = query + "'" + myProject.pro_titre + "',";
        query = query + "'" + myProject.pro_desc + "',";
        query = query + myProject.pro_dateD + ",";
        query = query + myProject.pro_dateFP + ",";
        query = query + myProject.pro_budgetD + ",";
        query = query + myProject.pro_budgetFP + ")";
        query = query + ";";
        isInsert = true;
        fc = functionCalled.eAddProjet;

        execute();
    }

    @Override
    protected void onPostExecute(Void result) {
        try {
            switch (fc){
                case eAddProjet:
                    caller.eAddProjetDone();
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}