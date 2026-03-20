package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MaxDurationAnalysisTest {

    @Test
    void maxDurationTest() {

        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025,10,1,23,0),
                        LocalDateTime.of(2025,10,2,7,0),
                        SleepQuality.GOOD
                )
        );

        MaxDurationAnalysis analysis = new MaxDurationAnalysis();

        SleepAnalysisResult result = analysis.apply(sessions);

        assertTrue((Long) result.getValue() >= 420);
    }
}
