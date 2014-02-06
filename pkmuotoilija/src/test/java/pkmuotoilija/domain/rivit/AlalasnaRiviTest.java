package pkmuotoilija.domain.rivit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import pkmuotoilija.domain.*;

public class AlalasnaRiviTest {
    
    PKtiedot tiedot;
    
    public AlalasnaRiviTest() {
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
    public void alalasnaPerusTesti() {
        AlalasnaRivi rivi = new AlalasnaRivi("Hallituksen jäsenet:");
        tiedot.setLeveys(80);
        rivi.formatoiRivi(tiedot);
        assertEquals("  Hallituksen jäsenet:", rivi.getSisalto());
    }
    
    @Test
    public void alalasnaPuutteitaTesti() {
        AlalasnaRivi rivi = new AlalasnaRivi("hallituksen varajäsenet");
        tiedot.setLeveys(40);
        rivi.formatoiRivi(tiedot);
        assertEquals("  Hallituksen varajäsenet:", rivi.getSisalto());
    }
    
}
