

package m1isi.apptest;

import java.sql.SQLException;

/**
 * Created by Sylvain LOIZEAU on 3/3/2016.
 */
public class BestSplashScreenDB extends AbstractDatabaseClass  {
    final static boolean _DEBUG = true;

    BestSplashScreen caller;

    private enum functionCalled{eTestId};
    private functionCalled fc;

    public BestSplashScreenDB(BestSplashScreen caller){
        this.caller = caller;
    }

    public void testId(final String name, final String password){
        fc = functionCalled.eTestId;
        query = " SELECT * FROM t_identifiant "
                + " WHERE id_username='" + name + "' "
                + " AND id_password='" + password + "';";
        isQuery = true;
        if(_DEBUG){ System.out.println(query); }
        execute();
    }

    @Override
    protected void onPostExecute(Void result) {
        switch (fc){
            case eTestId:
                try {
                    if(rs == null){
                        if(_DEBUG){ System.out.print("error\n"); }
                        caller.testIdError();
                    }else if(rs.first()){
                        if(_DEBUG){ System.out.print("true\n"); }
                        caller.testIdValide(true);
                    }else{
                        if(_DEBUG){ System.out.print("false\n"); }
                        caller.testIdValide(false);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }

    }
}