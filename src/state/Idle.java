package state;

public class Idle extends State {

    @Override
    public void insert_cups(int n) {
        if (n > 0) {
            mda.k += n;
            System.out.println("[Idle]: Added cups. Total cups now: " + mda.k);
        }
    }

    @Override
    public void setPrice() {
        op.StorePrice();
        System.out.println("[Idle]: Price updated.");
    }

    @Override
    public void coin(int f) {
        if (f == 0) {
            op.IncreaseCF();
            System.out.println("[Idle]: Coin inserted. Credit increased.");
        } else if (f == 1) {
            op.IncreaseCF();
            System.out.println("[Transition]: Idle -> Coins Inserted");
            mda.changeState(3); // Move to CoinsInserted
        }
    }

    @Override
    public void card() {
        op.ZeroCF();
        System.out.println("[Transition]: Idle -> Coins Inserted (card used)");
        mda.changeState(3); // Move to CoinsInserted
    }
}
