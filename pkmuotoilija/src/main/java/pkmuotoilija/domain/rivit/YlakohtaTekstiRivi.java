package pkmuotoilija.domain.rivit;

import pkmuotoilija.domain.*;

public class YlakohtaTekstiRivi extends Rivi {
    
    public YlakohtaTekstiRivi(String rivinSisalto) {
        super(rivinSisalto);
    }
    
    @Override
    public void formatoiRivi(PKtiedot tiedot) {
        
        hajotaRivi(false, tiedot.getLeveys(), 2 + tiedot.getYlakohdanSisennys());
        
    }
    
    
}
