package pkmuotoilija.domain.rivit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class OsallistujaRiviTest {
    
    public OsallistujaRiviTest() {
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
    public void ostallistujaRiviPerusTesti() {
        OsallistujaRivi rivi = new OsallistujaRivi("Urho Karhu");
        rivi.formatoiRivi(80);
        
    }
    
}
