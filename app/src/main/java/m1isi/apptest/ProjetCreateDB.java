package m1isi.apptest;

import java.sql.SQLException;

/**
 * Created by Godefroy on 14/03/2016.
 */
public class ProjetCreateDB extends AbstractDatabaseClass {
    private enum functionCalled{eAddProjet}
    final static boolean _DEBUG = true;
    private int id_identifiant;
    ProjetCreate caller;

    private functionCalled fc;

    public ProjetCreateDB(ProjetCreate caller, int id_identifiant){
        this.caller = caller;
        this.id_identifiant = id_identifiant;
    }

    public void eAddProjet(ItemProjet myProject){
        query = "INSERT INTO t_projet (pro_titre, pro_desc, pro_dateD, pro_dateFP, pro_budgetD, pro_budgetFP, id_identifiant) ";
        query = query + " VALUES (";
        query = query + "'" + myProject.pro_titre + "',";
        query = query + "'" + myProject.pro_desc + "',";
        query = query + myProject.pro_dateD + ",";
        query = query + myProject.pro_dateFP + ",";
        query = query + myProject.pro_budgetD + ",";
        query = query + myProject.pro_budgetFP + ",";
        query = query + id_identifiant + ")";
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