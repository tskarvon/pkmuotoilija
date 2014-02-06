package pkmuotoilija.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import pkmuotoilija.UI.*;

public class Kasittelija {
    
    /**
     * Suorittaa ohjelman.
     * 
     * @param kayttoliittyma kayttoliittyma
     */

    private final Kayttoliittyma kayttoliittyma;

    public Kasittelija() {
        this.kayttoliittyma = new Kayttoliittyma();

    }
    
    /**
     * Hakee käyttöliittymältä tiedot ja kutsuu kirjoittajaa.
     * 
     * @throws FileNotFoundException
     * @throws IOException 
     */

    public void suorita() throws FileNotFoundException, IOException {

        File lahdetiedosto = this.kayttoliittyma.kysyLahdeTiedosto();
        File kohdetiedosto = this.kayttoliittyma.kysyKohdeTiedosto();

        PKlukija lukija = new PKlukija(lahdetiedosto);
        RivitettyPK rivitetty = lukija.tunnistaRivit();
        rivitetty.setRivitetynLeveys(kayttoliittyma.kysyLeveys());

        Kirjoittaja kirjoittaja = new Kirjoittaja(kohdetiedosto, rivitetty);
        kirjoittaja.kirjoitaValmisPK();
        
    }

}
