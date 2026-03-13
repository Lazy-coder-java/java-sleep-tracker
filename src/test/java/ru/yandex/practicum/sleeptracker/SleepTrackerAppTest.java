package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SleepTrackerAppTest {

    private List<SleepingSession> sessions = List.of(
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

    @Test
    void countSessionsTest() {

        CountSessionsAnalysis analysis = new CountSessionsAnalysis();

        SleepAnalysisResult result = analysis.apply(sessions);

        assertEquals(2, result.getValue());
    }

    @Test
    void minDurationTest() {

        MinDurationAnalysis analysis = new MinDurationAnalysis();

        SleepAnalysisResult result = analysis.apply(sessions);

        assertTrue((Long) result.getValue() > 0);
    }

    @Test
    void badQualityTest() {

        BadQualityCountAnalysis analysis = new BadQualityCountAnalysis();

        SleepAnalysisResult result = analysis.apply(sessions);

        assertEquals(1L, result.getValue());
    }

    @Test
    void maxDurationTest() {

        MaxDurationAnalysis analysis = new MaxDurationAnalysis();

        SleepAnalysisResult result = analysis.apply(sessions);

        assertTrue((Long) result.getValue() >= 420);
    }

    @Test
    void avgDurationTest() {

        AvgDurationAnalysis analysis = new AvgDurationAnalysis();

        SleepAnalysisResult result = analysis.apply(sessions);

        assertTrue((Long) result.getValue() > 0);
    }
}