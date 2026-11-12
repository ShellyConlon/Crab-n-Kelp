
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
