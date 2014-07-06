import java.util.HashMap;
import java.util.Vector;

import Dice.D10;
import units.Carrier;
import units.Cruiser;
import units.Destroyer;
import units.Dreadnought;
import units.Fighter;
import units.GroundForce;
import units.Unit;
import units.WarSun;


public class Battler {

	private static HashMap<String, Integer> attackerSurvivorStatistics;
	private static HashMap<String, Integer> defenderSurvivorStatistics;
	
	private static int iterations = 500000;
	
	public static void main(String[] args) {
		
		int draws=0;
		
		int defenderScore=0;
		attackerSurvivorStatistics = new HashMap<>();
		
		int attackerScore=0;
		defenderSurvivorStatistics = new HashMap<>();

		for(int i=0; i < iterations; i++){

			Vector<Unit> attackers = new Vector<>();
			Vector<Unit> defenders = new Vector<>();

			attackers.add(new WarSun());
//			attackers.add(new Dreadnought(1));
			attackers.add(new Carrier());
//			attackers.add(new Destroyer());
//			attackers.add(new Dreadnought());
			//attackers.add(new Dreadnought());
//			attackers.add(new Cruiser());
//			attackers.add(new Cruiser());
			//attackers.add(new Cruiser());
//			attackers.add(new Cruiser());
//			attackers.add(new Cruiser());
//			attackers.add(new Cruiser());			
//			attackers.add(new Carrier());
//			attackers.add(new Destroyer());
//			attackers.add(new Destroyer());
//			ttackers.add(new Destroyer());
//			attackers.add(new Destroyer());
//			attackers.add(new Destroyer());
//			attackers.add(new Destroyer());
//			attackers.add(new Fighter());
			attackers.add(new Fighter());
			
//			defenders.add(new WarSun());
//			defenders.add(new Destroyer());
//			defenders.add(new Destroyer());
//			defenders.add(new Destroyer());
//			defenders.add(new Destroyer());
//			defenders.add(new Destroyer());
//			defenders.add(new Fighter());
//			defenders.add(new Fighter());
//			defenders.add(new Fighter());
			defenders.add(new Dreadnought());
			defenders.add(new Dreadnought());
//			defenders.add(new Fighter());
//			defenders.add(new Carrier());
//			defenders.add(new Fighter());
//			defenders.add(new Fighter());
//			defenders.add(new Fighter());
//			defenders.add(new Fighter());
//			defenders.add(new Carrier());
//			defenders.add(new Cruiser());
//			defenders.add(new Cruiser());
//			defenders.add(new Cruiser());
			
			attackers.add(new GroundForce());
			attackers.add(new GroundForce());
			attackers.add(new GroundForce());
			attackers.add(new GroundForce());
			attackers.add(new GroundForce());
			attackers.add(new GroundForce());
			attackers.add(new GroundForce());
			attackers.add(new Mech);
			
			defenders.add(new GroundForce());
			defenders.add(new GroundForce());
			defenders.add(new GroundForce());
			defenders.add(new GroundForce());
			
			for(Unit u : attackers){
				//u.addAutomatedDefenseTurrets();
//				u.addHylarVAssaultLaser();
				u.addCybernetics();
			}
			
			for(Unit u : defenders){
				//u.addCybernetics();
				//u.addHylarVAssaultLaser();
			}
			
			doPDSFire(0, false, false, attackers, false);
			doPDSFire(0, false, false, defenders, false);
			
			doPreBattleStuff(attackers, defenders);
			
			while(!attackers.isEmpty() && !defenders.isEmpty()){
				int attackingHits = calculateTotalHits(attackers);
				int defendingHits = calculateTotalHits(defenders);
				removeUnits(attackers, defendingHits);
				removeUnits(defenders, attackingHits);
			}

			if(attackers.isEmpty() && defenders.isEmpty()){
				//System.out.println("Draw");
				draws++;
			} else if(attackers.isEmpty()){
				//System.out.println("Defenders win!");
				defenderScore++;
				if(defenderSurvivorStatistics.containsKey(getFleetHash(defenders))){
					defenderSurvivorStatistics.put(getFleetHash(defenders), defenderSurvivorStatistics.get(getFleetHash(defenders)) + 1);
				} else {
					defenderSurvivorStatistics.put(getFleetHash(defenders), 1);
				}
			} else if(defenders.isEmpty()){
				//System.out.println("Attackers win!");
				attackerScore++;
				if(attackerSurvivorStatistics.containsKey(getFleetHash(attackers))){
					attackerSurvivorStatistics.put(getFleetHash(attackers), attackerSurvivorStatistics.get(getFleetHash(attackers)) + 1);
				} else {
					attackerSurvivorStatistics.put(getFleetHash(attackers), 1);
				}
			}

		}

		System.out.println("Attackers: " + attackerScore + " Defenders: " + defenderScore + " Draws: " + draws);
		System.out.println("Possibility for attackers winning: " + ((float)attackerScore/(float)(iterations))*100 + "%");
		for(String k : attackerSurvivorStatistics.keySet()){
			System.out.println(k + " " + ((float)attackerSurvivorStatistics.get(k)/(float)(iterations))*100 + "%");
		}
		System.out.println("Possibility for defenders winning: " + ((float)defenderScore/(float)(iterations))*100 + "%");
		for(String k : defenderSurvivorStatistics.keySet()){
			System.out.println(k + " " + ((float)defenderSurvivorStatistics.get(k)/(float)(iterations))*100 + "%");
		}
		System.out.println("Possibility for a draw: " + ((float)draws/(float)(iterations))*100 + "%");
		
	}
	
