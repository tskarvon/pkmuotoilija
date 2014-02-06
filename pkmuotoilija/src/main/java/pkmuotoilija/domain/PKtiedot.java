package pkmuotoilija.domain;

public class PKtiedot {
    
    /**
     * Pitää sisällään pöytäkirjan muotoilussa tarvittavia tietoja.
     */
    
    /**
     * Määrittävät, kuinka paljon kohtien ja alakohtien alla olevaa tekstiä
     * tulee sisentää.
     */
    private int ylakohdanSisennys;
    private int alakohdanSisennys;
    
    /**
     * Pöytäkirjan viimeisen liitteen numero.
     */
    private int suurinLiite;
    
    /**
     * Haluttu tekstinleveys.
     */
    private int leveys;
    
    /**
     * Tietoa tiettyjen rivityyppien esintymisestä oikean sisennyksen aikaansaamiseksi.
     */
    private boolean onkoPaikka;
    private boolean onkoAlalasna;
    
    public PKtiedot() {
        
        this.ylakohdanSisennys = 0;
        this.alakohdanSisennys = 0;
        this.suurinLiite = 0;
        this.leveys = 80;
        this.onkoPaikka = false;
        this.onkoAlalasna = false;
        
    }
    
    public int getLeveys() {
        return this.leveys;
    }
    
    public boolean getOnkoPaikka() {
        return this.onkoPaikka;
    }
    
    public boolean getOnkoAlalasna() {
        return this.onkoAlalasna;
    }
    
    public int getYlakohdanSisennys() {
        return this.ylakohdanSisennys;
    }
    
    public int getAlakohdanSisennys() {
        return this.alakohdanSisennys;
    }
    
    public int getSuurinLiite() {
        return this.suurinLiite;
    }
    
    public void setYlakohdanSisennys(int uusiYlSis) {
        
        this.ylakohdanSisennys = uusiYlSis;
        
    }
    
    public void setAlakohdanSisennys(int uusiAlSis) {
        
        this.alakohdanSisennys = uusiAlSis;
        
    }
    
    public void setLeveys(int leveys) {
        this.leveys = leveys;
    }
    
    public void setSuurinLiite(int liitenumero) {
        this.suurinLiite = liitenumero;
    }
    
    public void setOnkoPaikka(boolean onkoPaikka) {
        this.onkoPaikka = onkoPaikka;
    }
    
    public void setOnkoAlalasna(boolean onkoAlalasna) {
        this.onkoAlalasna = onkoAlalasna;
    }
    
    
}
