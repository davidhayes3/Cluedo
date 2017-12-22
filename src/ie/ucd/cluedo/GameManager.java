package ie.ucd.cluedo;

public class GameManager 
{
	
	static Game Cluedo;
	
	public static void main(String[] args) 
	{
		
		System.out.println("NEW GAME\n\n");
		
		Cluedo = new Game();
		
		Cluedo.getNumPlayers();
			
		Cluedo.printMurderDetails();
			
		Cluedo.makePlayers();
		
		Cluedo.getCharacters();
		
		Cluedo.createDeck();
		
		Cluedo.allocateCards();
						
		Cluedo.makeBoard();
		
		Cluedo.gameTurns();

	}

}
