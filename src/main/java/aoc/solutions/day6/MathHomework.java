package aoc.solutions.day6;

import aoc.solutions.AbstractDay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MathHomework extends AbstractDay {
    public enum ParseMode {
        LINE, R_TO_L_COLUMN
    }

    private List<MathProblem> problems;

    public MathHomework(String inputDir, ParseMode parseMode) {
        super(6, inputDir);
        problems = new ArrayList<>();
        if (parseMode == ParseMode.LINE) {
            parseProblemsByLine();
        } else {
            parseProblemsByColumn();
        }

    }

    private void parseProblemsByColumn() {
        List<Integer> columnWidths = new ArrayList<>();
        String[] lines = getInputData().split("\n");
        int lastCol = 0;
        for (int colPos = 0; colPos < lines[0].length(); colPos++) {
            if (isColumnAllSpaces(lines, colPos)) {
                columnWidths.add(colPos - lastCol);
                lastCol = colPos + 1;
            }
        }
        // add the last column too
        columnWidths.add(lines[0].length() - lastCol);
        // now process the columns
        int colStart = lines[0].length() - 1;
        int colIdx = columnWidths.size() - 1;
        while (colIdx >= 0) {
            MathProblem problem = new MathProblem();

            for (int pos = colStart; pos > colStart - columnWidths.get(colIdx); pos--) {
                // last row contains the operator
                StringBuilder digitString = new StringBuilder();
                for (int row = 0; row < lines.length - 1; row++) {
                    digitString.append(lines[row].charAt(pos));
                }
                problem.append(digitString.toString().trim());
            }
            // now handle the operator
            problem.append(lines[lines.length - 1].substring(Math.max(0, colStart - 1 - columnWidths.get(colIdx)), colStart).trim());
            problems.add(problem);
            colStart = colStart - columnWidths.get(colIdx) - 1;
            colIdx--;
        }
    }

    private boolean isColumnAllSpaces(String[] lines, int pos) {
        for (int line = 0; line < lines.length; line++) {
            if (lines[line].charAt(pos) != ' ') {
                return false;
            }
        }
        return true;
    }


    private void parseProblemsByLine() {
        List<List<String>> rows = new ArrayList<>();
        for (String line : getInputData().split("\n")) {
            rows.add(Arrays.asList(line.trim().split("\\s+")));
        }

        for (int i = 0; i < rows.size(); i++) {
            for (int j = 0; j < rows.get(i).size(); j++) {
                if (problems.size() == j) {
                    problems.add(new MathProblem());
                }
                problems.get(j).append(rows.get(i).get(j));
            }
        }
    }


    public List<Long> solveAll() {
        return problems.stream().mapToLong(MathProblem::solve).boxed().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        MathHomework homework = new MathHomework(args[0], ParseMode.LINE);
        System.out.println("Grand Total: " + homework.solveAll().stream().reduce(0L, Long::sum));
        MathHomework partTwo = new MathHomework(args[0], ParseMode.R_TO_L_COLUMN);
        System.out.println("Part 2 Grand Total: " + partTwo.solveAll().stream().reduce(0L, Long::sum));
    }
}
