package pkmuotoilija.domain.kirjoittajat;

import pkmuotoilija.domain.KayttajanSyotteet;
import java.io.File;
import java.io.FileWriter;

import pkmuotoilija.domain.*;
import pkmuotoilija.domain.rivit.*;

public class PKkirjoittaja {
    
    private RivitettyPK rivit;
    private KayttajanSyotteet syotteet;
    private File loppuPK;
    
    public PKkirjoittaja(RivitettyPK rivit, KayttajanSyotteet syotteet, File loppuPK) {
        
        this.rivit = rivit;
        this.syotteet = syotteet;
        this.loppuPK = loppuPK;
        
    }
    
    public void formatoiPK() throws Exception {
        
        FileWriter kirjoittaja = new FileWriter(loppuPK);
        
        for(int i = 0; i < this.rivit.getPituus(); i++) {
           // this.rivit.formatoiRivi(i);
            kirjoittaja.write(this.rivit.getRivi(i).getSisalto());
        }
        
    }
    
}
