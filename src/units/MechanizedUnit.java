package units;

public class MechanizedUnit extends Unit {
 
	public MechanizedUnit() {
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
