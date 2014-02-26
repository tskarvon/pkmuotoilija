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

    private PKtiedot tiedot;
    private final JTextField leveysKentta;
    private final JLabel leveysvirheKentta;
    private final JLabel tiedostovirheKentta;
    private final JCheckBox hyvaksyttyboksi;
    private final JCheckBox omarivitysboksi;

    /**
     *
     * @param tiedot olio, johon käyttäjän syöttämiä tietoja tallennetaan
     * @param leveysKentta kenttä, josta leveys otetaan
     * @param leveysvirheKentta kenttä, johon mahdolliset virheet leveydessä
     * ilmoitetaan
     * @param tiedostovirheKentta kenttä, johon mahdolliset virheet tiedostoissa
     * ilmoitetaan
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

        this.tiedot.setLisaaHyvaksyttyKokouksessa(this.hyvaksyttyboksi.isSelected());
        this.tiedot.setSailytaOmaRivitys(this.omarivitysboksi.isSelected());

        int leveys = 0;
        Kasittelija kasittelija = new Kasittelija();

        try {
            leveys = Integer.parseInt(this.leveysKentta.getText());
            if (leveys < 60 || leveys > 200) {
                leveys = 80;
                throw new NumberFormatException();
            }
            this.tiedot.setLeveys(leveys);
            try {
                kasittelija.suorita(this.tiedot); // tämä aiheuttaa FileNotFoundExceptionin
                nollaaTiedot();
                this.leveysvirheKentta.setText("");
                this.tiedostovirheKentta.setForeground(Color.green);
                this.tiedostovirheKentta.setText("Muotoilu onnistui!");
            } catch (Exception FileNotFoundException) {
                this.tiedostovirheKentta.setText("Valitsemaasi tiedostoa ei ole olemassa!");
            }
        } catch (Exception NumberFormatException) {
            this.leveysvirheKentta.setText("Syötä leveydeksi kokonaisluku väliltä 60-200!");
        }

    }

    private void nollaaTiedot() {
        this.tiedot = new PKtiedot(this.tiedot.getLahdetiedosto(), this.tiedot.getKohdetiedosto());
    }
}
