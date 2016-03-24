package m1isi.apptest;

import java.sql.Date;

/**
 * Created by godef on 24/03/2016.
 */
public class TacheCreateDB  extends AbstractDatabaseClass {
    private enum functionCalled{eAddTache}
    final static boolean _DEBUG = true;

    TacheCreate caller;

    private functionCalled fc;

    public TacheCreateDB(TacheCreate caller){
        this.caller = caller;
    }

    public void eAddTache(int id_identifiant, int id_projet, String tac_titre, String tac_desc, String tac_duree, Date tac_dateD, Date tac_dateF,
                          int id_statuttache){
        query = "INSERT INTO t_tache (id_projet, id_identifiant, tac_titre, tac_desc, tac_duree, tac_dateD, tac_F, id_statuttache) ";
        query = query + " VALUES (";
        query = query + "'" + id_projet + "',";
        query = query + "'" + id_identifiant + "',";
        query = query + "'" + tac_titre + "',";
        query = query + "'" + tac_desc + "',";
        query = query + tac_duree + ",";
        query = query + tac_dateD + ",";
        query = query + tac_dateF + ",";
        query = query + id_statuttache + ")";
        query = query + ";";
        isInsert = true;
        fc = functionCalled.eAddTache;

        execute();
    }

    @Override
    protected void onPostExecute(Void result) {
        try {
            switch (fc){
                case eAddTache:
                    caller.eAddTacheDone();
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
