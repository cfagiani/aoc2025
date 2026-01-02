package aoc.solutions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class AbstractDay {

    private final int dayNumber;
    private String inputData;

    public AbstractDay(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public AbstractDay(int dayNumber, String rootDir) {
        this.dayNumber = dayNumber;
        this.inputData = getInput(rootDir);
    }

    private String getInput(String rootDir) {
        Path inputPath = Paths.get(rootDir, "day" + getDayNumber());
        if (Files.exists(inputPath)) {
            try {
                return Files.readString(inputPath);
            } catch (IOException e) {
                throw new RuntimeException("Could not load input", e);
            }
        }
        return null;
    }

    public int getDayNumber() {
        return this.dayNumber;
    }

    public String getInputData() {
        return this.inputData;
    }

    public static String getInputDir(String[] args) {
        if (args.length < 1) {
            throw new RuntimeException("Missing command line arguments. Supply the path to the input dir.");
        }
        return args[0];
    }
}
