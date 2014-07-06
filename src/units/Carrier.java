package units;

public class Carrier extends Unit {

	public Carrier() {
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
	
}
