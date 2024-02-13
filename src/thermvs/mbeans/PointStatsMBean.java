package thermvs.mbeans;

public interface PointStatsMBean {
    void addShot(boolean isSuccessful);

    long getSumAllPoint();

    long getSumMissedPoint();

    void clear();
}
