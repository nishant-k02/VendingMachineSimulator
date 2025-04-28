package state;
import mda_efsm.MDA_EFSM;

import op.OP;

public abstract class State {
    MDA_EFSM mda;
    OP op;

    public void setMDA_EFSM(MDA_EFSM mda) {
        this.mda = mda;
    }

    public void setOP(OP op) {
        this.op = op;
    }

    // Meta events to override in each concrete state
    public void create() {}
    public void insert_cups(int n) {}
    public void coin(int f) {}
    public void card() {}
    public void setPrice() {}
    public void disposeDrink(int d) {}
    public void additives(int a) {}
    public void cancel() {}
}
