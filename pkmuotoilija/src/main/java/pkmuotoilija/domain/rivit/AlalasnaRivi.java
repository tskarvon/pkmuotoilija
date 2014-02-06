package pkmuotoilija.domain.rivit;

import pkmuotoilija.domain.*;

public class AlalasnaRivi extends Rivi {
    
    public AlalasnaRivi(String rivinSisalto) {
        super(rivinSisalto);
    }
    
    @Override
    public void formatoiRivi(PKtiedot tiedot) {
        this.sisalto = this.sisalto.trim().substring(0,1).toUpperCase() + this.sisalto.substring(1).trim();
        if(!this.sisalto.substring(this.sisalto.length() - 1).equals(":")) {
            this.sisalto = this.sisalto + ":";
        }
        hajotaRivi(false, tiedot.getLeveys(), 2);
    }
    
}
