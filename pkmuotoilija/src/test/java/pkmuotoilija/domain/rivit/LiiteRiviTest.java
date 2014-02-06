package pkmuotoilija.domain.rivit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import pkmuotoilija.domain.*;

public class LiiteRiviTest {

    PKtiedot tiedot;

    public LiiteRiviTest() {
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

    @Test
    public void liiteRiviPerusTesti1() {
        LiiteRivi rivi = new LiiteRivi("Kokouksen esityslista", 1);
        tiedot.setLeveys(80);
        tiedot.setSuurinLiite(5);
        rivi.formatoiRivi(tiedot);
        assertEquals("Liite 1: Kokouksen esityslista", rivi.getSisalto());
    }

    @Test
    public void liiteRiviPerusTesti2() {
        LiiteRivi rivi = new LiiteRivi("Kokouksen esityslista", 4);
        tiedot.setLeveys(80);
        tiedot.setSuurinLiite(12);
        rivi.formatoiRivi(tiedot);
        assertEquals("Liite 4:  Kokouksen esityslista", rivi.getSisalto());
    }

    @Test
    public void liiteRiviPitkaTesti1() {
        LiiteRivi rivi = new LiiteRivi("Kokouksen esityslista ja siihen liittyvät muut höpöt", 4);
        tiedot.setLeveys(45);
        tiedot.setSuurinLiite(9);
        rivi.formatoiRivi(tiedot);
        assertEquals("Liite 4: Kokouksen esityslista ja siihen\n"
                + "         liittyvät muut höpöt", rivi.getSisalto());
    }

    @Test
    public void liiteRiviPitkaTesti2() {
        LiiteRivi rivi = new LiiteRivi("Kokouksen esityslista ja siihen liittyvät muut höpöt", 4);
        tiedot.setLeveys(45);
        tiedot.setSuurinLiite(19);
        rivi.formatoiRivi(tiedot);
        assertEquals("Liite 4:  Kokouksen esityslista ja siihen\n"
                + "          liittyvät muut höpöt", rivi.getSisalto());
    }

    @Test
    public void liiteRiviJossaLiiteTesti1() {
        LiiteRivi rivi = new LiiteRivi("Liite 4: Toimintasuunnitelma", 4);
        tiedot.setLeveys(80);
        tiedot.setSuurinLiite(19);
        rivi.formatoiRivi(tiedot);
        assertEquals("Liite 4:  Toimintasuunnitelma", rivi.getSisalto());
    }

    @Test
    public void liiteRiviJossaLiiteTesti2() {
        LiiteRivi rivi = new LiiteRivi("Liite 4: Toimintasuunnitelma", 4);
        tiedot.setLeveys(80);
        tiedot.setSuurinLiite(9);
        rivi.formatoiRivi(tiedot);
        assertEquals("Liite 4: Toimintasuunnitelma", rivi.getSisalto());
    }
}
