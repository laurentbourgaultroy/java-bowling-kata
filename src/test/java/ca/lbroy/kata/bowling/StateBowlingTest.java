package ca.lbroy.kata.bowling;

import ca.lbroy.kata.bowling.StateBowling;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Copyright 2017 IAAH - All right reserved
 *
 * @author Laurent Bourgault-Roy
 */
public class StateBowlingTest {

    private StateBowling bowling;

    @Before
    public void createGame() {
        bowling = new StateBowling();
    }

    @Test
    public void allGutterGameIsZero() {
        int pins = 0;
        rollMany(pins, 20);
        assertEquals("All gutter game", 0, bowling.score());
    }

    @Test
    public void onlyOnePinIsTwenty() {
        rollMany(1, 20);
        assertEquals("Game of one pin per roll", 20, bowling.score());
    }

    @Test
    public void oneSpareTakeScoreFromOneRoll() {
        bowling.roll(6);
        bowling.roll(4);
        bowling.roll(7);
        rollMany(0, 17);
        assertEquals("Game with one spare", 24, bowling.score());
    }

    @Test
    public void allSpareGame() {
        rollMany(5, 21);
        assertEquals("Game with all spares", 150, bowling.score());
    }

    @Test
    public void strikeTakeScoreFromNextTwoRoll() {
        bowling.roll(10);
        bowling.roll(6);
        bowling.roll(3);
        rollMany(0, 17);
        assertEquals("Game with one spare", 28, bowling.score());
    }

    private void rollMany(int pins, int rollCount) {
        for (int i = 0; i < rollCount; i++) {
            bowling.roll(pins);
        }
    }

    @Test
    public void hasScoreAccordingToRoll() {
        StateBowling.Frame frame = new StateBowling.Frame();
        assertEquals("Frame with no roll", 0, frame.score());

        frame.addRoll(1);
        assertEquals("Frame with one roll", 1, frame.score());

        frame.addRoll(2);
        assertEquals("Frame with one roll", 3, frame.score());
    }

    @Test
    public void FrameStopSavingRollsWhenNotActive() {
        StateBowling.Frame standardFrame = new StateBowling.Frame();
        for (int i = 0; i < 4; i++) standardFrame.addRoll(1);
        assertEquals("Full standard frame", 2, standardFrame.score());

        StateBowling.Frame spareFrame = new StateBowling.Frame();
        for (int i = 0; i < 4; i++) spareFrame.addRoll(5);
        assertEquals("Full spare frame", 15, spareFrame.score());

        StateBowling.Frame strikeFrame = new StateBowling.Frame();
        for (int i = 0; i < 4; i++) strikeFrame.addRoll(10);
        assertEquals("Full spare frame", 30, strikeFrame.score());
    }
}
