package Day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;

/**
 * Solution for <a href="https://adventofcode.com/2022/day/2">Advent of Code - Day 2</a>
 * <p>
 * A / X - Rock
 * B / Y - Paper
 * C / Z - Scissors
 */
public class Main {
    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        var input = readInput("src/Day2/input.txt");
        Function<String, Integer> wonScore = in -> {
            char o = in.charAt(0);
            char p = in.charAt(2);

            if ((o == 'A' && p == 'X') ||
                    (o == 'B' && p == 'Y') ||
                    (o == 'C' && p == 'Z')) {
                return 3;
            }

            if ((o == 'A' && p == 'Y') ||
                    (o == 'B' && p == 'Z') ||
                    (o == 'C' && p == 'X')) {
                return 6;
            }

            return 0;
        };

        var score = Arrays.stream(input.split("\n")).map(
                l -> {
                    char player = l.charAt(2);
                    int scoreFromPlay = switch(player) {
                        case 'X' -> 1;
                        case 'Y' -> 2;
                        case 'Z' -> 3;
                        default -> throw new IllegalStateException("Unexpected value: " + player);
                    };
                    System.out.println(l + " " + scoreFromPlay + " " + wonScore.apply(l));
                    return scoreFromPlay + wonScore.apply(l);
                }
        ).mapToInt(Integer::intValue).sum();

        System.out.println(score);
    }

    public String readInput(String fileName) {
        try {
            return Files.readString(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}