package ru.yandex.practicum.sleeptracker;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChronotypeAnalysis implements Function<List<SleepingSession>, SleepAnalysisResult> {

    private static final String DESCRIPTION = "User chronotype";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {

        Map<Chronotype, Long> counts = sessions.stream()
                .filter(this::isNightSleep).collect(Collectors.groupingBy(this::classify, Collectors.counting()));

        long owl = counts.getOrDefault(Chronotype.OWL, 0L);
        long lark = counts.getOrDefault(Chronotype.LARK, 0L);
        long pigeon = counts.getOrDefault(Chronotype.PIGEON, 0L);

        Chronotype result;

        if (owl > lark && owl > pigeon) {
            result = Chronotype.OWL;
        } else if (lark > owl && lark > pigeon) {
            result = Chronotype.LARK;
        } else {
            result = Chronotype.PIGEON;
        }

        return new SleepAnalysisResult(DESCRIPTION, result);
    }

    private boolean isNightSleep(SleepingSession session) {

        LocalTime start = session.getStart().toLocalTime();
        LocalTime end = session.getEnd().toLocalTime();

        return start.isAfter(LocalTime.of(18, 0))
                || start.isBefore(LocalTime.of(6, 0))
                || end.isBefore(LocalTime.of(6, 0));
    }

    private Chronotype classify(SleepingSession session) {

        LocalTime sleep = session.getStart().toLocalTime();
        LocalTime wake = session.getEnd().toLocalTime();

        boolean crossesMidnight = session.getEnd().toLocalDate().isAfter(session.getStart().toLocalDate());

        if ((sleep.isAfter(LocalTime.of(23, 0)) || crossesMidnight)
                && wake.isAfter(LocalTime.of(9, 0))) {
            return Chronotype.OWL;
        }

        if (sleep.isBefore(LocalTime.of(22, 0))
                && wake.isBefore(LocalTime.of(7, 0))) {
            return Chronotype.LARK;
        }

        return Chronotype.PIGEON;
    }
}

