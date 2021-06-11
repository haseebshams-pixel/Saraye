/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saraye;

/**
 *
 * @author Haseeb
 */
import java.sql.*;

public class Database {

    String conn_string = "jdbc:sqlserver://localhost:1433;databaseName=se;integratedSecurity=true";
    String user = "";
    String pass = "";
    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    Connection get_conn(){
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(conn_string, user, pass);
            return conn;
        } catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    boolean login(String username, String password,int type){

        Connection conn = get_conn();
        int output = -1;

        if(conn!=null){
            try {
                CallableStatement call = conn.prepareCall("{call USER_LOGIN(?,?,?,?)}");
                call.setString(1, username);
                call.setString(2, password);
                call.setInt(3,type);
                call.registerOutParameter(4, Types.INTEGER);
                call.executeQuery();

                output = call.getInt(4);

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        try {
            conn.close();
        } catch(Exception e) {
            System.out.println(e);
        }

        if(output==1){
            return true;
        } else {
            return false;
        }
    }

}
