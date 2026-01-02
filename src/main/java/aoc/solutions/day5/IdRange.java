package aoc.solutions.day5;

public class IdRange {
    private long min;
    private long max;

    public IdRange(long min, long max) {
        this.min = min;
        this.max = max;
    }

    public IdRange(String rangeString) {
        String[] parts = rangeString.split("-");
        this.min = Long.parseLong(parts[0]);
        this.max = Long.parseLong(parts[1]);
    }

    public boolean inRange(long val) {
        return val >= min && val <= max;
    }

    public long size() {
        return max - min+1;
    }

    public long getMin() {
        return min;
    }

    public long getMax() {
        return max;
    }

    public void setMin(long min) {
        this.min = min;
    }

    public void setMax(long max) {
        this.max = max;
    }
}
