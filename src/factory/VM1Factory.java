package factory;

import datastore.*;
import op.strategies.*;

// [ABSTRACT FACTORY PATTERN] Concrete factory that provides all components specific to VM1
public class VM1Factory extends AbstractFactory {

    @Override
    public DataStore createDataStore() {
        return new DS1(); // [ABSTRACT FACTORY PATTERN] Provides VM1-specific DataStore
    }

    @Override
    public StorePrice createStorePrice() {
        return new StorePrice1(); // [STRATEGY PATTERN] Concrete strategy for storing price
    }

    @Override
    public ReturnCoins createReturnCoins() {
        return new ReturnCoins1();
    }

    @Override
    public IncreaseCF createIncreaseCF() {
        return new IncreaseCF1();
    }

    @Override
    public DisposeDrink createDisposeDrink() {
        return new DisposeDrink1();
    }

    @Override
    public DisposeAdditives createDisposeAdditives() {
        return new DisposeAdditives1();
    }

    @Override
    public ZeroCF createZeroCF() {
        return new ZeroCF1();
    }
}
