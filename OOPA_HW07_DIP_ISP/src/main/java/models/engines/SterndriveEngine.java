package models.engines;

public class SterndriveEngine extends BaseBoatEngine {
    private static final int STERNDRIVE_MULTIPLIER = 7;

    public int cachedOutput;

    public SterndriveEngine(String model, int horsepower, int displacement) {
        super(model, horsepower, displacement);
    }

    @Override
    protected int calculateOutput(int horsepower, int displacement) {
        return (horsepower * STERNDRIVE_MULTIPLIER) + displacement;
    }
}
