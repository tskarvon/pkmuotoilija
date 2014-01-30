package pkmuotoilija.domain.rivit;

import pkmuotoilija.domain.*;

public class AikaRivi extends Rivi {

    public AikaRivi(String rivinSisalto) {
        super(rivinSisalto);
    }

    @Override
    public void formatoiRivi(int leveys) {
        this.sisalto = "Aika:   " + this.sisalto.substring(this.sisalto.indexOf(" ")).trim();
        hajotaRivi(true, leveys, 8);
    }

}
