package pkmuotoilija.domain;

import javax.swing.SwingUtilities;
import pkmuotoilija.UI.Kayttoliittyma;

/**
 * 
 * Luokka luo käyttöliittymän ja käynnistää sen.
 * 
 * @author tskarvon
 */

public class Main {

    public static void main(String[] args) throws Exception {

        Kayttoliittyma kayttis = new Kayttoliittyma();
        SwingUtilities.invokeLater(kayttis);

    }
}
