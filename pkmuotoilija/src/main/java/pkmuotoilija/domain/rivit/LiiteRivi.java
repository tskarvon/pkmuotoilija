package pkmuotoilija.domain.rivit;

import pkmuotoilija.domain.*;

public class LiiteRivi extends Rivi {
    
    private final int liitenumero;
    
    public LiiteRivi(String rivinSisalto, int liitenumero) {
        super(rivinSisalto);
        this.liitenumero = liitenumero;
    }
    
    @Override
    public void formatoiRivi(PKtiedot tiedot) {
        
        this.sisalto = "Liite " + this.liitenumero + ":" + valia(tiedot.getSuurinLiite() / 10 + 1 - this.liitenumero / 10) 
                + liitteenNimi(this.sisalto) + rivita(tiedot.getSailytaOmaRivitys());
        hajotaRivi(true, tiedot.getLeveys(), 8 + tiedot.getSuurinLiite() / 10 + 1);
        
    }
    
    @Override
    protected String rivita(boolean sailytaOmaRivitys) {
        return "";
    }
    
    private String liitteenNimi(String liite) {
        if(liite.trim().toLowerCase().contains("liite")) {
            return liite.substring(liite.indexOf(" ", liite.indexOf(":")) + 1);
        } else {
            return liite;
        }
    }
    
}
