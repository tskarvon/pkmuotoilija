package pkmuotoilija.domain.rivit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import pkmuotoilija.domain.*;

public class OtsikkoRiviTest {

    PKtiedot tiedot;

    public OtsikkoRiviTest() {
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
    public void otsikkoRiviPerusTesti() {
        OtsikkoRivi rivi = new OtsikkoRivi("Kokouksen hallituksen kokous 1/2009");
        tiedot.setLeveys(80);
        rivi.formatoiRivi(tiedot);
        assertEquals("Kokouksen hallituksen kokous 1/2009                                PÖYTÄKIRJA\n", rivi.getSisalto());

    }

    @Test
    public void otsikkoRiviPitkaTesti() {
        OtsikkoRivi rivi = new OtsikkoRivi("Kokouksen hallituksen kokous herran vuonna 2009 ja olkoon se myös tämän kauden ensimmäinen kokous");
        tiedot.setLeveys(80);
        rivi.formatoiRivi(tiedot);
        assertEquals("Kokouksen hallituksen kokous herran vuonna 2009 ja olkoon se myös  PÖYTÄKIRJA\n"
                + "tämän kauden ensimmäinen kokous\n", rivi.getSisalto());

    }

    @Test
    public void otsikkoRiviHyvinPitkaTesti() {
        OtsikkoRivi rivi = new OtsikkoRivi("Kokouksen hallituksen kokous herran vuonna 2009 ja olkoon se myös tämän kauden ensimmäinen kokous ja viimeinenkin ehkä jos herramme jumala niin meille suuressa armossa sua");
        tiedot.setLeveys(80);
        rivi.formatoiRivi(tiedot);
        assertEquals("Kokouksen hallituksen kokous herran vuonna 2009 ja olkoon se myös  PÖYTÄKIRJA\n"
                + "tämän kauden ensimmäinen kokous ja viimeinenkin ehkä jos herramme\n"
                + "jumala niin meille suuressa armossa sua\n", rivi.getSisalto());

    }
}
