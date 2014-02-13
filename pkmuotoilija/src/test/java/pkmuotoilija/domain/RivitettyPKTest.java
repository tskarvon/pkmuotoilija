package pkmuotoilija.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import pkmuotoilija.domain.rivit.*;

public class RivitettyPKTest {
    
    RivitettyPK rivitetty;
    
    public RivitettyPKTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        rivitetty = new RivitettyPK();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void rivitettyPituusTest() {
        for(int i = 0; i < 98; i++) {
            rivitetty.lisaaRivi(new Rivi("conels"));
        }
        assertEquals(98, rivitetty.getPituus());
    }
}
