package state;

public class CoinsInserted extends State {

    @Override
    public void coin(int f) {
        if (f != 0) {
            op.IncreaseCF();
        }
    }

    @Override
    public void cancel() {
        op.ReturnCoins();
        op.ZeroCF();
        mda.changeState(2); // move back to Idle
    }

    @Override
    public void additives(int a) {
        if (mda.A[a] == 0) {
            mda.A[a] = 1;
        }
    }

    @Override
    public void disposeDrink(int d) {
        if (mda.k >= 1) {
            op.DisposeDrink(d);
            op.DisposeAdditives(mda.A);
            mda.k--;
            op.ZeroCF();
            if (mda.k > 0) {
                mda.changeState(2); // move to Idle
            } else {
                mda.changeState(1); // move to No Cups
            }
        }
    }
}
