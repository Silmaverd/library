package prsen.library.model.book;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@ToString
@NoArgsConstructor
public class BookView {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    public String title;
    public String author;
    public Boolean isRented;
    // TODO: EXPAND MODEL BY AMOUNT IN STOCK

    public BookView(int id, String title, String author, Boolean isRented) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isRented = isRented;
    }
}
