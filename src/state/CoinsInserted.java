package state;

import datastore.DataStore;
import datastore.DS1;
import datastore.DS2;

// [STATE PATTERN] Concrete State: Coin Inserted

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
        op.DisposeDrink(d);                 // Dispose selected drink
        op.DisposeAdditives(mda.A);        // Add additives
        op.ZeroCF();                        // Reset credit

        // ✅ RESET ADDITIVES after serving the drink
        for (int i = 0; i < mda.A.length; i++) {
            mda.A[i] = 0;
        }

        mda.k--;                            // Reduce cup count
        System.out.println("[Drink Served]: Remaining cups = " + mda.k);

        // ✅ Check for next state
        if (mda.k <= 0) {
            System.out.println("[Transition]: CoinsInserted -> NoCups (no cups left)");
            mda.changeState(1);  // NoCups
        } else {
            System.out.println("[Transition]: CoinsInserted -> Idle");
            mda.changeState(2);  // Idle
        }
    }



    @Override
    public void additives(int a) {
        if (a < 0 || a >= mda.A.length) {
            System.out.println("[Error]: Invalid additive index.");
            return;
        }

        if (mda.A[a] == 0) {
            mda.A[a] = 1;
            op.DisposeAdditives(mda.A);
            System.out.println("[Additive]: Added additive " + (a + 1));
        } else {
            System.out.println("[Additive]: Additive " + (a + 1) + " already added.");
        }
    }

    @Override
    public void coin(int f) {
        // ✅ Consistent with NoCups behavior
        System.out.println("[CoinsInserted]: Cannot accept more coins. Returning coin...");
        op.ReturnCoins();
    }
}
