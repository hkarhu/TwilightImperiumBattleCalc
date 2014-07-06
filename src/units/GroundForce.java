package units;

public class GroundForce extends Unit {
 
	public GroundForce() {
		winValue = 9;
		carriable = true;
	}
	
	public int getShipValue(){
		return 10 - winValue;
	}
	
	@Override
	public String toString() {
		return "Ground force";
	}
	
	public void addGenSynthesis(){ winValue -= 1; }
	
}
