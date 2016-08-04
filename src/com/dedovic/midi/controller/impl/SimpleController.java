package com.dedovic.midi.controller.impl;

import com.dedovic.midi.component.MidiComponent;
import com.dedovic.midi.component.impl.MidiDumpComponent;
import com.dedovic.midi.component.impl.RandomVelocityComponent;
import com.dedovic.midi.controller.AbstractController;

public class SimpleController extends AbstractController {

    @Override
    public MidiComponent createPipeline(MidiComponent in) {
        return in.pipe(new MidiDumpComponent())
                .pipe(new RandomVelocityComponent())
                .pipe(new MidiDumpComponent());
    }
}
