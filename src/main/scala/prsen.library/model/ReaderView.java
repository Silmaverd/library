package prsen.library.model;

import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
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
}
