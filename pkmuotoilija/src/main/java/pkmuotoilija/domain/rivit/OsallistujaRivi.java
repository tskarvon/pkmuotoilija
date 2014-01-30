package pkmuotoilija.domain.rivit;

import pkmuotoilija.domain.*;

public class OsallistujaRivi extends Rivi {
    
    public OsallistujaRivi(String rivinSisalto) {
        super(rivinSisalto);
    }
    
    @Override
    public void formatoiRivi(int leveys) {
        this.sisalto = this.sisalto.trim();
        hajotaRivi(true, leveys, this.sisalto.indexOf("("));
    }
    
}
