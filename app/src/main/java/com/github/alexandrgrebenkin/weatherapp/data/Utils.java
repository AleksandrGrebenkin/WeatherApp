package com.github.alexandrgrebenkin.weatherapp.data;

import java.io.BufferedReader;
import java.util.stream.Collectors;

public class Utils {
    public static String getLines(BufferedReader in) {
        return in.lines().collect(Collectors.joining("\n"));
    }

    public static String getRoundValueFromFloat (float f) {
        return String.valueOf(Math.round(f));
    }
}
