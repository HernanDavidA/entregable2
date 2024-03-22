package controller;

import entity.Author;
import entity.Book;
import model.AuthorModel;
import model.BookModel;

import javax.swing.*;

public class BookController {
    public static void create(){
        BookModel objAuthorModel = new BookModel();

        String title = JOptionPane.showInputDialog("Insert title");
        String released = JOptionPane.showInputDialog("Insert released");
        double price = Double.parseDouble(JOptionPane.showInputDialog("Insert price of the book"));
        int idAuthor = Integer.parseInt(JOptionPane.showInputDialog( getAllStringAuthor() +" \n Insert the id of the author"));


        Book objBook = new Book();
//          Create an instance of author
        objBook.setTitle(title);
        objBook.setReleased(released);
        objBook.setPrice(price);
        objBook.setIdAuthor(idAuthor);


        // Call the method of intersection and save the object thar return on author previously instantiated, we have to cast it
        objBook = (Book) objAuthorModel.insert(objBook);

        JOptionPane.showMessageDialog(null, objBook.toString());

    }
    public static String getAllStringAuthor(){
        AuthorModel objAuthorModel = new AuthorModel();
        String listAuthor = "\n Author LIST \n";
        for (Object ite : objAuthorModel.findAll()){
            // Become the Object to coder
            Author objAuthor = (Author) ite;
            listAuthor += objAuthor.toString() +"\n";
        }
        return listAuthor;
    }
    public static String getAllStringBook(){
        BookModel objBookModel = new BookModel();
        String listBook = "\n Author LIST \n";
        for (Object ite : objBookModel.findAll()){
            // Become the Object to coder
            Book objBook = (Book) ite;
            listBook += objBook.toString() +"\n";
        }
        return listBook;
    }
    public static void getAll(){
        BookModel objBookModel = new BookModel();
        String listBook = "\n AUTHOR LIST \n";
        for (Object ite : objBookModel.findAll()){
            // Become the Object to coder
            Book objBook = (Book) ite;
            listBook += objBook.toString() +"\n";
        }
        JOptionPane.showMessageDialog(null, listBook);
    }

    public static void findById(){
        BookModel objBookModel = new BookModel();
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the book's ID"));
        String listBook = getAllStringBook();
        Book objBook = objBookModel.findById(id);
        if(objBook == null){
            JOptionPane.showMessageDialog(null, "Book not found");
        }else{
            JOptionPane.showMessageDialog(null, "Book found\n" + objBook);
        }
    }
    public static void update(){
        BookModel objBookModel = new BookModel();

        String listBook = getAllStringBook();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listBook + "\n Enter the id book to edit"));

        Book objBook = objBookModel.findById(idUpdate);

        if (objBook == null) {
            JOptionPane.showMessageDialog(null, "Coder not found");

        } else {

            String title = JOptionPane.showInputDialog(null, "Enter the new title:", objBook.getTitle());
            String released = JOptionPane.showInputDialog(null, "Enter the new nationality:", objBook.getReleased());
            double price = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the new price", objBook.getPrice()));
            int idAuthor = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the new ID Author", objBook.getIdAuthor()));

            objBook.setTitle(title);
            objBook.setReleased(released);
            objBook.setPrice(price);
            objBook.setIdAuthor(idAuthor);
            objBookModel.update(objBook);
            }





    }

    public static void delete(){
        BookModel objBookModel = new BookModel();

        String listBook = getAllStringBook();

        int idDeleted = Integer.parseInt(JOptionPane.showInputDialog(listBook +  "\n  Enter the ID of the author to delete"));

        Book objBook = objBookModel.findById(idDeleted);
        int confirm = 1;
        if(objBook == null){
            JOptionPane.showMessageDialog(null, "Book not found");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Are you sure want to delete the author? \n" + objBook.getTitle());
            if (confirm == 0){
                objBookModel.delete(objBook);
                System.out.println("Success");
            }
        }
    }

    public static void findByTitle(){
        BookModel objBookModel = new BookModel();
        String title = JOptionPane.showInputDialog(null, "Enter the book's title");


        Book objBook = objBookModel.findByTitle(title);
        if(objBookModel == null){
            JOptionPane.showMessageDialog(null,"Book not found");
        }else{
            JOptionPane.showMessageDialog(null, "Book found\n" + objBook);
        }

    }

    public static void findByAuthor(){

        BookModel objBookModel = new BookModel();


        int idAuthor = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the author ID"));

        Book objBook = objBookModel.findByAuthor(idAuthor);

        if(objBook == null){
            JOptionPane.showMessageDialog(null,"Book not found");
        }else{
            JOptionPane.showMessageDialog(null, "Book found\n" + objBook);
        }

    }
}
