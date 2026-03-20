package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SleeplessNightsAnalysisTest {

    @Test
    void sleeplessNightTest() {

        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025,10,1,23,0),
                        LocalDateTime.of(2025,10,2,7,0),
                        SleepQuality.GOOD
                )
        );

        SleeplessNightsAnalysis analysis = new SleeplessNightsAnalysis();

        SleepAnalysisResult result = analysis.apply(sessions);

        assertEquals(0L, result.getValue());
    }
}
