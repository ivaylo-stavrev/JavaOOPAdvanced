package callofduty.domain.missions;

public class EscortMission extends BaseMission {

    public EscortMission(String id, Double rating, Double bounty) {
        super(id);
        this.setRating(rating);
        this.setBounty(bounty);
    }

    @Override
    protected void setRating(Double rating) {
        super.setRating(rating - rating * 0.25);
    }

    @Override
    protected void setBounty(Double bounty) {
        super.setBounty(bounty + bounty * 0.25);
    }
}
