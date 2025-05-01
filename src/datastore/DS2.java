package datastore;

public class DS2 extends DataStore {
    private int temp_p;
    private int temp_v;
    private int price;
    private int cf;

    @Override
    public Object getTemp_p() {
        System.out.println("[DEBUG - DS2]: getTemp_p called, returning " + temp_p);
        return temp_p;
    }


    @Override
    public void setTemp_p(Object temp_p) {
        this.temp_p = (Integer) temp_p;
        System.out.println("[DEBUG - DS2]: temp_p set to " + this.temp_p);
    }


    @Override
    public Integer getTemp_v() { return temp_v; }

    @Override
    public void setTemp_v(Object temp_v) { this.temp_v = (Integer) temp_v; }

    @Override
    public Integer getPrice() { return price; }

    @Override
    public void setPrice(Object price) { this.price = (Integer) price; }

    @Override
    public Integer getCF() { return cf; }

    @Override
    public void setCF(Object cf) { this.cf = (Integer) cf; }


}
