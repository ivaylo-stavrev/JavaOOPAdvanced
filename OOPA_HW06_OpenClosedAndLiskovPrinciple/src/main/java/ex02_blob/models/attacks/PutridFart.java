package ex02_blob.models.attacks;

import ex02_blob.interfaces.Attack;
import ex02_blob.models.Blob;

public class PutridFart implements Attack {

    @Override
    public void execute(Blob source, Blob target) {
        //if (source.isDead() || target.isDead()) {
        //    return;
        //}

        //int currentHealth = target.getHealth();
        //currentHealth = target.getHealth() - source.getDamage();
        target.setHealth(target.getHealth() - source.getDamage());
    }
}
