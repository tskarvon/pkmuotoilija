package pkmuotoilija.domain.rivit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class RiviTest {
    
    public RiviTest() {
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
    public void hajotaTest1() {
        Rivi rivi = new Rivi("Keskusteltiin siirtymisestä kolmiportaiseen maksujärjestelmään, jossa järjestöt jaettaisiin pieniin, keskikokoisiin ja suuriin. Siirrettiin tarkempi suunnittelu Leppätalokomitealle. Uudistuksella ei ole vielä kiire, mutta se on saatava valmiiksi käyttömaksujen seuraavaan perintään vuoden lopulla mennessä valmiiksi. Matlun käyttömaksu luultavasti nousisi uudessa järjestelmässä, sillä isoilta järjestöiltä ei voida vaatia suurempia maksuja.");
        rivi.hajotaRivi(false, 80, 9);
        assertEquals("         Keskusteltiin siirtymisestä kolmiportaiseen maksujärjestelmään, jossa\n" +
"         järjestöt jaettaisiin pieniin, keskikokoisiin ja suuriin. Siirrettiin\n" +
"         tarkempi suunnittelu Leppätalokomitealle. Uudistuksella ei ole vielä\n" +
"         kiire, mutta se on saatava valmiiksi käyttömaksujen seuraavaan\n" +
"         perintään vuoden lopulla mennessä valmiiksi. Matlun käyttömaksu\n" +
"         luultavasti nousisi uudessa järjestelmässä, sillä isoilta järjestöiltä\n" +
"         ei voida vaatia suurempia maksuja.", rivi.getSisalto());
    }
    
    @Test
    public void hajotaTest2() {
        Rivi rivi = new Rivi("Anssi kertoi sunnuntaina 27.1. Väinämöisen kentällä järjestettävästä kyykkäturnauksesta. Päätettiin aloittaa pelaaminen valoisaan aikaan klo 14 ja pohdittiin, miten kenttä on valaistu, minkälaisia omia valaistusmahdollisuuksia on ja missä tarkalleen pelataan. Matrix on luvannut kyykkäkamat lainaan. Joukkueita voi ilmoittaa etukäteen, mutta ilmoittamattakin ilman valmista joukkuetta voi saapua paikalle.");
        rivi.hajotaRivi(false, 80, 9);
        assertEquals("         Anssi kertoi sunnuntaina 27.1. Väinämöisen kentällä järjestettävästä\n" +
"         kyykkäturnauksesta. Päätettiin aloittaa pelaaminen valoisaan aikaan klo\n" +
"         14 ja pohdittiin, miten kenttä on valaistu, minkälaisia omia\n" +
"         valaistusmahdollisuuksia on ja missä tarkalleen pelataan. Matrix on\n" +
"         luvannut kyykkäkamat lainaan. Joukkueita voi ilmoittaa etukäteen, mutta\n" +
"         ilmoittamattakin ilman valmista joukkuetta voi saapua paikalle.", rivi.getSisalto());

    }
    
    @Test
    public void eiSisennystaTest() {
        Rivi rivi = new Rivi("Keskusteltiin touko-kesäkuisesta Tarton matkasta, jolle on aikaisemmin osallistunut lähinnä hyvin vanhoja toimijoita. Viime vuonna matkaa ei");
        rivi.hajotaRivi(false, 100, 0);
        assertEquals("Keskusteltiin touko-kesäkuisesta Tarton matkasta, jolle on aikaisemmin osallistunut lähinnä hyvin\n"
                + "vanhoja toimijoita. Viime vuonna matkaa ei", rivi.getSisalto());
    }
    
    @Test
    public void hajotaTestPieniLeveys() {
        Rivi rivi = new Rivi("testi testi tetsari");
        rivi.hajotaRivi(false, 10, 2);
        assertEquals("  testi\n  testi\n  tetsari", rivi.getSisalto());
    }
    
    @Test
    public void hajotaLyhytRivi() {
        Rivi rivi = new Rivi("olipa kerran pieni piip");
        rivi.hajotaRivi(false, 40, 10);
        assertEquals("          olipa kerran pieni piip", rivi.getSisalto());
    }
    
    @Test
    public void eiEkaaSisennysta() {
        Rivi rivi = new Rivi("plöplöplöplö plöööö plööplöplöplö plöplöplöpl");
        rivi.hajotaRivi(true, 20, 5);
        assertEquals("plöplöplöplö plöööö\n" +
"     plööplöplöplö\n" +
"     plöplöplöpl", rivi.getSisalto());
    }
    
    
    
}
