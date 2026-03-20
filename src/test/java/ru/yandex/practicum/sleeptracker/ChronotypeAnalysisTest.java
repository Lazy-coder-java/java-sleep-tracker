package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChronotypeAnalysisTest {

    @Test
    void owlChronotypeTest() {

        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025,10,1,23,30),
                        LocalDateTime.of(2025,10,2,10,0),
                        SleepQuality.GOOD
                )
        );

        ChronotypeAnalysis analysis = new ChronotypeAnalysis();

        SleepAnalysisResult result = analysis.apply(sessions);

        assertEquals(Chronotype.OWL, result.getValue());
    }

    @Test
    void larkChronotypeTest() {

        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025,10,1,21,30),
                        LocalDateTime.of(2025,10,2,6,0),
                        SleepQuality.GOOD
                )
        );

        ChronotypeAnalysis analysis = new ChronotypeAnalysis();

        SleepAnalysisResult result = analysis.apply(sessions);

        assertEquals(Chronotype.LARK, result.getValue());
    }

    @Test
    void pigeonChronotypeTest() {

        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025,10,1,22,30),
                        LocalDateTime.of(2025,10,2,8,0),
                        SleepQuality.GOOD
                )
        );

        ChronotypeAnalysis analysis = new ChronotypeAnalysis();

        SleepAnalysisResult result = analysis.apply(sessions);

        assertEquals(Chronotype.PIGEON, result.getValue());
    }
}
