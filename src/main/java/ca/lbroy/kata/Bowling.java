package ca.lbroy.kata;

import java.util.List;

/**
 * Copyright 2017 IAAH - All right reserved
 *
 * @author Laurent Bourgault-Roy
 */
public class Bowling {
    public static int score(List<Integer> rolls) {
        int frameIndex = 0;
        int score = 0;
        while(frameIndex < 10) {
            int rollIndex = frameIndex * 2;
            int frameScore = rolls.get(rollIndex) + rolls.get(rollIndex + 1);
            if (frameScore == 10) frameScore += rolls.get(rollIndex + 2);
            score += frameScore;
            frameIndex++;
        }

        return score;
    }


}
