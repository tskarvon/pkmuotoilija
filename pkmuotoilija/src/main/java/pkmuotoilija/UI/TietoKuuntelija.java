package pkmuotoilija.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;

public class TietoKuuntelija implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent ae) {
        SwingUtilities.invokeLater(new OhjeValikko());        
    }
}


