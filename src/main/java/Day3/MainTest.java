package Day3;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MainTest {
    @Test
    public void testSimpleInput() {
        assertThat(new Main().run(
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