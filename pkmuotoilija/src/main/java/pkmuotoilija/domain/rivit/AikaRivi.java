package pkmuotoilija.domain.rivit;

import pkmuotoilija.domain.*;

public class AikaRivi extends Rivi {

    public AikaRivi(String rivinSisalto) {
        super(rivinSisalto);
    }

    @Override
    public void formatoiRivi(PKtiedot tiedot) {
        this.sisalto = "Aika: " + valiaJosPaikkaOn(tiedot.getOnkoPaikka()) + this.sisalto.substring(this.sisalto.indexOf(" ")).trim();
        hajotaRivi(true, tiedot.getLeveys(), 8);
    }
    
    private String valiaJosPaikkaOn(boolean onkoPaikka) {
        if(onkoPaikka) {
            return "  ";
        } else {
            return "";
        }
        
    }

}
