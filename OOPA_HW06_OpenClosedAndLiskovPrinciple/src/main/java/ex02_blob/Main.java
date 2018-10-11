package ex02_blob;

import ex02_blob.core.Engine;
import ex02_blob.interfaces.Repository;
import ex02_blob.io.implementations.ConsoleReader;
import ex02_blob.io.implementations.ConsoleWriter;
import ex02_blob.io.io_interfaces.Reader;
import ex02_blob.io.io_interfaces.Writer;
import ex02_blob.models.Blob;
import ex02_blob.observers.Subject;
import ex02_blob.repositories.BlobRepository;

public class Main {
    public static void main(String[] args) {
        Writer writer = new ConsoleWriter();
        Reader reader = new ConsoleReader();
        Repository<Blob> blobRepository = new BlobRepository();
        Subject subject = new Subject();

        Runnable engine = new Engine(reader, writer, blobRepository, subject);

        engine.run();
    }
}
