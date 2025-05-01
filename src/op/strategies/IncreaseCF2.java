package op.strategies;

import datastore.DataStore;

public class IncreaseCF2 implements IncreaseCF {
    @Override
    public void increaseCF(DataStore d) {
        int current = (Integer) d.getCF();
        int value = (Integer) d.getTemp_v();
        d.setCF(current + value);
        System.out.println("[IncreaseCF2]: CF increased to " + d.getCF());
    }
}
