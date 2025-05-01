package vm;

import datastore.*;
import mda_efsm.MDA_EFSM;

public class VM2 {
    private MDA_EFSM mda;
    private DS2 ds;

    public VM2(MDA_EFSM mda, DataStore ds) {
        this.mda = mda;
        this.ds = (DS2) ds; // Type cast to DS2
    }

//    public void CREATE(int p) {
//        ds.setTemp_p((Object) p);
//        mda.create();
//    }

    public void COIN(int v) {
        ds.setTemp_v((Object) v);
        mda.coin(1);
    }

    public void InsertCups(int n) {
        mda.insert_cups(n);
    }

    public void SetPrice(int p) {
        ds.setTemp_p((Object) p);
        mda.setPrice();
    }

    public void SUGAR() {
        mda.additives(0);
    }

    public void CREAM() {
        mda.additives(1);
    }

    public void COFFEE() {
        mda.disposeDrink(1);
    }

    public void CANCEL() {
        mda.cancel();
    }

    public void setTemp_p(int p) {
        ds.setTemp_p((Object) p);
    }

    public void CREATE() {
        mda.create();  // This will now use the temp_p already set
    }



}
