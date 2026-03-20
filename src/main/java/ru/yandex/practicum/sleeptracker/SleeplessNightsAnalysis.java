package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
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
                .map(this::resolveNightDate)
                .distinct()
                .count();

        LocalDate firstNight = sessions.stream()
                .map(this::resolveNightDate)
                .min(LocalDate::compareTo)
                .orElseThrow();

        LocalDate lastNight = sessions.stream()
                .map(this::resolveNightDate)
                .max(LocalDate::compareTo)
                .orElseThrow();

        long totalNights = ChronoUnit.DAYS.between(firstNight, lastNight) + 1;

        long sleepless = totalNights - nightsWithSleep;

        return new SleepAnalysisResult(DESCRIPTION, Math.max(0, sleepless));
    }

    private LocalDate resolveNightDate(SleepingSession session) {
        LocalDate date = session.getStart().toLocalDate();

        if (session.getStart().toLocalTime().isBefore(NIGHT_END)) {
            return date.minusDays(1);
        }

        return date;
    }

    private boolean intersectsNight(SleepingSession session) {

        LocalTime start = session.getStart().toLocalTime();
        LocalTime end = session.getEnd().toLocalTime();

        return start.isBefore(LocalTime.of(6, 0)) ||
                end.isAfter(LocalTime.MIDNIGHT);
    }
}