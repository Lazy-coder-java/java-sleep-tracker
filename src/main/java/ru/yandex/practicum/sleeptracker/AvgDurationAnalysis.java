package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class AvgDurationAnalysis implements Function<List<SleepingSession>, SleepAnalysisResult> {

    private static final String DESCRIPTION = "Average session duration (minutes)";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {

        double avg = sessions.stream()
                .mapToLong(SleepingSession::getDurationMinutes)
                .average()
                .orElse(0);

        return new SleepAnalysisResult(
                DESCRIPTION,
                Math.round(avg)
        );
    }
}
