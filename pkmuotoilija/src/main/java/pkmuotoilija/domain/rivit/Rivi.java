package pkmuotoilija.domain.rivit;

public class Rivi {

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

    
    public void formatoiRivi(int leveys) {

    }

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
            System.out.println(this.sisalto);
            vikaValiEnnenKatkoa = katkottuRivi.lastIndexOf(" ", leveys - sis);

            if (katkottuRivi.length() <= leveys) {

                this.sisalto = this.sisalto + valia(sis) + katkottuRivi;
                break;
            }
            this.sisalto = this.sisalto + valia(sis) + katkottuRivi.substring(0, vikaValiEnnenKatkoa) + "\n";
            katkottuRivi = katkottuRivi.substring(vikaValiEnnenKatkoa + 1);
            System.out.println("TESTI");
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
