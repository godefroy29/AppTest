

package m1isi.apptest;

import java.sql.SQLException;

/**
 * Created by Sylvain LOIZEAU on 3/3/2016.
 */

public class CreateUserDB extends AbstractDatabaseClass  {
    final static boolean _DEBUG = true;

    CreateUser caller;

    private enum functionCalled{eTestId, eCreateId, eValidateId};
    private functionCalled fc;

    private String login;
    private String password;

    public CreateUserDB(CreateUser caller){
        this.caller = caller;
    }

    public void testId(final String login, final String password){
        fc = functionCalled.eTestId;

        this.login = login;
        this.password = password;

        query = " SELECT * FROM t_identifiant "
                + " WHERE id_username='" + login + "';";
        isQuery = true;
        if(_DEBUG){ System.out.println(query); }
        execute();
    }

    public void createUser(final String login, final String password){
        fc = functionCalled.eCreateId;

        this.login = login;
        this.password = password;

        query = " INSERT INTO t_identifiant (id_identifiant, id_username, id_password, id_collaborateur) " +
                "VALUES (DEFAULT, '"+ login +"', '"+ password +"', 0)";
        isUpdate = true;
        if(_DEBUG){ System.out.println(query); }
        execute();
    }

    public void validateId(final String login, final String password){
        fc = functionCalled.eValidateId;

        this.login = login;
        this.password = password;

        query = " SELECT * FROM t_identifiant "
                + " WHERE id_username='" + login + "';";
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
                    }else if(rs.next()){
                        if(_DEBUG){ System.out.print("already exist\n"); }
                        caller.idAlreadyExist();
                    }else{
                        new CreateUserDB(caller).createUser(login, password);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;


            case eCreateId:
                new CreateUserDB(caller).validateId(login, password);
                break;


            case eValidateId:
                try {
                    if(rs == null){
                        if(_DEBUG){ System.out.print("error\n"); }
                        caller.testIdError();
                    }else if(rs.next()){
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