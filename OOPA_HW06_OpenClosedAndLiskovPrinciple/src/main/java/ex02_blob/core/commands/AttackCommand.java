package ex02_blob.core.commands;

import ex02_blob.annotations.Inject;
import ex02_blob.interfaces.Executable;
import ex02_blob.interfaces.Repository;
import ex02_blob.models.Blob;

public class AttackCommand implements Executable {
    @Inject
    private Repository<Blob> blobRepository;

    @Inject
    private String[] data;

    @Override
    public void execute() {
        String blobAttackerName = data[0];
        String blobTargetName = data[1];

        Blob blobAttacker = blobRepository.getByName(blobAttackerName);
        Blob blobTarget = blobRepository.getByName(blobTargetName);

        blobAttacker.attack(blobTarget);
    }
}
