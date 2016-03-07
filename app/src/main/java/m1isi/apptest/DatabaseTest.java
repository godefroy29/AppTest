package m1isi.apptest;


import android.app.Activity;

import java.sql.SQLException;

/**
 * Created by Sylvain LOIZEAU on 3/3/2016.
 */
public class DatabaseTest extends AbstractDatabaseClass  {
    final static boolean _DEBUG = true;

    MainActivity caller;

    public DatabaseTest(MainActivity caller){
        this.caller = caller;
    }

    public void getNumberOfProject(){
        query = "SELECT COUNT(*) FROM t_projet";
        isQuery = true;

        execute();
    }

    @Override
    protected void onPostExecute(Void result) {
        try {
            if(rs.next()){
                caller.getResult(rs.getString(1));
            }else{
                caller.getResult(null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}