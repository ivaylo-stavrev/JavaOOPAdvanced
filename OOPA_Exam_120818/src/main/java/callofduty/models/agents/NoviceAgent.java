package callofduty.models.agents;

import callofduty.interfaces.Agent;
import callofduty.interfaces.Mission;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public class NoviceAgent implements Agent {

    private String id;
    private String name;
    private Double rating;
    //private Double bounty;
    private List<Mission> missions;
    private int missionsCompleted;
    private int totalMissionsAssigned;
    private boolean isPromoted;


    public NoviceAgent(String id, String name) {
        this.id = id;
        this.name = name;
        this.rating = 0.0; // TODO: Add a constant for the default
        this.missions = new LinkedList<>();
        this.totalMissionsAssigned = 0;
        this.missionsCompleted = 0;
        this.isPromoted = false;
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
            this.rating = this.rating + mission.getRating();
            if (this.isPromoted) {
                //this.bounty = this.bounty + mission.getBounty();
                try {
                    Field bountyField = this.getClass().getDeclaredField("bounty");
                    bountyField.setAccessible(true);
                    double bountyValue = (double)bountyField.get(this);
                    bountyField.set(this, bountyValue + mission.getBounty());
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            try {
                Field isCompletedField = mission.getClass().getSuperclass().getDeclaredField("isCompleted");
                isCompletedField.setAccessible(true);
                isCompletedField.set(mission, true);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if(this.missionsCompleted >= 3) {
            this.isPromoted = true;
        }
        this.missions.clear();
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
        return String.format("Registered Agent - %s:%s", this.name, this.id);
    }
}
