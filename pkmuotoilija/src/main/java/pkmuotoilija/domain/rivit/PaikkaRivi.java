package pkmuotoilija.domain.rivit;

import pkmuotoilija.domain.*;

public class PaikkaRivi extends Rivi {
    
    public PaikkaRivi(String rivinSisalto) {
        super(rivinSisalto);
    }
    
    @Override
    public void formatoiRivi(PKtiedot tiedot) {
        this.sisalto = "Paikka: " + this.sisalto.substring(this.sisalto.indexOf(" ")).trim() + rivita(tiedot.getSailytaOmaRivitys());
        hajotaRivi(true, tiedot.getLeveys(), 8);
    }
    
}
