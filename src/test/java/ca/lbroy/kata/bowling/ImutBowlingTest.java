package ca.lbroy.kata.bowling;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Copyright 2017 IAAH - All right reserved
 *
 * @author Laurent Bourgault-Roy
 */
public class ImutBowlingTest {
    @Test
    public void allGutterGame() {
        ImutGameStatus allGutterGame = addManyRoll(ImutGameStatus.start(), 20, 0);

        assertEquals("All gutter game", 0, allGutterGame.score());
    }

    private ImutGameStatus addManyRoll(ImutGameStatus gameStatus, int rollCount, int pins) {
        for (int i = 0; i < rollCount; i++) {
            gameStatus = gameStatus.addRoll(pins);
        }
        return gameStatus;
    }

    @Test
    public void onePinPerRollGame() {
        ImutGameStatus gameStatus = ImutGameStatus.start();
        gameStatus = addManyRoll(gameStatus, 20, 1);

        assertEquals("All gutter game", 20, gameStatus.score());
    }

    @Test
    public void oneSpareGame() {
        ImutGameStatus oneSpare = addManyRoll(ImutGameStatus.start(), 3, 5);
        ImutGameStatus completedGame = addManyRoll(oneSpare, 17, 0);

        assertEquals("One spare game", 20, completedGame.score());
    }

    @Test
    public void allSpareGame() {
        ImutGameStatus allSpareGame = addManyRoll(ImutGameStatus.start(), 21, 5);

        assertEquals("One spare game", 150, allSpareGame.score());
    }

    @Test
    public void oneStrikeGame() {
        ImutGameStatus oneStrike = ImutGameStatus.start()
                .addRoll(10)
                .addRoll(4)
                .addRoll(4);
        ImutGameStatus oneStrikeCompleted = addManyRoll(oneStrike, 16, 0);

        assertEquals("One strike game", 26, oneStrikeCompleted.score());
    }

    @Test
    public void allStrikeGame() {
        ImutGameStatus allStrikeGame = addManyRoll(ImutGameStatus.start(), 12, 10);

        assertEquals("One strike game", 300, allStrikeGame.score());
    }
}
