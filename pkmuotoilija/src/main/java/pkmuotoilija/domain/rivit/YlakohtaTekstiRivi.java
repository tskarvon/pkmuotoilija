package pkmuotoilija.domain.rivit;

import pkmuotoilija.domain.*;

public class YlakohtaTekstiRivi extends Rivi {
    
    public YlakohtaTekstiRivi(String rivinSisalto) {
        super(rivinSisalto);
    }
    
    @Override
    public void formatoiRivi(PKtiedot tiedot) {
        this.sisalto = this.sisalto + rivita(tiedot.getSailytaOmaRivitys());
        hajotaRivi(false, tiedot.getLeveys(), 2 + tiedot.getYlakohdanSisennys());
        
    }
    
    
}
