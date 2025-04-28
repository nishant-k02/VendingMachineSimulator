package op.strategies;

import datastore.DataStore;

public class DisposeAdditives2 implements DisposeAdditives {
    @Override
    public void disposeAdditives(DataStore d, int[] A) {
        if (A[0] == 1) System.out.println("Adding Sugar (VM2)");
        if (A[1] == 1) System.out.println("Adding Cream (VM2)");
    }
}
