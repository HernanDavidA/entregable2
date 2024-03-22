package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {

    // This attribute save the status of the connection
static Connection objConnection = null;
// Open the connection
public static Connection openConnection(){
    try{
        // Call upon the driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Enter the direction of the database
        String url = "jdbc:mysql://biigf9dkvytgbggjkmcg-mysql.services.clever-cloud.com:3306/biigf9dkvytgbggjkmcg";
        String user = "uryxqczrtwrs0rah";
        String password = "tOObEvxwvxzOE4fzzNHM";
        //Establish the connection
        objConnection= (Connection) DriverManager.getConnection(url, user, password);


    }catch (SQLException e){

    System.out.println("Error >>>>" + e.getMessage() );

    } catch (ClassNotFoundException e) {

        System.out.println("Error >>>" + e.getMessage());
    }
    return  objConnection;

}
public static void closeConnection(){
    try {
        if(objConnection != null)objConnection.close();


    } catch (SQLException e) {
        System.out.println("Error >>>>"+ e.getMessage());
    }

}
}
