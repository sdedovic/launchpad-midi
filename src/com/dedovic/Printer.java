package com.dedovic;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.swing.text.StringContent;
import java.util.Formatter;

public class Printer implements Receiver {

    private static final String[] KEYS = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};

    @Override
    public void send(MidiMessage message, long timeStamp) {
        if (message instanceof ShortMessage) {
            System.out.println(Long.toString(timeStamp) + " " + decodeShortMessage((ShortMessage) message));
        }
    }

    private String decodeShortMessage(ShortMessage message) {
        String response = null;
        switch (message.getCommand()) {
            case 144:
                response = "noteOn " + getKey(message.getData1()) + " " + message.getData2();
                break;
            case 128:
                response = "noteOff" + getKey(message.getData2()) + " " + message.getData2();
                break;
        }
        return response;
    }

    private static String getKey(int nKeyNumber) {
        if (nKeyNumber > 127)
        {
            return "illegal value";
        }
        else
        {
            int	nNote = nKeyNumber % 12;
            int	nOctave = nKeyNumber / 12;
            return KEYS[nNote] + (nOctave - 1);
        }
    }

    @Override
    public void close() {

    }
}
