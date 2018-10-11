package callofduty.models;

import callofduty.interfaces.BountyAgent;
import callofduty.interfaces.Mission;

import java.lang.reflect.Field;
import java.util.List;

public class MasterAgent extends BaseAgent implements BountyAgent {
    private Double bounty;

    public MasterAgent(String id, String name, Double rating, int missionsCompleted) {
        super(id, name);
        this.bounty = 0.0;
        this.setRating(rating);
        this.setMissionsCompleted(missionsCompleted);
    }

    @Override
    public Double getBounty() {
        return this.bounty;
    }

    @Override
    protected void collectBonuses(Mission mission) {
        //super.collectBonuses(mission);
        this.setRating( this.getRating() + mission.getRating());
        this.bounty = this.bounty + mission.getBounty();
    }

    @Override
    public String toString() {
        return super.toString() + String.format("%nBounty Earned: $%.2f", this.getBounty());
    }
}
