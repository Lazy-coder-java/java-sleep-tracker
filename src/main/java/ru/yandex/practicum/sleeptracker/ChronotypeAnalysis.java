package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChronotypeAnalysis implements Function<List<SleepingSession>, SleepAnalysisResult> {

    private static final String DESCRIPTION = "User chronotype";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {

        Map<Chronotype, Long> counts = sessions.stream()
                .collect(Collectors.groupingBy(
                        this::classify,
                        Collectors.counting()
                ));

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

    private Chronotype classify(SleepingSession session) {

        var sleep = session.getStart().toLocalTime();
        var wake = session.getEnd().toLocalTime();

        if (wake.isAfter(java.time.LocalTime.of(9, 0))) {
            return Chronotype.OWL;
        }

        if (sleep.isBefore(java.time.LocalTime.of(22, 0))
                && wake.isBefore(java.time.LocalTime.of(7, 0))) {
            return Chronotype.LARK;
        }

        return Chronotype.PIGEON;
    }
}
