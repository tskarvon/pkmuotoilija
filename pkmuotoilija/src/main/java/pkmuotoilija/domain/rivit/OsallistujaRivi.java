package pkmuotoilija.domain.rivit;

import pkmuotoilija.domain.*;

public class OsallistujaRivi extends Rivi {

    public OsallistujaRivi(String rivinSisalto) {
        super(rivinSisalto);
    }

    @Override
    public void formatoiRivi(PKtiedot tiedot) {

        tunnistaVirka(tiedot);

        this.sisalto = valiaSenMukaanOnkoAlalasna(tiedot.getOnkoAlalasna()) + this.sisalto.trim() + rivita(tiedot.getSailytaOmaRivitys());;
        hajotaRivi(true, tiedot.getLeveys(), this.sisalto.indexOf("(") + 1);
    }

    @Override
    protected String rivita(boolean sailytaOmaRivitys) {
        return "";
    }
    
    /**
     * Tunnistetaan kokousvirkoja (puheenjohtaja, sihteeri, pöytäkirjantarkastajat).
     * Nämä oletetaan kerrottavan läsnäolijan nimen perässä suluissa.
     * 
     * @param tiedot 
     */

    private void tunnistaVirka(PKtiedot tiedot) {
        if (tunnistaPJ()) {
            tiedot.setPJ(this.sisalto.substring(0, this.sisalto.indexOf("(") - 1).trim());
        } else if (tunnistaSihteeri()) {
            tiedot.setSihteeri(this.sisalto.substring(0, this.sisalto.indexOf("(") - 1).trim());
        } else if (tunnistaTarkastaja()) {
            tiedot.lisaaTarkastaja(this.sisalto.substring(0, this.sisalto.indexOf("(") - 1).trim());
        }
    }
    
    /**
     * Tunnistetaan kokouksen puheenjohtaja.
     * 
     * @return kokouksen puheenjohtaja
     */

    private boolean tunnistaPJ() {
        if (this.sisalto.toLowerCase().contains("puheenjohtaja")) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Tunnistetaan kokouksen sihteeri.
     * @return kokouksen sihteeri
     */

    private boolean tunnistaSihteeri() {
        if (this.sisalto.toLowerCase().contains("sihteeri")) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Tunnistetaan kokouksen pöytäkirjantarkastajat
     * @return kokouksen pöytäkirjantarkastaja
     */

    private boolean tunnistaTarkastaja() {
        if (this.sisalto.toLowerCase().contains("pöytäkirjantarkastaja")) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Lisätään läsnäolijoiden eteen sisennystä riippuen siitä, onko läsnä-
     * olijaluettelossa alaotsikoita
     * @param onkoAlalasna onko läsnäolijaluettelossa alaotsikoita
     * @return 
     */

    private String valiaSenMukaanOnkoAlalasna(boolean onkoAlalasna) {

        if (onkoAlalasna) {
            return "    ";
        } else {
            return "  ";
        }

    }

}
