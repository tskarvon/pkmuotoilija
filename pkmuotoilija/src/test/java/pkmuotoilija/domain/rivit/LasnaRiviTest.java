package pkmuotoilija.domain.rivit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import pkmuotoilija.domain.*;

public class LasnaRiviTest {
    
    PKtiedot tiedot;
    
    public LasnaRiviTest() {
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
    public void lasnaTest() {
        LasnaRivi rivi = new LasnaRivi("läsnä");
        tiedot.setLeveys(80);
        rivi.formatoiRivi(tiedot);
        assertEquals("Läsnä:", rivi.getSisalto());
    }
    
}
