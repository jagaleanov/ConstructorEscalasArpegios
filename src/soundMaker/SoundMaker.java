/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soundMaker;

import arpeggio.IArpeggio;
import java.nio.ByteBuffer;
import java.util.Map;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import scales.IScale;

/**
 *
 * @author jgale
 */
public class SoundMaker {

    public void play(IScale scale) throws LineUnavailableException, InterruptedException {
        play(scale.getTones());
    }

    public void play(IArpeggio arpeggio) throws LineUnavailableException, InterruptedException {
        play(arpeggio.getTones());
    }

    public void play(Map<String, Double> fFreqs) throws LineUnavailableException, InterruptedException {
        for (Double value : fFreqs.values()) {
            play(value);
        }
    }

    //This is just an example - you would want to handle LineUnavailable properly...
    public void play(double fFreq) throws LineUnavailableException, InterruptedException {
        final int SAMPLING_RATE = 44100;              // Audio sampling rate
        final int SAMPLE_SIZE = 2;                    // Audio sample size in bytes

        SourceDataLine line;

        //Position through the sine wave as a percentage (i.e. 0 to 1 is 0 to 2*PI)
        double fCyclePosition = 0;

        //Open up audio output, using 44100hz sampling rate, 16 bit samples, mono, 
        //and big endian byte ordering
        AudioFormat format = new AudioFormat(SAMPLING_RATE, 16, 1, true, true);
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

        if (!AudioSystem.isLineSupported(info)) {
            System.out.println("Line matching " + info + " is not supported.");
            throw new LineUnavailableException();
        }

        line = (SourceDataLine) AudioSystem.getLine(info);
        line.open(format);
        line.start();

        // Make our buffer size match audio system's buffer
        ByteBuffer cBuf = ByteBuffer.allocate(line.getBufferSize());

        int ctSamplesTotal = SAMPLING_RATE * 1;         // Output for roughly 5 seconds

        //On each pass main loop fills the available free space in the audio buffer
        //Main loop creates audio samples for sine wave, runs until we tell the thread to exit
        //Each sample is spaced 1/SAMPLING_RATE apart in time
        while (ctSamplesTotal > 0) {
            double fCycleInc = fFreq / SAMPLING_RATE;    // Fraction of cycle between samples

            cBuf.clear();                              // Discard the samples from the last pass

            // Figure out how many samples we can add
            int ctSamplesThisPass = line.available() / SAMPLE_SIZE;
            for (int i = 0; i < ctSamplesThisPass; i++) {
                cBuf.putShort((short) (Short.MAX_VALUE * Math.sin(2 * Math.PI * fCyclePosition)));

                fCyclePosition += fCycleInc;
                if (fCyclePosition > 1) {
                    fCyclePosition -= 1;
                }
            }

            //Write sine samples to the line buffer.  If the audio buffer is full, this will 
            // block until there is room (we never write more samples than buffer will hold)
            line.write(cBuf.array(), 0, cBuf.position());
            ctSamplesTotal -= ctSamplesThisPass;     // Update total number of samples written 

            //Wait until the buffer is at least half empty  before we add more
            while (line.getBufferSize() / 2 < line.available()) {
                Thread.sleep(1);
            }
        }

        // Done playing the whole waveform, now wait until the queued samples finish 
        //playing, then clean up and exit
        line.drain();
        line.close();
    }

}
