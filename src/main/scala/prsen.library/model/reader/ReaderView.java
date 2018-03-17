package prsen.library.model.reader;

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
public class ReaderView {

    public String name;
    public int rentsNumber;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public boolean hasEverDamagedABook;
    public boolean hasEverLostABook;

    public ReaderView(String name, int rentsNumber) {
        this.name = name;
        this.rentsNumber = rentsNumber;
        this.hasEverDamagedABook = false;
        this.hasEverLostABook = false;
    }
}
