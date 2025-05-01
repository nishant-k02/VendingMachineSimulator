package mda_efsm;

import op.OP;
import state.Start;
import state.NoCups;
import state.Idle;
import state.CoinsInserted;
import state.State;

public class MDA_EFSM {
    private State[] states = new State[4];
    private int stateID;
    public int k; // cups
    public int[] A = new int[3]; // additives A[0], A[1], A[2]

    public MDA_EFSM(OP op) {
        states[0] = new Start();
        states[1] = new NoCups();
        states[2] = new Idle();
        states[3] = new CoinsInserted();
        for (State s : states) {
            s.setMDA_EFSM(this);
            s.setOP(op);
        }
        stateID = 0;
    }

    public void changeState(int id) {
        this.stateID = id;
    }

    public int getStateID() {
        return stateID;
    }

    public void create() { states[stateID].create(); }
    public void insert_cups(int n) { states[stateID].insert_cups(n); }
    public void coin(int f) { states[stateID].coin(f); }
    public void card() { states[stateID].card(); }
    public void setPrice() { states[stateID].setPrice(); }
    public void disposeDrink(int d) { states[stateID].disposeDrink(d); }
    public void additives(int a) { states[stateID].additives(a); }
    public void cancel() { states[stateID].cancel(); }

    public datastore.DataStore getDataStore() {
        return states[stateID].op.getDataStore();
    }

}
