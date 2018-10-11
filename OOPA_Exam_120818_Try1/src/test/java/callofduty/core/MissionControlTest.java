package callofduty.core;

import callofduty.interfaces.Mission;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MissionControlTest {
    public static final String CORRECT_MISSION_ID = "123kg";
    public static final String LONG_MISSION_ID = "1234567890";
    public static final Double MISSION_RATING_ABOVE_MAX = 101D;
    public static final Double MISSION_RATING_CORRECT = 50D;
    public static final Double MISSION_RATING_UNDER_MIN = -1D;
    public static final Double MISSION_BOUNTY_ABOVE_MAX = 100001D;
    public static final Double MISSION_BOUNTY_CORRECT = 50000D;
    public static final Double MISSION_BOUNTY_UNDER_MIN = -1D;
    public static final Double ESCORT_MISSION_RATING_ADJUST = 0.75;
    public static final Double HUNT_MISSION_RATING_ADJUST = 1.5;
    public static final Double SURVEILLANCE_MISSION_RATING_ADJUST = 0.25;
    public static final Double ESCORT_MISSION_BOUNTY_ADJUST = 1.25;
    public static final Double HUNT_MISSION_BOUNTY_ADJUST = 2.0;
    public static final Double SURVEILLANCE_MISSION_BOUNTY_ADJUST = 1.5;

    private MissionControlImpl missionControl;

    @Before
    public void init() {
        missionControl = new MissionControlImpl();
    }

    @Test
    public void testSubtractingLongerMissionID() throws Exception {
        // Arrange

        // Act
        Mission mission = missionControl.generateMission(LONG_MISSION_ID, MISSION_RATING_CORRECT, MISSION_BOUNTY_CORRECT);

        // Assert
        Assert.assertTrue("Test the length.", mission.getId().length() == 8);
        Assert.assertEquals("Test the ID content.", mission.getId(), "12345678");
    }

    @Test
    public void testUpperLimitMissionRating() throws Exception {
        // Arrange
        Double expectValue = 100D;

        // Act
        Mission escortMission = missionControl.generateMission(CORRECT_MISSION_ID, MISSION_RATING_ABOVE_MAX, MISSION_BOUNTY_CORRECT);
        Mission huntMission = missionControl.generateMission(CORRECT_MISSION_ID, MISSION_RATING_ABOVE_MAX, MISSION_BOUNTY_CORRECT);
        Mission surveillanceMission = missionControl.generateMission(CORRECT_MISSION_ID, MISSION_RATING_ABOVE_MAX, MISSION_BOUNTY_CORRECT);

        // Assert
        Assert.assertTrue(String.format("Test the MAX Rating. Mission rating = %.2f", escortMission.getRating()),
                escortMission.getRating() == expectValue * ESCORT_MISSION_RATING_ADJUST);
        Assert.assertTrue(String.format("Test the MAX Rating. Mission rating = %.2f", huntMission.getRating()),
                huntMission.getRating() == expectValue * HUNT_MISSION_RATING_ADJUST);
        Assert.assertTrue(String.format("Test the MAX Rating. Mission rating = %.2f", surveillanceMission.getRating()),
                surveillanceMission.getRating() == expectValue * SURVEILLANCE_MISSION_RATING_ADJUST);
    }

    @Test
    public void testUpperLimitMissionBounty() throws Exception {
        // Arrange
        Double expectValue = 100000D;

        // Act
        Mission escortMission = missionControl.generateMission(CORRECT_MISSION_ID, MISSION_RATING_CORRECT, MISSION_BOUNTY_ABOVE_MAX);
        Mission huntMission = missionControl.generateMission(CORRECT_MISSION_ID, MISSION_RATING_CORRECT, MISSION_BOUNTY_ABOVE_MAX);
        Mission surveillanceMission = missionControl.generateMission(CORRECT_MISSION_ID, MISSION_RATING_CORRECT, MISSION_BOUNTY_ABOVE_MAX);

        // Assert
        Assert.assertTrue(String.format("Test the MAX Bounty. Mission Bounty = %.2f", escortMission.getBounty()),
                escortMission.getBounty() == expectValue * ESCORT_MISSION_BOUNTY_ADJUST);
        Assert.assertTrue(String.format("Test the MAX Bounty. Mission Bounty = %.2f", huntMission.getBounty()),
                huntMission.getBounty() == expectValue * HUNT_MISSION_BOUNTY_ADJUST);
        Assert.assertTrue(String.format("Test the MAX Bounty. Mission Bounty = %.2f", surveillanceMission.getBounty()),
                surveillanceMission.getBounty() == expectValue * SURVEILLANCE_MISSION_BOUNTY_ADJUST);
    }

    @Test
    public void testUnderLimitMissionRating() throws Exception {
        // Arrange
        Double expectValue = 0D;

        // Act
        Mission escortMission = missionControl.generateMission(CORRECT_MISSION_ID, MISSION_RATING_UNDER_MIN, MISSION_BOUNTY_CORRECT);
        Mission huntMission = missionControl.generateMission(CORRECT_MISSION_ID, MISSION_RATING_UNDER_MIN, MISSION_BOUNTY_CORRECT);
        Mission surveillanceMission = missionControl.generateMission(CORRECT_MISSION_ID, MISSION_RATING_UNDER_MIN, MISSION_BOUNTY_CORRECT);

        // Assert
        Assert.assertTrue(String.format("Test the MIN Rating. Mission rating = %.2f", escortMission.getRating()),
                escortMission.getRating() == expectValue * ESCORT_MISSION_RATING_ADJUST);
        Assert.assertTrue(String.format("Test the MIN Rating. Mission rating = %.2f", huntMission.getRating()),
                huntMission.getRating() == expectValue * HUNT_MISSION_RATING_ADJUST);
        Assert.assertTrue(String.format("Test the MIN Rating. Mission rating = %.2f", surveillanceMission.getRating()),
                surveillanceMission.getRating() == expectValue * SURVEILLANCE_MISSION_RATING_ADJUST);
    }

    @Test
    public void testUnderLimitMissionBounty() throws Exception {
        // Arrange
        Double expectValue = 0D;

        // Act
        Mission escortMission = missionControl.generateMission(CORRECT_MISSION_ID, MISSION_RATING_CORRECT, MISSION_BOUNTY_UNDER_MIN);
        Mission huntMission = missionControl.generateMission(CORRECT_MISSION_ID, MISSION_RATING_CORRECT, MISSION_BOUNTY_UNDER_MIN);
        Mission surveillanceMission = missionControl.generateMission(CORRECT_MISSION_ID, MISSION_RATING_CORRECT, MISSION_BOUNTY_UNDER_MIN);

        // Assert
        Assert.assertTrue(String.format("Test the MIN Bounty. Mission Bounty = %.2f", escortMission.getBounty()),
                escortMission.getBounty() == expectValue * ESCORT_MISSION_BOUNTY_ADJUST);
        Assert.assertTrue(String.format("Test the MIN Bounty. Mission Bounty = %.2f", huntMission.getBounty()),
                huntMission.getBounty() == expectValue * HUNT_MISSION_BOUNTY_ADJUST);
        Assert.assertTrue(String.format("Test the MIN Bounty. Mission Bounty = %.2f", surveillanceMission.getBounty()),
                surveillanceMission.getBounty() == expectValue * SURVEILLANCE_MISSION_BOUNTY_ADJUST);
    }
}