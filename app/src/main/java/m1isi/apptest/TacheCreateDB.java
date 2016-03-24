package m1isi.apptest;

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

    public void eAddTache(ItemTache myTache){
        query = "INSERT INTO t_tache (tac_titre, tac_desc, tac_duree, tac_dateD, tac_F, id_statuttache) ";
        query = query + " VALUES (";
        query = query + "'" + myTache.tac_titre + "',";
        query = query + "'" + myTache.tac_desc + "',";
        query = query + myTache.tac_duree + ",";
        query = query + myTache.tac_dateD + ",";
        query = query + myTache.tac_dateF + ",";
        query = query + myTache.id_statuttache + ")";
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
