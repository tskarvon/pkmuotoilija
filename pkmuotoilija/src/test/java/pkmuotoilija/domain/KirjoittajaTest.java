package pkmuotoilija.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.util.Scanner;

public class KirjoittajaTest {

    PKtiedot tiedot;
    Kirjoittaja kirjoittaja;
    RivitettyPK rivitetty;

    public KirjoittajaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        kirjoittaja = new Kirjoittaja();
        rivitetty = new RivitettyPK();
        tiedot = new PKtiedot();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void PKtesti() {
        try {
            tiedot.setLahdetiedosto(new File("src/test/java/pkmuotoilija/domain/testiPKt/kirjoittajaTestiPK.txt"));
            tiedot.setKohdetiedosto(new File("src/test/java/pkmuotoilija/domain/testiPKt/kirjoittajaTestiPKtulos.txt"));
        } catch (Exception FileNotFoundException) {
            System.out.println("Ongelma testissä KirjoittajaTest");
        }
        Kasittelija kasittelija = new Kasittelija();
        
        try {
            kasittelija.suorita(tiedot);
        } catch (Exception FileNotFoundException) {
            System.out.println("Ongelma käsittelijässä. KirjoittajaTest.");
        }
        String tulos = "";
        String odotettu = "a";

        try {
            tulos = new Scanner(new File("src/test/java/pkmuotoilija/domain/testiPKt/kirjoittajaTestiPKtulos.txt")).useDelimiter("\\Z").next();
            odotettu = new Scanner(new File("src/test/java/pkmuotoilija/domain/testiPKt/kirjoittajaTestiPKodotettu.txt")).useDelimiter("\\Z").next();
        } catch (Exception FileNotFoundException) {
            System.out.println("Ongelma testissä KirjoittajaTestASGGG");
        }
        assertEquals(tulos, odotettu);
    }

    @Test
    public void allekirjoitusTesti1() {
        tiedot.setPJ("Urho Karhu");
        tiedot.setSihteeri("Juoppo Lalli");
        tiedot.lisaaTarkastaja("Ökkö Pökkö");
        tiedot.lisaaTarkastaja("Plöp plöpö");

        tiedot.setKohdetiedosto(new File("src/test/java/pkmuotoilija/domain/testiPKt/alkTesti1.txt"));

        String tulos = "";
        String odotettu = "";

        try {
            kirjoittaja.kirjoitaValmisPK(rivitetty, tiedot);
            tulos = new Scanner(new File("src/test/java/pkmuotoilija/domain/testiPKt/alkTesti1.txt")).useDelimiter("\\Z").next();
            odotettu = new Scanner(new File("src/test/java/pkmuotoilija/domain/testiPKt/alkTesti1odotettu.txt")).useDelimiter("\\Z").next();
        } catch (Exception FileNotFoundException) {
            System.out.println("Ongelma testissä KirjoittajaTest");
        }

        assertEquals(tulos, odotettu);

    }

    @Test
    public void allekirjoitusTesti2() {
        tiedot.setPJ("Urho Karhu");
        tiedot.setSihteeri("Juoppo Lalli");
        tiedot.lisaaTarkastaja("Ökkö Pökkö");

        tiedot.setKohdetiedosto(new File("src/test/java/pkmuotoilija/domain/testiPKt/alkTesti2.txt"));

        String tulos = "";
        String odotettu = "";

        try {
            kirjoittaja.kirjoitaValmisPK(rivitetty, tiedot);
            tulos = new Scanner(new File("src/test/java/pkmuotoilija/domain/testiPKt/alkTesti2.txt")).useDelimiter("\\Z").next();
            odotettu = new Scanner(new File("src/test/java/pkmuotoilija/domain/testiPKt/alkTesti2odotettu.txt")).useDelimiter("\\Z").next();
        } catch (Exception FileNotFoundException) {
            System.out.println("Ongelma testissä KirjoittajaTest");
        }

        assertEquals(tulos, odotettu);

    }
}
