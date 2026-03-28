package com.logincontroller.filmrentalsystem.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Sakila MPAA order: G &lt; PG &lt; PG-13 &lt; R &lt; NC-17
 */
public final class FilmRatingUtil {

    private static final List<String> ORDER = Arrays.asList("G", "PG", "PG-13", "R", "NC-17");

    private FilmRatingUtil() {
    }

    public static int indexOf(String rating) {
        if (rating == null) {
            return -1;
        }
        return ORDER.indexOf(rating.trim());
    }

    public static List<String> ratingsStrictlyGreaterThan(String rating) {
        int i = indexOf(rating);
        if (i < 0) {
            return List.of();
        }
        return ORDER.subList(i + 1, ORDER.size());
    }

    public static List<String> ratingsStrictlyLessThan(String rating) {
        int i = indexOf(rating);
        if (i < 0) {
            return List.of();
        }
        return ORDER.subList(0, i);
    }
}
