package pkmuotoilija.domain.rivit;

import pkmuotoilija.domain.*;

public class TyhjaRivi extends Rivi {

    public TyhjaRivi(String rivinSisalto) {
        super(rivinSisalto);
    }

    @Override
    public void formatoiRivi(PKtiedot tiedot) {
        this.sisalto = rivita(tiedot.getSailytaOmaRivitys());

    }
    
    @Override
    protected String rivita(boolean sailytaOmaRivitys) {
        return "";
    }

}
