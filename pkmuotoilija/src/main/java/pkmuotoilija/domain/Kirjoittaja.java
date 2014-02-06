package pkmuotoilija.domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import pkmuotoilija.domain.rivit.*;

public class Kirjoittaja {
    
    /**
     * Kirjoittaa muotoillun pöytäkirjan haluttuun tiedostoon.
     * 
     * @param kohdetiedosto tiedosto, johon kirjoitetaan
     * 
     * @param rivitetty tämän sisältämät rivit kirjoitetaan
     */
    
    private final File kohdetiedosto;
    private final RivitettyPK rivitetty;
    
    public Kirjoittaja(File kohdetiedosto, RivitettyPK rivitetty) {
        this.kohdetiedosto = kohdetiedosto;
        this.rivitetty = rivitetty;
    }
    
    /**
     * Kirjoittaa muotoillun pöytäkirjan tiedostoon. Ensin kutsuu rivitettyPK:n
     * metodia formatoiRivit muotoilemaan rivit ja tämän jälkeen kirjoittaa ne
     * tiedostoon.
     * 
     * @throws IOException 
     */
    
    public void kirjoitaValmisPK() throws IOException {
        
        FileWriter kirjoittaja = new FileWriter(this.kohdetiedosto);        
        
        this.rivitetty.formatoiRivit();
        
        for(int i = 0; i < this.rivitetty.getPituus(); i++) {
            kirjoittaja.write(this.rivitetty.getRivi(i).getSisalto());
        }
        
        kirjoittaja.close();
        
    }
    
    
    
}
