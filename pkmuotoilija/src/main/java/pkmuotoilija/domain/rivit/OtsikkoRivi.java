package pkmuotoilija.domain.rivit;

import pkmuotoilija.domain.*;

public class OtsikkoRivi extends Rivi {

    public OtsikkoRivi(String rivinSisalto) {
        super(rivinSisalto);
    }

    @Override
    public void formatoiRivi(PKtiedot tiedot) {
        
        this.sisalto = this.sisalto.trim();
        
        if (this.sisalto.length() > tiedot.getLeveys() - 15) {
            int katkaisupaikka = this.sisalto.lastIndexOf(" ", tiedot.getLeveys() - 15);
            String ekaRivi = this.sisalto.substring(0, katkaisupaikka) + "  PÖYTÄKIRJA";
            this.sisalto = this.sisalto.substring(katkaisupaikka + 1);
            hajotaRivi(false, katkaisupaikka, 0);
            this.sisalto = ekaRivi + "\n" + this.sisalto + rivita(tiedot.getSailytaOmaRivitys());
        } else {
            this.sisalto = this.sisalto + valia(tiedot.getLeveys() - this.sisalto.length() - 13) + "PÖYTÄKIRJA" + rivita(tiedot.getSailytaOmaRivitys());
        }

    }

}
