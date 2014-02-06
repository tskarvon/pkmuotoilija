package pkmuotoilija.domain.rivit;

import pkmuotoilija.domain.*;

public class LasnaRivi extends Rivi {
    
    public LasnaRivi(String rivinSisalto) {
        super(rivinSisalto);
    }
    
    @Override
    public void formatoiRivi(PKtiedot tiedot) {
        this.sisalto = "Läsnä:";
    }
    
}
