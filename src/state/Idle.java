package state;

import datastore.DS1;
import datastore.DS2;
import datastore.DataStore;

// [STATE PATTERN] Concrete State: Idle

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
        op.IncreaseCF();  // ✅ Always increase CF regardless of input

        DataStore ds = mda.getDataStore();

        boolean sufficient = false;
        float cfFloat = 0, priceFloat = 0;
        int cfInt = 0, priceInt = 0;

        if (ds instanceof DS1) {
            cfFloat = (Float) ds.getCF();
            priceFloat = (Float) ds.getPrice();
            System.out.printf("[DEBUG] VM1 - CF = %.2f, Price = %.2f%n", cfFloat, priceFloat);
            sufficient = cfFloat >= priceFloat;
        } else if (ds instanceof DS2) {
            cfInt = (Integer) ds.getCF();
            priceInt = (Integer) ds.getPrice();
            System.out.printf("CF = %d, Price = %d%n", cfInt, priceInt);
            sufficient = cfInt >= priceInt;
        }

        if (sufficient) {
            System.out.println("[Transition]: Idle -> CoinsInserted");
            mda.changeState(3); // Move to CoinsInserted
        } else {
            if (ds instanceof DS1)
                System.out.printf("[Idle]: Insert %.2f more to proceed.%n", priceFloat - cfFloat);
            else
                System.out.printf("[Idle]: Insert %d more to proceed.%n", priceInt - cfInt);
        }
    }

    @Override
    public void card() {
        op.ZeroCF();
        System.out.println("[Transition]: Idle -> Coins Inserted (card used)");
        mda.changeState(3); // Move to CoinsInserted
    }
}
