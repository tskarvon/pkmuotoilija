package pkmuotoilija.domain;

import java.util.ArrayList;

import pkmuotoilija.domain.rivit.*;

public class RivitettyPK {
    
    private final ArrayList<Rivi> rivit;
    private final int suurinYlakohta;
    private final int suurinAlakohta;
    
    public RivitettyPK(int suurinYlakohta, int suurinAlakohta) {    
        this.rivit = new ArrayList<Rivi>();
        this.suurinYlakohta = suurinYlakohta;
        this.suurinAlakohta = suurinAlakohta;      
    }
    
    public void lisaaRivi(Rivi rivi) {
        this.rivit.add(rivi);        
    }
    
    public Rivi getRivi(int i) {        
        return this.rivit.get(i);       
    }
    
    public int getPituus() {
        return this.rivit.size();
    }
    
    // Metodi manuaaliseen testaukseen
    public void print() {
        
        for(Rivi rivi : this.rivit) {
            rivi.printR();
            System.out.println("  " + rivi);
        }
        
    }
      
}
