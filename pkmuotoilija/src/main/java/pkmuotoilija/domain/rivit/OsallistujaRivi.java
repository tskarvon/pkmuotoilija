package pkmuotoilija.domain.rivit;

import pkmuotoilija.domain.*;

public class OsallistujaRivi extends Rivi {
    
    public OsallistujaRivi(String rivinSisalto) {
        super(rivinSisalto);
    }
    
    @Override
    public void formatoiRivi(PKtiedot tiedot) {
        this.sisalto = valiaSenMukaanOnkoAlalasna(tiedot.getOnkoAlalasna()) + this.sisalto.trim();
        hajotaRivi(true, tiedot.getLeveys(), this.sisalto.indexOf("(") + 1);
    }
    
    private String valiaSenMukaanOnkoAlalasna(boolean onkoAlalasna) {
        
        if(onkoAlalasna) {
            return "    ";
        } else {
            return "  ";
        }
        
        
    }
    
}
