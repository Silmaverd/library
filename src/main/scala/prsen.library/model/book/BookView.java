package prsen.library.model.book;

public class BookView {

    public int id;
    public String title;
    public String author;
    public Boolean isRented;

    public BookView(int id, String title, String author, Boolean isRented) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isRented = isRented;
    }
}
