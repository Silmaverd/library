package prsen.library.model.rent;

import lombok.AllArgsConstructor;

import java.sql.Date;
import java.util.UUID;

public class RentView {
    public int id;
    public UUID readerGuid;
    public String bookIsbn;
    public Date rentalDate;
    public boolean isClosed;

    public RentView(int id, UUID readerGuid, String bookIsbn, Date rentalDate, boolean isClosed) {
        this.id = id;
        this.readerGuid = readerGuid;
        this.bookIsbn = bookIsbn;
        this.rentalDate = rentalDate;
        this.isClosed = isClosed;
    }
}
