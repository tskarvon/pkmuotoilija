package pkmuotoilija.domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import pkmuotoilija.domain.rivit.*;

/**
 * 
 * Kirjoittaa muotoillun pöytäkirjan haluttuun tiedostoon.
 * 
 * @author tskarvon
 */

public class Kirjoittaja {

    public Kirjoittaja() {

    }
    
    /**
     * 
     * Päämetodi, joka hoitaa kirjoitustyön sujumisen.
     * 
     * @param rivitetty kokoelma rivejä, joiden tyypit on määritelty
     * @param tiedot kirjoittamisessa tarvittavia käyttäjältä tulleita tietoja
     * @throws IOException 
     */

    public void kirjoitaValmisPK(RivitettyPK rivitetty, PKtiedot tiedot) throws IOException {

        FileWriter kirjoittaja = new FileWriter(tiedot.getKohdetiedosto());

        for (int i = 0; i < rivitetty.getPituus(); i++) {           
            kirjoitaRivi(kirjoittaja, tiedot, rivitetty.getRivi(i));
        }

        //kirjoitaLoppuTiedot(kirjoittaja);
        kirjoittaja.close();

    }
    /**
     * 
     * Metodi kirjoittaa yksittäisen rivin. Kutsujana toimii metodi kirjoitaValmisPK
     * 
     * @param kirjoittaja FileWriter, johon tallennettu kohdetiedosto
     * @param tiedot kirjoittamiseen tarvittavia käyttäjältä tulleita tietoja
     * @param rivi kirjoitettava rivi
     * @throws IOException 
     */

    private void kirjoitaRivi(FileWriter kirjoittaja, PKtiedot tiedot, Rivi rivi) throws IOException {
        if (!tiedot.getSailytaOmaRivitys() && rivi.getClass() != TyhjaRivi.class) {
            kirjoittaja.write(rivi.getSisalto());
            kirjoittaja.write("\n");
        } else if (tiedot.getSailytaOmaRivitys()) {
            kirjoittaja.write(rivi.getSisalto());
            kirjoittaja.write("\n");
        }

    }

    // keskeneräinen

    /*private void kirjoitaLoppuTiedot(FileWriter kirjoittaja) throws IOException {
     if (tiedot.getLisaaHyvaksyttyKokouksessa()) {
     kirjoittaja.write("\n\nHyväksytty kokouksessa __/____");
     }

     if (!tiedot.getPJ().isEmpty() || !tiedot.getSihteeri().isEmpty() || !this.tiedot.getVirat().isEmpty()) {
     kirjoittaja.write("\n\n\n\n\n\n");
     }

     if (!tiedot.getPJ().isEmpty()) {
     kirjoittaja.write("   ___________________________________");
     }
     } */
    
    private String alleviivaus() {
        return "___________________________________";
    }

}
