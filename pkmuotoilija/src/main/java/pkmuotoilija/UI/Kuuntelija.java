package pkmuotoilija.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JCheckBox;

import pkmuotoilija.domain.*;

/**
 * Luokka kuuntelee käyttöliittymän Muotoile-nappia.
 * 
 * @author tskarvon
 */

public class Kuuntelija implements ActionListener {

    private final PKtiedot tiedot;
    private final JTextField leveysKentta;
    private final JLabel leveysvirheKentta;
    private final JLabel tiedostovirheKentta;
    private final JCheckBox hyvaksyttyboksi;
    private final JCheckBox omarivitysboksi;
    
    /**
     * 
     * @param tiedot olio, johon käyttäjän syöttämiä tietoja tallennetaan
     * @param leveysKentta kenttä, josta leveys otetaan
     * @param leveysvirheKentta kenttä, johon mahdolliset virheet leveydessä ilmoitetaan
     * @param tiedostovirheKentta kenttä, johon mahdolliset virheet tiedostoissa ilmoitetaan
     * @param hyvaksyttyboksi kenttä, johon muotoilun onnistumisesta ilmoitetaan
     * @param omarivitysboksi
     */

    public Kuuntelija(PKtiedot tiedot, JTextField leveysKentta, JLabel leveysvirheKentta, JLabel tiedostovirheKentta, JCheckBox hyvaksyttyboksi,
            JCheckBox omarivitysboksi) {
        this.tiedot = tiedot;
        this.leveysKentta = leveysKentta;
        this.leveysvirheKentta = leveysvirheKentta;
        this.tiedostovirheKentta = tiedostovirheKentta;
        this.hyvaksyttyboksi = hyvaksyttyboksi;
        this.omarivitysboksi = omarivitysboksi;

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
      
        this.leveysvirheKentta.setText("");
        this.tiedostovirheKentta.setText("");
        this.tiedostovirheKentta.setForeground(Color.red);

        int leveys = 0;

        try {
            leveys = Integer.parseInt(this.leveysKentta.getText());
        } catch (Exception NumberFormatException) {
            this.leveysvirheKentta.setText("Syötä leveydeksi kokonaisluku väliltä 40-200!");
        }

        try {
            if (leveys < 40 || leveys > 200) {
                this.leveysvirheKentta.setText("Syötä leveydeksi kokonaisluku väliltä 40-200!");
            } else {
                
                this.leveysvirheKentta.setText("");
                this.tiedot.setLeveys(leveys);
                Kasittelija kasittelija = new Kasittelija();
                kasittelija.suorita(this.tiedot);
                this.tiedostovirheKentta.setForeground(Color.green);
                this.tiedostovirheKentta.setText("Muotoilu onnistui!");
            }
        } catch (Exception FileNotFoundException) {
            this.tiedostovirheKentta.setText("Valitsemaasi tiedostoa ei ole olemassa!");
        }

    }
}
