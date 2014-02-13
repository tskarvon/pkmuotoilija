package pkmuotoilija.domain.rivit;

import pkmuotoilija.domain.*;

public class YlakohtaOtsikkoRivi extends Rivi {
    
    private final int ylakohdanNumero;
    
    public YlakohtaOtsikkoRivi(String rivinSisalto, int ylakohdanNumero) {
        super(rivinSisalto);
        this.ylakohdanNumero = ylakohdanNumero;
    }
    
    @Override
    public void formatoiRivi(PKtiedot tiedot) {
        
        this.sisalto = this.ylakohdanNumero + "." + valia(tiedot.getYlakohdanSisennys() - this.ylakohdanNumero / 10) + 
                this.sisalto.trim().substring(this.sisalto.trim().indexOf(" ") + 1) + rivita(tiedot.getSailytaOmaRivitys());;
        hajotaRivi(true, tiedot.getLeveys(), 2 + tiedot.getYlakohdanSisennys());
        
    }
        
}
