package com.dedovic.controller;

import com.dedovic.midi.transmitter.ColorTransmitter;
import com.dedovic.midi.receiver.MidiDumpReceiver;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Transmitter;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

public class Arp implements MidiDevice {
    private Transmitter transmitter;
    private Receiver receiver;
    private boolean isOpen;
    private long timestamp;
    private Info info;
    private long lastTimestamp;


    public Arp() {
        info = new ArpInfo("Arp", "dedovic", "Java Midi Arp", "0.1.0");

        transmitter = new ColorTransmitter();
        receiver = new MidiDumpReceiver();

    }

    @Override
    public Info getDeviceInfo() {
        return info;
    }

    @Override
    public void open() throws MidiUnavailableException {
        isOpen = true;
        timestamp = 0L;
    }

    @Override
    public void close() {
        isOpen = false;
        timestamp = 0;
    }

    @Override
    public boolean isOpen() {
        return isOpen;
    }

    @Override
    public long getMicrosecondPosition() {
        long now = Instant.now().toEpochMilli();
        timestamp += now - lastTimestamp;
        lastTimestamp = now;
        return timestamp;
    }

    @Override
    public int getMaxReceivers() {
        return 1;
    }

    @Override
    public int getMaxTransmitters() {
        return 1;
    }

    @Override
    public Receiver getReceiver() throws MidiUnavailableException {
        return receiver;
    }

    @Override
    public List<Receiver> getReceivers() {
        return Collections.singletonList(receiver);
    }

    @Override
    public Transmitter getTransmitter() throws MidiUnavailableException {
        return transmitter;
    }

    @Override
    public List<Transmitter> getTransmitters() {
        return Collections.singletonList(transmitter);
    }

    private class ArpInfo extends Info {
        /**
         * Constructs a device info object.
         *
         * @param name        the name of the device
         * @param vendor      the name of the company who provides the device
         * @param description a description of the device
         * @param version     version information for the device
         */
        protected ArpInfo(String name, String vendor, String description, String version) {
            super(name, vendor, description, version);
        }
    }
}
