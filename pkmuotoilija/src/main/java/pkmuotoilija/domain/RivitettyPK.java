package pkmuotoilija.domain;

import pkmuotoilija.domain.PKtiedot;
import java.util.ArrayList;

import pkmuotoilija.domain.rivit.*;

/**
 * 
 * Luokka säilyttää ArrayListissa rivejä, joiden tyypit PKlukija on määrittänyt.
 * Mukana kulkee myös muotoilussa tarvittava PKtiedot.
 * 
 * @author tskarvon
 */

public class RivitettyPK {
    
    /**
     * Lista pöytäkirjan riveistä.
     */    
    private final ArrayList<Rivi> rivit;
    
    /**
     * Muotoiluun tarvittavat tiedot.
     */   
    protected PKtiedot tiedot;


    public RivitettyPK() {
        this.rivit = new ArrayList<Rivi>();
        this.tiedot = new PKtiedot();
    }
    
    /**
     * Lisää uuden rivin.
     * 
     * @param rivi lisättävä rivi
     */

    public void lisaaRivi(Rivi rivi) {
        this.rivit.add(rivi);
    }
    
    /**
     * Palauttaa rivin.
     * 
     * @param i monesko rivi palautetaan
     * @return i:s rivi
     */

    public Rivi getRivi(int i) {
        return this.rivit.get(i);
    }
    
    /**
     * Palauttaa rivien määrän.
     * 
     * @return rivien määrä
     */

    public int getPituus() {
        return this.rivit.size();
    }

    public void setTiedot(PKtiedot tiedot) {
        this.tiedot = tiedot;
    }
    
    /**
     * Käy läpi kaikki sisältämänsä rivit ja kutsuu niiden muotoilumetodia
     * formatoiRivi.
     */
    
    public void formatoiRivit() {
        for(Rivi rivi : this.rivit) {
            rivi.formatoiRivi(this.tiedot);
        }
    }

}
