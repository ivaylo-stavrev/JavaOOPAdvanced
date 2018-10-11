package callofduty.core;

import callofduty.interfaces.*;
import callofduty.models.MasterAgent;
import callofduty.models.NoviceAgent;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MissionManagerImpl implements MissionManager {
    private Map<String, Agent> agents;
    private Map<String, Mission> allMissions;
    private MissionControl missionControl;
    private int totalMissionsAssigned;

    public MissionManagerImpl(MissionControl missionControl) {
        this.missionControl = missionControl;
        this.agents = new LinkedHashMap<>();
        this.allMissions = new LinkedHashMap<>();
        this.totalMissionsAssigned = 0;
    }

    private Agent getAgentById(String id) {
        return this.agents.get(id);
    }

    private Mission getMissionById(String id) {
        return this.allMissions.get(id);
    }

//    private MasterAgent copyAgentParams(Agent noviceAgent) {
//        //MasterAgent masterAgent = null;
//        MasterAgent masterAgent = new MasterAgent(noviceAgent.getId(), noviceAgent.getName());
//
//        try {
//            Constructor masterAgentCtor = MasterAgent.class.getDeclaredConstructor(String.class, String.class);
//            //Constructor masterAgentCtor = masterAgent.getClass().getDeclaredConstructor(String.class, String.class);
//            masterAgent = (MasterAgent) masterAgentCtor.newInstance(noviceAgent.getId(), noviceAgent.getName());
//
//            Field[] noviceAgentFields = noviceAgent.getClass().getDeclaredFields();
//            for (Field noviceAgentField : noviceAgentFields) {
//                noviceAgentField.setAccessible(true);
//                noviceAgentField.set(masterAgent, noviceAgentField.get(noviceAgent));
//            }
//        } catch (IllegalAccessException | NoSuchMethodException | InstantiationException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
//
//        return masterAgent;
//    }

    private void promoteAgent(Agent agent) {
        //Class<?> agentClassName = agent.getClass().getSimpleName();
        int currentMissionsCompleted = 0;
        try {
            Field missionsCompletedField = agent.getClass().getSuperclass().getDeclaredField("missionsCompleted");
            missionsCompletedField.setAccessible(true);
            currentMissionsCompleted = (int) missionsCompletedField.get(agent);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        BountyAgent promotedAgent = new MasterAgent(agent.getId(), agent.getName(), agent.getRating(), currentMissionsCompleted);

        this.agents.remove(agent.getId());
        this.agents.put(promotedAgent.getId(), promotedAgent);

    }

    @Override
    public String agent(List<String> arguments) {
        Agent agentInstance;
        String agentId = arguments.get(0);
        String agentName = arguments.get(1);
        agentInstance = new NoviceAgent(agentId, agentName);
        this.agents.put(agentId, agentInstance);
        return String.format("Registered Agent - %s:%s", agentName, agentId);
    }

    @Override
    public String request(List<String> arguments) {
        Agent currentAgent;
        String agentId = arguments.get(0);
        String missionId = arguments.get(1);
        Double missionRating = Double.parseDouble(arguments.get(2));
        Double missionBounty = Double.parseDouble(arguments.get(3));
        Mission missionInstance = this.missionControl.generateMission(missionId, missionRating, missionBounty);
        this.allMissions.put(missionId, missionInstance);
        currentAgent = this.getAgentById(agentId);
        currentAgent.acceptMission(missionInstance);
        this.totalMissionsAssigned++;
        return String.format("Assigned %s Mission - %s to Agent - %s", missionInstance.getClass().getSimpleName().replace("Mission", ""), missionId, currentAgent.getName());
    }

    @Override
    public String complete(List<String> arguments) {
        String agentId = arguments.get(0);
        //MasterAgent promotedAgent;
        Agent currentAgent = this.getAgentById(agentId);
        currentAgent.completeMissions();
        Field isPromotedField;
        try {
            if ("MasterAgent".equals(currentAgent.getClass().getSimpleName())) {
                isPromotedField = currentAgent.getClass().getSuperclass().getDeclaredField("isPromoted");
            } else {
                isPromotedField = currentAgent.getClass().getSuperclass().getDeclaredField("isPromoted");
            }
            isPromotedField.setAccessible(true);
            if ((boolean)isPromotedField.get(currentAgent) && !"MasterAgent".equals(currentAgent.getClass().getSimpleName())) {
                //promotedAgent = this.copyAgentParams(currentAgent);
                //this.agents.remove(agentId);
                //this.agents.put(promotedAgent.getId(), promotedAgent);

                this.promoteAgent(currentAgent);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return String.format("Agent - %s:%s has completed all assigned missions.", currentAgent.getName(), currentAgent.getId());
    }

    @Override
    public String status(List<String> arguments) {
        String result = null;
        String statusId = arguments.get(0);
        boolean hasMatch = false;
        for (Agent agent : this.agents.values()) {
            if (statusId.equals(agent.getId())) {
                result = agent.toString();
                hasMatch = true;
                break;
            }
        }
        if (!hasMatch) {
            for (Mission mission : this.allMissions.values()) {
                if (statusId.equals(mission.getId())) {
                    result = mission.toString();
                }
            }
        }
        return result;
    }

    @Override
    public String over(List<String> arguments) {
        StringBuilder overSB = new StringBuilder();
        int totalNoviceAgents = 0;
        int totalMasterAgents = 0;
        int totalCompletedMissionsCount = 0;
        //int totalAssignedMissionsCount = 0;
        double totalAgentsRating = 0.0;
        double totalAgentsBounty = 0.0;
        Field missionsCompletedField;
        //Field totalMissionsAssignedField;

        for (Agent agent : this.agents.values()) {
            try {
                missionsCompletedField = agent.getClass().getSuperclass().getDeclaredField("missionsCompleted");
                //totalMissionsAssignedField = agent.getClass().getSuperclass().getDeclaredField("totalMissionsAssigned");
                missionsCompletedField.setAccessible(true);
                //totalMissionsAssignedField.setAccessible(true);
                totalCompletedMissionsCount += (int) missionsCompletedField.get(agent);
                //totalAssignedMissionsCount += (int) totalMissionsAssignedField.get(agent);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
            if ("MasterAgent".equals(agent.getClass().getSimpleName())) {
                MasterAgent tempAgent = (MasterAgent) agent;
                totalMasterAgents++;
                totalAgentsBounty += tempAgent.getBounty();
            } else {
                totalNoviceAgents++;
            }
            totalAgentsRating += agent.getRating();
        }

        overSB.append(String.format("Novice Agents: %d", totalNoviceAgents))
                .append(System.lineSeparator())
                .append(String.format("Master Agents: %d", totalMasterAgents))
                .append(System.lineSeparator())
                .append(String.format("Assigned Missions: %d", this.totalMissionsAssigned))
                .append(System.lineSeparator())
                .append(String.format("Completed Missions: %d", totalCompletedMissionsCount))
                .append(System.lineSeparator())
                .append(String.format("Total Rating Given: %.2f", totalAgentsRating))
                .append(System.lineSeparator())
                .append(String.format("Total Bounty Given: $%.2f", totalAgentsBounty))
                .append(System.lineSeparator());

        return overSB.toString();
    }
}
