package Dice;

public class D10 {

	static int dieSize = 9;
	
	public static boolean rollAgainst(int winValue){
		int value = (int) (Math.ceil(Math.random()*dieSize)+1);
		//System.out.println("D10 rolled: " + value);
		return value >= winValue;
	}	
}