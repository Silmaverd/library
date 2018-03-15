package prsen.library.model.book;

public class BookView {

    public String ISBN;
    public String title;
    public String author;
    public Boolean isRented;

    public BookView(String ISBN, String title, String author, Boolean isRented) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.isRented = isRented;
    }
}
