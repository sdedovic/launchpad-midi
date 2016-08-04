package com.dedovic;

import com.dedovic.midi.MidiDeviceConfiguration;
import com.dedovic.midi.MidiLaunchpad;
import com.dedovic.midi.component.MidiComponent;
import com.dedovic.midi.component.impl.MidiDumpComponent;
import com.dedovic.midi.component.impl.RandomVelocityComponent;
import com.dedovic.midi.controller.impl.SimpleController;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiUnavailableException;

public class Main {
    public static void main(String[] args) {
        try {
            MidiLaunchpad launchpad = new MidiLaunchpad(MidiDeviceConfiguration.autodetect());
            MidiDevice controller = new SimpleController();

            controller.getTransmitter().setReceiver(launchpad.getReceiver());

            launchpad.getTransmitter().setReceiver(controller.getReceiver());

        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }
}
