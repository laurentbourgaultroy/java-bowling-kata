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
        ImutGameStatus allGutterGame = addManyRoll(ImutGameStatus.newGame(), 20, 0);

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
        ImutGameStatus gameStatus = ImutGameStatus.newGame();
        gameStatus = addManyRoll(gameStatus, 20, 1);

        assertEquals("All gutter game", 20, gameStatus.score());
    }

    @Test
    public void oneSpareGame() {
        ImutGameStatus oneSpare = addManyRoll(ImutGameStatus.newGame(), 3, 5);
        ImutGameStatus completedGame = addManyRoll(oneSpare, 17, 0);

        assertEquals("One spare game", 20, completedGame.score());
    }

    @Test
    public void allSpareGame() {
        ImutGameStatus allSpareGame = addManyRoll(ImutGameStatus.newGame(), 21, 5);

        assertEquals("One spare game", 150, allSpareGame.score());
    }
}
