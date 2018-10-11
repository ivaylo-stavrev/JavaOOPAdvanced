package ex02_blob.core.commands;

import ex02_blob.annotations.Inject;
import ex02_blob.interfaces.Executable;
import ex02_blob.interfaces.Repository;
import ex02_blob.io.io_interfaces.Writer;
import ex02_blob.models.Blob;

public class StatusCommand implements Executable {

    @Inject
    private Repository<Blob> blobRepository;

    @Inject
    private Writer writer;

    @Override
    public void execute() {
        for (Blob blob : blobRepository.findAll()) {
            this.writer.writeLine(blob.toString());
        }
    }
}
