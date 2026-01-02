package aoc.solutions.day1;

import aoc.solutions.AbstractDay;

public class Dial extends AbstractDay {
    private static int MAX = 100;
    private int curValue;
    private int targetValue;
    private int passes;
    private int targetOccurrence;

    public Dial(String inputDir, int targetValue) {
        super(1, inputDir);
        curValue = 50;
        this.targetValue = targetValue;
        this.passes = 0;
        this.targetOccurrence = 0;
    }

    public void processCombination() {
        for (String move : getInputData().split("\n")) {
            String dir = move.substring(0, 1);
            int amount = Integer.parseInt(move.substring(1));
            int fullTurns = amount / MAX;
            int modAmount = amount % MAX;
            if ("L".equalsIgnoreCase(dir)) {
                passes += fullTurns;
                int newValue = (curValue - modAmount + MAX) % MAX;
                if (curValue - modAmount < 0 && curValue != 0) {
                    passes++;
                } else if (newValue == 0 && curValue != 0) {
                    passes++;
                }
                curValue = newValue;
            } else {
                passes += fullTurns;
                int newValue = (curValue + modAmount);
                if (newValue >= MAX) {
                    passes++;
                }
                curValue = newValue % MAX;
            }
            if (curValue == targetValue) {
                targetOccurrence++;
            }
        }
    }

    public int getPasses() {
        return passes;
    }

    public int getTargetOccurrence() {
        return targetOccurrence;
    }

    public static void main(String[] args) {
        Dial dial = new Dial(getInputDir(args), 0);
        dial.processCombination();
        System.out.println("Count: " + dial.getTargetOccurrence());
        System.out.println("Passes: " + dial.getPasses());
    }

}
