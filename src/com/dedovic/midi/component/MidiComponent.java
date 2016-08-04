package com.dedovic.midi.component;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.Transmitter;

public abstract class MidiComponent implements Receiver, Transmitter {
    private Receiver receiver;
    private boolean isCosed;

    @Override
    public void send(MidiMessage message, long timeStamp) {
        if(receiver != null) {
            MidiMessage outMessage = transform(message);
            receiver.send(outMessage, timeStamp);
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

    public abstract MidiMessage transform(MidiMessage message);

    public MidiComponent pipe(MidiComponent component) {
        setReceiver(component);
        return component;
    }
}
