package aoc.solutions.day9;

import aoc.solutions.AbstractDay;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FloorPlanner extends AbstractDay {

    private final List<FloorTile> floorTiles;
    private final Map<String, Long> allRectangles;
    private final CompressedGrid compressedGrid;


    public FloorPlanner(String inputDir) {
        super(9, inputDir);
        floorTiles = new ArrayList<>();
        List<Integer> xCoords = new ArrayList<>();
        List<Integer> yCoords = new ArrayList<>();
        for (String line : getInputData().split("\n")) {
            FloorTile floorTile = new FloorTile(line);
            floorTiles.add(floorTile);
            if (!xCoords.contains(floorTile.getX())) {
                xCoords.add(floorTile.getX());
            }
            if (!yCoords.contains(floorTile.getY())) {
                yCoords.add(floorTile.getY());
            }
        }
        compressedGrid = new CompressedGrid(xCoords, yCoords);
        compressedGrid.colorGrid(floorTiles);
        allRectangles = new HashMap<>();
        computeRectangles();
    }

    private void computeRectangles() {
        for (int i = 0; i < floorTiles.size(); i++) {
            for (int j = i + 1; j < floorTiles.size(); j++) {
                long area = floorTiles.get(i).areaBetween(floorTiles.get(j));
                allRectangles.put(i + "," + j, area);
            }
        }
    }



    private long getRedGreenRectangleArea() {


        return -1;
    }

    public long getLargestRectangle() {

        return allRectangles.values().stream().max(Comparator.naturalOrder()).get();
    }

    public static void main(String[] args) {
        FloorPlanner planner = new FloorPlanner(args[0]);
        System.out.println("Largest rectangle: " + planner.getLargestRectangle());
        System.out.println("Largest red/green rectangle: " + planner.getRedGreenRectangleArea());
    }

}