	private static void doPDSFire(int numPDS, boolean hasMagenDefenseGrid, boolean hasGravitonLaserSystem, Vector<Unit> fleet, boolean hasMenouveringJets){
		
		int winValue = 6;
		if(hasMagenDefenseGrid) winValue -= 1; 
		
		int hits = 0;
		
		for(int i=0; i<numPDS; i++){
			if(D10.rollAgainst(winValue)){
				hits++;
			} else if(hasGravitonLaserSystem){ //Reroll once
				if(D10.rollAgainst(winValue)){
					hits++;
				}
			}
		}
		
		removeUnits(fleet, hits);
		
	}
	
	private static int getAntiFighterBarrageHits(Vector<Unit> fleet){
		int hits = 0;
		
		for(Unit u : fleet){
			if(u.hasAutomatedDefenseTurrets() && u.hasAntiFighterBarrage()){
				for(int r=0; r<3; r++){
					if(D10.rollAgainst(u.getWinValue() - 2)){
						hits++;
					}
				}	
			} else if(u.hasAntiFighterBarrage()){
				hits += u.getHits();
				hits += u.getHits();
			}
		}
		
		return hits;
	}
	
	private static void doPreBattleStuff(Vector<Unit> fleet1, Vector<Unit> fleet2){
		
		int fleet1Hits = 0, fleet2Hits = 0;
		
		//AntiFighterBarrage
		fleet1Hits = getAntiFighterBarrageHits(fleet1);
		fleet2Hits = getAntiFighterBarrageHits(fleet2);
		
		removeFighters(fleet1, fleet2Hits);
		removeFighters(fleet2, fleet1Hits);
		
		//Dreadnoughtasdasd
		fleet1Hits = 0;
		fleet2Hits = 0;
		
		for(Unit u : fleet1){
			if(u.hasAssaultCannon()){
				fleet1Hits += u.getHits();
			}
		}
		for(Unit u : fleet2){
			if(u.hasAssaultCannon()){
				fleet2Hits += u.getHits();
			}
		}
		
		removeUnits(fleet1, fleet2Hits);
		removeUnits(fleet2, fleet1Hits);
		
	}
	
	private static int calculateTotalHits(Vector<Unit> units){
		int hits = 0;
		for(Unit u : units){
			hits += u.getHits();
		}
		return hits;
	}
	
	private static String getFleetHash(Vector<Unit> units){
		String fleetHash = " ";
		for(Unit u : units){
			fleetHash += u.toString();
		}
		return fleetHash;		
	}
	
	private static void removeUnits(Vector<Unit> units, int numHits){
		int hitsTaken = 0;
		while(hitsTaken < numHits && !units.isEmpty()){
			
			Unit removableUnit = units.get(0);
			int lowValue = units.get(0).getShipValue();
			
			//Find the lowest value unit
			for(Unit u : units){
				if(u.getShipValue() < lowValue){
					lowValue = u.getShipValue();
					removableUnit = u;
				}
			}
			
			removableUnit.takeHit();
			hitsTaken++;
			
			if(removableUnit.willBeRemoved()){
				//System.out.println("Removing" + removableUnit);
				units.remove(removableUnit);
			}
			
		}
	}
	
	private static void removeFighters(Vector<Unit> units, int numHits){
		
		int hitsTaken = 0;
		Vector<Unit> fighters = new Vector<Unit>();

		for(Unit u : units){
			if(u.toString() == "Fighter"){
				fighters.add(u);
				hitsTaken ++;
			}
			
			if(hitsTaken >= numHits) break;
		}
		
		units.removeAll(fighters);
		
	}


	
}
