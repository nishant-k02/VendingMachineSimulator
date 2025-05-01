package vm;

import datastore.*;
import mda_efsm.MDA_EFSM;
import op.OP;

public class VM1 {
    private MDA_EFSM mda;
    private DS1 ds;

    public VM1(MDA_EFSM mda, DataStore ds) {
        this.mda = mda;
        this.ds = (DS1) ds; // Type cast to DS1
    }

    public void create(float p) {
        ds.setTemp_p(p);
        mda.create();
    }

    public void coin(float v) {
        ds.setTemp_v(v);
        mda.coin(v >= ds.getPrice() ? 1 : 0);
    }

    public void card(float x) {
        ds.setTemp_v(x);
        mda.card();
    }

    public void insert_cups(int n) {
        mda.insert_cups(n);
    }

    public void set_price(float p) {
        ds.setTemp_p(p);
        mda.setPrice();
    }

    public void sugar() {
        mda.additives(0);
    }

    public void chocolate() {
        mda.disposeDrink(1);
    }

    public void cappuccino() {
        mda.disposeDrink(3);
    }

    public void cancel() {
        mda.cancel();
    }

}
