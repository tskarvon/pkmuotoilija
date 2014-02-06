// Metodien runko valmis, tarkka toteutus ja varsinainen käyttöliittymä puuttuu

package pkmuotoilija.UI;

import java.io.File;

import pkmuotoilija.domain.*;

public class Kayttoliittyma {
    
    /**
     * Käyttöliittymä.
     */

    public Kayttoliittyma() {
    }
    
    /**
     * Hakee käyttäjältä muokattavan pöytäkirjan sijainnin.
     * 
     * @return lahdetiedosto muokattava pöytäkirja
     */

    public File kysyLahdeTiedosto() {       
        File lahdetiedosto = new File("");        
        return lahdetiedosto;
    }
    
    /**
     * Hakee käyttäjältä muokatun pöytäkirjan tallennussijainnin.
     * 
     * @return kohdetiedosto muokattu pöytäkirja
     */
    
    public File kysyKohdeTiedosto() {        
        File kohdetiedosto = new File("");
        return kohdetiedosto;
    }
    
    /**
     * Hakee käyttäjältä tekstinleveyden.
     * 
     * @return tekstinleveys
     */
    
    public int kysyLeveys() {
        return 80;        
    }
    
}
