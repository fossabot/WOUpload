package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
//import static lng.LNG.ipAddress;


public class DataBase {

    
    //Esta clase es para realizar la conexión/desconexión de la Base de Datos cada vez que se quiera realizar una transacción.
    private Connection connection = null;
    //private final String hostName = "8zvcgx1";
    private  String db = "mv_bom";
    private  String user = "mvcr_systems";
    private  String password = "mvapps@cr#";
     private  String server = "tus-sql3";

     public DataBase(String country){
         if(country.equals("CR")){
             db = "mv_bom";
             user = "mvcr_systems";
             password = "mvapps@cr#";
             server = "sjo-sql1";
         }else{
              db = "mv_bom";
             user = "mvcr_systems";
             password = "mvapps@cr#";
             server = "tus-sql3";
         }
         
     }
     
    public Connection connect() {
        try {
            
           //Class.forName("com.mysql.jdbc.Driver");
            //connection = DriverManager.getConnection("jdbc:mysql://" + ipAddress + "/" + db, user, password);
            //System.out.println("Connection Succesfull");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver") /*Microsoft*/;
            //System.out.println("jdbc:sqlserver://" + hostName + ";databaseName=" + db + user + password);
            connection = DriverManager.getConnection("jdbc:sqlserver://" + server + ";databaseName=" + db, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException = " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQLException = " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("NullPointerException = " + e.getMessage());
            JOptionPane.showMessageDialog(null, "DataBase Error, check connection", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return connection;
    }


    public void disconnect() throws SQLException {
        connection.close();
    }

}