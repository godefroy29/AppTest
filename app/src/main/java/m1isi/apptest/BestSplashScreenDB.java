

package m1isi.apptest;


        import android.app.Activity;

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

    public void testId(){
        fc = functionCalled.eTestId;
        query = " SELECT * FROM t_identifiant "
                + " WHERE id_username like '" + caller.txtName.getText() + "' "
                + " AND id_password like '" + caller.txtPwd.getText() + "';";
        isQuery = true;
        System.out.println(query);
        execute();
    }

    @Override
    protected void onPostExecute(Void result) {
        switch (fc){
            case eTestId:
                try {
                    if(rs.first()){
                        System.out.print("true\n");
                        caller.testIdValide(true);
                    }else{
                        System.out.print("false\n");
                        caller.testIdValide(false);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }

    }
}