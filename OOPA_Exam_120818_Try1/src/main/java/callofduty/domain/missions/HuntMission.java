package callofduty.domain.missions;

import callofduty.interfaces.Mission;

public class HuntMission extends BaseMission {

    public HuntMission(String id, Double rating, Double bounty) {
        super(id, rating + rating * 0.5, bounty + bounty);
    }

}
