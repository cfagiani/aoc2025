package aoc.solutions.day9;


public class FloorTile {
    private final int x;
    private final int y;

    public FloorTile(String line) {
        String[] parts = line.split(",");
        this.x = Integer.parseInt(parts[0]);
        this.y = Integer.parseInt(parts[1]);
    }


    public long areaBetween(FloorTile other) {
        return (long) (Math.abs(this.x - other.x) + 1) * (long) (Math.abs(this.y - other.y) + 1);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
