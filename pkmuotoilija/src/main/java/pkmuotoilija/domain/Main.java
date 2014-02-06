package pkmuotoilija.domain;

/* import java.io.File;
import java.io.FileWriter;

import pkmuotoilija.domain.rivit.*; */

public class Main {

    public static void main(String[] args) throws Exception {
        
        Kasittelija kasittelija = new Kasittelija();
        kasittelija.suorita();
        
        
        
        
/*        PKtiedot tiedot = new PKtiedot();
        tiedot.setLeveys(80);
        PKlukija pk = new PKlukija(new File("src/test/java/pkmuotoilija/domain/testiPKt/testi2.txt"), tiedot);
        File f = new File("src/main/java/pkmuotoilija/testi.txt");
        FileWriter t = new FileWriter(f);

        YlakohtaTekstiRivi rivi = new YlakohtaTekstiRivi("Tämä on lyhyt rivi. Paitsi ettei olekaan riittävän pitkä mahtuakseen yhdelle riville!");
        tiedot.setLeveys(45);
        tiedot.setYlakohdanSisennys(1);
        rivi.formatoiRivi(tiedot);
        t.write(rivi.getSisalto());
        t.close(); */

    }
}
