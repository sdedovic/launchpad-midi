package com.dedovic.midi.controller;

import com.dedovic.midi.component.MidiComponent;
import com.dedovic.midi.component.impl.PipeComponent;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Transmitter;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

public abstract class AbstractController implements MidiDevice {
    private Transmitter transmitter;
    private Receiver receiver;
    private boolean isOpen;
    private long timestamp;
    private long lastTimestamp;

    public AbstractController() {
        MidiComponent in = new PipeComponent();

        this.transmitter = createPipeline(in);

        this.receiver = in;
    }

    public abstract MidiComponent createPipeline(MidiComponent in);

    @Override
    public Info getDeviceInfo() {
        return new ControllerInfo("Midi Launchpad Controller", "dedovic.com", "Launchpad Controller", "0.1.0");
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

    private class ControllerInfo extends Info {
        /**
         * Constructs a device info object.
         *
         * @param name        the name of the device
         * @param vendor      the name of the company who provides the device
         * @param description a description of the device
         * @param version     version information for the device
         */
        protected ControllerInfo(String name, String vendor, String description, String version) {
            super(name, vendor, description, version);
        }
    }
}
