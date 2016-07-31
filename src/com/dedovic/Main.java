package com.dedovic;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Transmitter;
import java.io.IOException;

public class Main {

    public static final String DEVICE_NAME = "Launchpad S";

    private static MidiDevice device;

    public static void main(String[] args) {
        MidiDevice.Info[] deviceInfo = MidiSystem.getMidiDeviceInfo();

        for (MidiDevice.Info info: deviceInfo) {
            if (info.getName().equals(DEVICE_NAME)) {
                try {
                    device = MidiSystem.getMidiDevice(info);
                    device.open();
                    System.out.println("Connected to " + info.getName());
                    System.out.println("Press Enter to exit");
                } catch (MidiUnavailableException e) {
                    System.out.println("Unable to connect to " + info.getName());
                }
                System.out.println("======================");
                break;
            }
        }

        if (device != null && device.isOpen()) {
            try {
                Transmitter transmitter = device.getTransmitter();
                transmitter.setReceiver(new Printer());
            } catch (MidiUnavailableException e) {
                e.printStackTrace();
            }
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            device.close();
        }
    }
}
