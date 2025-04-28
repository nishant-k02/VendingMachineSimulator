package state;

public class NoCups extends State {

    @Override
    public void insert_cups(int n) {
        if (n > 0) {
            mda.k = n;
            System.out.println("[Transition]: No Cups -> Idle (cups inserted: " + n + ")");
            op.ZeroCF();
            mda.changeState(2); // Move to Idle
        } else {
            System.out.println("[No Cups]: Insert more than 0 cups.");
        }
    }

    @Override
    public void coin(int f) {
        System.out.println("[No Cups]: Returning coin as no cups are available.");
        op.ReturnCoins();
    }
}
