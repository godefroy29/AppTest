package m1isi.apptest;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by godef on 08/03/2016.
 */
public class AccueilDB extends AbstractDatabaseClass{
    private enum functionCalled{eGetProjet}
    final static boolean _DEBUG = true;

    Accueil caller;

    private functionCalled fc;

    public AccueilDB(Accueil caller){
        this.caller = caller;
    }

    public void eGetProjet(){
        query = "SELECT * FROM t_projet JOIN p_statutprojet ON t_projet.id_statutprojet = p_statutprojet.id_statutprojet";
        isQuery = true;
        fc = functionCalled.eGetProjet;

        System.out.println("PLOP");
        execute();
    }

    @Override
    protected void onPostExecute(Void result) {
        try {
            switch (fc){
                case eGetProjet:
                    ArrayList<ItemProjet> myList = new ArrayList<ItemProjet>();
                    while(rs.next()){
                        ItemProjet myItem = new ItemProjet();
                        myItem.id_projet = rs.getInt("id_projet");
                        myItem.pro_titre = rs.getString("pro_titre");
                        myItem.pro_desc = rs.getString("pro_desc");
                        myItem.pro_dateD = rs.getDate("pro_dateD");
                        myItem.pro_dateFP = rs.getDate("pro_dateFP");
                        myItem.pro_dateF = rs.getDate("pro_dateF");
                        myItem.pro_budgetD = rs.getFloat("pro_budgetD");
                        myItem.pro_budgetFP = rs.getFloat("pro_budgetFP");
                        myItem.pro_budgetF = rs.getFloat("pro_budgetF");
                        myItem.id_statutprojet = rs.getInt("id_statutprojet");
                        myItem.statutprojet_desc = rs.getString("statutprojet_desc");
                        myList.add(myItem);
                        System.out.print(myItem.id_projet);
                    }
                    caller.setListItemProjet(myList);
                    break;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
