package ex11_threeuple;

public class Threeuple<G, H, I> {
    private G item1;
    private H item2;
    private I item3;

    public Threeuple(G item1, H item2, I item3) {
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
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

    public I getItem3() {
        return this.item3;
    }

    public void setItem3(I item3) {
        this.item3 = item3;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s -> %s", this.item1, this.item2, this.item3);
    }
}
