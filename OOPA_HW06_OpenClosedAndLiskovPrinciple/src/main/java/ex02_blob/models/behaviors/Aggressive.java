package ex02_blob.models.behaviors;

import ex02_blob.models.Blob;

public class Aggressive extends AbstractBehavior {

    private static final int AGGRESSIVE_DAMAGE_MULTIPLY = 2;
    private static final int AGGRESSIVE_DAMAGE_DECREMENT = 5;

    private int sourceInitialDamage;

    public Aggressive() {
    }

    @Override
    public void trigger(Blob source) {
        //if (super.isTriggered()) {
        //    throw new IllegalStateException();
        //}
        this.sourceInitialDamage = source.getDamage();
        super.setIsTriggered(true);
        source.setDamage(source.getDamage() * AGGRESSIVE_DAMAGE_MULTIPLY);
    }

    @Override
    public void applyRecurrentEffect(Blob source) {
        if (super.toDelayRecurrentEffect()) {
            super.setToDelayRecurrentEffect(false);
        } else {
            source.setDamage(source.getDamage() - AGGRESSIVE_DAMAGE_DECREMENT);

            if (source.getDamage() <= this.sourceInitialDamage) {
                source.setDamage(this.sourceInitialDamage);
            }
        }
    }

//    public boolean toDelayRecurrentEffect() {
//        return super.toDelayRecurrentEffect;
//    }

//    public void setToDelayRecurrentEffect(boolean toDelayRecurrentEffect){
//        super.toDelayRecurrentEffect = toDelayRecurrentEffect;
//    }

//    private void applyTriggerEffect(Blob source) {
//        source.setDamage(source.getDamage() * AGGRESSIVE_DAMAGE_MULTIPLY);
//    }
}
