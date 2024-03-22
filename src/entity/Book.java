package entity;

public class Book {
    private int id;
    private String title;
    private String released;
    private double price;
    private int idAuthor;

    public Book(int id, String title, String released, double price, int idAuthor) {
        this.id = id;
        this.title = title;
        this.released = released;
        this.price = price;
        this.idAuthor = idAuthor;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleased() {
        return released;
    }
    public void setReleased(String released) {
        this.released = released;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getIdAuthor() {
        return idAuthor;
    }
    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", released='" + released + '\'' +
                ", price=" + price +
                ", idAuthor=" + idAuthor +
                '}';
    }
}
