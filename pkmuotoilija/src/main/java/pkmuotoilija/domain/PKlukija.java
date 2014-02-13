package pkmuotoilija.domain;

import java.io.FileNotFoundException;
import java.util.Scanner;

import pkmuotoilija.domain.rivit.*;

/**
 * Luokan tehtävänä on määrittää annetun pöytäkirjan kunkin rivin tyyppi.
 * Tyyppi selviää siitä, millaiseksi Rivi-luokan aliluokaksi kukin rivi tallennetaan.
 * Luokka luo RivitettyPK-luokan ilmentymänä, johon nämä tallennetaan rivi kerrallaan.
 * 
 * Oikean aliluokan määrittämiseksi kullekin riville käytetään monimutkaista ehtolauseiden
 * rakennelmaa, joka perustuu viime kädessä tiettyjen avainsanojen ja pöytäkirjan kohtien
 * aloittavien numeroiden tunnistamiseen. Apuna tässä on mittava joukko totuusmuuttujia,
 * jotka kertovat, onko jonkintyyppisiä rivejä jo tallennettu.
 * 
 * 
 * @author tskarvon
 */

public class PKlukija {

    /**
     * Totuusarvoja, joilla pidetään, huoli, etteivät tietyt rivityypit tule
     * tallennetuiksi useaan kertaan.
     */
    
    private boolean otsikkoOllut;
    private boolean aikaOllut;
    private boolean paikkaOllut;
    private boolean lasnaOllut;
    private boolean alalasnaOllut;
    private boolean liitteetOllut;
    
    /**
     * Juoksevia laskureita pöytäkirjan kohtien ja liitteiden numeroinnista. 
     * Näiden avulla varmistetaan, ettei kohdiksi lueta vahingossa mitään ylimääräistä
     * kuten päivämäärillä alkavia rivejä.
     * 
     */

    private int ylakohta;
    private int alakohta;
    private int liitenumero;
    
    /**
     * Laskureita, joiden avulla selvitetään suurimmat kohdan ja alakohdan numerot.
     * Näitä tietoja tarvitaan halutun sisennyksen aikaansaamiseen.
     */
    
    private int suurinYlakohta;
    private int suurinAlakohta;
    private int viimeisinAlakohta;
    
    /**
     * Asetetaan kaikki totuusarvot epätosiksi ja laskurit nolliksi. Mitään
     * rivejä ei ole tullut vastaan.
     */

    public PKlukija() {

        this.otsikkoOllut = false;
        this.aikaOllut = false;
        this.paikkaOllut = false;
        this.lasnaOllut = false;
        this.liitteetOllut = false;

        this.ylakohta = 0;
        this.alakohta = 0;
        this.suurinYlakohta = 0;
        this.suurinAlakohta = 0;
        this.viimeisinAlakohta = 0;

    }
    
    /**
     * Metodi käy läpi tiedot-olion määrittämän tiedoston, joka sisältää alkuperäisen pöytäkirjan 
     * ja tunnistaa sen jokaisen rivin tyypin myöhemmin tapahtuvaa rivin tyypistä
     * riippuvaista muotoilua varten. Samalla muokataan tiedot-olion sisältöä.
     * 
     * @return RivitettyPK-luokan ilmentymä, johon alkuperäisen pöytäkirjan
     * rivit on tallennettu tyyppitietoineen.
     */
    
    public RivitettyPK tunnistaRivit(PKtiedot tiedot) throws FileNotFoundException {
        
        tunnistaViimeinenKohta(new Scanner(tiedot.getLahdetiedosto()));
        
        tiedot.setYlakohdanSisennys(this.suurinYlakohta / 10 + 1);
        tiedot.setAlakohdanSisennys(this.suurinAlakohta / 10 + 1);
        
        RivitettyPK rivitetty = new RivitettyPK();

        Scanner lukija = new Scanner(tiedot.getLahdetiedosto());
        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            rivitetty.lisaaRivi(maaritaLisattavaRivi(rivi));
        }
        
        tiedot.setOnkoPaikka(this.paikkaOllut);
        tiedot.setOnkoAlalasna(this.alalasnaOllut);
        tiedot.setSuurinLiite(this.liitenumero);
        
        rivitetty.setTiedot(tiedot);
        
