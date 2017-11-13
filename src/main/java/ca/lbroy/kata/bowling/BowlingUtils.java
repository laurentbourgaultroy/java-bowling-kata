package ca.lbroy.kata.bowling;

import java.util.List;

/**
 * Copyright 2017 IAAH - All right reserved
 *
 * @author Laurent Bourgault-Roy
 */
public class BowlingUtils {
    private BowlingUtils() {
        // Static method helper
    }

    static int sum(List<Integer> rolls) {
        return rolls.stream().mapToInt(Integer::intValue).sum();
    }

    static <T> List<T> head(List<T> list, int take) {
        return list.subList(0, take);
    }

    static <T> List<T> tail(List<T> list, int skip) {
        return list.subList(skip, list.size());
    }
}
