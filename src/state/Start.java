package state;

import datastore.DataStore;
import datastore.DS1;
import datastore.DS2;

public class Start extends State {

    @Override
    public void create() {
        op.StorePrice();  // This will store temp_p into price based on DS1 or DS2

        DataStore ds = mda.getDataStore();
        if (ds instanceof DS1) {
            System.out.printf("[Start - VM1]: Price stored as %.2f%n", (Float) ds.getPrice());
        } else if (ds instanceof DS2) {
            System.out.printf("[Start - VM2]: Price stored as %d%n", (Integer) ds.getPrice());
        }

        System.out.println("[Transition]: Start -> NoCups");
        mda.changeState(1); // Move to NoCups
    }
}
