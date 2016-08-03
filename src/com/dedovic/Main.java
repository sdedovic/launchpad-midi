package com.dedovic;

import com.dedovic.controller.Arp;
import com.dedovic.midi.receiver.MidiDumpReceiver;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Transmitter;
import java.io.IOException;

public class Main {

    private static final String DEVICE_NAME = "Launchpad S";

    private static MidiDevice device;

    public static void main(String[] args) {
        MidiDevice.Info[] deviceInfo = MidiSystem.getMidiDeviceInfo();

        // find launchpad midi device
        for (MidiDevice.Info info: deviceInfo) {
            if (info.getName().equals(DEVICE_NAME)) {
                try {
                    device = MidiSystem.getMidiDevice(info);
                    device.open();
                    System.out.println("Connected to " + info.getName());
                    System.out.println("Press Enter to exit");
                } catch (MidiUnavailableException e) {
                    System.out.println("Unable to connect to " + info.getName());
                } finally {
                    System.out.println("======================");
                }
                break;
            }
        }
        if (device != null && device.isOpen()) {
            try {
                // route midi so that launchpad out -> arp in -> stdout
                Arp arp = new Arp();
                Transmitter transmitter = device.getTransmitter();
                transmitter.setReceiver(arp.getReceiver()); 
            } catch (MidiUnavailableException e) {
                e.printStackTrace();
            }
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Exiting program");
                device.close();
            }
        } else {
            System.out.println("Unable to find" + DEVICE_NAME);
        }
    }
}
