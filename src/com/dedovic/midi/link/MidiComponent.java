package com.dedovic.midi.link;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.Transmitter;

public class MidiComponent implements Receiver, Transmitter {

    @Override
    public void send(MidiMessage message, long timeStamp) {

    }

    @Override
    public void close() {

    }

    @Override
    public void setReceiver(Receiver receiver) {

    }

    @Override
    public Receiver getReceiver() {
        return null;
    }
}
