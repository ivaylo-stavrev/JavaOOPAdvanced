package models.engines;

public class JetEngine extends BaseBoatEngine {
    private static final int JET_MULTIPLIER = 5;

    public JetEngine(String model, int horsepower, int displacement)
    {
        super(model, horsepower, displacement);
    }

    @Override
    public int calculateOutput(int horsepower, int displacement) {
        return (horsepower * JET_MULTIPLIER) + displacement;
    }
}
