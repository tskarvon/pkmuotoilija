package pkmuotoilija.domain.rivit;

import pkmuotoilija.domain.*;

public class LiiteOtsikkoRivi extends Rivi {

    public LiiteOtsikkoRivi(String rivinSisalto) {
        super(rivinSisalto);
    }

    @Override
    public void formatoiRivi(PKtiedot tiedot) {

        this.sisalto = "LIITTEET:" + rivita(tiedot.getSailytaOmaRivitys());;

    }

    @Override
    protected String rivita(boolean sailytaOmaRivitys) {
        return "";
    }

}
