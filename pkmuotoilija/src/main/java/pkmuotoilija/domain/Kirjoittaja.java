package pkmuotoilija.domain;

import java.io.File;
import java.io.FileOutputStream;
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
        boolean onkoAikaOllut = false;
        boolean onkoPaikkaOllut = false;

        for (int i = 0; i < rivitetty.getPituus(); i++) {
            Rivi rivi = rivitetty.getRivi(i);

            if (!tiedot.getSailytaOmaRivitys() && rivi.getClass() != TyhjaRivi.class) {
                kirjoittaja.write(rivi.getSisalto());

                // seuraava tarkistaa, seuraako aikariviä paikkarivi tai paikkariviä aikarivi
                // ja säätää rivityksen tämän mukaan
                if (rivi.getClass() == AikaRivi.class || rivi.getClass() == PaikkaRivi.class) {
                    for (int j = i + 1; j < rivitetty.getPituus(); j++) {
                        Rivi APrivi = rivitetty.getRivi(j);
                        if (APrivi.getClass() == AikaRivi.class || APrivi.getClass() == PaikkaRivi.class) {
                            break;
                        } else if (APrivi.getClass() != TyhjaRivi.class) {
                            kirjoittaja.write("\n");
                            break;
                        }
                    }
                    // seuraa tarkistaa, seuraako osallistujariviä toinen osallistujarivi
                    // vai päättyykö osallistujalista ja säätää rivityksen tämän mukaan
                } else if (rivi.getClass() == OsallistujaRivi.class) {
                    for (int j = i + 1; j < rivitetty.getPituus(); j++) {
                        Rivi Orivi = rivitetty.getRivi(j);
                        if (Orivi.getClass() == OsallistujaRivi.class) {
                            break;
                        } else if (Orivi.getClass() != TyhjaRivi.class) {
                            kirjoittaja.write("\n");
                            break;
                        }
                    }

                } else {
                    kirjoittaja.write("\n");
                }

            } else if (tiedot.getSailytaOmaRivitys()) {
                kirjoittaja.write(rivi.getSisalto());
                kirjoittaja.write("\n");
            }

        }

        kirjoittaja.write(kirjoitaHyvaksytty(tiedot));
        kirjoittaja.write(allekirjoitukset(tiedot));
        kirjoittaja.close();

    }
    
    /**
     * Muotoillun pöytäkirjan loppuun lisättävät kohdat allekirjoituksille.
     * 
     * @param tiedot
     * @return allek allekirjoitukset muotoiltuna merkkijonoon, joka voidaan kirjoittaa
     */

    private String allekirjoitukset(PKtiedot tiedot) {

        String allek = "";

        if (tiedot.onkoVirkoja()) {
            
            // jos leveys on alle 80, laitetaan jokainen allekirjoitus omalle rivilleen
            
            if (tiedot.getLeveys() < 80) {
                for (int i = 0; i < tiedot.getVirat().size(); i++) {
                    if (!tiedot.getVirkailija(i).isEmpty()) {
                        allek += "\n\n\n\n\n\n";
                        allek += "   " + alleviivaus() + "\n";
                        allek += "   " + tiedot.getVirkailija(i) + "\n";
                        allek += "   " + tiedot.getVirka(i) + "";
                    }
                }
                
                // muutoin jokaiselle riville mahtuu kaksi allekirjoitusta
                
            } else {
                int virkojaJaljella = tiedot.getVirkojenMaara();
                int i = 0;
                while (virkojaJaljella > 0) {
                    allek += "\n\n\n\n\n\n";
                    if (virkojaJaljella > 1) {
                        allek += "   " + alleviivaus() + "  " + alleviivaus() + "\n";
                        allek += "   " + tiedot.getVirkailija(i) + valia(38 - tiedot.getVirkailija(i).length()) + tiedot.getVirkailija(tiedot.getSeuraavaVirkailija(i)) + "\n";
                        allek += "   " + tiedot.getVirka(i) + valia(38 - tiedot.getVirka(i).length()) + tiedot.getVirka(tiedot.getSeuraavaVirkailija(i));
                        virkojaJaljella -= 2;
                        i = tiedot.getSeuraavaVirkailija(tiedot.getSeuraavaVirkailija(i));
                    } else if (virkojaJaljella == 1) {
                        allek += "   " + alleviivaus() + "\n";
                        allek += "   " + tiedot.getVirkailija(i) + "\n";
                        allek += "   " + tiedot.getVirka(i) + "";
                        virkojaJaljella--;

                    }
                }
            }
        }

        return allek;

    }
    
    /**
     * Lisätään pöytäkirjan loppuun teksti "Hyväksytty kokouksessa __/____", mikäli näin on valittu
     * 
     * @param tiedot
     * @return
     */

    private String kirjoitaHyvaksytty(PKtiedot tiedot) {
        if (tiedot.getLisaaHyvaksyttyKokouksessa()) {
            return "\n\nHyväksytty kokouksessa __/____";
        } else {
            return "";
        }

    }
    
    /**
     * Alleviivaus allekirjoituksia varten
     * 
     * @return 
     */

    private static String alleviivaus() {
        return "____________________________________";
    }
    
    /**
     * Palautetaan haluttu määrä välilyöntejä
     * 
     * @param montako välilyöntien määrä
     * @return vali välilyöntejä
     */

    private static String valia(int montako) {
        String vali = "";
        for (int i = 0; i < montako; i++) {
            vali = vali + " ";
        }
        return vali;

    }

}
