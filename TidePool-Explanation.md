Crab eats Kelp Explained: Week 7 

The Class: ##name the class: public class TidePool
+---------------------------------------+
 TidePool                              
+---------------------------------------+

| size : int  
##stores the width and height of the square board (such as 8 for an 8x8 grid)

| stepCount : int  
##It tracks how many turns have passed since the simulation started.

| kelpGrid : boolean[][]                
##It is a 2D grid of true/false squares where true means kelp is growing on that tile and false means empty water.

+---------------------------------------+
| TidePool(gridSize, kelpSpacing)      
##class and no return type = constructor. When new TidePool(...) runs, it takes those two inputs, builds the 2D kelpGrid array, and places the starting kelp

 
| stepSimulation() : void   
##The : void at the end means it does not return a value. Every time the user clicks "Next Step," this method runs once to add 1 to stepCount and regrow kelp every 3rd turn.


The Simulation:
2D -Layout : Program Behavior:     (Key: K = Kelp, . = Water)      

Kelp Tile State Diagram:
+---+---+---+---+
| K | . | K | . |                              +-------+   every 3 steps   +------+
+---+---+---+---+                              | WATER | 

----------------> | KELP |
| . | K | . | K |                              |false  |                   | true |
+---+---+---+---+                              +-------+                   +------+

On the left is a snapshot of my boolean[][] kelpGrid. The key translates Java booleans into visuals: K means the cell is true and has kelp, while a period (.) means the cell is false and is just empty water. When kelpSpacing is set to 2, my nested for loop uses modulo math (r + c) % 2 == 0 to place kelp on every other square in a checkerboard pattern.

Rules (Week 7):
1. Grid starts with kelp spaced by kelpSpacing.
2. Every 3 steps, a row of water regrows into kelp. Simulation loops across a row and transitions WATER tiles into KELP tiles by setting them to true. There is no arrow going back from KELP to WATER
