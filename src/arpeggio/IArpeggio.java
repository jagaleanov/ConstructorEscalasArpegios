/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arpeggio;

import java.util.Map;
import scales.IScale;

/**
 *
 * @author jgale
 */
public interface IArpeggio {
    public Map<String, Double> getTones();
    public void generateArpeggio(IScale scale);
    public String getName();
    public String getDescription();
}
