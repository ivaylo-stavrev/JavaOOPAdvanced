package callofduty.domain.missions;

public class SurveillanceMission extends BaseMission {

    public SurveillanceMission(String id, Double rating, Double bounty) {
        super(id);
        this.setRating(rating);
        this.setBounty(bounty);
    }

    @Override
    protected void setRating(Double rating) {
        super.setRating(rating - rating * 0.75);
    }

    @Override
    protected void setBounty(Double bounty) {
        super.setBounty(bounty + bounty * 0.50);
    }

    //    @Override
//    public Double getBounty() {
//        return null;
//    }
//
//    @Override
//    public String getId() {
//        return null;
//    }
//
//    @Override
//    public Double getRating() {
//        return null;
//    }
}
