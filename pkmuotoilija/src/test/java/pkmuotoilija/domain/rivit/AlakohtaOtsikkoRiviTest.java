package pkmuotoilija.domain.rivit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import pkmuotoilija.domain.*;

public class AlakohtaOtsikkoRiviTest {

    PKtiedot tiedot;

    public AlakohtaOtsikkoRiviTest() {
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
        tiedot.setSailytaOmaRivitys(false);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void alakohtaOtsikkoRiviPerusTesti1() {
        AlakohtaOtsikkoRivi rivi = new AlakohtaOtsikkoRivi("8.1. Suuri ryyppyretki", 1, 8);
        tiedot.setLeveys(80);
        tiedot.setYlakohdanSisennys(1);
        tiedot.setAlakohdanSisennys(1);
        rivi.formatoiRivi(tiedot);
        assertEquals("   8.1. Suuri ryyppyretki\n", rivi.getSisalto());
    }

    @Test
    public void alakohtaOtsikkoRiviPerusTesti2() {
        AlakohtaOtsikkoRivi rivi = new AlakohtaOtsikkoRivi("8.1. Suuri ryyppyretki", 1, 8);
        tiedot.setLeveys(80);
        tiedot.setYlakohdanSisennys(2);
        tiedot.setAlakohdanSisennys(1);
        rivi.formatoiRivi(tiedot);
        assertEquals("    8.1. Suuri ryyppyretki\n", rivi.getSisalto());
    }

    @Test
    public void alakohtaOtsikkoRiviPerusTesti3() {
        AlakohtaOtsikkoRivi rivi = new AlakohtaOtsikkoRivi("8.1. Suuri ryyppyretki", 1, 8);
        tiedot.setLeveys(80);
        tiedot.setYlakohdanSisennys(2);
        tiedot.setAlakohdanSisennys(2);
        rivi.formatoiRivi(tiedot);
        assertEquals("    8.1.  Suuri ryyppyretki\n", rivi.getSisalto());
    }

    @Test
    public void alakohtaOtsikkoRiviPerusTesti4() {
        AlakohtaOtsikkoRivi rivi = new AlakohtaOtsikkoRivi("8.1. Suuri ryyppyretki", 1, 8);
        tiedot.setLeveys(80);
        tiedot.setYlakohdanSisennys(1);
        tiedot.setAlakohdanSisennys(2);
        rivi.formatoiRivi(tiedot);
        assertEquals("   8.1.  Suuri ryyppyretki\n", rivi.getSisalto());
    }

    @Test
    public void alakohtaOtsikkoRiviPitkaTesti1() {
        AlakohtaOtsikkoRivi rivi = new AlakohtaOtsikkoRivi("8.1. Suuri ryyppyretki jonnekin hulluun mestaan", 1, 8);
        tiedot.setLeveys(45);
        tiedot.setYlakohdanSisennys(1);
        tiedot.setAlakohdanSisennys(1);
        rivi.formatoiRivi(tiedot);
        assertEquals("   8.1. Suuri ryyppyretki jonnekin hulluun\n"
                + "        mestaan\n", rivi.getSisalto());
    }

    @Test
    public void alakohtaOtsikkoRiviPitkaTesti2() {
        AlakohtaOtsikkoRivi rivi = new AlakohtaOtsikkoRivi("8.1. Suuri ryyppyretki jonnekin hulluun mestaan", 1, 8);
        tiedot.setLeveys(45);
        tiedot.setYlakohdanSisennys(2);
        tiedot.setAlakohdanSisennys(2);
        rivi.formatoiRivi(tiedot);
        assertEquals("    8.1.  Suuri ryyppyretki jonnekin hulluun\n"
                + "          mestaan\n", rivi.getSisalto());
    }

    @Test
    public void alakohtaOtsikkoRiviIsoNumeroTesti1() {
        AlakohtaOtsikkoRivi rivi = new AlakohtaOtsikkoRivi("8.10. Suuri ryyppyretki jonnekin hulluun mestaan", 10, 8);
        tiedot.setLeveys(80);
        tiedot.setYlakohdanSisennys(1);
        tiedot.setAlakohdanSisennys(2);
        rivi.formatoiRivi(tiedot);
        assertEquals("   8.10. Suuri ryyppyretki jonnekin hulluun mestaan\n", rivi.getSisalto());
    }

    @Test
    public void alakohtaOtsikkoRiviIsoNumeroTesti2() {
        AlakohtaOtsikkoRivi rivi = new AlakohtaOtsikkoRivi("89.1. Suuri ryyppyretki jonnekin hulluun mestaan", 1, 89);
        tiedot.setLeveys(80);
        tiedot.setYlakohdanSisennys(1);
        tiedot.setAlakohdanSisennys(2);
        rivi.formatoiRivi(tiedot);
        assertEquals("   89.1.  Suuri ryyppyretki jonnekin hulluun mestaan\n", rivi.getSisalto());
    }

    @Test
    public void alakohtaOtsikkoRiviIsoNumeroPitkaTesti1() {
        AlakohtaOtsikkoRivi rivi = new AlakohtaOtsikkoRivi("8.10. Suuri ryyppyretki jonnekin hulluun mestaan", 10, 8);
        tiedot.setLeveys(45);
        tiedot.setYlakohdanSisennys(1);
        tiedot.setAlakohdanSisennys(2);
        rivi.formatoiRivi(tiedot);
        assertEquals("   8.10. Suuri ryyppyretki jonnekin hulluun\n" +
"         mestaan\n", rivi.getSisalto());
    }
}
