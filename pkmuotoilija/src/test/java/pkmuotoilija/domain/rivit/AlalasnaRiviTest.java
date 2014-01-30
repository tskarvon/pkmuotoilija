package pkmuotoilija.domain.rivit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AlalasnaRiviTest {
    
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
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void alalasnaPerusTesti() {
        AlalasnaRivi rivi = new AlalasnaRivi("Hallituksen jäsenet:");
        rivi.formatoiRivi(80);
        assertEquals("  Hallituksen jäsenet:", rivi.getSisalto());
    }
    
    @Test
    public void alalasnaPuutteitaTesti() {
        AlalasnaRivi rivi = new AlalasnaRivi("hallituksen varajäsenet");
        rivi.formatoiRivi(40);
        assertEquals("  Hallituksen varajäsenet:", rivi.getSisalto());
    }
    
}
