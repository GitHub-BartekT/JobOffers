package pl.iseebugs.JobOffers.domain.scheduler;

import java.time.Clock;

class TimeValidator {
    private static long lastCallTime = 0;
    private static final long HOUR_IN_MILIS = 3600 * 1000;

    static boolean wasCalledWithinLastHour(Clock clock){
        long currentTime = clock.millis();
        boolean result = currentTime - HOUR_IN_MILIS < lastCallTime;
        lastCallTime = currentTime;
        return result;
    }
}
