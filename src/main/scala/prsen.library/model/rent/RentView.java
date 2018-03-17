package prsen.library.model.rent;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.util.UUID;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RentView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public int readerId;
    public int bookId;
    public Date rentalDate;
    public boolean isClosed;

    public RentView(int readerId, int bookId, Date rentalDate, boolean isClosed) {
        this.readerId = readerId;
        this.bookId = bookId;
        this.rentalDate = rentalDate;
        this.isClosed = isClosed;
    }
}
