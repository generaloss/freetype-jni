package generaloss.freetype.types;

public class FTGeneric { // struct done.

    private Object data;
    private Runnable finalizer;


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Runnable getFinalizer() {
        return finalizer;
    }

    public void setFinalizer(Runnable finalizer) {
        this.finalizer = finalizer;
    }


    public void set(Object data, Runnable finalizer) {
        this.data = data;
        this.finalizer = finalizer;
    }

}
