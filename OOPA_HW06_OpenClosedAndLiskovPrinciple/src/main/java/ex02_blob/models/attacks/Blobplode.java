package ex02_blob.models.attacks;

import ex02_blob.interfaces.Attack;
import ex02_blob.models.Blob;

public class Blobplode implements Attack {

    @Override
    public void execute(Blob source, Blob target) {
        int healthAfterBlobplodeAttack = source.getHealth() - source.getHealth() / 2;
        if (healthAfterBlobplodeAttack >= 1) {
            source.setHealth(healthAfterBlobplodeAttack);
        }
        target.setHealth(target.getHealth() - source.getDamage() * 2);
    }
}
