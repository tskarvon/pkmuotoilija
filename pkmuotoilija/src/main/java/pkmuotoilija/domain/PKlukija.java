package pkmuotoilija.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import pkmuotoilija.domain.rivit.*;

/*
 Tämän luokan tehtävänä on määrittää syötetyn pöytäkirjan kunkin rivin tyyppi. Luokka
 luo RivitettyPK-luokan ilmentymän, johon syötetty pöytäkirja tallennetaan rivi
 kerrallaan siten, että jokaisesta rivistä luodaan Rivi-luokan sopivan
 aliluokan ilmentymä.

 Oikean aliluokan määrittämiseksi kullekin riville käytetään monimutkaista ehto-
 lauseiden rakennelmaa, joka perustuu viime kädessä tiettyjen avainsanojen ja 
 pöytäkirjan kohtien aloittavien numeroiden tunnistamiseen.

 Varsinaisen muotoilun hoitavat PKkirjoitta-luokan aliluokat käyttäen Rivi-luokan
 aliluokista riippuvaisi hajotus- ja muotoilumetodeja.
 */
public class PKlukija {

    // Alkuperäinen pöytäkirja, jota halutaan muokata.
    private final File alkuPK;
    private RivitettyPK rivitetty;

    // Totuusarvoja, joiden avulla pidetään kirjaa siitä, onko tietyt kohdat
    // jo talletettu RivitettyPK-luokan ilmentymään.
    private boolean otsikkoOllut;
    private boolean aikaOllut;
    private boolean paikkaOllut;
    private boolean lasnaOllut;
    private boolean liitteetOllut;

    // Juoksevia laskureita pöytäkirjan kohtien numeroinnista. Näiden avulla
    // varmistetaan, ettei kohdiksi lueta vahingossa mitään ylimääräistä
    // kuten päivämäärällä alkavia rivejä.
    private int ylakohta;
    private int alakohta;

    // Käytetään laskemaan suurimman kohdan ja alakohdan numerot. PKkirjoittaja
    // tarvitsee tätä tietoa oikeanlaisen sisennyksen takaamiseksi. Lisäksi
    // varmistutaan, että liiteluettelo tulee oikeaan paikkaan.
    private int suurinYlakohta;
    private int suurinAlakohta;
    private int viimeisinAlakohta;

    public PKlukija(File alkuperainenPK) {

        this.alkuPK = alkuperainenPK;

        // Asetetaan kaikki epätosiksi, mikään näihin liittyvä asia ei ole tullut
        // vielä vastaan.
        this.otsikkoOllut = false;
        this.aikaOllut = false;
        this.paikkaOllut = false;
        this.lasnaOllut = false;
        this.liitteetOllut = false;

        // Samoin nämä nolliksi.
        this.ylakohta = 0;
        this.alakohta = 0;
        this.suurinYlakohta = 0;
        this.suurinAlakohta = 0;
        this.viimeisinAlakohta = 0;

    }

    // Tämä on luokan päämetodi, jonka kautta jokainen luokan metodi tulee
    // kutsutuksi. Käy syötetyn pöytäkirjan lävitse ja kutsuu jokaisen rivin
    // kohdalla metodeita, joiden kautta rivien tyyppi päästään tunnistamaan.
    // Olemattoman tiedoston antama poikkeus käsitellään korkeammalla
    // luokkahierarkiassa jo käyttäjän antaessa tiedostonimeä.
    public RivitettyPK tunnistaRivit() throws FileNotFoundException {

        tunnistaViimeinenKohta(new Scanner(this.alkuPK));
        this.rivitetty = new RivitettyPK(this.suurinYlakohta, this.suurinAlakohta);

        // Käydään Scannerilla koko pöytäkirja läpi.
        Scanner lukija = new Scanner(this.alkuPK);
        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            rivitetty.lisaaRivi(maaritaLisattavaRivi(rivi));
        }

