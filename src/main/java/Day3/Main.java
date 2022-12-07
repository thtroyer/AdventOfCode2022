package Day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().part1());
        System.out.println(new Main().part2());
    }

    private int part1() {
        return part1(readInput("src/main/java/Day3/input.txt"));
    }

    private int part2() {
        return part2(readInput("src/main/java/Day3/input.txt"));
    }

    public int part1(String input) {
        var splitInput = asList(input.split("\n"));
        int sum = 0;

        for(String s : splitInput) {
            sum += getValueOfChar(findCommonLetterInFirstAndBackHalves(s));
        }

        return sum;
    }

    public int part2(String input) {
        return batch(Arrays.asList(input.split("\n")))
                .stream()
                .map(l -> getValueOfChar(findUnique(l)))
                .reduce(0, Integer::sum);

    }

    private List<List<String>> batch(List<String> input) {
        int batchSize = 3;
        return IntStream.range(0, (input.size()+ batchSize -1)/ batchSize)
                .mapToObj(i -> input.subList(i* batchSize, Math.min(input.size(), (i+1)* batchSize)))
                .toList();
    }

    private char findCommonLetterInFirstAndBackHalves(String input) {
        String front = input.substring(0, input.length()/2);
        String back = input.substring(input.length()/2);

        List<Character> matches = front.chars().mapToObj(c -> (char) c)
                .filter(back.chars().mapToObj(c -> (char) c).toList()::contains)
                .collect(Collectors.toList());

        return matches.get(0);
    }

    int getValueOfChar(char c) {
        if ((int) c >= 97) {
            return c - 96;
        }
        return c - 38;
    }

    char findUnique(List<String> input) {
        List<Character> matches = input.get(0).chars().mapToObj(c -> (char) c)
                .filter(input.get(1).chars().mapToObj(c -> (char) c).toList()::contains)
                .filter(input.get(2).chars().mapToObj(c -> (char) c).toList()::contains)
                .collect(Collectors.toList());

        return matches.get(0);
    }


    public String readInput(String fileName) {
        try {
            return Files.readString(Path.of( fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
