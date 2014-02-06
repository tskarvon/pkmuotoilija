package pkmuotoilija.domain;

import pkmuotoilija.domain.PKtiedot;
import java.util.ArrayList;

import pkmuotoilija.domain.rivit.*;

public class RivitettyPK {
    
    /**
     * Säilöö rivejä, joiden tyypit on selvitetty ja niiden tarvitsemia tietoja.
     * 
     * @param rivit lista, jossa rivejä säilytetään
     * 
     * @param tiedot tiedot, joita tarvitaan halutun muotoilun aikaansaamiseksi
     */

    private final ArrayList<Rivi> rivit;
    protected PKtiedot tiedot;

    public RivitettyPK() {
        this.rivit = new ArrayList<Rivi>();
        this.tiedot = new PKtiedot();
    }

    public void lisaaRivi(Rivi rivi) {
        this.rivit.add(rivi);
    }

    public Rivi getRivi(int i) {
        return this.rivit.get(i);
    }

    public int getPituus() {
        return this.rivit.size();
    }

    public void setTiedot(PKtiedot tiedot) {
        this.tiedot = tiedot;
    }
    
    /**
     * Asettaa ilmentymään liittyvän PK-tiedot-olion leveyden
     * 
     * @param leveys uusi leveys
     */
    public void setRivitetynLeveys(int leveys) {
        this.tiedot.setLeveys(leveys);
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
