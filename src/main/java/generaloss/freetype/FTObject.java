package generaloss.freetype;

public class FTObject {

    protected long pointer;

    protected FTObject(long pointer) {
        if(pointer == 0L) {
            throw new IllegalArgumentException(
                "Invalid pointer (0). Unable to create " + this.getClass().getSimpleName() +
                " object. FT last error: " + FTLibrary.getLastError()
            );
        }
        this.pointer = pointer;
    }

    public long getPointer() {
        return pointer;
    }

    protected void done() {
        pointer = 0L;
    }

}
