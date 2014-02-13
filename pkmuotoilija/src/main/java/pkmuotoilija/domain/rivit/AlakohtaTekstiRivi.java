package pkmuotoilija.domain.rivit;

import pkmuotoilija.domain.*;

public class AlakohtaTekstiRivi extends Rivi {
    
    public AlakohtaTekstiRivi(String rivinSisalto) {
        super(rivinSisalto);
    }
    
    @Override
    public void formatoiRivi(PKtiedot tiedot) {
        
        this.sisalto = this.sisalto + rivita(tiedot.getSailytaOmaRivitys());
        hajotaRivi(false, tiedot.getLeveys(), 6 + tiedot.getYlakohdanSisennys() + tiedot.getAlakohdanSisennys());
        
    }
}
