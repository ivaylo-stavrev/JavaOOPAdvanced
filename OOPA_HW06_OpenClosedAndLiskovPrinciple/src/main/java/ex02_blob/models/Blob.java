package ex02_blob.models;

import ex02_blob.interfaces.Attack;
import ex02_blob.interfaces.Behavior;
import ex02_blob.models.attacks.PutridFart;
import ex02_blob.models.behaviors.Aggressive;
import ex02_blob.observers.Observer;
import ex02_blob.observers.Subject;

public class Blob extends Observer{

    private String name;
    private int health;
    private int damage;
    private Behavior behavior;
    private Attack attack;
    private int initialHealth;
    private Subject subject;
    //private int initialDamage;
    //private boolean isDead;


    // private AbstractBehavior behavior;
    // private AbstractAttack attack;
    private int triggerCount;



    public Blob(String name, int health, int damage, Behavior behavior, Attack attack, Subject subject) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.behavior = behavior;
        this.attack = attack;
        this.initialHealth = health;
        this.subject = subject;
        this.subject.attach(this);
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;

        if (this.health < 0) {
            this.health = 0;
        }

        if (this.health <= this.initialHealth / 2 && !this.behavior.isTriggered()) {
            this.behavior.trigger(this);
        }
    }

    public int getDamage() {
        return this.damage;
    }

    public void setDamage(int currentDamage) {
        this.damage = currentDamage;
    }

    public String getName() {
        return this.name;
    }

    public Behavior getBehavior() {
        return this.behavior;
    }

    public void attack(Blob target) {
        if (this.getHealth() == 0 || target.getHealth() == 0) {
            return;
        }
        this.attack.execute(this, target);
    }

    @Override
    public void update() {
        if (this.behavior.isTriggered()) {
            this.behavior.applyRecurrentEffect(this);
        }
    }

    @Override
    public String toString() {
        if (this.getHealth() <= 0) {
            return String.format("Blob %s KILLED", this.getName());
        }

        return String.format("Blob %s: %s HP, %s Damage", this.getName(), this.getHealth(), this.getDamage());
    }
}
