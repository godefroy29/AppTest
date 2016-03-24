package m1isi.apptest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by godef on 08/03/2016.
 */
public class AccueilDB extends AbstractDatabaseClass{
    private int id_identifiant;
    private enum functionCalled{eGetProjet,eGetTache}
    final static boolean _DEBUG = true;

    Accueil caller;

    private functionCalled fc;

    public AccueilDB(Accueil caller, int id_identifiant){
        this.caller = caller;
        this.id_identifiant = id_identifiant;
    }

    public void eGetProjet(){
        query = "SELECT * ";
        query = query + " FROM t_projet ";
        query = query + " JOIN p_statutprojet ON t_projet.id_statutprojet = p_statutprojet.id_statutprojet ";
        query = query + " JOIN r_pi ON t_projet.id_projet = r_pi.id_projet ";
        query = query + " WHERE r_pi.id_identifiant = " + id_identifiant;
        isQuery = true;
        fc = functionCalled.eGetProjet;
        execute();
    }

    public void eGetTache(){
        query = "SELECT * FROM t_tache JOIN p_statuttache ON t_tache.id_statuttache = p_statuttache.id_statuttache WHERE t_tache.id_identifiant = " + id_identifiant;
        isQuery = true;
        fc = functionCalled.eGetTache;
        execute();
    }


    @Override
    protected void onPostExecute(Void result) {
        try {
            switch (fc){
                case eGetProjet:
                    ArrayList<ItemProjet> myList = new ArrayList<ItemProjet>();
                    if (rs == null){
                        caller.setListItemProjet(myList);
                        new AccueilDB(caller, id_identifiant).eGetTache();
                        break;
                    }
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
                    new AccueilDB(caller, id_identifiant).eGetTache();
                    break;

                case eGetTache:
                    ArrayList<ItemTache> myListTache = new ArrayList<ItemTache>();
                    while(rs.next()){
                        ItemTache myItem = new ItemTache();
                        myItem.id_tache = rs.getInt("id_tache");
                        myItem.tac_titre = rs.getString("tac_titre");
                        myItem.tac_desc = rs.getString("tac_desc");
                        myItem.tac_dateD = rs.getDate("tac_dateD");
                        myItem.tac_dateF = rs.getDate("tac_dateF");
                        myItem.tac_duree = rs.getFloat("tac_duree");
                        myItem.id_statuttache = rs.getInt("id_statuttache");
                        myItem.statuttache_desc = rs.getString("statuttache_desc");
                        myListTache.add(myItem);
                    }
                    caller.setListItemTache(myListTache);
                    break;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
