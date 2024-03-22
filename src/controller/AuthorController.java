package controller;

import database.ConfigDB;
import entity.Author;
import entity.Book;
import model.AuthorModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorController {


    public static void create(){
        AuthorModel objAuthorModel = new AuthorModel();

        String name = JOptionPane.showInputDialog("Insert name");
        String nationality = JOptionPane.showInputDialog("Insert nationality");

        Author objAuthor = new Author();
//          Create an instance of author
        objAuthor.setName(name);
        objAuthor.setNationality(nationality);


        // Call the method of intersection and save the object thar return on author previously instantiated, we have to cast it
        objAuthor = (Author) objAuthorModel.insert(objAuthor);

        JOptionPane.showMessageDialog(null, objAuthor.toString());

    }

    public static void getAll(){
        AuthorModel objAuthorModel = new AuthorModel();
        String listAuthor = "\n AUTHOR LIST \n";
        for (Object ite : objAuthorModel.findAll()){
            // Become the Object to coder
            Author objAuthor = (Author) ite;
            listAuthor += objAuthor.toString() +"\n";
        }
        JOptionPane.showMessageDialog(null, listAuthor);
    }

    public static String getAllString(){
        AuthorModel objAuthorModel = new AuthorModel();
        String listAuthor = "\n Author LIST \n";
        for (Object ite : objAuthorModel.findAll()){
            // Become the Object to coder
            Author objAuthor = (Author) ite;
            listAuthor += objAuthor.toString() +"\n";
        }
        return listAuthor;
    }

    public static void delete(){
        AuthorModel objAuthorModel = new AuthorModel();

        String listAuthors = getAllString();

        int idDeleted = Integer.parseInt(JOptionPane.showInputDialog(listAuthors +  "\n  Enter the ID of the author to delete"));

        Author objAuthor = objAuthorModel.findById(idDeleted);
        int confirm = 1;
        if(objAuthor == null){
            JOptionPane.showMessageDialog(null, "Author not found");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Are you sure want to delete the author? \n" + objAuthor.getName());
            if (confirm == 0){
                objAuthorModel.delete(objAuthor);
                System.out.println("Success");
            }
        }
    }

    public static void update() {
        AuthorModel objAuthorModel = new AuthorModel();

        String listAuthor = getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listAuthor + "\n Enter the id author to edit"));

        Author objAuthor = objAuthorModel.findById(idUpdate);

        if (objAuthor == null) {
            JOptionPane.showMessageDialog(null, "Coder not found");

        } else {

            String name = JOptionPane.showInputDialog(null, "Enter the new name:", objAuthor.getName());
            String nationality = JOptionPane.showInputDialog(null, "Enter the new nationality:", objAuthor.getNationality());


            objAuthor.setName(name);
            objAuthor.setNationality(nationality);

            objAuthorModel.update(objAuthor);
        }
    }
    public static void findById(){
        AuthorModel objAuthorModel = new AuthorModel();
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the Author's ID"));
        String listAuthor = getAllString();
        Author objAuthor = objAuthorModel.findById(id);
        if(objAuthor == null){
            JOptionPane.showMessageDialog(null, "Author not found");
        }else{
            JOptionPane.showMessageDialog(null, "Author found" + objAuthor);
        }
    }

    public static void findBooksWritten(){
        AuthorModel objAuthorModel = new AuthorModel();
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the Author's ID"));
        String listAuthor = "\n Books Written \n";




        for(Book ite : objAuthorModel.findBooks(id)){


            listAuthor += ite.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listAuthor);

    }
}


