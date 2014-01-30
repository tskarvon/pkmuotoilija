package pkmuotoilija.domain.rivit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PaikkaRiviTest {

    public PaikkaRiviTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void paikkaRiviPerusTesti() {
        PaikkaRivi rivi = new PaikkaRivi("Paikka: klusteri, humala, Etelä-Helsinki");
        rivi.formatoiRivi(80);
        assertEquals("Paikka: klusteri, humala, Etelä-Helsinki", rivi.getSisalto());
    }

    @Test
    public void paikkaRiviPerusTurhiaValejaTesti() {
        PaikkaRivi rivi = new PaikkaRivi("Paikka:        klusteri, humala, Etelä-Helsinki      ");
        rivi.formatoiRivi(80);
        assertEquals("Paikka: klusteri, humala, Etelä-Helsinki", rivi.getSisalto());
    }
    
    @Test
    public void paikkaRiviPitkaTesti() {
        PaikkaRivi rivi = new PaikkaRivi("PAIKKA: asfagasd agdsa fa asdaga sfa agasgasfafsabga");
        rivi.formatoiRivi(45);
        assertEquals("Paikka: asfagasd agdsa fa asdaga sfa\n" +
"        agasgasfafsabga", rivi.getSisalto());
        
    }

}
