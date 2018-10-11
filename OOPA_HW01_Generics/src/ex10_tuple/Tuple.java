package ex10_tuple;

public class Tuple<G, H> {
    private G item1;
    private H item2;

    public Tuple(G item1, H item2) {
        this.item1 = item1;
        this.item2 = item2;
    }

    public G getItem1() {
        return this.item1;
    }

    public void setItem1(G item1) {
        this.item1 = item1;
    }

    public H getItem2() {
        return this.item2;
    }

    public void setItem2(H item2) {
        this.item2 = item2;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s", this.item1, this.item2);
    }
}
