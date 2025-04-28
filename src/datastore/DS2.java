package datastore;

// Concrete DataStore for VM2
public class DS2 extends DataStore {

    @Override
    public float getTemp_p() { return temp_p; }

    @Override
    public void setTemp_p(float temp_p) { this.temp_p = temp_p; }

    @Override
    public float getTemp_v() { return temp_v; }

    @Override
    public void setTemp_v(float temp_v) { this.temp_v = temp_v; }

    @Override
    public float getPrice() { return price; }

    @Override
    public void setPrice(float price) { this.price = price; }

    @Override
    public float getCF() { return cf; }

    @Override
    public void setCF(float cf) { this.cf = cf; }
}
