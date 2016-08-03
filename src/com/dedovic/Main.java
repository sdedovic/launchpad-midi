package com.dedovic;

import com.dedovic.midi.MidiDeviceConfiguration;
import com.dedovic.midi.MidiLaunchpad;
import com.dedovic.midi.link.RandomColor;

import javax.sound.midi.MidiUnavailableException;

public class Main {
    public static void main(String[] args) {
        try {
            MidiLaunchpad launchpad = new MidiLaunchpad(MidiDeviceConfiguration.autodetect());

            RandomColor randomColor = new RandomColor();
            randomColor.setReceiver(launchpad.getReceiver());
            launchpad.getTransmitter().setReceiver(randomColor);

        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }
}
