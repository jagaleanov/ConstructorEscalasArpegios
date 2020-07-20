/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scales;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jgale
 */
public class Ionian implements IScale {

    private Map<String, Double> tones;

    public Ionian(double fundamentalHz) {
        generateScale(fundamentalHz);
    }

    @Override
    public Map<String, Double> getTones() {
        return tones;
    }

    @Override
    public void generateScale(double fundamentalHz) {
        tones = new HashMap<String, Double>();
        tones.put("1", fundamentalHz);
        tones.put("2", fundamentalHz * Math.pow(2, 1.0 / 6.0));
        tones.put("3", fundamentalHz * Math.pow(2, 1.0 / 3.0));
        tones.put("4", fundamentalHz * Math.pow(2, 5.0 / 12.0));
        tones.put("5", fundamentalHz * Math.pow(2, 7.0 / 12.0));
        tones.put("6", fundamentalHz * Math.pow(2, 3.0 / 4.0));
        tones.put("7", fundamentalHz * Math.pow(2, 11.0 / 12.0));
        tones.put("8", fundamentalHz * 2);
    }

    @Override
    public String getName() {
        return "escala jónica o escala mayor";
    }

    @Override
    public String getDescription() {
        return "esta compuesta por los grados:\n"
                + "fundamental,\n"
                + "segunda mayor,\n"
                + "tercera mayor,\n"
                + "cuarta justa,\n"
                + "quinta justa,\n"
                + "sexta mayor,\n"
                + "septima mayor\n"
                + "y octava.\n";
    }

}
