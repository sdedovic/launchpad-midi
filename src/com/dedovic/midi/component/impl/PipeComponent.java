package com.dedovic.midi.component.impl;

import com.dedovic.midi.component.MidiComponent;

import javax.sound.midi.MidiMessage;

public class PipeComponent extends MidiComponent {
    @Override
    public MidiMessage transform(MidiMessage message) {
        return message;
    }
}
