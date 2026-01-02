package aoc.solutions.day4;

import java.util.List;

public class WarehouseCell {

    private static final String OCCUPIED_VALUE = "@";
    private static final String UNOCCUPIED_VALUE = ".";
    private String value;
    private int xPos;
    private int yPos;

    public WarehouseCell(String value, int xPos, int yPos) {
        this.value = value;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void remove() {
        this.value = UNOCCUPIED_VALUE;
    }

    public boolean isOccupied() {
        return value.equals(OCCUPIED_VALUE);
    }

    public int countOccupiedNeighbors(List<List<WarehouseCell>> contents) {
        int count = 0;
        for (int y = yPos - 1; y <= yPos + 1; y++) {
            for (int x = xPos - 1; x <= xPos + 1; x++) {
                if (y >= 0 && y < contents.size()) {
                    if (x >= 0 && x < contents.get(y).size()) {
                        if (!(x == xPos && y == yPos) && contents.get(y).get(x).isOccupied()) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }
}
