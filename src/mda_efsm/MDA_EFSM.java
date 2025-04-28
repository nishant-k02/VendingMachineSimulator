package mda_efsm;

import op.OP;

// ✅ Import State classes from state package
import state.State;
import state.Start;
import state.NoCups;
import state.Idle;
import state.CoinsInserted;

public class MDA_EFSM {
    private State[] states = new State[4];
    private int stateID; // 0=start, 1=no cups, 2=idle, 3=coins inserted
    public int k; // number of cups
    public int[] A = new int[3]; // additives

    public MDA_EFSM(OP op) {
        states[0] = new Start();
        states[1] = new NoCups();
        states[2] = new Idle();
        states[3] = new CoinsInserted();
        for (State s : states) {
            s.setMDA_EFSM(this);
            s.setOP(op);
        }
        stateID = 0; // initial state: Start
    }

    public void changeState(int id) {
        stateID = id;
    }

    public int getCurrentStateId() {
        return stateID;
    }

    public void create() {
        states[stateID].create();
    }

    public void insert_cups(int n) {
        states[stateID].insert_cups(n);
    }

    public void coin(int f) {
        states[stateID].coin(f);
    }

    public void card() {
        states[stateID].card();
    }

    public void setPrice() {
        states[stateID].setPrice();
    }

    public void disposeDrink(int d) {
        states[stateID].disposeDrink(d);
    }

    public void additives(int a) {
        states[stateID].additives(a);
    }

    public void cancel() {
        states[stateID].cancel();
    }
}
