package com.dedovic.midi.receiver;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

public class MidiDumpReceiver implements Receiver {

    private static final String[] KEYS = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};

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
                response = "noteOn " + getKey(message.getData1()) + " " + message.getData2();
                break;
            case 0x80:
                response = "noteOff" + getKey(message.getData2()) + " " + message.getData2();
                break;
        }
        return message.getChannel() + " " + response;
    }

    private static String getKey(int key) {
        if (key > 127)
        {
            return "illegal value";
        }
        else
        {
            int	nNote = key % 12;
            int	nOctave = key / 12;
            return KEYS[nNote] + (nOctave - 1) + "(" + key + ")";
        }
    }

    @Override
    public void close() {

    }
}

