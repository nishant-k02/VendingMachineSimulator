package datastore;

public abstract class DataStore {
    public abstract Object getTemp_p();
    public abstract void setTemp_p(Object temp_p);

    public abstract Object getTemp_v();
    public abstract void setTemp_v(Object temp_v);

    public abstract Object getPrice();
    public abstract void setPrice(Object price);

    public abstract Object getCF();
    public abstract void setCF(Object cf);
}
