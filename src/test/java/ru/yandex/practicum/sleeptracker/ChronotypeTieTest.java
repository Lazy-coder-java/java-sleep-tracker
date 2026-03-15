package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChronotypeTieTest {

    @Test
    void tieShouldReturnPigeon() {

        SleepingSession owlSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 23, 30),
                LocalDateTime.of(2025, 10, 2, 10, 0),
                SleepQuality.GOOD
        );

        SleepingSession larkSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 21, 30),
                LocalDateTime.of(2025, 10, 3, 6, 0),
                SleepQuality.GOOD
        );

        SleepingSession pigeonSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 3, 23, 0),
                LocalDateTime.of(2025, 10, 4, 7, 0),
                SleepQuality.GOOD
        );

        ChronotypeAnalysis analysis = new ChronotypeAnalysis();

        SleepAnalysisResult result = analysis.apply(
                List.of(owlSession, larkSession, pigeonSession)
        );

        assertEquals(Chronotype.PIGEON, result.getValue());
    }
}
