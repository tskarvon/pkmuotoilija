package pkmuotoilija.UI;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.io.File;
import java.util.Scanner;

/**
 * Käyttöohjevalikko, avataan varsinaisen käyttöliittymän yläosasta.
 * 
 * @author tskarvon
 */

public class OhjeValikko implements Runnable {

    private JFrame frame;

    @Override
    public void run() {
        frame = new JFrame("Pöytäkirjamuotoilija - käyttöhjeet");
        frame.setPreferredSize(new Dimension(600, 500));
        Dimension ruutu = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setLocation(300 + (int) ruutu.getWidth() / 2 - (int) ruutu.getWidth() / 2, (int) ruutu.getHeight() / 2 - (int) ruutu.getHeight() / 3);
        frame.setVisible(true);
    }
    
    /**
     * 
     * Luodaan käyttöohjekenttä, jonka teksti haetaan kayttohjeet.txt-tiedostosta.
     * 
     * @param container 
     */

    private void luoKomponentit(Container container) {

        String teksti = "";
        JTextArea tekstiAlue = new JTextArea(teksti);
        tekstiAlue.setLineWrap(true);
        tekstiAlue.setWrapStyleWord(true);

        try {
            teksti = new Scanner(new File("/src/main/java/pkmuotoilija/UI/kayttohjeet.txt")).useDelimiter("\\Z").next();
            tekstiAlue.setText(teksti);
        } catch (Exception FileNotFoundException) {
            teksti = "Ongelma tiedoston kayttohjeet.txt lukemisessa! Virhe: " + FileNotFoundException.getMessage();
            tekstiAlue.setText(teksti);
            tekstiAlue.setForeground(Color.red);
        }

        JScrollPane rullaus = new JScrollPane(tekstiAlue);
        container.add(rullaus);
    }

}
