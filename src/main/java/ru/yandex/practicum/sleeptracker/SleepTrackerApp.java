package ru.yandex.practicum.sleeptracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SleepTrackerApp {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            System.out.println("Please provide path to sleep log file");
            return;
        }

        List<SleepingSession> sessions = Files.lines(Path.of(args[0]))
                .map(SleepTrackerApp::parseLine)
                .collect(Collectors.toList());

        List<Function<List<SleepingSession>, SleepAnalysisResult>> analyses = List.of(
                new CountSessionsAnalysis(),
                new MinDurationAnalysis(),
                new MaxDurationAnalysis(),
                new AvgDurationAnalysis(),
                new BadQualityCountAnalysis(),
                new SleeplessNightsAnalysis(),
                new ChronotypeAnalysis()
        );

        analyses.stream()
                .map(a -> a.apply(sessions))
                .forEach(r ->
                        System.out.println(r.getDescription() + ": " + r.getValue())
                );
    }

    private static SleepingSession parseLine(String line) {

        String[] parts = line.split(";");

        LocalDateTime start = LocalDateTime.parse(parts[0], FORMATTER);
        LocalDateTime end = LocalDateTime.parse(parts[1], FORMATTER);

        SleepQuality quality = SleepQuality.valueOf(parts[2]);

        return new SleepingSession(start, end, quality);
    }
}