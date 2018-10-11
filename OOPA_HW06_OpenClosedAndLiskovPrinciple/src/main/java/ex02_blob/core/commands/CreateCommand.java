package ex02_blob.core.commands;

import ex02_blob.annotations.Inject;
import ex02_blob.factories.AttackFactory;
import ex02_blob.factories.BehaviorFactory;
import ex02_blob.factories.BlobFactory;
import ex02_blob.interfaces.Attack;
import ex02_blob.interfaces.Behavior;
import ex02_blob.interfaces.Executable;
import ex02_blob.interfaces.Repository;
import ex02_blob.models.Blob;
import ex02_blob.observers.Subject;

public class CreateCommand implements Executable {

    @Inject
    private String[] data;

    @Inject
    private Repository<Blob> blobRepository;

    @Inject
    private Subject subject;

    public CreateCommand() {
    }

    @Override
    public void execute() {
        String blobName = data[0];
        int blobHealth = Integer.parseInt(data[1]);
        int blobDamage = Integer.parseInt(data[2]);
        String behaviorType = data[3];
        String attackType = data[4];

        Behavior blobBehavior =  BehaviorFactory.create(behaviorType);
        Attack blobAttack = AttackFactory.create(attackType);
        Blob blob = BlobFactory.create(blobName, blobHealth, blobDamage, blobBehavior, blobAttack, subject);
        blobRepository.add(blob);
    }
}
