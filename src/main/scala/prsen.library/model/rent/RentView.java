package prsen.library.model.rent;

import lombok.AllArgsConstructor;

import java.sql.Date;
import java.util.UUID;

public class RentView {
    public int id;
    public int readerId;
    public int bookId;
    public Date rentalDate;
    public boolean isClosed;

    public RentView(int id, int readerId, int bookId, Date rentalDate, boolean isClosed) {
        this.id = id;
        this.readerId = readerId;
        this.bookId = bookId;
        this.rentalDate = rentalDate;
        this.isClosed = isClosed;
    }
}
