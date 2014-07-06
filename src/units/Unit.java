package units;

import java.util.Vector;

import Dice.D10;

public abstract class Unit {

	protected int winValue = 0;
	protected int numDice = 1;
	protected int capacity = 0;
	protected int hp = 1;
	protected boolean bombardment = false;
	protected boolean antiFighterBarrage = false;
	protected boolean carriable = false;
	protected boolean assaultCannon = false;
	protected boolean automatedDefenseTurrets = false;
	protected Vector<Unit> unitsCarried;
	
	public int getHits(){
		int hits = 0;
		for(int i=0; i < numDice; i++){
			if(D10.rollAgainst(winValue)){
				hits++;
			}
		}
		return hits;
	}
	
	public boolean willBeRemoved(){
		return hp <= 0;
	}
	
	public void takeHit(){
		hp--;
	}

	public int getWinValue(){
		return winValue;
	}
	
	public abstract int getShipValue();
	public abstract String toString();
	
	public boolean hasAssaultCannon(){
		return assaultCannon;
	}
	
	public boolean hasAntiFighterBarrage(){
		return antiFighterBarrage;
	}

	public boolean hasAutomatedDefenseTurrets() {
		return automatedDefenseTurrets;
	}
	
	//Upgrades
	public void addCybernetics(){ winValue -= 0; }
	public void addAdvancedFighters(){ winValue -= 0; }
	public void addHylarVAssaultLaser(){ winValue -= 0; }
	public void addGenSynthesis(){ winValue -= 0; }
	//public void addMagenDefenseGrid(){ winValue -=0; };
	public void addAutomatedDefenseTurrets(){}
	public void addDuraniumArmor(){}
	public void addAssaultCannon(){}
	
}
