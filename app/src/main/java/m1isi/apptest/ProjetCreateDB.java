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
        query = "SELECT COUNT(*) FROM t_projet";
        isQuery = true;
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