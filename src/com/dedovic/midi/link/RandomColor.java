package com.dedovic.midi.link;

import javax.sound.midi.*;
import java.util.Random;

public class RandomColor implements Receiver, Transmitter {
    Receiver receiver;
    Random random = new Random();

    @Override
    public void send(MidiMessage message, long timeStamp) {
        if (message instanceof ShortMessage) {
            ShortMessage in = (ShortMessage) message;
            try {
                MidiMessage out = in;
                if(in.getData2() != 0) {
                    out = new ShortMessage(in.getCommand(), in.getChannel(), in.getData1(), random.nextInt(127));
                }
                System.out.println("out.toString() = " + in.getData2());
                receiver.send(out, timeStamp);
            } catch (InvalidMidiDataException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public Receiver getReceiver() {
        return receiver;
    }
}
