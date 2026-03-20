package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountSessionsAnalysisTest {

    @Test
    void countSessionsTest() {

        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025,10,1,23,0),
                        LocalDateTime.of(2025,10,2,7,0),
                        SleepQuality.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2025,10,2,23,0),
                        LocalDateTime.of(2025,10,3,6,0),
                        SleepQuality.BAD
                )
        );

        CountSessionsAnalysis analysis = new CountSessionsAnalysis();

        SleepAnalysisResult result = analysis.apply(sessions);

        assertEquals(2, result.getValue());
    }
}
