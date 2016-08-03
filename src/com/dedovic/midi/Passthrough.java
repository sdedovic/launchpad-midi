package com.dedovic.midi;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.Transmitter;

public class Passthrough implements Transmitter, Receiver {
    private Receiver receiver;
    private boolean isCosed;

    @Override
    public void send(MidiMessage message, long timeStamp) {
        if(receiver != null) {
            receiver.send(message, timeStamp);
        }
    }

    @Override
    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public Receiver getReceiver() {
        return receiver;
    }

    @Override
    public void close() {
        isCosed = true;
    }
}
