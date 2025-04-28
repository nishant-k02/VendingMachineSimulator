package op.strategies;

import datastore.DataStore;

public class DisposeDrink2 implements DisposeDrink {
    @Override
    public void disposeDrink(DataStore d, int drinkType) {
        if (drinkType == 1) {
            System.out.println("Dispensing Coffee (VM2)");
        }
    }
}
