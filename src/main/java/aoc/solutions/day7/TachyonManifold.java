package aoc.solutions.day7;

import aoc.solutions.AbstractDay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TachyonManifold extends AbstractDay {
    private final List<char[]> manifold;
    private int splitCount = 0;
    private long timelineCount = 0L;


    public TachyonManifold(String inputDir) {
        super(7, inputDir);
        manifold = new ArrayList<>();
        for (String line : getInputData().split("\n")) {
            manifold.add(line.toCharArray());
        }

    }

    public void fireBeam() {
        long[] incomingBeams = new long[manifold.getFirst().length];
        for (int i = 1; i < manifold.size(); i++) {
            for (int j = 0; j < manifold.get(i).length; j++) {
                char parent = manifold.get(i - 1)[j];
                char node = manifold.get(i)[j];
                if (parent == 'S') {
                    incomingBeams[j] = 1L;
                }
                if (parent == 'S' || parent == '|') {
                    // beam came into this node
                    if (node == '^') {

                        if (j > 0) {
                            manifold.get(i)[j - 1] = '|';
                            incomingBeams[j - 1] += incomingBeams[j];
                        }
                        if (j < manifold.get(i).length - 1) {
                            manifold.get(i)[j + 1] = '|';
                            incomingBeams[j + 1] += incomingBeams[j];
                        }
                        incomingBeams[j] = 0L;
                        splitCount++;
                    } else if (node == '.') {
                        manifold.get(i)[j] = '|';
                    }
                }
            }
        }
        timelineCount = Arrays.stream(incomingBeams).sum();
    }

    public int getSplitCount() {
        return splitCount;
    }

    public long getTimelineCount() {
        // return timelines.values().stream().mapToLong(Long::longValue).sum();
        return timelineCount;
    }

    public static void main(String[] args) {
        TachyonManifold manifold = new TachyonManifold(args[0]);
        manifold.fireBeam();
        System.out.println("Beam splits: " + manifold.getSplitCount());
        System.out.println("Timelines: " + manifold.getTimelineCount());
    }
}
