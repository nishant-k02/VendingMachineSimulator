package factory;

import datastore.*;
import op.strategies.*;

// [ABSTRACT FACTORY PATTERN] Concrete Factory for VM2

public class VM2Factory extends AbstractFactory {

    @Override
    public DataStore createDataStore() {
        return new DS2();
    }

    @Override
    public StorePrice createStorePrice() {
        return new StorePrice2();
    }

    @Override
    public ReturnCoins createReturnCoins() {
        return new ReturnCoins2();
    }

    @Override
    public IncreaseCF createIncreaseCF() {
        return new IncreaseCF2();
    }

    @Override
    public DisposeDrink createDisposeDrink() {
        return new DisposeDrink2();
    }

    @Override
    public DisposeAdditives createDisposeAdditives() {
        return new DisposeAdditives2();
    }

    @Override
    public ZeroCF createZeroCF() {
        return new ZeroCF2();
    }

}
