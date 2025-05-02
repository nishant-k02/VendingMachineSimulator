package mda_efsm;

import op.OP;
import state.Start;
import state.NoCups;
import state.Idle;
import state.CoinsInserted;
import state.State;

// [STATE PATTERN] Context class that holds and delegates behavior to current state
public class MDA_EFSM {
    // [STATE PATTERN] States array holding concrete state instances
    private State[] states = new State[4];

    // [STATE PATTERN] Tracks current active state
    private int stateID;

    // Number of cups remaining
    public int k;

    // [STATE PATTERN] Shared additive flags (0 or 1): sugar, cream, etc.
    public int[] A = new int[3];

    // [STATE PATTERN] Constructor initializes states and injects OP
    public MDA_EFSM(OP op) {
        states[0] = new Start();         // S0
        states[1] = new NoCups();        // S1
        states[2] = new Idle();          // S2
        states[3] = new CoinsInserted(); // S3

        // Inject this MDA_EFSM and OP into each state
        for (State s : states) {
            s.setMDA_EFSM(this);
            s.setOP(op);
        }

        // Initial state is Start
        stateID = 0;
    }

    // [STATE PATTERN] Change current state
    public void changeState(int id) {
        this.stateID = id;
    }

    // Get current state index (for debugging or logging)
    public int getStateID() {
        return stateID;
    }

    // === Event delegation ===
    // [STATE PATTERN] Delegate 'create' to current state
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

    // Expose DataStore through OP for strategy classes and debugging
    public datastore.DataStore getDataStore() {
        return states[stateID].op.getDataStore();
    }
}
