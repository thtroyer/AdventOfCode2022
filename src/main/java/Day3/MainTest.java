package Day3;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MainTest {
    @Test
    public void testSimpleInput() {
        assertThat(new Main().part1(
                """
                        vJrwpWtwJgWrhcsFMMfFFhFp
                        jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
                        PmmdzqPrVvPwwTWBwg
                        wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
                        ttgJtRGJQctTZtZT
                        CrZsJsPPZsGzwwsLwLmpwMDw
                        """
        )).isEqualTo(157);
    }

    @Test
    public void testFindUnique() {

        assertThat(new Main().findUnique(
                List.of("""
                        vJrwpWtwJgWrhcsFMMfFFhFp
                        jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
                        PmmdzqPrVvPwwTWBwg
                        """.split("\n")))
        ).isEqualTo('r');
    }

    @Test
    public void testGetValueOfChar() {
        Main main = new Main();

        assertThat(main.getValueOfChar('a'))
                .isEqualTo(1);
        assertThat(main.getValueOfChar('z'))
                .isEqualTo(26);
        assertThat(main.getValueOfChar('A'))
                .isEqualTo(27);
        assertThat(main.getValueOfChar('Z'))
                .isEqualTo(52);
    }
}