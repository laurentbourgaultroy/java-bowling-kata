package ca.lbroy.kata.bowling;

import static java.lang.Math.max;

/**
 * Copyright 2017 IAAH - All right reserved
 *
 * @author Laurent Bourgault-Roy
 */
public class StateGame {

    private final Bonus bonus;
    private final int score;
    private final Frame frame;

    private StateGame(Frame frame, Bonus bonus, int score) {
        this.score = score;
        this.frame = frame;
        this.bonus = bonus;
    }

    public static StateGame start() {
        return new StateGame(Frame.start(), Bonus.start(), 0);
    }

    public StateGame addRoll(int pins) {
        return new StateGame(
                frame.addRoll(pins),
                frame.updateBonus(bonus, pins),
                score + pins * scoreMultiplier()
        );
    }

    private int scoreMultiplier() {
        if (frame.isExtraRoll()) return bonus.multiplier();
        return 1 + bonus.multiplier();
    }

    public int score() {
        return score;
    }

    abstract private static class Frame {
        protected static final int ALL_PINS = 10;

        protected final int index;

        private Frame(int index) {
            this.index = index;
        }

        private static Frame start() {
            return new FirstRollFrame(0);
        }

        protected abstract Frame addRoll(int pins);

        protected abstract Bonus updateBonus(Bonus bonus, int rollPins);

        protected boolean isExtraRoll() {
            return index >= 10;
        }
    }

    private static class FirstRollFrame extends Frame {
        private FirstRollFrame(int index) {
            super(index);
        }

        protected Frame addRoll(int pins) {
            if (pins == ALL_PINS) return new FirstRollFrame(index + 1);
            return new SecondRollFrame(index, pins);
        }

        @Override
        protected Bonus updateBonus(Bonus bonus, int pins) {
            if (isExtraRoll()) return bonus.standardRoll();
            if (pins == ALL_PINS) return bonus.strike();
            return bonus.standardRoll();
        }
    }

    private static class SecondRollFrame extends Frame {
        protected final int previousPins;

        private SecondRollFrame(int index, int downedPins) {
            super(index);
            this.previousPins = downedPins;
        }

        protected Frame addRoll(int pins) {
            return new FirstRollFrame(index + 1);
        }

        protected Bonus updateBonus(Bonus bonus, int pins) {
            if (isExtraRoll()) return bonus.standardRoll();
            if (previousPins + pins == ALL_PINS) return bonus.spare();
            return bonus.standardRoll();
        }
    }

    static class Bonus {
        private final int currentRoll;
        private final int nextRoll;

        private Bonus(int currentRoll, int nextRoll) {
            this.currentRoll = currentRoll;
            this.nextRoll = nextRoll;
        }

        private static Bonus start() {
            return new Bonus(0, 0);
        }

        private Bonus standardRoll() {
            return new Bonus(nextRoll, 0);
        }

        private Bonus spare() {
            return new Bonus(nextRoll + 1, 0);
        }

        private Bonus strike() {
            return new Bonus(nextRoll + 1, 1);
        }

        private int multiplier() {
            return currentRoll;
        }
    }
}
