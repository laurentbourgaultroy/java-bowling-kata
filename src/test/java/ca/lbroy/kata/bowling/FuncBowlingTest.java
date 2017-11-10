package ca.lbroy.kata.bowling;

import ca.lbroy.kata.bowling.FuncBowling;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.nCopies;
import static org.junit.Assert.assertEquals;

/**
 * Copyright 2017 IAAH - All right reserved
 *
 * @author Laurent Bourgault-Roy
 */
public class FuncBowlingTest {
    @Test
    public void allGutterIsZero() {
        assertEquals("Only gutter score",
                0, FuncBowling.score(nCopies(20, 0)));
    }

    @Test
    public void standardIsSumOfRolls() {
        assertEquals("Only one pin rolls",
                20, FuncBowling.score(nCopies(20, 1)));
    }

    @Test
    public void spareTakeSumFromNextFrame() {
        List<Integer> rolls = newArrayList(7, 3, 4);
        rolls.addAll(nCopies(17, 0));

        assertEquals("Score with a spare",
                18, FuncBowling.score(rolls));
    }

    @Test
    public void strikeTakeSumOfNextTwoRolls() {
        List<Integer> rolls = newArrayList(10, 4, 4);
        rolls.addAll(nCopies(16, 0));

        assertEquals("Score with a strike",
                26, FuncBowling.score(rolls));
    }

    @Test
    public void allStrikeScore() {
        assertEquals("Only strike score", 300, FuncBowling.score(nCopies(12, 10)));
    }

    @Test
    public void allSpareScore() {
        assertEquals("Only strike score", 150, FuncBowling.score(nCopies(21, 5)));
    }
}
