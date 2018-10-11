package callofduty.domain.missions;

import callofduty.interfaces.Mission;

public class EscortMission extends BaseMission {

    public EscortMission(String id, Double rating, Double bounty) {
        super(id, rating - rating * 0.25, bounty + bounty * 0.25);
    }

}
