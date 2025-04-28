package state;


public class Idle extends State {

    @Override
    public void coin(int f) {
        if (f != 0) {
            op.IncreaseCF();
            mda.changeState(3); // move to CoinsInserted
        }
    }

    @Override
    public void card() {
        op.ZeroCF();
        // A = 0 -> resetting additives
        for (int i = 0; i < mda.A.length; i++) {
            mda.A[i] = 0;
        }
        mda.changeState(3); // move to CoinsInserted
    }

    @Override
    public void insert_cups(int n) {
        if (n > 0) {
            mda.k += n;
        }
    }

    @Override
    public void setPrice() {
        op.StorePrice();
    }
}
