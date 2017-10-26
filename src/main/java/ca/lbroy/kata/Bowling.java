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
        if (isLastFrame(rolls)) {
            return sum(rolls);
        }

        if (isStrike(rolls)) {
            return sum(head(rolls, 3)) + score(tail(rolls, 1));
        }

        if (isSpare(rolls)) {
            return sum(head(rolls, 3)) + score(tail(rolls, 2));
        }

        return sum(head(rolls, 2)) + score(tail(rolls, 2));
    }

    private static boolean isLastFrame(List<Integer> rolls) {
        return rolls.size() <= 3;
    }

    private static boolean isStrike(List<Integer> rolls) {
        return rolls.get(0) == ALL_PINS;
    }

    private static boolean isSpare(List<Integer> rolls) {
        return rolls.get(0) + rolls.get(1) == ALL_PINS;
    }

    private static int sum(List<Integer> rolls) {
        return rolls.stream().mapToInt(Integer::intValue).sum();
    }

    private static List<Integer> head(List<Integer> list, int take) {
        return list.subList(0, take);
    }

    private static List<Integer> tail(List<Integer> list, int skip) {
        return list.subList(skip, list.size());
    }
}
