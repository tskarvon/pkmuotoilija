package pkmuotoilija.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import pkmuotoilija.domain.rivit.*;

import java.io.File;
import java.util.Scanner;

public class PKlukijaTest {

    PKtiedot tiedot;

    public PKlukijaTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        tiedot = new PKtiedot();
    }

    @After
    public void tearDown() {
    }

    // Testin ajatuksena olisi verrata metodin tunnistaRivit tuottamia Rivi-luokan
    // aliluokkia annetulta pöytäkirjalta näiden rivien kohdalla odotettuihin
    // aliluokkiin. Jokaiselle Rivin aliluokalle on annettu metodilla tyyppiString
    // merkkijonokoodi. Käsin on tuotettu testiNodotettu.txt, jonka riveille on
    // syötetty siinä järjestyksessä jossa tunnistaRivit-metodin haluttaisiin
    // rivit tunnistavan.
    //
    // Testien vaatimien tekstitiedostojen luominen on varsin työlästä, joten
    // testejä on melko vähän.
    @Test
    public void testaaPK1() throws Exception {

        File tulos = new File("src/test/java/pkmuotoilija/domain/testiPKt/testi1odotettu.txt");
        Scanner scan = new Scanner(tulos);
        PKlukija lukija = new PKlukija(new File("src/test/java/pkmuotoilija/domain/testiPKt/testi1.txt"));

        RivitettyPK riv = lukija.tunnistaRivit();

        assertEquals(riv.getPituus(), testaaT(scan, riv));

    }

    @Test
    public void testaaPK2() throws Exception {

        File tulos = new File("src/test/java/pkmuotoilija/domain/testiPKt/testi2odotettu.txt");
        Scanner scan = new Scanner(tulos);
        PKlukija lukija = new PKlukija(new File("src/test/java/pkmuotoilija/domain/testiPKt/testi2.txt"));

        RivitettyPK riv = lukija.tunnistaRivit();

        assertEquals(riv.getPituus(), testaaT(scan, riv));

    }

    private int testaaT(Scanner scan, RivitettyPK riv) {

        int t = 0;

        for (int i = 0; i < riv.getPituus(); i++) {
            if (!scan.nextLine().equals(tyyppiString(riv.getRivi(i)))) {
                break;
            }
            t++;
        }
        return t;

    }

    private static String tyyppiString(Rivi rivi) {

        if (rivi.getClass() == AikaRivi.class) {
            return "a";
        } else if (rivi.getClass() == AlakohtaOtsikkoRivi.class) {
            return "ao";
        } else if (rivi.getClass() == AlakohtaTekstiRivi.class) {
            return "at";
        } else if (rivi.getClass() == AlalasnaRivi.class) {
            return "al";
        } else if (rivi.getClass() == LasnaRivi.class) {
            return "l";
        } else if (rivi.getClass() == LiiteOtsikkoRivi.class) {
            return "lo";
        } else if (rivi.getClass() == LiiteRivi.class) {
            return "li";
        } else if (rivi.getClass() == OsallistujaRivi.class) {
            return "o";
        } else if (rivi.getClass() == OtsikkoRivi.class) {
            return "ot";
        } else if (rivi.getClass() == PaikkaRivi.class) {
            return "p";
        } else if (rivi.getClass() == TyhjaRivi.class) {
            return "t";
        } else if (rivi.getClass() == YlakohtaOtsikkoRivi.class) {
            return "yo";
        } else if (rivi.getClass() == YlakohtaTekstiRivi.class) {
            return "y";
        } else {
            return "r";
        }

    }

}
