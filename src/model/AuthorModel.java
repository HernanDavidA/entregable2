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

public class AuthorModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Author objAuthor = (Author) obj;

        try {
            String sql = "INSERT INTO author (nombre, nacionalidad) VALUES (? ,?);";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);


            objPrepared.setString(1,objAuthor.getName());
            objPrepared.setString(2,objAuthor.getNationality());

            objPrepared.execute();

            ResultSet objResult =objPrepared.getGeneratedKeys();

            while(objResult.next()){
                objAuthor.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "CODER INSERTION WAS SUCCESSFULLY");



        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR >>>> " + e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }


        return objAuthor;
    }

    @Override
    public List<Object> findAll() {

        List<Object> listAuthors = new ArrayList<>();
        // Open the connection
        Connection objConnection = ConfigDB.openConnection();

        try {
            // 3. Enter the query of sql
            String sql = "SELECT * FROM author;";
            // 4. Use the prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            // 5. Execute the query and get the result (ResultSet)
            ResultSet objResult = objPrepare.executeQuery();
            // 6. While there is a next result, do
            while (objResult.next()){
                // 6.1 Create a coder
                Author objAuthor = new Author();

                objAuthor.setName(objResult.getString("nombre"));

                objAuthor.setId(objResult.getInt("id"));
                objAuthor.setNationality(objResult.getString("nacionalidad"));
                listAuthors.add(objAuthor);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error >>> " + e.getMessage());

        }
        ConfigDB.closeConnection();
        return listAuthors;


    }

    @Override
    public boolean delete(Object obj) {
        // Convert the object to the entity
        Author objAuthor = (Author) obj;
        // Open the connection
        Connection objConnection = ConfigDB.openConnection();
        // Open a variable of status
        boolean isDeleted = false;
        try {
            // Enter the statement SQL
            String sql = "DELETE FROM author WHERE id = ?";
            // Create the prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            // Give value to ?
            objPrepare.setInt(1, objAuthor.getId());
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

    public static Author findById(int id){
        // Open the connection

        Connection objConnection = ConfigDB.openConnection();
        Author objAuthor = null;
        try{                                         // ? = query parameter
            String sql = "SELECT * FROM author WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //
            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();

            if(objResult.next()){
                objAuthor = new Author();
                objAuthor.setName(objResult.getString("nombre"));
                objAuthor.setNationality(objResult.getString("nacionalidad"));
                objAuthor.setId(objResult.getInt("id"));

            }
            ConfigDB.closeConnection();

        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR >>>" + e.getMessage());
        }

        return objAuthor;
    }

    @Override
    public boolean update(Object obj) {

        Connection objConnection = ConfigDB.openConnection();

        Author objAuthor = (Author) obj;

        boolean isUpdated = false;
        try {
            String sql = "update author set nombre = ?, nacionalidad = ? where id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setString(1, objAuthor.getName());
            objPrepared.setString(2, objAuthor.getNationality());
            objPrepared.setInt(3, objAuthor.getId());

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

    public static List<Book> findBooks(int id){
        Connection objConnection = ConfigDB.openConnection();
        Book objBook = null;

        List<Book> objBooksWritten = new ArrayList<>();

        try{
            String sql = "SELECT book.titulo, book.fecha_publi FROM author INNER JOIN book ON ? = author.id;";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setInt(1, id);

            ResultSet objResult = objPrepared.executeQuery();

            while (objResult.next()){
                objBook = new Book();


                objBook.setTitle(objResult.getString("titulo"));
                objBook.setReleased(objResult.getString("fecha_publi"));


                objBooksWritten.add(objBook);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "ERROR >>> " + e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return objBooksWritten;
    }
}
