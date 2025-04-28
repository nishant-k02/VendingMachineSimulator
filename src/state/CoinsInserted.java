package state;

public class CoinsInserted extends State {

    @Override
    public void cancel() {
        op.ReturnCoins();
        System.out.println("[Transition]: Coins Inserted -> Idle (cancelled)");
        mda.changeState(2); // Back to Idle
    }

    @Override
    public void disposeDrink(int d) {
        if (mda.k > 0) {
            op.DisposeDrink(d);
            op.DisposeAdditives(mda.A);
            mda.k = mda.k - 1;
            op.ZeroCF();
            System.out.println("[Drink Served]: Remaining cups = " + mda.k);
            if (mda.k == 0) {
                System.out.println("[Transition]: CoinsInserted -> No Cups (no cups left)");
                mda.changeState(1); // Move to NoCups
            } else {
                System.out.println("[Transition]: CoinsInserted -> Idle");
                mda.changeState(2); // Move to Idle
            }
        } else {
            System.out.println("[Error]: No cups available to dispense drink.");
        }
    }

    @Override
    public void additives(int a) {
        if (a >= 0 && a < mda.A.length) {
            if (mda.A[a] == 0) {
                mda.A[a] = 1;
                op.DisposeAdditives(mda.A);
                System.out.println("[Additive]: Added additive " + (a + 1));
            } else {
                System.out.println("[Additive]: Additive " + (a + 1) + " already added.");
            }
        } else {
            System.out.println("[Error]: Invalid additive selected.");
        }
    }

    @Override
    public void coin(int f) {
        System.out.println("[CoinsInserted]: Additional coins not accepted. Please select drink or cancel.");
    }
}
