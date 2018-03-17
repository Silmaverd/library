package prsen.library.model.book;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String title;
    public String author;
    public int amountInStock;

    public BookView(String title, String author, int amountInStock) {
        this.title = title;
        this.author = author;
        this.amountInStock = amountInStock;
    }
}
