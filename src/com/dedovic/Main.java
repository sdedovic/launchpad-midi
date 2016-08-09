package com.dedovic;

import com.dedovic.midi.MidiDeviceConfiguration;
import com.dedovic.midi.MidiLaunchpad;
import com.dedovic.midi.MidiUtils;
import com.dedovic.midi.controller.impl.SimpleController;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;

public class Main {
    private static final boolean DEBUG = true;

    public static void main(String[] args) {
        if (DEBUG) {
            try {
                System.out.println("Devices:");
                for(MidiDevice.Info deviceInfo: MidiSystem.getMidiDeviceInfo()) {
                    String output = deviceInfo.getName();
                    MidiDevice device = MidiSystem.getMidiDevice(deviceInfo);

                    output += device.getMaxTransmitters() == -1 ? ", Reciever" : "";
                    output += device.getMaxReceivers() == -1 ? ", Transmitter" : "";

                    System.out.println(output);
                }
            } catch (MidiUnavailableException e) {
                e.printStackTrace();
            }
        }
        try {

            MidiDevice drums = MidiUtils.getTransmitterByName("E-MU XMidi1X1 ");

            MidiLaunchpad launchpad = new MidiLaunchpad(MidiDeviceConfiguration.autodetect());
            MidiDevice controller = new SimpleController();

            controller.getTransmitter().setReceiver(launchpad.getReceiver());

            launchpad.getTransmitter().setReceiver(controller.getReceiver());

        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }
}
