package op.strategies;

import datastore.DataStore;

public class IncreaseCF1 implements IncreaseCF {
    @Override
    public void increaseCF(DataStore d) {
        float current = (Float) d.getCF();
        float value = (Float) d.getTemp_v();
        d.setCF(current + value);
        System.out.println("[IncreaseCF1]: CF increased to " + d.getCF());
    }
}
