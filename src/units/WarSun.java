package units;

public class WarSun extends Unit {

	public WarSun() {
		winValue = 3;
		numDice = 3;
		hp = 2;
		bombardment = true;
		capacity = 6;
	}
	
	public int getShipValue(){
		return 10 - winValue;
	}
	
	@Override
	public String toString() {
		if(hp <= 1) return "WarSun (damaged)";
		return "WarSun";
	}
	
}
