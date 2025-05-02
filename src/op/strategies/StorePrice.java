package op.strategies;

import datastore.DataStore;

// [STRATEGY PATTERN] Strategy Interface for storing price

public interface StorePrice {
    void storePrice(DataStore d);
}
