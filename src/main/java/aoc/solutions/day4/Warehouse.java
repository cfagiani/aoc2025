package aoc.solutions.day4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Warehouse {

    private List<List<WarehouseCell>> warehouseContents;

    public Warehouse() {
        warehouseContents = new ArrayList<>();
    }

    public void addContentsFromString(String row) {
        AtomicInteger idx = new AtomicInteger();
        warehouseContents.add(row.chars().mapToObj(c ->
                new WarehouseCell(Character.toString(c), idx.getAndIncrement(), warehouseContents.size())
        ).collect(Collectors.toList()));

    }

    public List<WarehouseCell> getCellsWithFewerThanNNeighbors(int n) {
        List<WarehouseCell> cells = new ArrayList<>();
        for (int y = 0; y < warehouseContents.size(); y++) {
            for (int x = 0; x < warehouseContents.get(y).size(); x++) {
                WarehouseCell cell = warehouseContents.get(y).get(x);
                if(cell.isOccupied()) {
                    if (cell.countOccupiedNeighbors(warehouseContents) < n) {
                        cells.add(cell);
                    }
                }
            }
        }
        return cells;
    }
}
