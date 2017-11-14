package ca.lbroy.kata.bowling;

import static java.lang.Math.max;

/**
 * Copyright 2017 IAAH - All right reserved
 *
 * @author Laurent Bourgault-Roy
 */
public class ImutGameStatus {

    private final Bonus bonus;
    private final int score;
    private final Frame frame;

    private ImutGameStatus(Frame frame, Bonus bonus, int score) {
        this.score = score;
        this.frame = frame;
        this.bonus = bonus;
    }

    public static ImutGameStatus start() {
        return new ImutGameStatus(Frame.start(), Bonus.start(), 0);
    }

    public ImutGameStatus addRoll(int pins) {
        return new ImutGameStatus(
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

    private static class Frame {
        private final int index;
        private final int score;
        private final int rolls;

        public Frame(int index, int score, int rolls) {
            this.index = index;
            this.score = score;
            this.rolls = rolls;
        }

        private static Frame start() {
            return new Frame(0, 0, 0);
        }

        private Frame addRoll(int pins) {
            if (isStrike(pins) || rolls == 1) {
                return new Frame(index + 1, 0, 0);
            }

            return new Frame(index, pins, rolls + 1);
        }

        private Bonus updateBonus(Bonus bonus, int rollPins) {
            if (isExtraRoll()) return bonus.standardRoll();
            if (isStrike(rollPins)) return bonus.strike();
            if (isSpare(rollPins)) return bonus.spare();

            return bonus.standardRoll();
        }

        private boolean isExtraRoll() {
            return index >= 10;
        }

        private boolean isSpare(int rollPins) {
            return rolls == 1 && score + rollPins == 10;
        }

        private boolean isStrike(int rollPins) {
            return rolls == 0 && rollPins == 10;
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
