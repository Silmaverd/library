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
public class ReaderView {

    public String name;
    public int rentsNumber;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    public ReaderView(String name, int rentsNumber, int id) {
        this.name = name;
        this.rentsNumber = rentsNumber;
        this.id = id;
    }
}
