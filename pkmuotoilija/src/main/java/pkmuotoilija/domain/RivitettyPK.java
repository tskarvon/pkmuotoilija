package pkmuotoilija.domain;

import java.util.ArrayList;

import pkmuotoilija.domain.rivit.*;

public class RivitettyPK {

    private final ArrayList<Rivi> rivit;
    private final int ylakohdanSisennys;
    private final int alakohdanSisennys;
    private boolean onkoPaikka;
    private boolean onkoAlalasna;

    public RivitettyPK(int suurinYlakohta, int suurinAlakohta) {
        this.rivit = new ArrayList<Rivi>();
        this.ylakohdanSisennys = laskeSisennys(suurinYlakohta);
        this.alakohdanSisennys = laskeSisennys(suurinAlakohta);
        onkoPaikka = false;
        onkoAlalasna = false;
    }   

    public void lisaaRivi(Rivi rivi) {
        this.rivit.add(rivi);
    }

    public Rivi getRivi(int i) {
        return this.rivit.get(i);
    }

    public int getPituus() {
        return this.rivit.size();
    }

    public void setTrueOnkoPaikka() {
        this.onkoPaikka = true;
    }

    public void setTrueOnkoAlalasna() {
        this.onkoAlalasna = true;
    }

 /*   public void formatoiRivi(int i) {

        if (getRivi(i).getClass() == AikaRivi.class) {
            getRivi(i).formatoiRivi(this.onkoPaikka);
        } else if (getRivi(i).getClass() == OsallistujaRivi.class) {
            getRivi(i).formatoiRivi(this.onkoAlalasna);
        } else {
            getRivi(i).formatoiRivi(false, );
        }

    } */

    private int laskeSisennys(int suurinKohtanumero) {

        return suurinKohtanumero / 10 + 1;

    }

}
