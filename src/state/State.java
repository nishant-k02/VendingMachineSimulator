package state;

import mda_efsm.MDA_EFSM;
import op.OP;

// [STATE PATTERN] Abstract base class for states

public abstract class State {
    protected MDA_EFSM mda;
    public OP op;

    public void setMDA_EFSM(MDA_EFSM mda) {
        this.mda = mda;
    }

    public void setOP(OP op) {
        this.op = op;
    }

    public void create() {}
    public void insert_cups(int n) {}
    public void coin(int f) {}
    public void card() {}
    public void setPrice() {}
    public void disposeDrink(int d) {}
    public void additives(int a) {}
    public void cancel() {}
}
