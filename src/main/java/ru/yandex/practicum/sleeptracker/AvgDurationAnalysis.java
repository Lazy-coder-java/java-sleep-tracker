package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class AvgDurationAnalysis
        implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {

        double avg = sessions.stream()
                .mapToLong(SleepingSession::getDurationMinutes)
                .average()
                .orElse(0);

        return new SleepAnalysisResult(
                "Average session duration (minutes)",
                Math.round(avg)
        );
    }
}
