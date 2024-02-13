package thermvs.mbeans;

import javax.management.NotificationBroadcasterSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ClickInterval extends NotificationBroadcasterSupport implements Serializable, ClickIntervalMBean {
    long lastPointTime;

    List<Long> AllInterval;

    public ClickInterval() {
        lastPointTime = System.currentTimeMillis();
        AllInterval = new ArrayList<>();
    }

    public long countInterval() {
        if (AllInterval.isEmpty()) {
            return 0;
        }

        long totalInterval = 0;
        for (long pointInterval : AllInterval) {
            totalInterval += pointInterval;
        }

        return TimeUnit.MILLISECONDS.toSeconds(totalInterval / AllInterval.size());
    }

    @Override
    public void addPoint() {
        long currentShotTime = System.currentTimeMillis();
        AllInterval.add(currentShotTime - lastPointTime);
        lastPointTime = currentShotTime;
    }

}


