package prsen.library.model.reader;

import lombok.AllArgsConstructor;

import java.util.UUID;

public class ReaderView {
    public String firstName;
    public String lastName;
    public int rentsNumber;
    public UUID readerUUID;

    public ReaderView(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        rentsNumber = 0;
        readerUUID = UUID.randomUUID();
    }

    public ReaderView(String firstName, String lastName, int rentsNumber, UUID readerUUID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rentsNumber = rentsNumber;
        this.readerUUID = readerUUID;
    }
}
