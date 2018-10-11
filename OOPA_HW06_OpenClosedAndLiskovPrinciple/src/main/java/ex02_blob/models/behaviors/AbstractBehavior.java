package ex02_blob.models.behaviors;

import ex02_blob.interfaces.Behavior;

public abstract class AbstractBehavior implements Behavior {

    private boolean isTriggered;
    private boolean toDelayRecurrentEffect;

    public AbstractBehavior() {
        this.toDelayRecurrentEffect = true;
    }

    @Override
    public boolean isTriggered() {
        return this.isTriggered;
    }

    @Override
    public void setIsTriggered(boolean isTriggered) {
        this.isTriggered = isTriggered;
    }

    public boolean toDelayRecurrentEffect() {
        return this.toDelayRecurrentEffect;
    }

    public void setToDelayRecurrentEffect(boolean toDelayRecurrentEffect){
        this.toDelayRecurrentEffect = toDelayRecurrentEffect;
    }

//    @Override
//    //public abstract void trigger(Blob source);
//    public abstract void trigger(Blob source);
//
//    @Override
//    public abstract void applyRecurrentEffect(Blob source);
}
