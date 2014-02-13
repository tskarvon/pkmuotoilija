package pkmuotoilija.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import pkmuotoilija.domain.*;

/**
 * Kuuntelee ja luo lähde- ja kohdetiedoston valitsemisessa tarvittavat
 * napit ja käyttöliittymät.
 * 
 * @author tskarvon
 */

public class TiedostoKuuntelija implements ActionListener {

    private final JButton sijainti;
    private final JLabel tiedostoKentta;
    private final PKtiedot tiedot;
    private final String kumpiTiedosto;
    
    /**
     * 
     * @param tiedot olio, johon tiedostot tallennetaan
     * @param sijainti tiedostohakukäyttöliittymän sijainti ruudulla
     * @param tiedostoKentta kenttä, johon valitun tiedoston nimi syötetään
     * @param kumpiTiedosto onko kyse lähde- vai kohdetiedostosta
     */

    public TiedostoKuuntelija(PKtiedot tiedot, JButton sijainti, JLabel tiedostoKentta, String kumpiTiedosto) {
        this.tiedot = tiedot;
        this.sijainti = sijainti;
        this.tiedostoKentta = tiedostoKentta;
        this.kumpiTiedosto = kumpiTiedosto;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        final JFileChooser tiedostoValitsin = new JFileChooser();
        FileNameExtensionFilter tiedostoSuodatin = new FileNameExtensionFilter("*.txt", "txt");
        tiedostoValitsin.setFileFilter(tiedostoSuodatin);

        int returnVal = tiedostoValitsin.showOpenDialog(this.sijainti);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            this.tiedostoKentta.setText(tiedostoValitsin.getSelectedFile().getAbsolutePath());
            this.tiedostoKentta.setToolTipText(this.tiedostoKentta.getText());
        } else if(returnVal == JFileChooser.CANCEL_OPTION) {
            this.tiedostoKentta.setText("...");
            this.tiedostoKentta.setToolTipText("...");
        }
        
        if(kumpiTiedosto.equals("lähde")) {
            this.tiedot.setLahdetiedosto(tiedostoValitsin.getSelectedFile());
        } else if(kumpiTiedosto.equals("kohde")) {
            this.tiedot.setKohdetiedosto(tiedostoValitsin.getSelectedFile());
        }

    }

}
