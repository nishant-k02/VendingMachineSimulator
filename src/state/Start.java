package state;

public class Start extends State {

    @Override
    public void create() {
        op.StorePrice();
        mda.changeState(1); // move to "No Cups" state
    }
}
