package m1isi.apptest;

import java.sql.SQLException;

/**
 * Created by Sylvain LOIZEAU on 3/8/2016.
 */
public class ProjetSelectionDB extends AbstractDatabaseClass {
    private enum functionCalled{eGetNumberOfProjet, eGetProjet}
    final static boolean _DEBUG = true;

    ProjetSelection caller;

    private functionCalled fc;

    public ProjetSelectionDB(ProjetSelection caller){
        this.caller = caller;
    }

    public void getNumberOfProject(){
        query = "SELECT COUNT(*) FROM t_projet";
        isQuery = true;
        fc = functionCalled.eGetNumberOfProjet;

        execute();
    }

    @Override
    protected void onPostExecute(Void result) {
        try {
            switch (fc){
                case eGetNumberOfProjet:
                    if(rs.next()){
                        caller.getNumberOfProjectResult(Integer.parseInt(rs.getString(1)));
                    }else{
                        caller.getNumberOfProjectResult(0);
                    }
                    break;
                case eGetProjet:
                    break;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
