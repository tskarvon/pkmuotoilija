package pkmuotoilija.domain.rivit;

public class LasnaRivi extends Rivi {
    
    public LasnaRivi(String rivinSisalto) {
        super(rivinSisalto);
    }
    
    @Override
    public void formatoiRivi(int leveys) {
        this.sisalto = "Läsnä:";
    }
    
}
