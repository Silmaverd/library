package prsen.library.model;

public class BookView {

    public String ISBN;
    public String title;
    public String author;
    public Boolean isRented;

    public BookView(String ISBN, String title, String author) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        isRented = false;
    }
}
