/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scales;

import java.util.Map;

/**
 *
 * @author jgale
 */
public interface IScale {
    
    public Map<String, Double> getTones();
    public void generateScale( double fundamentalHz );
    public String getName();
    public String getDescription();
}
