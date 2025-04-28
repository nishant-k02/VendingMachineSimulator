package datastore;

// Abstract class
public abstract class DataStore {
    public float temp_p;
    public float temp_v;
    public float price;
    public float cf;

    // Getter and setter methods
    public abstract float getTemp_p();
    public abstract void setTemp_p(float temp_p);
    public abstract float getTemp_v();
    public abstract void setTemp_v(float temp_v);
    public abstract float getPrice();
    public abstract void setPrice(float price);
    public abstract float getCF();
    public abstract void setCF(float cf);
}
