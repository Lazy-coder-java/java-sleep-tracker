package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Function;

public class SleeplessNightsAnalysis implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {

        if (sessions.isEmpty()) {
            return new SleepAnalysisResult("Sleepless nights", 0);
        }

        LocalDate first = sessions.get(0).getStart().toLocalDate();
        LocalDate last = sessions.get(sessions.size() - 1).getEnd().toLocalDate();

        long totalNights = ChronoUnit.DAYS.between(first, last);

        long nightsWithSleep = sessions.stream()
                .filter(this::intersectsNight)
                .map(s -> {
                    LocalDate date = s.getStart().toLocalDate();
                    if (s.getStart().toLocalTime().isBefore(LocalTime.of(6, 0))) {
                        return date.minusDays(1);
                    }
                    return date;
                })
                .distinct()
                .count();

        long sleepless = totalNights - nightsWithSleep;

        return new SleepAnalysisResult(
                "Sleepless nights",
                sleepless
        );
    }

    private boolean intersectsNight(SleepingSession s) {

        LocalTime start = s.getStart().toLocalTime();
        LocalTime end = s.getEnd().toLocalTime();

        return start.isBefore(LocalTime.of(6,0))
                || end.isAfter(LocalTime.MIDNIGHT);
    }
}
