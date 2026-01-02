package aoc.solutions.day9;

import java.util.Comparator;
import java.util.List;

public class CompressedGrid {

    private char[][] grid;
    private List<Integer> xCoords;
    private List<Integer> yCoords;

    public CompressedGrid(List<Integer> xCoords, List<Integer> yCoords) {
        xCoords.sort(Comparator.naturalOrder());
        yCoords.sort(Comparator.naturalOrder());
        this.grid = new char[yCoords.size()][xCoords.size()];
        this.xCoords = xCoords;
        this.yCoords = yCoords;
        // initialize to .
        for (int y = 0; y < yCoords.getLast(); y++) {
            for (int x = 0; x < xCoords.getLast(); x++) {
                grid[y][x] = '.';
            }
        }
    }

    public void colorGrid(List<FloorTile> floorTiles) {
        for (int i = 0; i < floorTiles.size() - 1; i++) {
            FloorTile tileA = floorTiles.get(i);
            FloorTile tileB = floorTiles.get(i + 1);
            // first set the color of the tiles themselves
            setGridCell(tileA, '#');
            setGridCell(tileB, '#');
            // now colorize everything in a line between a and b
            // then fill
            // then compute rectangles and check for each if they only have colored tiles

        }
    }

    private void setGridCell(FloorTile tile, char val) {
        grid[compressCoordinate(tile.getY(), yCoords)][compressCoordinate(tile.getX(), xCoords)] = val;
    }

    private int compressCoordinate(int coord, List<Integer> coords) {
        return coords.indexOf(coord);
    }
}
