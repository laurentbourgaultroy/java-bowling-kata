package ca.lbroy.kata;

import java.util.List;

/**
 * Copyright 2017 IAAH - All right reserved
 *
 * @author Laurent Bourgault-Roy
 */
public class Bowling {

    private static final int ALL_PINS = 10;

    public static int score(List<Integer> rolls) {
        if (rolls.isEmpty()) return 0;

        if (rolls.get(0) == ALL_PINS) {
            int frameScore = rolls.get(0) + rolls.get(1) + rolls.get(2);

            if (isLastFrame(rolls))return frameScore;
            else return frameScore + score(rolls.subList(1, rolls.size()));
        }

        if (rolls.get(0) + rolls.get(1) == ALL_PINS) {
            int frameScore = rolls.get(0) + rolls.get(1) + rolls.get(2);

            if (isLastFrame(rolls))return frameScore;
            else return frameScore + score(rolls.subList(2, rolls.size()));
        }

        return rolls.get(0) + rolls.get(1) + score(rolls.subList(2, rolls.size()));
    }

    private static boolean isLastFrame(List<Integer> rolls) {
        return rolls.size() <= 3;
    }

}
