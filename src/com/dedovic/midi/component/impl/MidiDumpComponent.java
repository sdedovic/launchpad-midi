package com.dedovic.midi.component.impl;


import com.dedovic.midi.MidiUtils;
import com.dedovic.midi.component.MidiComponent;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Transmitter;

public class MidiDumpComponent extends MidiComponent {

    @Override
    public MidiMessage transform(MidiMessage message) {
        if (message instanceof ShortMessage) {
            System.out.println(decodeShortMessage((ShortMessage) message));
        }
        return message;
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
}

