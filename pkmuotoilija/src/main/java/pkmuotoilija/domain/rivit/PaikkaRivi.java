package pkmuotoilija.domain.rivit;

import pkmuotoilija.domain.*;

public class PaikkaRivi extends Rivi {
    
    public PaikkaRivi(String rivinSisalto) {
        super(rivinSisalto);
    }
    
    @Override
    public void formatoiRivi(int leveys) {
        this.sisalto = "Paikka: " + this.sisalto.substring(this.sisalto.indexOf(" ")).trim();
        hajotaRivi(true, leveys, 8);
    }
    
}
