package model;

import database.CRUD;
import database.ConfigDB;
import entity.Author;
import entity.Book;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Book objBook = (Book) obj;

        try {
            String sql = "INSERT INTO book (titulo, fecha_publi, precio, id_autor) VALUES (? ,?, ?, ?);";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);


            objPrepared.setString(1,objBook.getTitle());
            objPrepared.setString(2,objBook.getReleased());
            objPrepared.setDouble(3, objBook.getPrice());
            objPrepared.setInt(4, objBook.getIdAuthor());


            objPrepared.execute();

            ResultSet objResult =objPrepared.getGeneratedKeys();

            while(objResult.next()){
                objBook.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "CODER INSERTION WAS SUCCESSFULLY");



        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR >>>> " + e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }


        return objBook;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listBook = new ArrayList<>();
        // Open the connection
        Connection objConnection = ConfigDB.openConnection();

        try {
            // 3. Enter the query of sql
            String sql = "SELECT * FROM book;";
            // 4. Use the prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            // 5. Execute the query and get the result (ResultSet)
            ResultSet objResult = objPrepare.executeQuery();
            // 6. While there is a next result, do
            while (objResult.next()){
                // 6.1 Create a coder
                Book objBook = new Book();
                objBook.setId(objResult.getInt("id"));
                objBook.setTitle(objResult.getString("titulo"));
                objBook.setReleased(objResult.getString("fecha_publi"));
                objBook.setPrice(objResult.getDouble("precio"));
                objBook.setIdAuthor(objResult.getInt("id_autor"));
                listBook.add(objBook);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error >>> " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listBook;
    }

    @Override
    public boolean update(Object obj) {


        Connection objConnection = ConfigDB.openConnection();

        Book objBook = (Book) obj;

        boolean isUpdated = false;
        try {
            String sql = "update book set titulo = ?, fecha_publi = ?, precio = ?, id_autor = ? where id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setString(1, objBook.getTitle());
            objPrepared.setString(2, objBook.getReleased());
            objPrepared.setDouble(3, objBook.getPrice());
            objPrepared.setInt(4, objBook.getIdAuthor());
            objPrepared.setInt(5, objBook.getId());



            int totalRowAffected = objPrepared.executeUpdate();

            if (totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Updated successfully");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error >>>> " + e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        // Convert the object to the entity
        Book objBook = (Book) obj;
        // Open the connection
        Connection objConnection = ConfigDB.openConnection();
        // Open a variable of status
        boolean isDeleted = false;
        try {
            // Enter the statement SQL
            String sql = "DELETE FROM book WHERE id = ?";
            // Create the prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            // Give value to ?
            objPrepare.setInt(1, objBook.getId());
            // 7. Execute the query (executeUpdate) return the quantity of data affected
            int totalAffectedRows = objPrepare.executeUpdate();
            if (totalAffectedRows > 0 ){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The update was successfully");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error >>>" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDeleted;
    }

    public static Book findById(int id){
        // Open the connection

        Connection objConnection = ConfigDB.openConnection();
        Book objBook = null;
        try{                                         // ? = query parameter
            String sql = "SELECT * FROM book WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //
            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();

            if(objResult.next()){
                objBook = new Book();
                objBook.setTitle(objResult.getString("titulo"));
                objBook.setReleased(objResult.getString("fecha_publi"));
                objBook.setId(objResult.getInt("id"));
                objBook.setPrice(objResult.getDouble("precio"));
                objBook.setIdAuthor(objResult.getInt("id_autor"));
            }
            ConfigDB.closeConnection();

        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR >>>" + e.getMessage());
        }

        return objBook;
    }


    public static Book findByTitle(String title){

        Connection objConnection = ConfigDB.openConnection();

        Book objBook = null;

        try {
            String sql = "SELECT * FROM book where titulo like ?;";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setString(1, "%" + title+ "%");

            ResultSet objResult = objPrepared.executeQuery();

            if(objResult.next()){
                objBook = new Book();

                objBook.setId(objResult.getInt("id"));
                objBook.setTitle(objResult.getString("titulo"));
                objBook.setReleased(objResult.getString("fecha_publi"));
                objBook.setPrice(objResult.getDouble("precio"));
                objBook.setIdAuthor(objResult.getInt("id_autor"));
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR >>>" + e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
    return objBook;
    }

    public static Book findByAuthor(int id){

        Book objBook = null;

        Connection objConnection = ConfigDB.openConnection();


        try{

            String sql = "SELECT * FROM book INNER JOIN author ON book.id_autor = ?;";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setInt(1, id);

            ResultSet objResult = objPrepared.executeQuery();

            if(objResult.next()) {

                objBook = new Book();
                objBook.setId(objResult.getInt("id"));
                objBook.setTitle(objResult.getString("titulo"));
                objBook.setReleased(objResult.getString("fecha_publi"));
                objBook.setPrice(objResult.getDouble("precio"));
                objBook.setIdAuthor(objResult.getInt("id_autor"));

            }

        }catch (SQLException e){

            JOptionPane.showMessageDialog(null,"Error >>>" + e.getMessage());

        }finally {
            ConfigDB.closeConnection();
        }
return objBook;
    }
}
