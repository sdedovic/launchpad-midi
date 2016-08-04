package com.dedovic.midi.component.impl;

import com.dedovic.midi.component.MidiComponent;

import javax.sound.midi.*;
import java.util.Random;

public class RandomVelocityComponent extends MidiComponent {
    private Random rng;

    public RandomVelocityComponent() {
        rng = new Random();
    }
    @Override
    public MidiMessage transform(MidiMessage message) {
        if (message instanceof ShortMessage) {
            ShortMessage in = (ShortMessage) message;
            try {
                MidiMessage out = in;
                if(in.getData2() != 0) {
                    out = new ShortMessage(in.getCommand(), in.getChannel(), in.getData1(), rng.nextInt(127));
                }
                return out;
            } catch (InvalidMidiDataException e) {
                e.printStackTrace();
            }
        }
        return message;
    }
}
