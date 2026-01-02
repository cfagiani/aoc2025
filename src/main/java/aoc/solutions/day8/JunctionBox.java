package aoc.solutions.day8;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class JunctionBox {
    private final int xPos;
    private final int yPos;
    private final int zPos;
    private final Set<JunctionBox> connections;

    public JunctionBox(String line) {
        String[] parts = line.split(",");
        xPos = Integer.parseInt(parts[0]);
        yPos = Integer.parseInt(parts[1]);
        zPos = Integer.parseInt(parts[2]);
        connections = new HashSet<>();
    }

    public int getXPos() {
        return xPos;
    }

    public double getDistanceTo(JunctionBox box) {
        return Math.sqrt(Math.pow(xPos - box.xPos, 2) + Math.pow(yPos - box.yPos, 2) + Math.pow(zPos - box.zPos, 2));
    }

    public boolean connect(JunctionBox box) {
        if (isConnected(box)) {
            return false;
        }
        // set bidirectional connection
        connections.add(box);
        // manipulate directly rather than calling connect so we don't infinitely recurse
        box.connections.add(this);
        return true;
    }

    public boolean isConnected(JunctionBox box) {
        return isConnected(box, new HashSet<>());
    }

    protected boolean isConnected(JunctionBox box, Set<JunctionBox> seen) {
        seen.add(this);
        if (connections.contains(box)) {
            return true;
        } else {
            for (JunctionBox connectedBox : connections) {
                if (!seen.contains(connectedBox) && connectedBox.isConnected(box, seen)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getCircuitSize() {
        Set<JunctionBox> connectedBoxes = new HashSet<>();
        accumulateUniqueConnections(connectedBoxes);
        return connectedBoxes.size();
    }

    protected void accumulateUniqueConnections(Set<JunctionBox> seen) {
        seen.add(this);
        for (JunctionBox connectedBox : connections) {
            if (!seen.contains(connectedBox)) {
                connectedBox.accumulateUniqueConnections(seen);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        JunctionBox that = (JunctionBox) o;
        return xPos == that.xPos && yPos == that.yPos && zPos == that.zPos;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xPos, yPos, zPos);
    }

    @Override
    public String toString() {
        return "JunctionBox{" +
                "zPos=" + zPos +
                ", yPos=" + yPos +
                ", xPos=" + xPos +
                '}';
    }
}
