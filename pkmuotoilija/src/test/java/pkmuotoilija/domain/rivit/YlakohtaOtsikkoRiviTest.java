package pkmuotoilija.domain.rivit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import pkmuotoilija.domain.*;

public class YlakohtaOtsikkoRiviTest {

    PKtiedot tiedot;

    public YlakohtaOtsikkoRiviTest() {
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
    public void ylakohtaOtsikkoRiviPerusTesti1() {
        YlakohtaOtsikkoRivi rivi = new YlakohtaOtsikkoRivi("8. Plöplöplöplöp plöplö plöpl", 8);
        tiedot.setLeveys(80);
        tiedot.setYlakohdanSisennys(1);
        rivi.formatoiRivi(tiedot);
        assertEquals("8. Plöplöplöplöp plöplö plöpl\n", rivi.getSisalto());

    }

    @Test
    public void ylakohtaOtsikkoRiviPerusTesti2() {
        YlakohtaOtsikkoRivi rivi = new YlakohtaOtsikkoRivi("18. Plöplöplöplöp plöplö plöpl", 18);
        tiedot.setLeveys(80);
        tiedot.setYlakohdanSisennys(2);
        rivi.formatoiRivi(tiedot);
        assertEquals("18. Plöplöplöplöp plöplö plöpl\n", rivi.getSisalto());

    }

    @Test
    public void ylakohtaOtsikkoRiviPerusTesti3() {
        YlakohtaOtsikkoRivi rivi = new YlakohtaOtsikkoRivi("1. Plöplöplöplöp plöplö plöpl", 1);
        tiedot.setLeveys(80);
        tiedot.setYlakohdanSisennys(2);
        rivi.formatoiRivi(tiedot);
        assertEquals("1.  Plöplöplöplöp plöplö plöpl\n", rivi.getSisalto());

    }

    @Test
    public void ylakohtaOtsikkoRiviPitkaTesti1() {
        YlakohtaOtsikkoRivi rivi = new YlakohtaOtsikkoRivi("1. Plöplöplöplöp plöplö plöpl plöplö plöplöä plöplö sgdasha", 1);
        tiedot.setLeveys(45);
        tiedot.setYlakohdanSisennys(1);
        rivi.formatoiRivi(tiedot);
        assertEquals("1. Plöplöplöplöp plöplö plöpl plöplö plöplöä\n"
                + "   plöplö sgdasha\n", rivi.getSisalto());

    }

    @Test
    public void ylakohtaOtsikkoRiviPitkaTesti2() {
        YlakohtaOtsikkoRivi rivi = new YlakohtaOtsikkoRivi("1. Plöplöplöplöp plöplö plöpl plöplö plöplöä plöplö sgdasha", 1);
        tiedot.setLeveys(45);
        tiedot.setYlakohdanSisennys(2);
        rivi.formatoiRivi(tiedot);
        assertEquals("1.  Plöplöplöplöp plöplö plöpl plöplö plöplöä\n"
                + "    plöplö sgdasha\n", rivi.getSisalto());

    }
}
