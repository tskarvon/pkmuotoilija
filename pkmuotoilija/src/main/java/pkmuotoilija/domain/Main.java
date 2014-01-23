package pkmuotoilija.domain;

/*
Tätä on käytetty manuaaliseen testaamiseen.
*/

import java.io.File;

import pkmuotoilija.domain.*;

public class Main 
{
    public static void main( String[] args ) throws Exception 
    {
        PKlukija pk = new PKlukija(new File("src/test/java/pkmuotoilija/domain/testiPKt/testi2.txt"));

        // Tätä käytetty kokonaisten pöytäkirjojen manuaaliseen testaamiseen.
        // Automatisoidut testit näille tulevat myöhemmin.
        pk.tunnistaRivit().print();
        System.out.println("dsahsjsfrjmntkmrst");
        
        
    }
}
