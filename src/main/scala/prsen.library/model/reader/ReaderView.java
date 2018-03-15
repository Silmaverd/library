package prsen.library.model.reader;

import lombok.AllArgsConstructor;

import java.util.UUID;

public class ReaderView {
    public String name;
    public int rentsNumber;
    public UUID readerUUID;

    public ReaderView(String name) {
        this.name = name;
        rentsNumber = 0;
        readerUUID = UUID.randomUUID();
    }

    public ReaderView(String name, int rentsNumber, UUID readerUUID) {
        this.name = name;
        this.rentsNumber = rentsNumber;
        this.readerUUID = readerUUID;
    }
}
