package pkmuotoilija.domain;

/*
Tätä on käytetty manuaaliseen testaamiseen.
*/

import java.io.File;
import java.io.FileWriter;

import pkmuotoilija.domain.rivit.*;

public class Main 
{
    public static void main( String[] args ) throws Exception 
    {
        PKlukija pk = new PKlukija(new File("src/test/java/pkmuotoilija/domain/testiPKt/testi2.txt"));
        File f = new File("src/main/java/pkmuotoilija/testi.txt");
        FileWriter t = new FileWriter(f);

        PaikkaRivi rivi = new PaikkaRivi("PAIKKA: asfagasd agdsa fa asdaga sfa agasgasfafsabga");
        rivi.formatoiRivi(80);
        t.write(rivi.getSisalto());
        t.close();

        
    }
}
