package pkmuotoilija.domain.rivit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LasnaRiviTest {
    
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
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void lasnaTest() {
        LasnaRivi rivi = new LasnaRivi("läsnä");
        rivi.formatoiRivi(80);
        assertEquals("Läsnä:", rivi.getSisalto());
    }
    
}
