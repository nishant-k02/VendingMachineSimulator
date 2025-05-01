package op;

import datastore.DataStore;
import op.strategies.*;


public class OP {
    private DataStore d;
    private StorePrice storePrice;
    private ReturnCoins returnCoins;
    private IncreaseCF increaseCF;
    private DisposeDrink disposeDrink;
    private DisposeAdditives disposeAdditives;
    private ZeroCF zeroCF;
    private factory.AbstractFactory factory;  // optional, if needed

    public void setDataStore(DataStore d) {
        this.d = d;
    }

    public void setAbstractFactory(factory.AbstractFactory factory) {
        this.factory = factory;
    }

    public void setStorePrice(StorePrice sp) {
        this.storePrice = sp;
    }

    public void setReturnCoins(ReturnCoins rc) {
        this.returnCoins = rc;
    }

    public void setIncreaseCF(IncreaseCF icf) {
        this.increaseCF = icf;
    }

    public void setDisposeDrink(DisposeDrink dd) {
        this.disposeDrink = dd;
    }

    public void setDisposeAdditives(DisposeAdditives da) {
        this.disposeAdditives = da;
    }

    public void setZeroCF(ZeroCF zcf) {
        this.zeroCF = zcf;
    }

    public void StorePrice() {
        storePrice.storePrice(d);
    }

    public void ReturnCoins() {
        returnCoins.returnCoins(d);
    }

    public void IncreaseCF() {
        increaseCF.increaseCF(d);
    }

    public void DisposeDrink(int d1) {
        disposeDrink.disposeDrink(d, d1);
    }

    public void DisposeAdditives(int[] A) {
        disposeAdditives.disposeAdditives(d, A);
    }

    public void ZeroCF() {
        zeroCF.zeroCF(d);
    }

    public DataStore getDataStore() {
        return d;
    }

}
