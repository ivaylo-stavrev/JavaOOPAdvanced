package callofduty.models.agents;

import callofduty.interfaces.BountyAgent;

public class MasterAgent extends NoviceAgent implements BountyAgent {
    private Double bounty;

    public MasterAgent(String id, String name) {
        super(id, name);
        this.bounty = 0.0; // TODO: Add a constant for the default
    }

    @Override
    public Double getBounty() {
        return this.bounty;
    }
}
