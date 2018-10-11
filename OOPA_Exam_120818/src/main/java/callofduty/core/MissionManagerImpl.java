package callofduty.core;

import callofduty.interfaces.Agent;
import callofduty.interfaces.Mission;
import callofduty.interfaces.MissionControl;
import callofduty.interfaces.MissionManager;
import callofduty.models.agents.MasterAgent;
import callofduty.models.agents.NoviceAgent;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MissionManagerImpl implements MissionManager {
    private Map<String, Agent> agents;
    private Map<String, Mission> allMissions;
    //private Agent currentAgent;
    private MissionControl missionControl;

    public MissionManagerImpl(MissionControl missionControl) {
        this.missionControl = missionControl;
        this.agents = new LinkedHashMap<>();
        this.allMissions = new LinkedHashMap<>();
    }

    private Agent getAgentById(String id) {
        return this.agents.get(id);
    }

    private Mission getMissionById(String id) {
        return this.allMissions.get(id);
    }

    private MasterAgent copyAgentParams(Agent noviceAgent) {
        MasterAgent masterAgent = null;

        try {
            Constructor masterAgentCtor = MasterAgent.class.getDeclaredConstructor(String.class, String.class);
            //Constructor masterAgentCtor = masterAgent.getClass().getDeclaredConstructor(String.class, String.class);
            masterAgent = (MasterAgent) masterAgentCtor.newInstance(noviceAgent.getId(), noviceAgent.getName());

            Field[] noviceAgentFields = noviceAgent.getClass().getDeclaredFields();
            for (Field noviceAgentField : noviceAgentFields) {
                noviceAgentField.setAccessible(true);
                noviceAgentField.set(masterAgent, noviceAgentField.get(noviceAgent));
            }
        } catch (IllegalAccessException | NoSuchMethodException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return masterAgent;
    }

//    private MasterAgent promoteAgent(Agent agent) {
//        MasterAgent masterAgent = null;
//        //Class<?> agentClassName = agent.getClass().getSimpleName();
//        try {
//            Field isPromotedField = agent.getClass().getDeclaredField("isPromoted");
//            if((boolean)isPromotedField.get(agent)) {
//                masterAgent = (MasterAgent)agent;
//            }
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
//
//        return masterAgent;
//    }

    @Override
    public String agent(List<String> arguments) {
        Agent agentInstance;
        String agentId = arguments.get(0);
        String agentName = arguments.get(1);
        agentInstance = new NoviceAgent(agentId, agentName);
        this.agents.put(agentId, agentInstance);
        return agentInstance.toString();
    }

    @Override
    public String request(List<String> arguments) {
        Agent currentAgent;
        String agentId = arguments.get(0);
        String missionId = arguments.get(1);
        double missionRating = Double.parseDouble(arguments.get(2));
        double missionBounty = Double.parseDouble(arguments.get(3));
        Mission missionInstance = missionControl.generateMission(missionId, missionRating, missionBounty);
        this.allMissions.put(missionId, missionInstance);
        currentAgent = this.getAgentById(agentId);
        currentAgent.acceptMission(missionInstance);
        return String.format("Assigned %s Mission - %s to Agent - %s", missionInstance.getClass().getSimpleName().replace("Mission", ""), missionId, currentAgent.getName());
    }

    @Override
    public String complete(List<String> arguments) {
        String agentId = arguments.get(0);
        MasterAgent promotedAgent;
        Agent currentAgent = this.getAgentById(agentId);
        currentAgent.completeMissions();
        Field isPromotedField;
        try {
            if ("MasterAgent".equals(currentAgent.getClass().getSimpleName())) {
                isPromotedField = currentAgent.getClass().getSuperclass().getDeclaredField("isPromoted");
            } else {
                isPromotedField = currentAgent.getClass().getDeclaredField("isPromoted");
            }
            isPromotedField.setAccessible(true);
            if ((boolean)isPromotedField.get(currentAgent)) {
                promotedAgent = this.copyAgentParams(currentAgent);
                this.agents.remove(agentId);
                this.agents.put(promotedAgent.getId(), promotedAgent);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return String.format("Agent - %s:%s has completed all assigned missions.", currentAgent.getName(), currentAgent.getId());
    }

    @Override
    public String status(List<String> arguments) {
        Agent currentAgent = null;
        boolean isAgentsId = true;
        boolean hasMatch = false;
        String statusId = arguments.get(0);
        for (Agent agent : this.agents.values()) {
            if(agent.getId().equals(statusId)) {
                isAgentsId = true;
                hasMatch = true;
                currentAgent = this.getAgentById(statusId);
                break;
            }
        }

        if (!hasMatch) {
            for (Mission mission : this.allMissions.values()) {
                if (mission.getId().equals(statusId)) {
                    isAgentsId = false;
                    hasMatch = true;
                    break;
                }
            }
        }

        if (isAgentsId && hasMatch) {
            int agentCompletedMissions = 0;
            List<Mission> agentMissions = new LinkedList<>();
            try {
                Field missionsCompletedField;
                Field missionsField;
                if ("MasterAgent".equals(currentAgent.getClass().getSimpleName())) {
                    missionsCompletedField = currentAgent.getClass().getSuperclass().getDeclaredField("missionsCompleted");
                    missionsField = currentAgent.getClass().getSuperclass().getDeclaredField("missions");
                } else {
                    missionsCompletedField = currentAgent.getClass().getDeclaredField("missionsCompleted");
                    missionsField = currentAgent.getClass().getDeclaredField("missions");
                }
                missionsCompletedField.setAccessible(true);
                agentCompletedMissions = (int) missionsCompletedField.get(currentAgent);

                missionsField.setAccessible(true);
                agentMissions = (List<Mission>) missionsField.get(currentAgent);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }


            StringBuilder agentStatus = new StringBuilder();
            agentStatus.append(String.format("%s Agent - %s", currentAgent.getClass().getSimpleName().replace("Agent", ""), currentAgent.getName()))
                    .append(System.lineSeparator())
                    .append(String.format("Personal Code: %s", currentAgent.getId()))
                    .append(System.lineSeparator())
                    .append(String.format("Assigned Missions: %d", agentMissions.size()))
                    .append(System.lineSeparator())
                    .append(String.format("Completed Missions: %d", agentCompletedMissions))
                    .append(System.lineSeparator())
                    .append(String.format("Rating: %.2f", currentAgent.getRating()));
                    //.append(System.lineSeparator());

            if ("MasterAgent".equals(currentAgent.getClass().getSimpleName())) {
                MasterAgent masterAgent = (MasterAgent) currentAgent;
                agentStatus.append(System.lineSeparator())
                        .append(String.format("Bounty Earned: $%.2f", masterAgent.getBounty()));
            }
            return agentStatus.toString();

        } else if (!isAgentsId && hasMatch) {
            // TODO: Edit the toString() to be correct for this case
            StringBuilder missionStatus = new StringBuilder();
            Mission currentMission = this.getMissionById(statusId);

//            missionStatus.append(String.format("%s Mission – %s", currentMission.getClass().getSimpleName().replace("Mission", ""), currentMission.getId()))
//                    .append(System.lineSeparator())
//                    .append(String.format("Status: {Open / Completed}"))
//                    .append(System.lineSeparator())
//                    .append(String.format("Status: %.2f", currentMission.getRating()))
//                    .append(System.lineSeparator())
//                    .append(String.format("Status: %.2f", currentMission.getBounty()));
            //return missionStatus.toString();

            return currentMission.toString();
        } else {
            return null;
        }
    }

    @Override
    public String over(List<String> arguments) {
        StringBuilder overSB = new StringBuilder();
        int totalNoviceAgents = 0;
        int totalMasterAgents = 0;
        int totalCompletedMissionsCount = 0;
        int totalAssignedMissionsCount = 0;
        double totalAgentsRating = 0.0;
        double totalAgentsBounty = 0.0;
        Field missionsCompletedField;
        Field totalMissionsAssignedField;

        for (Agent agent : this.agents.values()) {
            try {
                if ("MasterAgent".equals(agent.getClass().getSimpleName())) {
                    missionsCompletedField = agent.getClass().getSuperclass().getDeclaredField("missionsCompleted");
                    totalMissionsAssignedField = agent.getClass().getSuperclass().getDeclaredField("totalMissionsAssigned");
                } else {
                    missionsCompletedField = agent.getClass().getDeclaredField("missionsCompleted");
                    totalMissionsAssignedField = agent.getClass().getDeclaredField("totalMissionsAssigned");
                }
                missionsCompletedField.setAccessible(true);
                totalMissionsAssignedField.setAccessible(true);
                totalCompletedMissionsCount += (int) missionsCompletedField.get(agent);
                totalAssignedMissionsCount += (int) totalMissionsAssignedField.get(agent);
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
                .append(String.format("Assigned Missions: %d", totalAssignedMissionsCount))
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
