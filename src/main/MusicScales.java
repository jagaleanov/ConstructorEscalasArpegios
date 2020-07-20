/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import soundMaker.SoundMaker;
import arpeggio.IArpeggio;
import arpeggio.NinthArpeggio;
import arpeggio.SeventhArpeggio;
import arpeggio.TriadArpeggio;
import arpeggio.addNinthArpeggio;
import java.util.Scanner;
import javax.sound.sampled.LineUnavailableException;
import scales.Doric;
import scales.Ionian;
import scales.Aeolian;
import scales.MinorPentatonic;
import scales.IScale;

/**
 *
 * @author jgale
 */
public class MusicScales {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws LineUnavailableException, 
            InterruptedException {

        System.out.println("Ingrese una frecuencia fundamental 20Hz - 5000Hz "
                + "(double)");
        Scanner txtHz = new Scanner(System.in);
        while (!txtHz.hasNextDouble()) {
            txtHz.nextLine();
        }
        double fundamentalHz = txtHz.nextDouble();

        if (fundamentalHz < 20 || fundamentalHz > 5000) {
            System.out.println("La frecuencia seleccionada puede generar tonos "
                    + "no audibles por el oido humano. Se tomara 440Hz en su "
                    + "lugar.");
            fundamentalHz = 440;
        }
        
        SoundMaker instrument = new SoundMaker();
        System.out.println("La frecuencia seleccionada como nota fundamental "
                + "es " + fundamentalHz + "Hz y se escucha así:\n");
        instrument.play(fundamentalHz);

        System.out.println("Seleccione el número correspondiente a la opción "
                + "de escala que desea construir:");
        System.out.println("1 -> Jónica (Mayor)");
        System.out.println("2 -> Eólica (menor)");
        System.out.println("3 -> Dórica");
        System.out.println("4 -> Pentatónica menor\n");

        Scanner txtScale = new Scanner(System.in);
        while (!txtScale.hasNextInt()) {
            txtScale.nextLine();
        }

        int idScale = txtScale.nextInt();

        IScale scale;
        String scaleName;

        switch (idScale) {
            case 1:
                System.out.println("Jónica\n");
                scale = new Ionian(fundamentalHz);
                break;
            case 2:
                System.out.println("Eólica\n");
                scale = new Aeolian(fundamentalHz);
                break;
            case 3:
                System.out.println("Dórica\n");
                scale = new Doric(fundamentalHz);
                break;
            case 4:
                System.out.println("Pentatónica menor\n");
                scale = new MinorPentatonic(fundamentalHz);
                break;
            default:
                System.out.println("No se ha seleccionado una opción válida, "
                        + "se seleccionara la opción por defecto (Jónica)\n");
                scale = new Ionian(fundamentalHz);
                break;
        }
        
        System.out.println("La " + scale.getName() + " "
                + scale.getDescription() + "Sobre una frecuencia fundamental "
                + "de " + fundamentalHz + "Hz se escucha así:\n");
        instrument.play(scale);
        
        

        System.out.println("Seleccione el número correspondiente a la opción "
                + "de arpegio que desea construir sobre la escala:");
        System.out.println("1 -> Arpegio de triada");
        System.out.println("2 -> Arpegio con séptima");
        System.out.println("3 -> Arpegio con séptima y novena");
        System.out.println("4 -> Arpegio con novena\n");

        Scanner txtArpegio = new Scanner(System.in);
        while (!txtArpegio.hasNextInt()) {
            txtArpegio.nextLine();
        }

        int idArpegio = txtArpegio.nextInt();

        IArpeggio arpeggio;
        String arpeggioName;

        switch (idArpegio) {
            case 1:
                System.out.println("Arpegio triada\n");
                arpeggio = new TriadArpeggio(scale);
                break;
            case 2:
                System.out.println("Arpegio 7\n");
                arpeggio = new SeventhArpeggio(scale);
                break;
            case 3:
                System.out.println("Arpegio 9\n");
                arpeggio = new NinthArpeggio(scale);
                break;
            case 4:
                System.out.println("Arpegio add9\n");
                arpeggio = new addNinthArpeggio(scale);
                break;
            default:
                System.out.println("No se ha seleccionado una opción válida, "
                        + "se seleccionara la opción por defecto (triada)\n");
                arpeggio = new TriadArpeggio(scale);
                break;
        }
        
        System.out.println("El " + arpeggio.getName() + " " + 
                arpeggio.getDescription() 
                + "Construido esobre la " + scale.getName() 
                + " en una frecuencia fundamental de " + fundamentalHz 
                + "Hz se escucha así:\n");
        instrument.play(arpeggio);
    }
}
