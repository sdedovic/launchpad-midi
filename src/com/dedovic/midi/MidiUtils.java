package com.dedovic.midi;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MidiUtils {

    public static final String[] KEYS = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};

    public static String keyToString(int key) {
        if (key > 127) {
            return "illegal value";
        } else {
            int nNote = key % 12;
            int nOctave = key / 12;
            return KEYS[nNote] + (nOctave - 1) + "(" + key + ")";
        }
    }

    public static List<MidiDevice> getDevicesByName(String deviceName) throws MidiUnavailableException {
        List<MidiDevice> out = new ArrayList<>();

        MidiDevice.Info[] midiDeviceInfo = MidiSystem.getMidiDeviceInfo();
        for (MidiDevice.Info info : midiDeviceInfo) {
            if (info.getName().equals(deviceName)) {
                out.add(MidiSystem.getMidiDevice(info));
            }
        }
        return out;
    }

    public static MidiDevice getTransmitterByName(String name) {
        try {
            return getDevicesByName(name).stream()
                    .filter(device -> device.getMaxReceivers() == -1)
                    .collect(Collectors.toList())
                    .get(0);
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
        return null;
    }
}
