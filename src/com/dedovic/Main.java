package com.dedovic;

import com.dedovic.midi.MidiDeviceConfiguration;
import com.dedovic.midi.MidiLaunchpad;
import com.dedovic.midi.component.MidiComponent;
import com.dedovic.midi.component.impl.MidiDumpComponent;
import com.dedovic.midi.component.impl.RandomVelocityComponent;

import javax.sound.midi.MidiUnavailableException;

public class Main {
    public static void main(String[] args) {
        try {
            MidiLaunchpad launchpad = new MidiLaunchpad(MidiDeviceConfiguration.autodetect());

            MidiComponent randomColor = new RandomVelocityComponent();
            randomColor.setReceiver(launchpad.getReceiver());
            MidiComponent dump = new MidiDumpComponent();
            dump.pipe(randomColor);

            launchpad.getTransmitter().setReceiver(dump);

        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }
}
