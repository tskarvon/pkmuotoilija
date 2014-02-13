package pkmuotoilija.domain;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Käynnistää tarvittavat osat logiikasta oikeassa kohdassa.
 * 
 * @author tskarvon
 */

public class Kasittelija {

    public Kasittelija() {

    }

    /**
     * 
     * Kuuntelija kutsuu tätä metodia saatuaan hyväksyttävät tiedot käyttäjältä
     * käyttöliittymän kautta ja käyttäjän painettua "Muotoile"-painiketta.
     * 
     * @param tiedot pöytäkirjan muotoiluun liittyvät tiedot
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void suorita(PKtiedot tiedot) throws FileNotFoundException, IOException {

        PKlukija lukija = new PKlukija();
        RivitettyPK rivitetty = lukija.tunnistaRivit(tiedot);
        rivitetty.formatoiRivit();
        
        Kirjoittaja kirjoittaja = new Kirjoittaja();
        kirjoittaja.kirjoitaValmisPK(rivitetty, tiedot);

    }

}
