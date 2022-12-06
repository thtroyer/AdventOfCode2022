package Day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().run());
    }

    private int run() {
        return run(readInput("src/main/java/Day3/input.txt"));
    }

    public int run(String input) {
        var splitInput = asList(input.split("\n"));
        int sum = 0;

        for(String s : splitInput) {
            sum += getValueOfChar(findCommonLetterInFirstAndBackHalves(s));
        }

        return sum;
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


    public String readInput(String fileName) {
        try {
            return Files.readString(Path.of( fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
