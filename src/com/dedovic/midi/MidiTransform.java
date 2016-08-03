package com.dedovic.midi;

import javax.sound.midi.*;

public abstract class MidiTransform {

    private ShortMessage transpose(ShortMessage message) throws InvalidMidiDataException {
        int newCommand = transformCommand(message.getCommand());
        int newChannel = transformChannel(message.getChannel());
        int newData1 = transformData1(message.getData1());
        int newData2 = transformData2(message.getData2());

        return new ShortMessage(newCommand, newChannel, newData1, newData2);
    }

    public abstract int transformCommand(int key);
    public abstract int transformChannel(int key);
    public abstract int transformData1(int key);
    public abstract int transformData2(int key);
}
