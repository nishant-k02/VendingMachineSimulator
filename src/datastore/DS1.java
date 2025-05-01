package datastore;

public class DS1 extends DataStore {
    private float temp_p;
    private float temp_v;
    private float price;
    private float cf;

    @Override
    public Float getTemp_p() { return temp_p; }

    @Override
    public void setTemp_p(Object temp_p) { this.temp_p = (Float) temp_p; }

    @Override
    public Float getTemp_v() { return temp_v; }

    @Override
    public void setTemp_v(Object temp_v) { this.temp_v = (Float) temp_v; }

    @Override
    public Float getPrice() { return price; }

    @Override
    public void setPrice(Object price) { this.price = (Float) price; }

    @Override
    public Float getCF() { return cf; }

    @Override
    public void setCF(Object cf) { this.cf = (Float) cf; }
}
