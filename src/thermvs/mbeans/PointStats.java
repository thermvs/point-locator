package thermvs.mbeans;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class PointStats extends NotificationBroadcasterSupport implements PointStatsMBean {
    private long sumAllPoint = 0;
    private long sumMissedPoint = 0;

    @Override
    public void addShot(boolean b) {
        sumAllPoint++;
        if (!b) {
            sumMissedPoint++;
            Notification notification = new Notification("false_point", this, sumMissedPoint,
                    System.currentTimeMillis(), "you have new false_point");
            this.sendNotification(notification);
        }
    }

    @Override
    public void clear() {
        sumAllPoint = 0;
        sumMissedPoint = 0;
    }

    @Override
    public long getSumAllPoint() {
        return sumAllPoint;
    }

    @Override
    public long getSumMissedPoint() {
        return sumMissedPoint;
    }
}
