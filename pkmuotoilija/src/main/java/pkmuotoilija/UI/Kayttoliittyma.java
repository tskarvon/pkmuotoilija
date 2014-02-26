package pkmuotoilija.UI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.accessibility.AccessibleContext;
import javax.swing.JCheckBox;


import pkmuotoilija.domain.*;

/**
 * Ohjelman käyttöliittymä.
 *
 * @author tskarvon
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;

    /**
     * Olio, johon käyttäjän antamia ja myöhemmin annetusta pöytäkirjasta
     * määritettäviä tietoja tallennetaan.
     */
    private final PKtiedot tiedot;

    public Kayttoliittyma() {
        this.tiedot = new PKtiedot();
    }

    @Override
    public void run() {
        frame = new JFrame("Pöytäkirjamuotoilija");
        frame.setPreferredSize(new Dimension(600, 350));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Luodaan käyttöliittymän komponentit.
     *
     * @param container
     */
    private void luoKomponentit(Container container) {
        JButton tietoNappi = new JButton("Käyttöohjeet");
        tietoNappi.addActionListener(new TietoKuuntelija());
        container.add(tietoNappi, BorderLayout.NORTH);
        container.add(luoValikot());
    }
    
    /**
     * Luodaan valintavalikot
     * 
     * @return valikot
     */

    private JPanel luoValikot() {
        JPanel valikot = new JPanel(new GridLayout(5, 2));
        
        JPanel valikkoPaneeli = luoRaksiValikko();
        AccessibleContext valikko = valikkoPaneeli.getAccessibleContext();

        JLabel leveysTeksti = new JLabel("Leveys:");
        JTextField leveysKentta = new JTextField("80");
        leveysKentta.setToolTipText("Leveyden tulee olla kokonaisluku väliltä 40-200.");
        leveysTeksti.setSize(200, 20);

        JPanel virhePaneeli = luoVirheKentta();
        AccessibleContext virheet = virhePaneeli.getAccessibleContext();

        Kuuntelija kuuntelija = new Kuuntelija(this.tiedot, leveysKentta, (JLabel) virheet.getAccessibleChild(0), (JLabel) virheet.getAccessibleChild(1),
                (JCheckBox) valikko.getAccessibleChild(0), (JCheckBox) valikko.getAccessibleChild(1));
        JButton muotoileNappi = new JButton("Muotoile!");
        muotoileNappi.addActionListener(kuuntelija);

        valikot.add(new JLabel("Tee valintoja:"));
        valikot.add(valikkoPaneeli);
        valikot.add(leveysTeksti);
        valikot.add(leveysKentta);
        valikot.add(new JLabel("Valitse lähdetiedosto:"));
        valikot.add(luoTiedostoValikko("lähde"));
        valikot.add(new JLabel("Valitse kohdetiedosto:"));
        valikot.add(luoTiedostoValikko("kohde"));
        valikot.add(virhePaneeli);
        valikot.add(muotoileNappi);
        
        return valikot;
    }

    /**
     * Luodaan raksitettavien laatikoiden komponentit.
     *
     * @return
     */
    private JPanel luoRaksiValikko() {
        JPanel raksiPaneeli = new JPanel(new GridLayout(2, 1));

        JCheckBox hyvaksyttyKokouksessaNappi = new JCheckBox("Hyväksytty kokouksessa __/____");
        hyvaksyttyKokouksessaNappi.setSelected(true);
        JCheckBox sailytaOmaRivitysNappi = new JCheckBox("Säilytä oma rivitys");

        raksiPaneeli.add(hyvaksyttyKokouksessaNappi);
        raksiPaneeli.add(sailytaOmaRivitysNappi);

        return raksiPaneeli;

    }

    /**
     * Luodaan lähde- ja kohdetiedostoja kysyvien laatikoiden komponentit.
     *
     * @param kumpiTiedosto onko kyse lähde- vai kohdetiedostosta
     * @return
     */
    private JPanel luoTiedostoValikko(String kumpiTiedosto) {
        JPanel tiedostoPaneeli = new JPanel(new GridLayout(3, 1));

        JLabel tiedostoKenttaOtsikko = new JLabel("Olet valinnut " + kumpiTiedosto + "tiedoston:");
        JLabel tiedostoKentta = new JLabel("...");
        tiedostoKentta.setToolTipText("...");
        JButton valitseLahdeNappi = new JButton("Valitse " + kumpiTiedosto + "tiedosto");

        TiedostoKuuntelija lahdeKuuntelija = new TiedostoKuuntelija(this.tiedot, valitseLahdeNappi, tiedostoKentta, kumpiTiedosto);
        valitseLahdeNappi.addActionListener(lahdeKuuntelija);

        tiedostoPaneeli.add(tiedostoKenttaOtsikko);
        tiedostoPaneeli.add(tiedostoKentta);
        tiedostoPaneeli.add(valitseLahdeNappi);

        return tiedostoPaneeli;
    }

    /**
     * Luodaan virhe- ja onnistumisilmoitukset sisältävä laatikko.
     *
     * @return
     */
    private JPanel luoVirheKentta() {
        JPanel virhePaneeli = new JPanel(new GridLayout(2, 1));

        JLabel leveysVirheKentta = new JLabel("");
        JLabel tiedostoVirheKentta = new JLabel("");

        leveysVirheKentta.setForeground(Color.red);
        tiedostoVirheKentta.setForeground(Color.red);

        virhePaneeli.add(leveysVirheKentta);
        virhePaneeli.add(tiedostoVirheKentta);

        return virhePaneeli;

    }

    public JFrame getFrame() {
        return frame;
    }

}
