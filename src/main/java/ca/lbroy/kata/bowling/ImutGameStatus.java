package ca.lbroy.kata.bowling;

/**
 * Copyright 2017 IAAH - All right reserved
 *
 * @author Laurent Bourgault-Roy
 */
public class ImutGameStatus {

    private final boolean spareBonus;
    private final int score;
    private final FrameStatus frameStatus;

    private ImutGameStatus(FrameStatus frameStatus, int score, boolean spareBonus) {
        this.score = score;
        this.frameStatus = frameStatus;
        this.spareBonus = spareBonus;
    }

    public static ImutGameStatus newGame() {
        return new ImutGameStatus(FrameStatus.firstFrame(), 0, false);
    }

    public ImutGameStatus addRoll(int pins) {
        return new ImutGameStatus(
                frameStatus.addRoll(pins),
                nextScore(pins),
                frameStatus.isSpare(pins)
        );
    }

    private int nextScore(int pins) {
        if (frameStatus.isBonusRoll() && spareBonus) {
            return score + pins * 2;
        }

        return score + pins;
    }

    public int score() {
        return score;
    }

    private static class FrameStatus {
        private final int index;
        private final int score;
        private final int rolls;

        public FrameStatus(int index, int score, int rolls) {
            this.index = index;
            this.score = score;
            this.rolls = rolls;
        }

        private static FrameStatus firstFrame() {
            return new FrameStatus(0, 0, 0);
        }

        private FrameStatus addRoll(int pins) {
            if (rolls == 1) {
                return new FrameStatus(index + 1, 0, 0);
            }

            return new FrameStatus(index, pins, rolls + 1);
        }

        private boolean isBonusRoll() {
            return index < 10;
        }

        private boolean isSpare(int rollPins) {
            return rolls == 1 && score + rollPins == 10;

        }
    }
}
