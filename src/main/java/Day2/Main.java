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
 * (Not the cleanest code, but I'm trying out some new Java syntax and pushing my knowledge of streams a bit)
 */
public class Main {
    public static void main(String[] args) {
        new Main().solution1();
        new Main().solution2();
    }

    public void solution1() {
        var input = readInput("src/main/java/Day2/input.txt");
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
                    int scoreFromPlay = switch (player) {
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

    public void solution2() {
        var input = readInput("src/main/java/Day2/input.txt");

        // X = lose, Y = tie, Z = win

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

        var score = Arrays.stream(input.split("\n"))
                .map( // map win/loss logic to existing player input
                        l -> {
                            switch (l.charAt(2)) {
                                case 'X' -> { // lose
                                    return switch (l.charAt(0)) {
                                        case 'A' -> "A Z";
                                        case 'B' -> "B X";
                                        case 'C' -> "C Y";
                                        default -> throw new IllegalStateException("Unexpected value: " + l.charAt(0));
                                    };
                                }
                                case 'Y' -> { // Tie
                                    return switch (l.charAt(0)) {
                                        case 'A' -> "A X";
                                        case 'B' -> "B Y";
                                        case 'C' -> "C Z";
                                        default -> throw new IllegalStateException("Unexpected value: " + l.charAt(0));
                                    };
                                }
                                case 'Z' -> { // Win
                                    return switch (l.charAt(0)) {
                                        case 'A' -> "A Y";
                                        case 'B' -> "B Z";
                                        case 'C' -> "C X";
                                        default -> throw new IllegalStateException("Unexpected value: " + l.charAt(0));
                                    };
                                }
                                default -> throw new IllegalStateException("Unexpected value: " + l.charAt(2));
                            }
                        }
                ).map(l -> {
                            char player = l.charAt(2);
                            int scoreFromPlay = switch (player) {
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