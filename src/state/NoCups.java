package state;

import datastore.DataStore;
import datastore.DS1;
import datastore.DS2;

// [STATE PATTERN] Concrete State: No cups

public class NoCups extends State {

    @Override
    public void insert_cups(int n) {
        if (n > 0) {
            mda.k = n;
            System.out.println("[Transition]: NoCups -> Idle (cups inserted: " + n + ")");
            op.ZeroCF();  // Reset credit
            mda.changeState(2); // Move to Idle
        } else {
            System.out.println("[NoCups]: Please insert more than 0 cups.");
        }
    }

    @Override
    public void coin(int f) {
        DataStore ds = mda.getDataStore();

        if (ds instanceof DS1) {
            System.out.println("[NoCups - VM1]: Returning coin. No cups available.");
        } else if (ds instanceof DS2) {
            System.out.println("[NoCups - VM2]: Returning coin. No cups available.");
        }

        op.ReturnCoins();
    }
}
