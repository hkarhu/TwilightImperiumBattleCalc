package units;

public class Dreadnought extends Unit {

	public Dreadnought(int winPlus) {
		winValue = 5;
		bombardment = true;
		hp = 2;
	}
	
	public Dreadnought(){
		this(0);
	}
	
	public int getShipValue(){
		return 10 - winValue;
	}
	
	@Override
	public String toString() {
		if(hp <= 1) return "Dreadnought (damaged)";
		return "Dreadnought";
	}
	
	public void addAssaultCannon(){ assaultCannon = true; }
	
}
