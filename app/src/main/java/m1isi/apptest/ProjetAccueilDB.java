package m1isi.apptest;

import java.sql.SQLException;

/**
 * Created by godef on 24/03/2016.
 */
public class ProjetAccueilDB extends AbstractDatabaseClass  {
    final static boolean _DEBUG = true;
    ProjetAccueil caller;
    private enum functionCalled{searchProjet};
    private functionCalled fc;
    private int id_projet;

    public ProjetAccueilDB(ProjetAccueil caller, int id_projet){
        this.caller = caller;
        this.id_projet = id_projet;
    }

    public void searchProjet(){
        fc = functionCalled.searchProjet;

        query = " SELECT * FROM t_projet "
                + " WHERE id_projet = " + id_projet;
        isQuery = true;
        if(_DEBUG){ System.out.println(query); }
        execute();
    }



    @Override
    protected void onPostExecute(Void result) {
        switch (fc){
            case searchProjet:
                ItemProjet myItem = new ItemProjet();
                if (id_projet == 0) {
                    if(_DEBUG){ System.out.print("error : id_projet = 0 \n"); }
                    caller.populateProjet(myItem);
                }
                try {
                    if(rs == null){
                        if(_DEBUG){ System.out.print("error\n"); }

                    }else if(rs.next()){
                        if(_DEBUG){ System.out.print("find one\n"); }

                        myItem.id_projet = id_projet;
                        myItem.pro_titre = rs.getString("pro_titre");
                        myItem.pro_desc = rs.getString("pro_desc");
                        myItem.pro_dateD = rs.getDate("pro_dateD");
                        myItem.pro_dateFP = rs.getDate("pro_dateFP");
                        myItem.pro_budgetD = rs.getFloat("pro_budgetD");
                        myItem.pro_budgetFP = rs.getFloat("pro_budgetFP");
                        caller.populateProjet(myItem);
                    }else{

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

        }

    }
}
