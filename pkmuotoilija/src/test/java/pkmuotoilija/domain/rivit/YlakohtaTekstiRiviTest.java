package pkmuotoilija.domain.rivit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import pkmuotoilija.domain.*;

public class YlakohtaTekstiRiviTest {

    PKtiedot tiedot;

    public YlakohtaTekstiRiviTest() {
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
    public void ylakohtaTekstiRiviLyhytTesti1() {
        YlakohtaTekstiRivi rivi = new YlakohtaTekstiRivi("Tämä on lyhyt rivi.");
        tiedot.setLeveys(80);
        tiedot.setYlakohdanSisennys(1);
        rivi.formatoiRivi(tiedot);
        assertEquals("   Tämä on lyhyt rivi.\n", rivi.getSisalto());
    }

    @Test
    public void alakohtaTekstiRiviLyhytTesti2() {
        YlakohtaTekstiRivi rivi = new YlakohtaTekstiRivi("Tämä on lyhyt rivi.");
        tiedot.setLeveys(80);
        tiedot.setYlakohdanSisennys(2);
        rivi.formatoiRivi(tiedot);
        assertEquals("    Tämä on lyhyt rivi.\n", rivi.getSisalto());
    }
    /*    @Test
     public void alakohtaTekstiRiviPidempiTesti() {
     YlakohtaTekstiRivi rivi = new YlakohtaTekstiRivi("Tämä on lyhyt rivi. Paitsi ettei olekaan riittävän pitkä mahtuakseen yhdelle riville!");
     tiedot.setLeveys(45);
     tiedot.setYlakohdanSisennys(1);
     rivi.formatoiRivi(tiedot);
     assertEquals("   Tämä on lyhyt rivi. Paitsi ettei olekaan\n" +
     "   riittävän pitkä mahtuakseen yhdelle\n" +
     "   riville!", rivi.getSisalto());
     } */

}
