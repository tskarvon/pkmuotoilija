package pkmuotoilija.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PKtiedotTest {

    PKtiedot tiedot;

    public PKtiedotTest() {
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
    public void leveysTest() {
        tiedot.setLeveys(98);
        assertEquals(98, tiedot.getLeveys());
    }

    @Test
    public void onkoPaikkaTest() {
        tiedot.setOnkoPaikka(true);
        assertEquals(true, tiedot.getOnkoPaikka());
    }

    @Test
    public void onkoAlalasnaTest() {
        tiedot.setOnkoAlalasna(true);
        assertEquals(true, tiedot.getOnkoAlalasna());
    }

    @Test
    public void onkoHyvaksyttyTest() {
        tiedot.setLisaaHyvaksyttyKokouksessa(false);
        assertEquals(false, tiedot.getLisaaHyvaksyttyKokouksessa());
    }

    @Test
    public void PJTest() {
        tiedot.setPJ("Tony Conels");
        assertEquals("Tony Conels", tiedot.getPJ());
    }

    @Test
    public void SihteeriTest() {
        tiedot.setSihteeri("Tony Conels");
        assertEquals("Tony Conels", tiedot.getSihteeri());
    }

    @Test
    public void tarkastaja1Test() {
        tiedot.lisaaTarkastaja("Tony Conels");
        assertEquals("Tony Conels", tiedot.getTarkastaja(2));
    }

    @Test
    public void tarkastaja2Test() {
        tiedot.lisaaTarkastaja("Tony Conels");
        tiedot.lisaaTarkastaja("Jouni Haapakoski");
        assertEquals("Jouni Haapakoski", tiedot.getTarkastaja(3));
    }

}
