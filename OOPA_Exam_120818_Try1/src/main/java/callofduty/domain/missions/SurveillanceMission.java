package callofduty.domain.missions;

import callofduty.interfaces.Mission;

public class SurveillanceMission extends BaseMission {

    public SurveillanceMission(String id, Double rating, Double bounty) {
        super(id, rating - rating * 0.75, bounty + bounty * 0.5);
    }

}
