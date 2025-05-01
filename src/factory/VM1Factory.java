package factory;

import datastore.*;
import op.strategies.*;

public class VM1Factory extends AbstractFactory {

    @Override
    public DataStore createDataStore() {
        return new DS1();
    }

    @Override
    public StorePrice createStorePrice() {
        return new StorePrice1();
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
