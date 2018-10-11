package panzer.models.miscellaneous;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import panzer.contracts.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class VehicleAssemblerTest {

    private Assembler vehicleAssembler;
    private AttackModifyingPart attackModifyingPart;
    private DefenseModifyingPart defenseModifyingPart;
    private HitPointsModifyingPart hitPointsModifyingPart;


    @Before
    public void setUp() throws Exception {
        vehicleAssembler = new VehicleAssembler();
        this.attackModifyingPart = Mockito.mock(AttackModifyingPart.class);
        this.defenseModifyingPart = Mockito.mock(DefenseModifyingPart.class);
        this.hitPointsModifyingPart = Mockito.mock(HitPointsModifyingPart.class);
        this.vehicleAssembler.addArsenalPart(this.attackModifyingPart);
        this.vehicleAssembler.addShellPart(this.defenseModifyingPart);
        this.vehicleAssembler.addEndurancePart(this.hitPointsModifyingPart);

    }

    @Test
    public void testGetTotalWeight() throws Exception {
        // arrange
        Mockito.when(this.attackModifyingPart.getWeight()).thenReturn(10.0);
        Mockito.when(this.defenseModifyingPart.getWeight()).thenReturn(20.0);
        Mockito.when(this.hitPointsModifyingPart.getWeight()).thenReturn(30.0);

        // actual
        double actualTotalWeight = vehicleAssembler.getTotalWeight();

        // expected
        double expectedTotalWeight = 60.0;

        Assert.assertEquals(expectedTotalWeight, actualTotalWeight, 0.1);
    }

    @Test
    public void testGetTotalPrice() throws Exception {
        // Arrange
        Mockito.when(this.attackModifyingPart.getPrice()).thenReturn(BigDecimal.TEN);
        Mockito.when(this.defenseModifyingPart.getPrice()).thenReturn(BigDecimal.ZERO);
        Mockito.when(this.hitPointsModifyingPart.getPrice()).thenReturn(BigDecimal.ONE);

        // Act
        BigDecimal actualTotalPrice = vehicleAssembler.getTotalPrice();
        BigDecimal expectedTotalPrice = BigDecimal.valueOf(11);

        // Assert
        Assert.assertEquals(expectedTotalPrice, actualTotalPrice);
    }

    @Test
    public void testGetTotalPriceWithoutModifications() throws Exception {
        // Arrange
        Assembler assembler = new VehicleAssembler();
        // Act
        BigDecimal actualTotalPrice = assembler.getTotalPrice();
        BigDecimal expectedTotalPrice = BigDecimal.ZERO;

        // Assert
        Assert.assertEquals(expectedTotalPrice, actualTotalPrice);
    }

    @Test
    public void testGetTotalAttackModification() throws Exception {
        // Arrange
        Mockito.when(this.attackModifyingPart.getAttackModifier()).thenReturn(50);
        AttackModifyingPart attackModifyingPart = Mockito.mock(AttackModifyingPart.class);
        Mockito.when(attackModifyingPart.getAttackModifier()).thenReturn(120);
        this.vehicleAssembler.addArsenalPart(attackModifyingPart);

        // Actual
        long actualTotalAttackModification = this.vehicleAssembler.getTotalAttackModification();
        long expectedTotalAttackModification = 170;

        // Assertion
        Assert.assertEquals(expectedTotalAttackModification, actualTotalAttackModification, 0.1);
    }

    @Test
    public void testGetTotalDefenseModification() throws Exception {
        // Arrange
        Mockito.when(this.defenseModifyingPart.getDefenseModifier()).thenReturn(50);
        DefenseModifyingPart defenseModifyingPart = Mockito.mock(DefenseModifyingPart.class);
        Mockito.when(defenseModifyingPart.getDefenseModifier()).thenReturn(120);
        this.vehicleAssembler.addShellPart(defenseModifyingPart);

        // Actual
        long actualTotalDefenseModification = this.vehicleAssembler.getTotalDefenseModification();
        long expectedTotalDefenseModification = 170;

        // Assertion
        Assert.assertEquals(expectedTotalDefenseModification, actualTotalDefenseModification, 0.1);
    }

    @Test
    public void testGetTotalHitPointModification() throws Exception {
        // Arrange
        Mockito.when(this.hitPointsModifyingPart.getHitPointsModifier()).thenReturn(50);
        HitPointsModifyingPart hitPointsModifyingPart = Mockito.mock(HitPointsModifyingPart.class);
        Mockito.when(hitPointsModifyingPart.getHitPointsModifier()).thenReturn(120);
        this.vehicleAssembler.addEndurancePart(hitPointsModifyingPart);

        // Actual
        long actualTotalHitPointModification = this.vehicleAssembler.getTotalHitPointModification();
        long expectedTotalHitPointModification = 170;

        // Assertion
        Assert.assertEquals(expectedTotalHitPointModification, actualTotalHitPointModification, 0.1);
    }

    @Test
    public void testAddArsenalPartCollectionSize() throws Exception {
        // Arrange
        Assembler assembler = new VehicleAssembler();
        AttackModifyingPart part = Mockito.mock(AttackModifyingPart.class);
        AttackModifyingPart part1 = Mockito.mock(AttackModifyingPart.class);
        AttackModifyingPart part2 = Mockito.mock(AttackModifyingPart.class);

        // Act
        assembler.addArsenalPart(part);
        assembler.addArsenalPart(part1);
        assembler.addArsenalPart(part2);
        // long actualTotalAttackModification = assembler.getTotalAttackModification();
        // long expectedTotalAttackModification = (long) Integer.MAX_VALUE + Integer.MAX_VALUE;
        int actualSize = 0;
        Field arsenalParts = assembler.getClass().getDeclaredField("arsenalParts");
        arsenalParts.setAccessible(true);
        List<AttackModifyingPart> parts = (List<AttackModifyingPart>) arsenalParts.get(assembler);
        actualSize = parts.size();
        int expectedSize = 3;

        // Assert
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void testAddArsenalPart() throws Exception {
        // Arrange
        Assembler assembler = new VehicleAssembler();
        AttackModifyingPart part = Mockito.mock(AttackModifyingPart.class);
        AttackModifyingPart part1 = Mockito.mock(AttackModifyingPart.class);
        Mockito.when(part.getAttackModifier()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(part1.getAttackModifier()).thenReturn(Integer.MAX_VALUE);

        // Act
        assembler.addArsenalPart(part);
        assembler.addArsenalPart(part1);
        long actualTotalAttackModification = assembler.getTotalAttackModification();
        long expectedTotalAttackModification = (long) Integer.MAX_VALUE + Integer.MAX_VALUE;


        // Assert
        Assert.assertEquals(expectedTotalAttackModification, actualTotalAttackModification);
    }

    @Test
    public void testAddShellPart() throws Exception {
        // Arrange
        Assembler assembler = new VehicleAssembler();
        DefenseModifyingPart part = Mockito.mock(DefenseModifyingPart.class);
        DefenseModifyingPart part1 = Mockito.mock(DefenseModifyingPart.class);
        Mockito.when(part.getDefenseModifier()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(part1.getDefenseModifier()).thenReturn(Integer.MAX_VALUE);

        // Act
        assembler.addShellPart(part);
        assembler.addShellPart(part1);
        long actualTotalDefenseModification = assembler.getTotalDefenseModification();
        long expectedTotalDefenseModification = (long) Integer.MAX_VALUE + Integer.MAX_VALUE;

        // Assert
        Assert.assertEquals(expectedTotalDefenseModification, actualTotalDefenseModification);
    }

    @Test
    public void testAddEndurancePart() throws Exception {
        // Arrange
        Assembler assembler = new VehicleAssembler();
        HitPointsModifyingPart part = Mockito.mock(HitPointsModifyingPart.class);
        HitPointsModifyingPart part1 = Mockito.mock(HitPointsModifyingPart.class);
        Mockito.when(part.getHitPointsModifier()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(part1.getHitPointsModifier()).thenReturn(Integer.MAX_VALUE);

        // Act
        assembler.addEndurancePart(part);
        assembler.addEndurancePart(part1);
        long actualTotalHitPointModification = assembler.getTotalHitPointModification();
        long expectedTotalHitPointModification = (long) Integer.MAX_VALUE + Integer.MAX_VALUE;

        // Assert
        Assert.assertEquals(expectedTotalHitPointModification, actualTotalHitPointModification);
    }
}