        return rivitetty;

    }

    // Tämä metodi etsii viimeisen kohdan, alakohdan ja suurimman alakohdan
    // numerot. Käytössä on oma Scanner.
    private void tunnistaViimeinenKohta(Scanner esilukija) {

        while (esilukija.hasNextLine()) {
            String rivi = esilukija.nextLine().trim();
            if (tunnistaYlakohta(rivi, this.suurinYlakohta)) {
                this.suurinYlakohta++;
                continue;
            }
            if (tunnistaAlakohta(rivi, this.viimeisinAlakohta, this.suurinYlakohta)) {
                this.viimeisinAlakohta++;
                // Suurin alakohta ei välttämättä ole viimeisenä oleva alakohta.
                this.suurinAlakohta = Math.max(this.suurinAlakohta, this.viimeisinAlakohta);
            }
        }
    }

    // Tämä jakaa rivin tyypin määrityksen kolmeen osaan
    private Rivi maaritaLisattavaRivi(String rivi) {

        // Pelkkiä välilyöntejä sisältävät rivit voidaan luokitella suoraan
        if (rivi.trim().isEmpty()) {
            return new TyhjaRivi(rivi);
            // Tämä if-osa koskee pöytäkirjan varsinaista sisältöosaa. Sisältöosa
            // on tunnistettavissa siitä, että kohdilla on posiitivinen numero tai
            // törmätään ensimmäiseen tällaiseen kohtaan.
        } else if (this.ylakohta > 0 || tunnistaYlakohta(rivi.trim(), 0)) {
            return maaritaMaareTekstiosalle(rivi);
            // Muussa tapauksessa todetaan, että käsittelyssä ovat alkutiedot.
        } else {
            return maaritaMaareAlkutiedoille(rivi);
        }

    }

    // Määrätään alkutietoihin kuuluvien rivien tyypit.
    private Rivi maaritaMaareAlkutiedoille(String rivi) {

        // Ei oteta välilyöntejä alussa tai lopussa eikä kirjainten kokoa huomioon
        String trimRivi = rivi.trim().toLowerCase();

        // Seuraavien if-lauseiden järjestyksellä on merkitystä! Tämä koskee
        // erityisesti tunnistaOsallistuja-ehtoa.
        if (tunnistaOtsikko(trimRivi)) {
            this.otsikkoOllut = true;
            return new OtsikkoRivi(rivi);

        } else if (tunnistaAika(trimRivi)) {
            this.aikaOllut = true;
            return new AikaRivi(rivi);

        } else if (tunnistaPaikka(trimRivi)) {
            this.paikkaOllut = true;
            this.rivitetty.setTrueOnkoPaikka();
            return new PaikkaRivi(rivi);

        } else if (tunnistaLasna(trimRivi)) {
            this.lasnaOllut = true;
            return new LasnaRivi(rivi);

        } else if (tunnistaAlalasna(trimRivi)) {
            this.rivitetty.setTrueOnkoAlalasna();
            return new AlalasnaRivi(rivi);

        } else if (tunnistaOsallistuja(trimRivi)) {
            return new OsallistujaRivi(rivi);
            // Jos mikään ehdoista ei täyty (eli sihteeri on kirjoittanut
            // vääränlaisen pöytäkirjan), luodaan geneerinen Rivi-olio.
        } else {
            return new Rivi(rivi);
        }

    }

    // Määrätään sisältöosan rivien tyypit.
    private Rivi maaritaMaareTekstiosalle(String rivi) {

        // Ei oteta välilyöntejä alussa tai lopussa eikä kirjainten kokoa huomioon
        String trimRivi = rivi.trim().toLowerCase();

        // Seuraavien if-lauseiden järjestyksellä on merkitystä! tunnistaYläkohdanTeksti
        // ja tunnistaAlakohdanTeksti -ehdot eivät voi sijaita ennen ehtoja
        // tunnistaYlakohta ja tunnistaAlakohta.
        if (tunnistaYlakohta(trimRivi, this.ylakohta)) {
            this.ylakohta++;
            this.alakohta = 0;
            return new YlakohtaOtsikkoRivi(rivi, this.ylakohta);

        } else if (tunnistaYlakohdanTeksti(trimRivi)) {
            return new YlakohtaTekstiRivi(rivi, this.ylakohta);

        } else if (tunnistaAlakohta(trimRivi, this.alakohta, this.ylakohta)) {
            this.alakohta++;
            return new AlakohtaOtsikkoRivi(rivi, this.alakohta);

        } else if (tunnistaAlakohdanTeksti(trimRivi)) {
            return new AlakohtaTekstiRivi(rivi, this.alakohta);

        } else if (tunnistaLiiteOtsikko(trimRivi)) {
            this.liitteetOllut = true;
            return new LiiteOtsikkoRivi(rivi);

        } else if (tunnistaLiitteet()) {
            return new LiiteRivi(rivi);
            // Jos mikään ehdoista ei täyty, luodaan geneerinen Rivi-olio.
        } else {
            return new Rivi(rivi);
        }

    }

    // otsikkoOllut pitää huolen, että otsikkorivejä otetaan mukaan vain yksi.
    // onkoOtsikko tutkii rivin sisällön.
    private boolean tunnistaOtsikko(String rivi) {
        if (!otsikkoOllut && onkoOtsikko(rivi)) {
            return true;
        }
        return false;
    }

    // aikaOllut pitää huolen, että aikarivejä otetaan mukaan vain yksi.
    // Varmistutaan, että rivin alku on oikeassa muodossa.
    private boolean tunnistaAika(String rivi) {
        if (!aikaOllut && rivi.startsWith("aika:")) {
            return true;
        }
        return false;
    }

    // paikkaOllut pitää huolen, että paikkarivejä otetaan mukaan vain yksi.
    // Varmistutaan, että rivin alku on oikeassa muodossa.
    private boolean tunnistaPaikka(String rivi) {
        if (!paikkaOllut && rivi.startsWith("paikka:")) {
            return true;
        }
        return false;
    }

    // lasnaOllut varmistaa, että "Läsnä:"-rivejä otetaan mukaan vain yksi.
    // Varmistutaan, että rivin alku on oikeassa muodossa.
    private boolean tunnistaLasna(String rivi) {
        if (!lasnaOllut && rivi.startsWith("läsnä")) {
            return true;
        }
        return false;

    }

    // Läsnäolijoiden alakategoriat tutkitaan vain, jos läsnäolijoita on ryhdytty
    // luettelemaan (lasnaOllut). onkoAlalasna pitää huolen, että mukaan otetaan
    // vain asiaankuuluvat rivit.
    private boolean tunnistaAlalasna(String rivi) {
        if (this.lasnaOllut && onkoAlalasna(rivi)) {
            return true;
        }
        return false;
    }

    // Läsnäolijoiksi luetaan kaikki "Läsnä"-rivin jälkeen tulevat, joita ei tunnisteta
    // läsnäolijoiden alakategorioiksi. Altis virheille, mikäli alakategorioita ei
    // syötetä oikein. Virheitä torjuu hiukan se, että vaaditaan rivin sisältävän
    // yhden välilyönnin kuten nimissä etu- ja sukunimen välissä kuuluu olla.
    private boolean tunnistaOsallistuja(String rivi) {
        if (this.lasnaOllut && !onkoAlalasna(rivi) && rivi.contains(" ")) {
            return true;
        }
        return false;
    }

    // Tunnistetaan kohtien otsikot. Otsikon on sisällettävä välilyönti numeron
    // pisteen ja otsikkoteksti välissä. Tätä tietoa hyödynnetään if-lauseen
    // kutsumissa metodeissa.
    private boolean tunnistaYlakohta(String rivi, int edellinenYlakohta) {
        if (rivi.contains(" ") && onkoYlakohta(rivi) && onkoSeuraavaYlakohta(rivi, edellinenYlakohta)) {
            return true;
        }
        return false;
    }

    // Tunnistetaan teksti, joka on varsinaisen kohdan, ei alakohdan alla.
    // Varmistutaan, ettei olla alakohdan tai liiteluettelon alla eikä lisäksi
    // olla käsittelemässä alakohdan otsikkoriviä.
    private boolean tunnistaYlakohdanTeksti(String rivi) {
        if (this.alakohta == 0 && !this.liitteetOllut && !tunnistaAlakohta(rivi, this.alakohta, this.ylakohta)
                && !tunnistaLiiteOtsikko(rivi)) {
            return true;
        }
        return false;
    }

    // Tunnistetaan alakohtien otsikot. Otsikon on sisällettävä välilyönti numeron
    // pisteen ja otsikkotekstin välissä. Tätä tietoa hyödynnetään if-lauseen
    // kutsumissa metodeissa.
    private boolean tunnistaAlakohta(String rivi, int edellinenAlakohta, int edellinenYlakohta) {
        if (rivi.contains(" ") && onkoAlakohta(rivi) && onkoSeuraavaAlakohta(rivi, edellinenAlakohta, edellinenYlakohta)) {
            return true;
        }
        return false;
    }
    
    // Tunnistetaan teksti, joka on varsinaisen kohdan, ei alakohdan alla.
    // Varmistutaan, ettei olla yläkohdan (törmättäessä yläkohdan otsikkoon
    // alakohtien numerointi nollataan) tai liiteluettelon alla.
    private boolean tunnistaAlakohdanTeksti(String rivi) {
        if (this.alakohta > 0 && !this.liitteetOllut) {
            return true;
        }
        return false;
    }

    // Tunnistetaan liiteluettelon otsikko. Varmistutaan, että kaikki kohdat
    // ja alakohdat on jo käyty läpi. Virheet mahdollisia, mikäli "liitteet"-teksti
    // esiintyy viimeisen kohdan tai alakohdan alla.
    private boolean tunnistaLiiteOtsikko(String rivi) {
        if (!this.liitteetOllut && rivi.startsWith("liitteet")
                && this.ylakohta == this.suurinYlakohta && this.alakohta == this.alakohta) {
            return true;
        }
        return false;
    }

    // Liitteinä pidetään kaikkea liiteotsikkoa seuraavaa.
    private boolean tunnistaLiitteet() {
        if (this.liitteetOllut) {
            return true;
        }
        return false;
    }

    // tunnistaOtsikko-metodin kutsuma. Tarkastetaan, että rivi sisältää avainsanat
    // ollakseen otsikko.
    private static boolean onkoOtsikko(String rivi) {
        if (rivi.contains("hallituksen kokous") || rivi.contains("yhdistyksen kokous")
                || rivi.contains("sääntömääräinen kokous") || rivi.contains("järjestäytymiskokous")) {
            return true;
        }
        return false;
    }

    // tunnistaAlalasna-metodin kutsuma. Tarkastetaan, että rivi sisältää avainsanat
    // ollakseen läsnäolijoiden alakategorian otsikko.
    private static boolean onkoAlalasna(String rivi) {
        if (rivi.contains("hallituksen jäsenet") || rivi.contains("hallituksen varajäsenet")
                || rivi.contains("muut") || rivi.contains("läsnäolo- ja puheoikeudella")
                || rivi.contains("läsnäolo-oikeudella")) {
            return true;
        }
        return false;
    }

    // Tutkitaan, onko rivi kohdan otsikkorivi. Tarkastetaan, onko sen alku muotoa
    // "[numero][numero]." Varmistutaan myös, että muotoa "[numero][numero].[numero][numero]."
    // olevat ehdokkaat hylätään. Näitä saattaa tulla vastaan, jos rivinä on alakohdan
    // otsikkorivi tai rivin aloittaa esimerkiksi päivämäärä.
    private static boolean onkoYlakohta(String rivi) {
        if (rivi.substring(0, rivi.indexOf(" ")).matches("[0-9]{1,}.")
                && !rivi.substring(0, rivi.indexOf(" ") - 1).contains(".")) {
            return true;
        }
        return false;
    }

    // Tehdään ylläolevaa vastaava tarkistus alakohdan otsikkoriville.s
    private static boolean onkoAlakohta(String rivi) {
        if (rivi.substring(0, rivi.indexOf(" ")).matches("[0-9]{1,}.[0-9]{1,}.")) {
            return true;
        }
        return false;
    }

    // Tarkistetaan, että kohdan otsikkoehdokkaan numerointi on kunnossa, eli 
    // numero on noussut yhdellä edellisestä.
    private static boolean onkoSeuraavaYlakohta(String rivi, int edellinenYlakohta) {
        if (Integer.parseInt(rivi.substring(0, rivi.indexOf(".")))
                == edellinenYlakohta + 1) {
            return true;
        }
        return false;
    }

    // Tarkistetaan, että alakohdan otsikkoehdokkaan numerointi on kunnossa, eli
    // numero on noussut yhdellä edellisestä.
    private static boolean onkoSeuraavaAlakohta(String rivi, int edellinenAlakohta, int edellinenYlakohta) {
        rivi = rivi.substring(0, rivi.indexOf(" ") - 1);
        if (Integer.parseInt(rivi.substring(rivi.indexOf(".") + 1)) == edellinenAlakohta + 1
                && onkoSeuraavaYlakohta(rivi.substring(0, rivi.indexOf(".") + 1), edellinenYlakohta - 1)) {
            return true;
        }
        return false;
    }

}
