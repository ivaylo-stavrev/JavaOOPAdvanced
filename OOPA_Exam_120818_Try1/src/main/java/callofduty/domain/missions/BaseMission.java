package callofduty.domain.missions;

import callofduty.interfaces.Mission;

public abstract class BaseMission implements Mission {
    private String id;
    private Double rating;
    private Double bounty;
    private boolean isCompleted;

    protected BaseMission(String id, Double rating, Double bounty) {
        this.id = id;
        this.rating = rating;
        this.bounty = bounty;
        this.isCompleted = false;
    }

//    protected void setIsCompleted(boolean isCompleted) {
//        this.isCompleted = isCompleted;
//    }

    @Override
    public Double getBounty() {
        return this.bounty;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Double getRating() {
        return this.rating;
    }

    @Override
    public String toString() {
        StringBuilder missionStatus = new StringBuilder();
        String isCompletedStatus = this.isCompleted ? "Completed" : "Open";

        missionStatus.append(String.format("%s Mission - %s", this.getClass().getSimpleName().replace("Mission", ""), this.getId()))
                .append(System.lineSeparator())
                .append(String.format("Status: %s", isCompletedStatus))
                .append(System.lineSeparator())
                .append(String.format("Rating: %.2f", this.getRating()))
                .append(System.lineSeparator())
                .append(String.format("Bounty: %.2f", this.getBounty()));

        return missionStatus.toString();
    }
}