        return rivitetty;

    }
    
    /**
     * Metodi etsii viimeisen yläkohdan ja suurimman alakohdan numerot.
     * Näitä tietoja tarvitaan sisennysten luomisessa ja sen varmistamisessa,
     * että liitetiedot syötetään oikealle paikalle.
     * 
     * @param esilukija 
     */
    
    
    private void tunnistaViimeinenKohta(Scanner esilukija) {

        while (esilukija.hasNextLine()) {
            String rivi = esilukija.nextLine().trim();
            if (tunnistaYlakohta(rivi, this.suurinYlakohta)) {
                this.suurinYlakohta++;
                continue;
            }
            if (tunnistaAlakohta(rivi, this.viimeisinAlakohta, this.suurinYlakohta)) {
                this.viimeisinAlakohta++;
                this.suurinAlakohta = Math.max(this.suurinAlakohta, this.viimeisinAlakohta);
            }
        }
    }
    
    /**
     * Metodi jakaa rivin tyypin määrittämisen kolmeen osaan.
     * 
     * @param rivi rivi, jonka tyyppiä ollaan määrittämässä
     * @return oikeantyyppinen rivi
     */

    private Rivi maaritaLisattavaRivi(String rivi) {

        if (rivi.trim().isEmpty()) {
            return new TyhjaRivi(rivi);
        } else if (this.ylakohta > 0 || tunnistaYlakohta(rivi.trim(), 0)) {
            return maaritaMaareTekstiosalle(rivi);
        } else {
            return maaritaMaareAlkutiedoille(rivi);
        }

    }
    
    /**
     * Määrittää alkutietoihin (siis ennen varsinaisten pöytäkirjan numeroitujen
     * kohtien alkamistA) kuuluvien rivien tyypit.
     * 
     * @param rivi alkutietoihin kuuluva rivi, jonka tyyppiä määritetään
     * @return oikeantyyppinen rivi
     */

    private Rivi maaritaMaareAlkutiedoille(String rivi) {

        String trimRivi = rivi.trim().toLowerCase();
        
        if (tunnistaOtsikko(trimRivi)) {
            this.otsikkoOllut = true;
            return new OtsikkoRivi(rivi);

        } else if (tunnistaAika(trimRivi)) {
            this.aikaOllut = true;
            return new AikaRivi(rivi);

        } else if (tunnistaPaikka(trimRivi)) {
            this.paikkaOllut = true;
            return new PaikkaRivi(rivi);

        } else if (tunnistaLasna(trimRivi)) {
            this.lasnaOllut = true;
            return new LasnaRivi(rivi);

        } else if (tunnistaAlalasna(trimRivi)) {
            this.alalasnaOllut = true;
            return new AlalasnaRivi(rivi);

        } else if (tunnistaOsallistuja(trimRivi)) {
            return new OsallistujaRivi(rivi);
        } else {
            return new Rivi(rivi);
        }

    }

    /**
     * Määrätään varsinaiseen tekstiosaan kuuluville riveille tyypit.
     * 
     * @param rivi rivi, jonka tyyppiä ollaan määrittämässä
     * @return oikeantyyppinen rivi
     */
    
    
    private Rivi maaritaMaareTekstiosalle(String rivi) {

        String trimRivi = rivi.trim().toLowerCase();

        if (tunnistaYlakohta(trimRivi, this.ylakohta)) {
            this.ylakohta++;
            this.alakohta = 0;
            return new YlakohtaOtsikkoRivi(rivi, this.ylakohta);

        } else if (tunnistaYlakohdanTeksti(trimRivi)) {
            return new YlakohtaTekstiRivi(rivi);

        } else if (tunnistaAlakohta(trimRivi, this.alakohta, this.ylakohta)) {
            this.alakohta++;
            return new AlakohtaOtsikkoRivi(rivi, this.alakohta, this.ylakohta);

        } else if (tunnistaAlakohdanTeksti(trimRivi)) {
            return new AlakohtaTekstiRivi(rivi);

        } else if (tunnistaLiiteOtsikko(trimRivi)) {
            this.liitteetOllut = true;
            return new LiiteOtsikkoRivi(rivi);

        } else if (tunnistaLiitteet()) {
            this.liitenumero++;
            return new LiiteRivi(rivi, this.liitenumero);
        } else {
            return new Rivi(rivi);
        }

    }
    
    /**
     * Pitää huolen, että otsikkorivejä otetaan mukaan vain yksi.
     * @param rivi
     * @return 
     */

    private boolean tunnistaOtsikko(String rivi) {
        if (!otsikkoOllut && onkoOtsikko(rivi)) {
            return true;
        }
        return false;
    }
    
    /**
     * Tunnistaa aikarivin ja pitää huolen, että kokouksen ajankohdan kertovia rivejä otetaan
     * mukaan vain yksi. 
     * @param rivi
     * @return 
     */

    private boolean tunnistaAika(String rivi) {
        if (!aikaOllut && rivi.startsWith("aika:")) {
            return true;
        }
        return false;
    }
    
    /**
     * Tunnistaa kokouksen paikan kertovan rivin ja pitää huolen, että 
     * niitä otetaan mukaan vain yksi.
     * @param rivi
     * @return 
     */

    private boolean tunnistaPaikka(String rivi) {
        if (!paikkaOllut && rivi.startsWith("paikka:")) {
            return true;
        }
        return false;
    }
    
    /**
     * Tunnistaa kokouksen läsnäolijoista ilmoittavan rivin ja pitää huolen,
     * että niitä otetaan mukaan vain yksi.
     * @param rivi
     * @return 
     */

    private boolean tunnistaLasna(String rivi) {
        if (!lasnaOllut && rivi.startsWith("läsnä")) {
            return true;
        }
        return false;

    }
    
    /**
     * Selvitetään läsnäolijoiden alaotsikot.
     * @param rivi
     * @return 
     */

    private boolean tunnistaAlalasna(String rivi) {
        if (this.lasnaOllut && onkoAlalasna(rivi)) {
            return true;
        }
        return false;
    }
    
    /**
     * Tunnistetaan rivit, jotka kertovat kokoukseen osallistujat. Osallistujiksi
     * luetaan kaikki läsnäolijoiden otsikon jälkeen tulevat rivit, joita ei tunnisteta 
     * läsnäolijoiden alaotsikoiksi. Virheiden torjumiseksi varmistutaan, että
     * rivit sisältävät välilyönnin, joka etu- ja sukunimen välissä kuuluisi olla.
     * 
     * @param rivi
     * @return 
     */

    private boolean tunnistaOsallistuja(String rivi) {
        if (this.lasnaOllut && !onkoAlalasna(rivi) && rivi.contains(" ")) {
            return true;
        }
        return false;
    }
    
    /**
     * Tunnistetaan yläkohtien otsikot. Otsikon numeron täytyy olla nykyisestä 
     * numerosta seuraava.
     * @param rivi
     * @param edellinenYlakohta
     * @return 
     */

    private boolean tunnistaYlakohta(String rivi, int edellinenYlakohta) {
        if (rivi.contains(" ") && onkoYlakohta(rivi) && onkoSeuraavaYlakohta(rivi, edellinenYlakohta)) {
            return true;
        }
        return false;
    }
    
    /**
     * Tunnistetaan yläkohdan alla oleva teksti. Varmistutaan, ettei olla
     * käsittelemässä alakohdan otsikkoriviä.
     * @param rivi
     * @return 
     */

    private boolean tunnistaYlakohdanTeksti(String rivi) {
        if (this.alakohta == 0 && !this.liitteetOllut && !tunnistaAlakohta(rivi, this.alakohta, this.ylakohta)
                && !tunnistaLiiteOtsikko(rivi)) {
            return true;
        }
        return false;
    }
    
    /**
     * Tunnistetaan alakohtien otsikkorivit. Otsikon numeron täytyy olla nykyisestä
     * numerosta seuraava.
     * @param rivi
     * @param edellinenAlakohta
     * @param edellinenYlakohta
     * @return 
     */
    
    private boolean tunnistaAlakohta(String rivi, int edellinenAlakohta, int edellinenYlakohta) {
        if (rivi.contains(" ") && onkoAlakohta(rivi) && onkoSeuraavaAlakohta(rivi, edellinenAlakohta, edellinenYlakohta)) {
            return true;
        }
        return false;
    }
    
    /**
     * Tunnistetaan teksti, joka on alakohdan alla. Varmistutaan, ettei kyseessä ole
     * yläkohdan otsikko eikä olla liiteluettelossa.
     * @param rivi
     * @return 
     */
    
    private boolean tunnistaAlakohdanTeksti(String rivi) {
        if (this.alakohta > 0 && !this.liitteetOllut) {
            return true;
        }
        return false;
    }
    
    /**
     * Tunnistetaan liiteluettelon otsikko. Varmistutaan, että kaikki
     * ylä- ja alakohdat on käyty jo läpi. Virheet ovat mahdollisia, jos liiteotsikko
     * esiintyy viimeisen ylä- tai alakohdan alla.
     * @param rivi
     * @return 
     */

    private boolean tunnistaLiiteOtsikko(String rivi) {
        if (!this.liitteetOllut && rivi.startsWith("liitteet")
                && this.ylakohta == this.suurinYlakohta && this.alakohta == this.alakohta) {
            return true;
        }
        return false;
    }
    
    /**
     * Liitteiksi luetaan kaikki liiteotsikko seuraavat rivit.
     * @return 
     */

    private boolean tunnistaLiitteet() {
        if (this.liitteetOllut) {
            return true;
        }
        return false;
    }
    
    /**
     * Tunnistetaan otsikkorivi avainsanojen perusteella.
     * @param rivi
     * @return 
     */

    private static boolean onkoOtsikko(String rivi) {
        if (rivi.contains("hallituksen kokous") || rivi.contains("yhdistyksen kokous")
                || rivi.contains("sääntömääräinen kokous") || rivi.contains("järjestäytymiskokous")) {
            return true;
        }
        return false;
    }
    
    /**
     * Tunnistetaan läsnäolijoiden alaotsikot avainsanojen perusteella.
     * @param rivi
     * @return 
     */

    private static boolean onkoAlalasna(String rivi) {
        if (rivi.contains("hallituksen jäsenet") || rivi.contains("hallituksen varajäsenet")
                || rivi.contains("muut") || rivi.contains("läsnäolo- ja puheoikeudella")
                || rivi.contains("läsnäolo-oikeudella")) {
            return true;
        }
        return false;
    }
    
    /**
     * Tunnistetaan yläkohta selvittämällä sen alun muoto ja hylätään mahdolliset
     * alakohdalta näyttävät rivit, joita saattaa esimerkiksi tulla vastaan riveinä, jotka
     * alkavat päivämäärällä.
     * @param rivi
     * @return 
     */

    private static boolean onkoYlakohta(String rivi) {
        if (rivi.substring(0, rivi.indexOf(" ")).matches("[0-9]{1,}.")
                && !rivi.substring(0, rivi.indexOf(" ") - 1).contains(".")) {
            return true;
        }
        return false;
    }
    
    /**
     * Vastaavasti selvitetään, onko rivi alakohdan otsikkorivi.
     * @param rivi
     * @return 
     */

    private static boolean onkoAlakohta(String rivi) {
        if (rivi.substring(0, rivi.indexOf(" ")).matches("[0-9]{1,}.[0-9]{1,}.")) {
            return true;
        }
        return false;
    }
    
    /**
     * Varmistutaan siitä, että yläkohdan otsikkoehdokkaan numero on nykyistä
     * numeroa seuraava.
     * @param rivi
     * @param edellinenYlakohta
     * @return 
     */

    private static boolean onkoSeuraavaYlakohta(String rivi, int edellinenYlakohta) {
        if (Integer.parseInt(rivi.substring(0, rivi.indexOf(".")))
                == edellinenYlakohta + 1) {
            return true;
        }
        return false;
    }
    
    /**
     * Varmistutaan, että alakohdan otsikkoehdokkaan numero on nykyistä numeroa
     * seuraava.
     * @param rivi
     * @param edellinenAlakohta
     * @param edellinenYlakohta
     * @return 
     */
    
    private static boolean onkoSeuraavaAlakohta(String rivi, int edellinenAlakohta, int edellinenYlakohta) {
        rivi = rivi.substring(0, rivi.indexOf(" ") - 1);
        if (Integer.parseInt(rivi.substring(rivi.indexOf(".") + 1)) == edellinenAlakohta + 1
                && onkoSeuraavaYlakohta(rivi.substring(0, rivi.indexOf(".") + 1), edellinenYlakohta - 1)) {
            return true;
        }
        return false;
    }

}
