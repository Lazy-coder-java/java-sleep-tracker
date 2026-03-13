package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class BadQualityCountAnalysis
        implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {

        long count = sessions.stream()
                .filter(s -> s.getQuality() == SleepQuality.BAD)
                .count();

        return new SleepAnalysisResult(
                "Bad quality sessions",
                count
        );
    }
}
