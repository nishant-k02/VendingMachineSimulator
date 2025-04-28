package state;

public class Start extends State {

    @Override
    public void create() {
        op.StorePrice();
        System.out.println("[Transition]: Start -> No Cups");
        mda.changeState(1); // Move to NoCups
    }
}
