package prsen.library.model.reader;

import lombok.AllArgsConstructor;

import java.util.UUID;

public class ReaderView {
    public String name;
    public int rentsNumber;
    public int id;

    public ReaderView(String name, int rentsNumber, int id) {
        this.name = name;
        this.rentsNumber = rentsNumber;
        this.id = id;
    }
}
