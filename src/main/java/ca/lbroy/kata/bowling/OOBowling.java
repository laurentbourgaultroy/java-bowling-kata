package ca.lbroy.kata.bowling;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Copyright 2017 IAAH - All right reserved
 *
 * @author Laurent Bourgault-Roy
 */
public class OOBowling {

    public static final int GAME_FRAMES = 10;
    private List<Frame> frames = newArrayList(new Frame());
    private Frame activeFrame;

    public OOBowling() {
        activeFrame = new Frame();
        frames = newArrayList(activeFrame);
    }

    public void roll(int pins) {
        for (Frame frame : frames) {
            frame.addRoll(pins);
        }

        if (!activeFrame.isActive() && frames.size() < GAME_FRAMES) {
            activeFrame = new Frame();
            frames.add(activeFrame);
        }
    }

    public int score() {
        return frames.stream().mapToInt(Frame::score).sum();
    }

    public static class Frame {
        private static final int ALL_PINS = 10;

        List<Integer> rolls = new ArrayList<>();

        public void addRoll(int pins) {
            if (isComplete()) return;
            rolls.add(pins);
        }

        private boolean isStrikeBonus() {
            return isStrike() && rolls.size() < 3;
        }

        private boolean isStrike() {
            return rolls.size() >= 1 && rolls.get(0) == ALL_PINS;
        }

        private boolean isSpareBonus() {
            return isSpare() && rolls.size() < 3;
        }

        private boolean isSpare() {
            return rolls.size() >= 2 && (rolls.get(0) + rolls.get(1)) == ALL_PINS;
        }

        public boolean isActive() {
            if (isStrike()) return false;
            return rolls.size() < 2;
        }

        private boolean isComplete() {
            if (isStrikeBonus() || isSpareBonus() || isActive()) return false;
            return true;
        }

        public int score() {
            return rolls.stream().mapToInt(Integer::intValue).sum();
        }
    }
}
