package units;

public class Fighter extends Unit {

	public Fighter() {
		winValue = 9;
		carriable = true;
	}
	
	public int getShipValue(){
		return 10 - winValue;
	}
	
	@Override
	public String toString() {
		return "Fighter";
	}
	
	public void addCybernetics(){ winValue -= 1; }
	public void addAdvancedFighters(){ winValue -= 1; }
	
}
