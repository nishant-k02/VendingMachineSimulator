package state;

public class NoCups extends State {

    @Override
    public void insert_cups(int n) {
        if (n > 0) {
            mda.k = n;
            op.ZeroCF(); // Important: Reset Coin Flag
            mda.changeState(2); // move to Idle
        }
    }

    @Override
    public void coin(int f) {
        op.ReturnCoins(); // return coins if no cups
    }
}
