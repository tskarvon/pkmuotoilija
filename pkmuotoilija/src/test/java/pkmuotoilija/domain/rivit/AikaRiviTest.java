/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkmuotoilija.domain.rivit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tskarvon
 */
public class AikaRiviTest {

    public AikaRiviTest() {
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
    public void aikaRiviPerusTesti() {
        AikaRivi rivi = new AikaRivi("Aika: 666. tammikuuta 1999");
        rivi.formatoiRivi(80);
        assertEquals("Aika:   666. tammikuuta 1999", rivi.getSisalto());
    }

    @Test
    public void aikaRiviPerusTurhiaValejaTesti() {
        AikaRivi rivi = new AikaRivi("Aika: 666. tammikuuta 1999           ");
        rivi.formatoiRivi(80);
        assertEquals("Aika:   666. tammikuuta 1999", rivi.getSisalto());
    }

    @Test
    public void aikaRiviPitkaTesti() {
        AikaRivi rivi = rivi = new AikaRivi("Aika: 666. tammikuuta herran Jeesus Kristuksen vuonna 11254141 kahdeksan pöllöä");
        rivi.formatoiRivi(40);
        assertEquals("Aika:   666. tammikuuta herran Jeesus\n"
                + "        Kristuksen vuonna 11254141\n"
                + "        kahdeksan pöllöä", rivi.getSisalto());

    }

}
