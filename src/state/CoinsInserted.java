package state;

import datastore.DataStore;
import datastore.DS1;
import datastore.DS2;

public class CoinsInserted extends State {

    @Override
    public void cancel() {
        op.ReturnCoins();
        System.out.println("[Transition]: CoinsInserted -> Idle (cancelled)");
        op.ZeroCF();
        mda.changeState(2); // Back to Idle
    }

    @Override
    public void disposeDrink(int d) {
        if (mda.k > 0) {
            op.DisposeDrink(d);
            op.DisposeAdditives(mda.A);
            mda.k -= 1;

            op.ZeroCF(); // Reset credit after dispensing

            System.out.println("[Drink Served]: Remaining cups = " + mda.k);
            if (mda.k == 0) {
                System.out.println("[Transition]: CoinsInserted -> NoCups (no cups left)");
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
        DataStore ds = mda.getDataStore();

        if (ds instanceof DS1) {
            System.out.println("[CoinsInserted - VM1]: Additional coins not accepted. Please select drink or cancel.");
        } else if (ds instanceof DS2) {
            System.out.println("[CoinsInserted - VM2]: Returning extra coins.");
            op.ReturnCoins();
        }
    }
}
