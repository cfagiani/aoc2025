package aoc.solutions.day5;

import aoc.solutions.AbstractDay;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IngredientChecker extends AbstractDay {

    private List<IdRange> freshRanges;
    private List<Long> ingredients;

    public IngredientChecker(String inputDir) {
        super(5, inputDir);
        freshRanges = new ArrayList<>();
        ingredients = new ArrayList<>();
        for (String line : getInputData().split("\n")) {
            if (line.trim().isEmpty()) {
                continue;
            }
            if (line.contains("-")) {
                freshRanges.add(new IdRange(line.trim()));
            } else {
                ingredients.add(Long.parseLong(line.trim()));
            }
        }
    }

    public Set<Long> getFreshIngredients() {
        Set<Long> fresh = new HashSet<>();
        for (Long ingredient : ingredients) {
            for (IdRange range : freshRanges) {
                if (range.inRange(ingredient)) {
                    fresh.add(ingredient);
                    break;
                }
            }
        }
        return fresh;
    }

    private List<IdRange> computeNonOverlapping() {
        List<IdRange> nonOverlapping = new ArrayList<>();
        // sort ranges by start so we can simplify comparisons
        List<IdRange> sortedRanges = freshRanges.stream().sorted(Comparator.comparingLong(IdRange::getMin)).toList();
        for (IdRange newRange : sortedRanges) {
            IdRange tempRange = new IdRange(newRange.getMin(), newRange.getMax());
            for (IdRange range : nonOverlapping) {
                if (range.inRange(tempRange.getMin())) {
                    if (!range.inRange(tempRange.getMax())) {
                        // max extends beyond this range so set tempRange to range.max +1 and keep checking
                        tempRange.setMin(range.getMax() + 1);
                    } else {
                        // tempRange is wholely contained by the ranges we already have. set min=max-1 so it gets omitted
                        tempRange.setMax(tempRange.getMin() - 1);
                        break;
                    }
                }
            }
            if (tempRange.size() > 0) {
                nonOverlapping.add(tempRange);
            }
        }
        return nonOverlapping;
    }


    public static void main(String[] args) {
        IngredientChecker ingredientChecker = new IngredientChecker(args[0]);
        Set<Long> fresh = ingredientChecker.getFreshIngredients();
        System.out.println("Avail Fresh Ingredients: " + fresh.size());
        List<IdRange> nonOverlapping = ingredientChecker.computeNonOverlapping();
        System.out.println("Fresh ids count: " + nonOverlapping.stream().mapToLong(IdRange::size).sum());
    }
}
