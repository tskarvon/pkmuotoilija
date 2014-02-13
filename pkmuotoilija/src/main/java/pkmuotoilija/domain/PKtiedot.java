package pkmuotoilija.domain;

import java.util.ArrayList;
import java.io.File;

/**
 * 
 * Luokan tarkoituksena on säilöä pöytäkirjan muotoiluun tarvittavia tietoja.
 * Koostuu lähinnä gettereistä ja settereistä. Välitetään toistuvasti eteenpäin ohjelmalogiikassa.
 * 
 * @author tskarvon
 */

public class PKtiedot {
    
    /**
     * Tiedosto, jossa alkuperäinen pöytäkirja on ja tiedosto johon muotoiltu
     * pöytäkirja halutaan kirjoitettavan.
     */
    
    private File lahdetiedosto;
    private File kohdetiedosto;
    
    /**
     * Käyttäjän antamia totuusarvoja, jotka vaikuttavat muotoiluun.
     */
    
    private boolean sailytaOmaRivitys;
    private boolean lisaaHyvaksyttyKokouksessa;
    
    /**
     * Määrittävät, kuinka paljon kohtien ja alakohtien alla olevaa tekstiä
     * tulee sisentää. PKlukijalta tulevia.
     */
    private int ylakohdanSisennys;
    private int alakohdanSisennys;
    
    /**
     * Pöytäkirjan viimeisen liitteen numero. PKlukijalta tuleva.
     */
    private int suurinLiite;
    
    /**
     * Haluttu tekstinleveys. Käyttäjä syöttää.
     */
    private int leveys;
    
    /**
     * Tietoa tiettyjen rivityyppien esintymisestä oikean sisennyksen aikaansaamiseksi.
     * Tulevat PKlukijalta.
     */
    private boolean onkoPaikka;
    private boolean onkoAlalasna;
    
    /**
     * Luettelo kokousviroista pöytäkirjan lopussa olevia allekirjoituskohtia varten.
     * Saadaan OsallistujaRivi-luokan ilmentymiltä formatoinnin yhteydessä.
     */
    
    private ArrayList<String> virat;
    
    public PKtiedot() {
        
        this.lisaaHyvaksyttyKokouksessa = true;
        this.sailytaOmaRivitys = false;
        this.ylakohdanSisennys = 0;
        this.alakohdanSisennys = 0;
        this.suurinLiite = 0;
        this.leveys = 80;
        this.onkoPaikka = false;
        this.onkoAlalasna = false;
        this.virat = new ArrayList<String>();
        this.virat.add("");
        this.virat.add("");
        
    }
    
    public boolean getLisaaHyvaksyttyKokouksessa() {
        return this.lisaaHyvaksyttyKokouksessa;
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
    
    public File getLahdetiedosto() {
        return this.lahdetiedosto;
    }
    
    public File getKohdetiedosto() {
        return this.kohdetiedosto;
    }
    
    public boolean getSailytaOmaRivitys() {
        return this.sailytaOmaRivitys;
    }
    
    public void setLisaaHyvaksyttyKokouksessa(boolean lisaaHyvaksyttyKokouksessa) {
        this.lisaaHyvaksyttyKokouksessa = lisaaHyvaksyttyKokouksessa;
    }
    
    /**
     * Virat-taulukon ensimmäisessä paikassa säilytetään tietoa kokouksen
     * puheenjohtajasta.
     * 
     * @param pj kokouksen puheenjohtaja
     */
    
    public void setPJ(String pj) {
        this.virat.set(0, pj);
    }
    
    /**
     * Virat-taulukon toisessa paikassa säilytetään tietoa kokouksen sihteeristä.
     * 
     * @param sihteeri kokouksen sihteeri
     */
    
    public void setSihteeri(String sihteeri) {
        this.virat.set(1, sihteeri);
    }
    
    /**
     * Virat-taulukon lopuissa paikoissa säilytetään tietoa kokouksen pöytäkirjantarkastajista.
     * 
     * @param tarkastaja kokouksen pöytäkirjantarkastaja
     */
    
    public void lisaaTarkastaja(String tarkastaja) {
        this.virat.add(tarkastaja);
    }
    
    public String getPJ() {
        return this.virat.get(0);
    }
    
    public String getSihteeri() {
        return this.virat.get(1);
    }
    
    public String getTarkastaja(int i) {
        return this.virat.get(i);
    }
    
    public ArrayList<String> getVirat() {
        return this.virat;
    }
    
    public void setSailytaOmaRivitys(boolean sailytetaanko) {
        this.sailytaOmaRivitys = sailytetaanko;
    }
    
    public void setLahdetiedosto(File lahdetiedosto) {
        this.lahdetiedosto = lahdetiedosto;
    }
    
    public void setKohdetiedosto(File kohdetiedosto) {
        this.kohdetiedosto = kohdetiedosto;
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
