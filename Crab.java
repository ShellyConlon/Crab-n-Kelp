
public class Crab {
	int row;               //Variables
	int col;
	int energy;
	String status;
	boolean alive;
	
	                       // Constructor
public Crab(int startRow, int startCol, int startEnergy) {
	this.row = startRow;
	this.col = startCol;
	this.energy = startEnergy;
	this.status = "HUNGRY";
	this.alive = true;
}

                            //method with Parameters
public boolean updateCrab(boolean hasKelp, int gridSize) {
	if (hasKelp) {
		this.energy = this.energy + 4;     //Eat Kelp to gain 4 energy
	} else {
		this.energy = this.energy - 1;     //Loose 1 energy searching
	}
	
	                        //Update status for the state diagram
	if (this.energy <= 0) {
		this.alive = false;
		this.status = "DEAD";
	} else if (this.energy >= 6) {
		this.status = "FULL";
	} else {
		this.status = "HUNGRY";
	}
	
	                        //Still Alive? move 1 space right and wrap around the grid
	if (this.alive) {
		this.col = this.col + 1;
		if (this.col >= gridSize) {
			this.col = 0;
			this.row = (this.row + 1) % gridSize;
		}
		
		}
	
	return this.alive;     // Return true if crab survived this step
	}
	
}

