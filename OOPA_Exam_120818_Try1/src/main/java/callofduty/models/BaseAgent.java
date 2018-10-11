package callofduty.models;

import callofduty.interfaces.Agent;
import callofduty.interfaces.Mission;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public abstract class BaseAgent implements Agent {
    private String id;
    private String name;
    private double rating;
    private List<Mission> missions;
    private int totalMissionsAssigned;
    private int missionsCompleted;
    private boolean isPromoted;

    protected BaseAgent(String id, String name) {
        this.id = id;
        this.name = name;
        this.rating = 0.0;
        this.missions = new LinkedList<>();
        this.totalMissionsAssigned = 0;
        this.missionsCompleted = 0;
        this.isPromoted = false;
    }

    protected List<Mission> getMissions() {
        return this.missions;
    }

    protected int getTotalMissionsAssigned() {
        return this.totalMissionsAssigned;
    }

    protected int getMissionsCompleted() {
        return this.missionsCompleted;
    }

    protected boolean isPromoted() {
        return this.isPromoted;
    }

    protected void collectBonuses(Mission mission) {
        this.rating = this.rating + mission.getRating();
    }

    protected void setRating(double rating) {
        this.rating = rating;
    }

    protected void setMissionsCompleted(int missionsCompleted) {
        this.missionsCompleted = missionsCompleted;
    }

    @Override
    public void acceptMission(Mission mission) {
        this.totalMissionsAssigned++;
        this.missions.add(mission);
    }

    @Override
    public void completeMissions() {
        for (Mission mission : missions) {
            this.missionsCompleted++;
            collectBonuses(mission);
            try {
                Field isCompletedField = mission.getClass().getSuperclass().getDeclaredField("isCompleted");
                isCompletedField.setAccessible(true);
                isCompletedField.set(mission, true);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        this.missions.clear();
        if(this.missionsCompleted >= 3) {
            this.isPromoted = true;
        }

    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Double getRating() {
        return this.rating;
    }

    @Override
    public String toString() {
        StringBuilder agentStatus = new StringBuilder();
        agentStatus.append(String.format("%s Agent - %s", this.getClass().getSimpleName().replace("Agent", ""), this.getName()))
                .append(System.lineSeparator())
                .append(String.format("Personal Code: %s", this.getId()))
                .append(System.lineSeparator())
                .append(String.format("Assigned Missions: %d", this.missions.size()))
                .append(System.lineSeparator())
                .append(String.format("Completed Missions: %d", this.missionsCompleted))
                .append(System.lineSeparator())
                .append(String.format("Rating: %.2f", this.getRating()));

        return agentStatus.toString();
    }
}
