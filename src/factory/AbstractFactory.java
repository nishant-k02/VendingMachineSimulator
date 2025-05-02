package factory;

import datastore.DataStore;
import op.strategies.*;

// [ABSTRACT FACTORY PATTERN] Abstract Factory defining VM components

public abstract class AbstractFactory {
    public abstract DataStore createDataStore();
    public abstract StorePrice createStorePrice();
    public abstract ReturnCoins createReturnCoins();
    public abstract IncreaseCF createIncreaseCF();
    public abstract DisposeDrink createDisposeDrink();
    public abstract DisposeAdditives createDisposeAdditives();
    public abstract ZeroCF createZeroCF();
}
