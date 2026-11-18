
import java.util.ArrayList;

public class TidePool {
	int size;                      //Variable
	int stepCount;
	double regrowRate;
	
	boolean[][] kelpGrid;          // 2D boolean array
	ArrayList<Crab> crabs;
	
public TidePool(int gridSize, int startingCrabs, int kelpSpacing) {
	this.size = gridSize;
	this.stepCount = 0;
	this.regrowRate = 0.25;
	this.kelpGrid = new boolean[gridSize][gridSize];
	this.crabs = new ArrayList<Crab>();
	
	for (int r = 0; r < gridSize; r++) {        // Loops & Nested Loops
		for (int c= 0; c < gridSize; c++) {
			if ((r + c) % kelpSpacing ==0) {
				kelpGrid[r][c] = true;
			}
		}
	}
	
	for (int i = 0; i < startingCrabs; i++) {
		int startRow = i % gridSize;
		int startCol = (i *2) % gridSize;
		Crab c = new Crab(startRow, startCol, 4);           //Object
		crabs.add(c);
	}
}
                                     //Advances the tide pool simulation by 1 step
public void stepSimulation() {
	 stepCount = stepCount + 1;
	 
	 for (int i = 0; i < crabs.size(); i++ ) {
		 Crab current = crabs.get(i);
		 if (current.alive) {
			 int oldRow = current.row;
			 int oldCol = current.col;
			 boolean foundKelp = kelpGrid[oldRow][oldCol];
			 
			                                         // Call method with parameters and return value
			 boolean survived = current.updateCrab(foundKelp,  size);
			 
			 if (foundKelp && survived) {
				 kelpGrid[oldRow][oldCol] = false;
			 }
		 }
	 }
	                                                // Regrow kelp on top and middle rows every 3 steps
	 if (stepCount % 3 == 0) {
		 for (int c= 0; c < size; c++) {
			 kelpGrid[stepCount % size][c]	= true;
		 }
	 } 
  }
	
}
