package pkmuotoilija.domain.rivit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import pkmuotoilija.domain.*;

public class TyhjaRiviTest {

    PKtiedot tiedot;

    public TyhjaRiviTest() {
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
    public void tyhjaRiviTest() {

        TyhjaRivi rivi = new TyhjaRivi("sadahaha");
        this.tiedot.setLeveys(80);
        rivi.formatoiRivi(tiedot);
        assertEquals("", rivi.getSisalto());
    }
}
