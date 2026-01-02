package aoc.solutions.day8;

import aoc.solutions.AbstractDay;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CircuitCreator extends AbstractDay {

    private final List<JunctionBox> boxes;

    private Map<String, Double> distances;

    public CircuitCreator(String inputDir) {
        super(8, inputDir);
        boxes = new ArrayList<>();
        for (String line : getInputData().split("\n")) {
            boxes.add(new JunctionBox(line));
        }
        computeAllPairsDistances();
    }

    private void computeAllPairsDistances() {
        distances = new HashMap<>();
        for (int i = 0; i < boxes.size(); i++) {
            for (int j = i + 1; j < boxes.size(); j++) {
                distances.put(i + "," + j, boxes.get(i).getDistanceTo(boxes.get(j)));
            }
        }
    }

    public long connectNBoxes(int n) {
        List<Map.Entry<String, Double>> sortedDistances = distances.entrySet().stream()
                .sorted(Comparator.comparingDouble(Map.Entry::getValue))
                .toList();

        int connectionsMade = 0;
        long lastProduct = 0;
        for (Map.Entry<String, Double> entry : sortedDistances) {
            if (connectionsMade >= n) break;
            String[] indices = entry.getKey().split(",");
            JunctionBox boxA = boxes.get(Integer.parseInt(indices[0]));
            JunctionBox boxB = boxes.get(Integer.parseInt(indices[1]));
            if (boxA.connect(boxB)) {
                lastProduct = (long) boxA.getXPos() * (long) boxB.getXPos();
                if (boxA.getCircuitSize() == boxes.size()) {
                    // everything is connected. We're done.
                    break;
                }
            }
            connectionsMade++;
        }
        return lastProduct;
    }


    public List<JunctionBox> getCircuits() {
        List<JunctionBox> circuits = new ArrayList<>();
        for (JunctionBox box : boxes) {
            boolean isNewCircuit = true;
            for (JunctionBox circuitBox : circuits) {
                if (circuitBox.isConnected(box)) {
                    isNewCircuit = false;
                    break;
                }
            }
            if (isNewCircuit) {
                circuits.add(box);
            }
        }
        return circuits;
    }


    public static void main(String[] args) {
        CircuitCreator circuitCreator = new CircuitCreator(args[0]);
        circuitCreator.connectNBoxes(1000);

        List<JunctionBox> circuits = circuitCreator.getCircuits();
        List<JunctionBox> sortedCircuits = circuits.stream().sorted(Comparator.comparingInt(JunctionBox::getCircuitSize).reversed()).toList();
        int val = 1;
        for (int i = 0; i < 3; i++) {
            val *= sortedCircuits.get(i).getCircuitSize();
        }
        System.out.println("Product of 3 largest circuit sizes after 1000 connections: " + val);
        System.out.println("Final product after all connections: " + circuitCreator.connectNBoxes(1000 * 1000 / 2));
    }
}
