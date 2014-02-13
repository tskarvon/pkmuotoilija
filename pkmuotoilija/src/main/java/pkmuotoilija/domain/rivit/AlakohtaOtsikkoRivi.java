package pkmuotoilija.domain.rivit;

import pkmuotoilija.domain.*;

public class AlakohtaOtsikkoRivi extends Rivi {

    private final int alakohdanNumero;
    private final int ylakohdanNumero;

    public AlakohtaOtsikkoRivi(String rivinSisalto, int alakohdanNumero, int ylakohdanNumero) {
        super(rivinSisalto);
        this.alakohdanNumero = alakohdanNumero;
        this.ylakohdanNumero = ylakohdanNumero;
    }

    @Override
    public void formatoiRivi(PKtiedot tiedot) {

        this.sisalto = valia(2 + tiedot.getYlakohdanSisennys()) + this.ylakohdanNumero + "." + this.alakohdanNumero + "."
                + valia(tiedot.getAlakohdanSisennys() - this.alakohdanNumero / 10) + 
                this.sisalto.trim().substring(this.sisalto.trim().indexOf(" ") + 1) + rivita(tiedot.getSailytaOmaRivitys());;
        hajotaRivi(true, tiedot.getLeveys(), 6 + tiedot.getYlakohdanSisennys() + tiedot.getAlakohdanSisennys());

    }

}
