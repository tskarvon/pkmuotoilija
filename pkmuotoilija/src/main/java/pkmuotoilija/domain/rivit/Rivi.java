package pkmuotoilija.domain.rivit;

public class Rivi {
    
    protected String sisalto;
    
    public Rivi(String rivinSisalto) {        
        this.sisalto = rivinSisalto;        
    }
    
    public String getSisalto() {
        
        return this.sisalto;
        
    }    
    
    public String toString() {
        
        return this.sisalto;
        
    }
    
    // Metodi manuaaliseen testaukseen    
    public void printR() {
        
        System.out.print(this.getClass());
        
    }
    
}
