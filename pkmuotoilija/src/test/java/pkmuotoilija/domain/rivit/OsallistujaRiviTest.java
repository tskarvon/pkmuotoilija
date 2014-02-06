package pkmuotoilija.domain.rivit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import pkmuotoilija.domain.*;

public class OsallistujaRiviTest {

    PKtiedot tiedot;

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
        tiedot = new PKtiedot();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void ostallistujaRiviPerusTesti1() {
        OsallistujaRivi rivi = new OsallistujaRivi("Urho Karhu");
        tiedot.setLeveys(80);
        tiedot.setOnkoAlalasna(true);
        rivi.formatoiRivi(tiedot);
        assertEquals("    Urho Karhu", rivi.getSisalto());

    }

    @Test
    public void ostallistujaRiviPerusTesti2() {
        OsallistujaRivi rivi = new OsallistujaRivi("Urho Karhu");
        tiedot.setLeveys(80);
        tiedot.setOnkoAlalasna(false);
        rivi.formatoiRivi(tiedot);
        assertEquals("  Urho Karhu", rivi.getSisalto());
    }
    
    @Test
    public void ostallistujaRiviPitkaTesti1() {
        OsallistujaRivi rivi = new OsallistujaRivi("Urho Karhu (saapui kohdassa 2, poistui kohdassa 100)");
        tiedot.setLeveys(45);
        tiedot.setOnkoAlalasna(true);
        rivi.formatoiRivi(tiedot);
        assertEquals("    Urho Karhu (saapui kohdassa 2, poistui\n" +
"                kohdassa 100)", rivi.getSisalto());
    }
    @Test
    public void ostallistujaRiviPitkaTesti2() {
        OsallistujaRivi rivi = new OsallistujaRivi("Urho Karhu (saapui kohdassa 2, poistui kohdassa 100)");
        tiedot.setLeveys(45);
        tiedot.setOnkoAlalasna(false);
        rivi.formatoiRivi(tiedot);
        assertEquals("  Urho Karhu (saapui kohdassa 2, poistui\n" +
"              kohdassa 100)", rivi.getSisalto());
    }
}
