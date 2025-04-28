package op.strategies;

import datastore.DataStore;

public class DisposeAdditives1 implements DisposeAdditives {
    @Override
    public void disposeAdditives(DataStore d, int[] A) {
        if (A[0] == 1) System.out.println("Adding Sugar (VM1)");
        if (A[1] == 1) System.out.println("Adding Cream (VM1)");
    }
}
