package op.strategies;

import datastore.DataStore;

public class DisposeDrink1 implements DisposeDrink {
    @Override
    public void disposeDrink(DataStore d, int drinkType) {
        if (drinkType == 1) {
            System.out.println("Dispensing Chocolate (VM1)");
        } else if (drinkType == 2) {
            System.out.println("Dispensing Coffee (VM1)");
        } else if (drinkType == 3) {
            System.out.println("Dispensing Cappuccino (VM1)");
        }
    }
}
