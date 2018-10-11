package ex02_blob.models.behaviors;

import ex02_blob.models.Blob;

public class Inflated extends AbstractBehavior {
    private static final int INFLATED_HEALTH_GAIN = 50;
    private static final int INFLATED_HEALTH_DECREMENT = 10;

    public Inflated() {
    }

    @Override
    public void trigger(Blob source) {
        //if (super.isTriggered()) {
        //    throw new IllegalStateException();
        //}
        this.setIsTriggered(true);
        source.setHealth(source.getHealth() + INFLATED_HEALTH_GAIN);
    }

    @Override
    public void applyRecurrentEffect(Blob source) {
        if (super.toDelayRecurrentEffect()) {
            super.setToDelayRecurrentEffect(false);
        } else {
            source.setHealth(source.getHealth() - INFLATED_HEALTH_DECREMENT);

            //if (source.getHealth() <= this.sourceInitialDamage) {
            //    source.setDamage(this.sourceInitialDamage);
            //}
        }
    }
}
