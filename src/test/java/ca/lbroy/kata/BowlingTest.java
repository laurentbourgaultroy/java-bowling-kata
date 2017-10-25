package ca.lbroy.kata;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.nCopies;
import static org.junit.Assert.assertEquals;

/**
 * Copyright 2017 IAAH - All right reserved
 *
 * @author Laurent Bourgault-Roy
 */
public class BowlingTest {
    @Test
    public void allGutterIsZero() {
        assertEquals("Only gutter score",
                0, Bowling.score(nCopies(20, 0)));
    }

    @Test
    public void standardIsSumOfRolls() {
        assertEquals("Only one pin rolls",
                20, Bowling.score(nCopies(20, 1)));
    }

    @Test
    public void spareTakeSumFromNextFrame() {
        List<Integer> rolls = newArrayList(7, 3, 4);
        rolls.addAll(nCopies(17, 0));

        assertEquals("Score with a spare",
                18, Bowling.score(rolls));
    }
}
