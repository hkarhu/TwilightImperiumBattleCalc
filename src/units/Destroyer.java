package units;

public class Destroyer extends Unit {
	
	public Destroyer() {
		winValue = 9;
		antiFighterBarrage = true;
	}
	
	public void addHylarVAssaultLasers(){
		winValue = 8;
	}

	public int getShipValue(){
		return 10 - winValue;
	}
	
	@Override
	public String toString() {
		return "Destroyer";
	}
	
	public void addHylarVAssaultLaser(){ winValue -= 1; }
	public void addAutomatedDefenseTurrets(){ automatedDefenseTurrets = true; }
	
}
