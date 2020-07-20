/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arpeggio;

import java.util.HashMap;
import java.util.Map;
import scales.IScale;

/**
 *
 * @author jgale
 */
public class addNinthArpeggio implements IArpeggio{
    
    private Map<String, Double> notes;

    public addNinthArpeggio(IScale scale) {
        generateArpeggio(scale);
    }

    @Override
    public Map<String, Double> getTones() {
        return notes;
    }

    @Override
    public void generateArpeggio(IScale scale) {
        notes = new HashMap<String, Double>();
        Map<String, Double> tones = scale.getTones();
        
        if (tones.containsKey("1")) {
            notes.put("1", tones.get("1"));
        }
        if (tones.containsKey("3")) {
            notes.put("3", tones.get("3"));
        }
        if (tones.containsKey("5")) {
            notes.put("5", tones.get("5"));
        }
        if (tones.containsKey("2")) {
            notes.put("9", tones.get("2") * 2.0);
        }
    }

    @Override
    public String getName() {
        return "arpegio con novena";
    }

    @Override
    public String getDescription() {
        return " esta compuesto por los grados:\n"
                + "fundamental,\n"
                + "tercero,\n"
                + "quinto\n"
                + "y noveno (que es el mismo segundo pero una octava más arriba)\n"
                + "de la escala.\n";
    }
}
