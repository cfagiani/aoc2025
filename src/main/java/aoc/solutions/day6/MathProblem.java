package aoc.solutions.day6;

import java.util.ArrayList;
import java.util.List;

public class MathProblem {
    List<Long> operands;
    String operator;

    public MathProblem() {
        operands = new ArrayList<>();
        operator = null;
    }

    public void append(String val) {
        try {
            long operand = Long.parseLong(val.trim());
            operands.add(operand);
        } catch (NumberFormatException e) {
            //assume non-numeric values are operators
            operator = val.trim();
        }
    }

    public long solve() {
        long val = operands.getFirst();
        for (int i = 1; i < operands.size(); i++) {
            if ("+".equals(operator)) {
                val = val + operands.get(i);
            } else {
                val = val * operands.get(i);
            }
        }
        return val;
    }
}
