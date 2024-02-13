package thermvs.point;

public class Point {

    private final float x;
    private final float y;
    private final float r;
    private final boolean status;

    public Point(float x, float y, float r, boolean status) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.status = status;
    }

    public boolean status() {
        return status;
    }

    @Override
    public String toString() {
        return String.format("x = %s \ny = %s \nr = %s \n status: %s", x, y, r, status ? "yes" : "no");
    }

}
