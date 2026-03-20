package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MinDurationAnalysisTest {

    @Test
    void minDurationTest() {

        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025,10,1,23,0),
                        LocalDateTime.of(2025,10,2,7,0),
                        SleepQuality.GOOD
                )
        );

        MinDurationAnalysis analysis = new MinDurationAnalysis();

        SleepAnalysisResult result = analysis.apply(sessions);

        assertTrue((Long) result.getValue() > 0);
    }
}
