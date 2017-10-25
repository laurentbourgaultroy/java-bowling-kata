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
        if (rolls.size() <= 3) return rolls.stream().mapToInt(Integer::intValue).sum();

        if (rolls.get(0) == ALL_PINS) {
            return rolls.get(0) + rolls.get(1) + rolls.get(2) + score(rolls.subList(1, rolls.size()));
        }

        if (rolls.get(0) + rolls.get(1) == ALL_PINS) {
            return rolls.get(0) + rolls.get(1) + rolls.get(2) + score(rolls.subList(2, rolls.size()));
        }

        return rolls.get(0) + rolls.get(1) + score(rolls.subList(2, rolls.size()));
    }
}
