package Day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.compare;
import static java.util.Arrays.stream;

/**
 * Solution for <a href="https://adventofcode.com/2022/day/1">...</a>
 * <p>
 * Read an input file, find the elf that is carrying the most.
 * File contains a list of numbers.  Each section is a different elf.
 * Elves index are 1-based.
 */
public class Main {
    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {

        var elves = sumElfContents( parseToList(readInput("src/Day1/input.txt")));
        var indexOfLargestCarryingElf = returnIndexOfLargest(elves);

        System.out.println("First solution:");
        System.out.println("  Elf id: " + indexOfLargestCarryingElf);
        System.out.println("  Carrying: " + elves.get(indexOfLargestCarryingElf));

        var sortedElves = sortElves(elves);
        int top3ElvesContents = sortedElves.get(0).getValue() + sortedElves.get(1).getValue() + sortedElves.get(2).getValue();

        System.out.println("Second solution:");
        System.out.println("  Top 3: " + top3ElvesContents);
    }

    public String readInput(String fileName) {
        try {
            return Files.readString(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Takes file input string, returns 1-based map of elf index to list of things it's carrying.
     */
    public Map<Integer, List<Integer>> parseToList(String input) {
        var listOfStrings = Arrays.stream(input.split("\n\n")).toList();
        Map<Integer, List<Integer>> results = new HashMap<>();

        int i = 1;

        for (var l : listOfStrings) {
            List<Integer> elf = new ArrayList<>();
            for (var s : l.split("\n")) {
                elf.add(Integer.parseInt(s));
            }
            results.put(i, elf);
            i++;
        }

        return results;
    }

    public Map<Integer, Integer> sumElfContents(Map<Integer,List<Integer>> elves) {
        Map<Integer, Integer> results = new HashMap<>();

        for (var e: elves.entrySet()) {
            results.put(
                    e.getKey(),
                    e.getValue().stream().mapToInt(Integer::intValue).sum()
            );
        }

        return results;
    }

    public List<Map.Entry<Integer, Integer>> sortElves(Map<Integer, Integer> elves) {
        return elves.entrySet().stream().sorted(
                (e1, e2) -> e1.getValue() < e2.getValue() ? 1 : ((e1.getValue().equals(e2.getValue())) ? 0 : -1)
        ).collect(Collectors.toList());
    }

    public int returnIndexOfLargest(Map<Integer, Integer> elves) {
        Optional<Map.Entry<Integer, Integer>> e = elves.entrySet().stream().max(
                (e1, e2) -> e1.getValue() < e2.getValue() ? -1 : ((e1.getValue().equals(e2.getValue())) ? 0 : 1)
        );

        if (e.isEmpty()) {
            throw new RuntimeException("Something broke in returnIndexOfLargest");
        }

        return e.get().getKey();
    }
}