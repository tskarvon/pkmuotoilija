package pkmuotoilija.domain.rivit;

import pkmuotoilija.domain.*;

public class Rivi {
    
    /**
     * Alkuperäisen pöytäkirjan jokainen rivi tallennetaan tämän luokan tai sen
     * aliluokkien ilmentymänä.
     * 
     * @param sisalto rivin sisältö
     */

    protected String sisalto;

    public Rivi(String rivinSisalto) {
        this.sisalto = rivinSisalto;
    }

    public String getSisalto() {

        return this.sisalto;

    }    

    @Override
    public String toString() {

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

    protected static String valia(int montako) {
        String vali = "";
        for (int i = 0; i < montako; i++) {
            vali = vali + " ";
        }
        return vali;

    }

}
