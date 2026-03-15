package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Function;

public class SleeplessNightsAnalysis implements Function<List<SleepingSession>, SleepAnalysisResult> {

    private static final String DESCRIPTION = "Sleepless nights";
    private static final LocalTime NIGHT_END = LocalTime.of(6, 0);

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {

        if (sessions.isEmpty()) {
            return new SleepAnalysisResult(DESCRIPTION, 0L);
        }

        long nightsWithSleep = sessions.stream()
                .filter(this::intersectsNight)
                .map(s -> {
                    LocalDate date = s.getStart().toLocalDate();
                    if (s.getStart().toLocalTime().isBefore(NIGHT_END)) {
                        return date.minusDays(1);
                    }
                    return date;
                })
                .distinct()
                .count();

        LocalDate firstNight = sessions.stream()
                .map(s -> s.getStart().toLocalDate())
                .min(LocalDate::compareTo)
                .orElseThrow();

        LocalDate lastNight = sessions.stream()
                .map(s -> s.getStart().toLocalDate())
                .max(LocalDate::compareTo)
                .orElseThrow();

        long totalNights = ChronoUnit.DAYS.between(firstNight, lastNight);

        long sleepless = totalNights - nightsWithSleep;

        return new SleepAnalysisResult(DESCRIPTION, Math.max(0, sleepless));
    }

    private boolean intersectsNight(SleepingSession s) {

        LocalDateTime start = s.getStart();
        LocalDateTime end = s.getEnd();

        LocalDateTime nightStart = start.toLocalDate().atStartOfDay();
        LocalDateTime nightEnd = nightStart.plusHours(6);

        return end.isAfter(nightStart) && start.isBefore(nightEnd);
    }
}