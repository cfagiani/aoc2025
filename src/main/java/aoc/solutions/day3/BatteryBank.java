package aoc.solutions.day3;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BatteryBank {
    private String batteryString;

    public BatteryBank(String batteryString) {
        this.batteryString = batteryString;
    }


    public long getMaxPower(int batteriesToUse) {
        List<Integer> intList = convertToDigits(batteryString);
        // now find the indexes of the batteriesToUse highest digits
        List<String> selectedDigits = new ArrayList<>();
        int lastIndex = 0;
        while (selectedDigits.size() < batteriesToUse) {
            int maxIdx = findMax(intList, lastIndex, batteryString.length() - (batteriesToUse - selectedDigits.size() - 1));
            selectedDigits.add(batteryString.substring(maxIdx, maxIdx + 1));
            lastIndex = maxIdx + 1;
        }

        String numericString = String.join("", selectedDigits);
        return Long.parseLong(numericString);
    }


    private List<Integer> convertToDigits(String input) {
        return input.chars().mapToObj(c -> Integer.parseInt(Character.toString(c))).collect(Collectors.toList());
    }

    private int findMax(List<Integer> intList, int start, int max) {
        int maxIdx = start;
        for (int i = start; i < max; i++) {
            if (intList.get(i) > intList.get(maxIdx)) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }
}
