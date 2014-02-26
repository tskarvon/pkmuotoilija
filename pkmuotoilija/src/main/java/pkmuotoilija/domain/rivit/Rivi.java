package pkmuotoilija.domain.rivit;

import pkmuotoilija.domain.*;

/**
 * Kaikki muotoiltavassa pöytäkirjassa esiintyvät rivit tallennetaan
 * tämän luokan tai sen aliluokkien ilmentyminä.
 * 
 * @author tskarvon
 */

public class Rivi {

    protected String sisalto;
    
    /**
     * Tallennettavan rivin tekstisisältö.
     * 
     * @param rivinSisalto 
     */

    public Rivi(String rivinSisalto) {
        this.sisalto = rivinSisalto;
    }

    public String getSisalto() {

        return this.sisalto;

    }    
    
    /**
     * Muotoilee rivin halutunlaiseksi. Aliluokilla on tämän ylittävät omat 
     * metodinsa asialle.
     * 
     * @param tiedot tiedot, joihin perustuen muotoilu suoritetaan
     */
    public void formatoiRivi(PKtiedot tiedot) {
        this.sisalto = this.sisalto.trim();
        hajotaRivi(false, tiedot.getLeveys(), 0);
    }

    /**
     * Hajottaa liian pitkän rivin uselle riville.
     * 
     * @param eiEkanRivinSisennysta halutaanko ensimmäinen rivi sisentää
     * @param leveys hajotettavan tekstin leveys
     * @param sis tekstin sisennys
     */
    public void hajotaRivi(boolean eiEkanRivinSisennysta, int leveys, int sis) {

        if (this.sisalto.length() + sis <= leveys) {
            if(eiEkanRivinSisennysta) {
                return;
            } else {
                this.sisalto = valia(sis) + this.sisalto;
                return;
            }
        }

        String katkottuRivi = this.sisalto;
        this.sisalto = "";
        int vikaValiEnnenKatkoa = 0;

        while (true) {
            
            if (eiEkanRivinSisennysta) {
                vikaValiEnnenKatkoa = katkottuRivi.lastIndexOf(" ", leveys);
                this.sisalto = this.sisalto + katkottuRivi.substring(0, vikaValiEnnenKatkoa) + "\n";
                katkottuRivi = katkottuRivi.substring(vikaValiEnnenKatkoa + 1);
                eiEkanRivinSisennysta = false;
                continue;
            }
            vikaValiEnnenKatkoa = katkottuRivi.lastIndexOf(" ", leveys - sis);

            if (katkottuRivi.length() <= leveys) {

                this.sisalto = this.sisalto + valia(sis) + katkottuRivi;
                break;
            }
            this.sisalto = this.sisalto + valia(sis) + katkottuRivi.substring(0, vikaValiEnnenKatkoa) + "\n";
            katkottuRivi = katkottuRivi.substring(vikaValiEnnenKatkoa + 1);
        }

    }
    
    /**
     * Lisää rivin loppuun rivinvaihdon riippuen käyttäjän valinnoista.
     * 
     * @param sailytaOmaRivitys säilytetäänkö käyttäjän oma rivitys vai yliajetaanko se
     * @return 
     */
    
    protected String rivita(boolean sailytaOmaRivitys) {
        if(!sailytaOmaRivitys) {
            return "\n";
        } else {
            return "";
        }
    }
    
    /**
     * Lisätään haluttu määrä välilyöntejä.
     * 
     * @param montako lisättävien välilyöntien määrä
     * @return 
     */

    protected static String valia(int montako) {
        String vali = "";
        for (int i = 0; i < montako; i++) {
            vali = vali + " ";
        }
        return vali;

    }

}
