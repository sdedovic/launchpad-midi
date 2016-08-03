package com.dedovic.midi.transmitter;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Transmitter;

public class ColorTransmitter implements Transmitter {


    private Receiver receiver;

    private boolean isClosed;

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
        this.isClosed = true;
    }

    public void send(int note) {
        ShortMessage message = new ShortMessage();
        try {
            message.setMessage(0x90, 1, note, 0x127);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        receiver.send(message, -1);
    }
}
