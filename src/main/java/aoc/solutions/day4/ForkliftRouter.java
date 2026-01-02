package aoc.solutions.day4;

import aoc.solutions.AbstractDay;

import java.util.ArrayList;
import java.util.List;

public class ForkliftRouter extends AbstractDay {
    private Warehouse warehouse;

    public ForkliftRouter(String inputDir) {
        super(4, inputDir);
        warehouse = new Warehouse();
        for (String line : getInputData().split("\n")) {
            warehouse.addContentsFromString(line.trim());
        }
    }

    public List<WarehouseCell> findMovableCells() {
        return warehouse.getCellsWithFewerThanNNeighbors(4);
    }

    public static void main(String[] args) {
        ForkliftRouter forkliftRouter = new ForkliftRouter(getInputDir(args));
        List<WarehouseCell> moveableCells = forkliftRouter.findMovableCells();
        System.out.println("Movable Cells: " + moveableCells.size());
        int totalRemoved = 0;
        List<WarehouseCell> cellsToMove = new ArrayList<>();
        do {
            cellsToMove = forkliftRouter.findMovableCells();
            for (WarehouseCell cell : cellsToMove) {
                cell.remove();
            }
            totalRemoved += cellsToMove.size();
        } while (!cellsToMove.isEmpty());
        System.out.println("Total Removed: " + totalRemoved);
    }
}
