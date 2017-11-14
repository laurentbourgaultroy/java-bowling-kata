package ca.lbroy.kata.bowling;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Copyright 2017 IAAH - All right reserved
 *
 * @author Laurent Bourgault-Roy
 */
public class StateGameTest {
    @Test
    public void allGutterGame() {
        StateGame allGutterGame = addManyRoll(StateGame.start(), 20, 0);

        assertEquals("All gutter game", 0, allGutterGame.score());
    }

    private StateGame addManyRoll(StateGame gameStatus, int rollCount, int pins) {
        for (int i = 0; i < rollCount; i++) {
            gameStatus = gameStatus.addRoll(pins);
        }
        return gameStatus;
    }

    @Test
    public void onePinPerRollGame() {
        StateGame gameStatus = StateGame.start();
        gameStatus = addManyRoll(gameStatus, 20, 1);

        assertEquals("All gutter game", 20, gameStatus.score());
    }

    @Test
    public void oneSpareGame() {
        StateGame oneSpare = addManyRoll(StateGame.start(), 3, 5);
        StateGame completedGame = addManyRoll(oneSpare, 17, 0);

        assertEquals("One spare game", 20, completedGame.score());
    }

    @Test
    public void allSpareGame() {
        StateGame allSpareGame = addManyRoll(StateGame.start(), 21, 5);

        assertEquals("One spare game", 150, allSpareGame.score());
    }

    @Test
    public void oneStrikeGame() {
        StateGame oneStrike = StateGame.start()
                .addRoll(10)
                .addRoll(4)
                .addRoll(4);
        StateGame oneStrikeCompleted = addManyRoll(oneStrike, 16, 0);

        assertEquals("One strike game", 26, oneStrikeCompleted.score());
    }

    @Test
    public void allStrikeGame() {
        StateGame allStrikeGame = addManyRoll(StateGame.start(), 12, 10);

        assertEquals("One strike game", 300, allStrikeGame.score());
    }
}
