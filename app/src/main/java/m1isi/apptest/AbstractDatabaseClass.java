package m1isi.apptest;


import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Sylvain LOIZEAU on 3/3/2016.
 */
public abstract class AbstractDatabaseClass extends AsyncTask<String, Integer, Void> {
    private static boolean _DEBUG = true;

    protected Connection dbConn = null;
    protected String query = null;
    protected Statement st = null;
    protected ResultSet rs = null;

    protected boolean isQuery = false;
    protected boolean isUpdate = false;

    //TODO Put them in a class that can change them
    protected static String adr = "164.132.198.97";
    protected static String dbName = "ProjetM1";
    protected static String dbUser = "ProjetM1";
    protected static String dbPwd = "ProjetM1";

    protected void getConnection(){
        try {
            if(_DEBUG){System.out.println("Loading driver");}
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            if(_DEBUG){System.out.println("Driver loaded");}
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            if(_DEBUG){System.out.println("Trying to connect to: jdbc:mysql://" + adr + ":3306/" + dbName + "?user=" + dbUser + "&password=" + dbPwd);}
            dbConn = DriverManager.getConnection("jdbc:mysql://" + adr + ":3306/" + dbName + "?user=" + dbUser + "&password=" + dbPwd);
            if(_DEBUG){System.out.println("Connected!");}
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void close(){
        try {
            if(dbConn != null){
                if(_DEBUG){System.out.println("[AbstractDatabaseClass]Closing dbConn");}
                dbConn.close();
            }
            if(rs != null){
                if(_DEBUG){System.out.println("[AbstractDatabaseClass]Closing rs");}
                rs.close();
            }
            if(st != null){
                if(_DEBUG){System.out.println("[AbstractDatabaseClass]Closing st");}
                st.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values){
        super.onProgressUpdate(values);
    }

    @Override
    protected Void doInBackground(String... querys) {
        getConnection();
        try {
            Statement st = dbConn.createStatement();
            if(isQuery){
                rs = st.executeQuery(query);
            }else if(isUpdate){
                st.executeUpdate(query);
            }else{
                System.err.println("[AbstractDatabaseClass]ERROR: No type selected for query\""+query+"\"");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(_DEBUG){System.out.println("Executed");}
        return(null);
    }

    @Override
    protected void onPostExecute(Void result) {
        close();
    }

    protected void finalize() throws Throwable {
        try {
            if(dbConn != null){
                System.err.println("[AbstractDatabaseClass]ERROR: dbConn isn't null in finalize!");
                dbConn.close();
            }
            if(rs != null){
                System.err.println("[AbstractDatabaseClass]ERROR: rs isn't null in finalize!");
                rs.close();
            }
            if(st != null){
                System.err.println("[AbstractDatabaseClass]ERROR: st isn't null in finalize!");
                st.close();
            }
        } finally {
            super.finalize();
        }
    }
}

