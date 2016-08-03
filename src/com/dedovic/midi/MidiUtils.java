package com.dedovic.midi;

import java.util.HashMap;

public class MidiUtils {

    public static final String[] KEYS = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};

    public static String keyToString(int key) {
        if (key > 127)
        {
            return "illegal value";
        }
        else
        {
            int	nNote = key % 12;
            int	nOctave = key / 12;
            return KEYS[nNote] + (nOctave - 1) + "(" + key + ")";
        }
    }
}
