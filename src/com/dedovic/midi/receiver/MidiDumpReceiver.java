package com.dedovic.midi.receiver;


import com.dedovic.midi.MidiUtils;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Transmitter;

public class MidiDumpReceiver implements Receiver {

    @Override
    public void send(MidiMessage message, long timeStamp) {
        if (message instanceof ShortMessage) {
            System.out.println(decodeShortMessage((ShortMessage) message));
        }
    }

    private String decodeShortMessage(ShortMessage message) {
        String response = null;
        switch (message.getCommand()) {
            case 0x90:
                response = "noteOn " + MidiUtils.keyToString(message.getData1()) + " " + message.getData2();
                break;
            case 0x80:
                response = "noteOff" + MidiUtils.keyToString(message.getData2()) + " " + message.getData2();
                break;
        }
        return message.getChannel() + " " + response;
    }

    @Override
    public void close() {

    }
}

