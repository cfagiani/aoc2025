package aoc.solutions.day3;


import aoc.solutions.AbstractDay;

import java.util.ArrayList;
import java.util.List;

public class BatteryArray extends AbstractDay {

    List<BatteryBank> batteryBanks;

    public BatteryArray(String inputDir) {
        super(3, inputDir);
        batteryBanks = new ArrayList<>();
        for (String line : getInputData().split("\n")) {
            batteryBanks.add(new BatteryBank(line.trim()));
        }
    }

    public long getMaxPower(int batteriesToUse) {
        return batteryBanks.stream().map(b ->b.getMaxPower(batteriesToUse)).reduce(0L, Long::sum);
    }

    public static void main(String[] args) {
        BatteryArray batteryArray = new BatteryArray(getInputDir(args));
        System.out.println(batteryArray.getMaxPower(2));
        System.out.println(batteryArray.getMaxPower(12));


    }

}

