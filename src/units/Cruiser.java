package units;

public class Cruiser extends Unit {
	
	public Cruiser() {
		winValue = 7;
	}
	
	public void addHylarVAssaultLasers(){
		winValue = 6;
	}

	public int getShipValue(){
		return 10 - winValue;
	}
	
	@Override
	public String toString() {
		return "Cruiser";
	}
	
	public void addHylarVAssaultLaser(){ winValue -= 1; }

}