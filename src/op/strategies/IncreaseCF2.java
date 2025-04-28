package op.strategies;

import datastore.DataStore;

public class IncreaseCF2 implements IncreaseCF {
    @Override
    public void increaseCF(DataStore d) {
        d.setCF(d.getCF() + d.getTemp_v());
    }
}
