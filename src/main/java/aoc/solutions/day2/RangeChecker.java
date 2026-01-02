package aoc.solutions.day2;

import aoc.solutions.AbstractDay;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class RangeChecker extends AbstractDay {

    public RangeChecker(String inputDir) {
        super(2, inputDir);
    }

    public List<Long> getInvalidIds(Predicate<String> checker) {
        List<Long> invalidIds = new ArrayList<>();
        for (String range : getInputData().split(",")) {
            for (long x = Long.parseLong(range.split("-")[0]); x <= Long.parseLong(range.split("-")[1]); x++) {
                String numString = String.valueOf(x);
                if (checker.test(numString)) {
                    invalidIds.add(x);
                }
            }
        }
        return invalidIds;
    }


    public static void main(String[] args) {
        RangeChecker rangeChecker = new RangeChecker(getInputDir(args));
        List<Long> invalidIds = rangeChecker.getInvalidIds(RangeChecker::singleRepeatCheck);
        System.out.println("Invalid id sum: " + invalidIds.stream().mapToLong(Long::valueOf).sum());
        invalidIds = rangeChecker.getInvalidIds(RangeChecker::anyRepeatCheck);
        System.out.println("Invalid id sum: " + invalidIds.stream().mapToLong(Long::valueOf).sum());
    }


    public static boolean anyRepeatCheck(String numString) {

        for (int i = 1; i <= numString.length() / 2; i++) {
            List<String> parts = Pattern.compile(".{1," + i + "}")
                    .matcher(numString)
                    .results()
                    .map(MatchResult::group)
                    .toList();
            if (parts.stream().allMatch(s -> s.equals(parts.getFirst()))) {
                return true;
            }
        }
        return false;
    }


    public static boolean singleRepeatCheck(String numString) {
        if (numString.length() % 2 == 0) {
            return (numString.substring(0, numString.length() / 2).equals(numString.substring(numString.length() / 2)));
        } else {
            return false;
        }
    }
}
