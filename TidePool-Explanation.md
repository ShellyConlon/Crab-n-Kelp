Crab eats Kelp Explained: Week 7 

The Class:
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


Week 8-10: 
WEEK 8 UML CLASS DIAGRAM (*NEW* = added in Week 8):

+---------------------------------------+          +----------------------------------+
| TidePool                              |          | Crab                      *NEW*  |
+---------------------------------------+          +----------------------------------+
| size : int                            |          | row : int                 *NEW*  |
| stepCount : int                       |   has    | col : int                 *NEW*  |
| kelpGrid : boolean[][]                | -------> | energy : int              *NEW*  |
| crabs : ArrayList of Crab      *NEW*  |   many   | alive : boolean           *NEW*  |
+---------------------------------------+          +----------------------------------+
| TidePool(gridSize, startCrabs,        |          | Crab(startRow, startCol,  *NEW*  |
|          kelpSpacing)          *NEW*  |          |      startEnergy)                |
| stepSimulation() : void               |          +----------------------------------+
+---------------------------------------+

SIMULATION, STATE DIAGRAM & UI (*NEW* = added in Week 8):

2D Grid Layout (Key: K = Kelp, C = Crab, . = Water)
+---+---+---+---+
| C | . | K | . |    *NEW*: Crabs (C) spawn on the grid at starting coordinates.
+---+---+---+---+
| . | K | C | K |    Crab State Diagram (*NEW*):
+---+---+---+---+    [ Spawned ] ---> ( ALIVE: energy = 4 )

Rules Added in Week 8:
- Constructor loops through startingCrabs and adds new Crab objects into the ArrayList.

Week 8, I added the Crab class with position and energy fields and stored multiple Crab objects inside an ArrayList in TidePool. This allowed the simulation to place living agents onto the 2D kelp grid.

WEEK 9 UML CLASS DIAGRAM (*NEW* = added in Week 9):

+---------------------------------------+          +----------------------------------+
| TidePool                              |          | Crab                             |
+---------------------------------------+          +----------------------------------+
| size : int                            |          | row : int                        |
| stepCount : int                       |   has    | col : int                        |
| regrowRate : double            *NEW*  | -------> | energy : int                     |
| kelpGrid : boolean[][]                |   many   | status : String           *NEW*  |
| crabs : ArrayList of Crab             |          | alive : boolean                  |
+---------------------------------------+          +----------------------------------+
| TidePool(gridSize, startCrabs,        |          | Crab(startRow, startCol,         |
|          kelpSpacing)                 |          |      startEnergy)                |
| stepSimulation() : void               |          | updateCrab(hasKelp,       *NEW*  |
+---------------------------------------+          |   gridSize) : boolean            |
                                                   +----------------------------------+

SIMULATION, STATE DIAGRAM & UI (*NEW* = added in Week 9):

2D Grid Layout (Key: K = Kelp, H = Hungry Crab, F = Full Crab, D = Dead Crab, . = Water)
+---+---+---+---+
| F | . | H | . |    *NEW* Crab State Diagram:
+---+---+---+---+                     eat kelp (+4, energy >= 6)
| . | D | . | K |                   +----------------------------+
+---+---+---+---+                   |                            v
                     +----------+ --+               *NEW*   +----------+
Rules Added (Wk 9):  |  HUNGRY  | <------------------------ |   FULL   |
- Eat kelp: +4 en.   | (en 1-5) |   no kelp (-1, en < 6)    | (en >=6) |
- No kelp:  -1 en.   +----------+                           +----------+
- Kelp eaten -> .         |
                          | no kelp (-1, energy <= 0)
                          v
                     +----------+
                     |   DEAD   | *NEW*
                     | (en <=0) |
                     +----------+


Week 9, I added the status field and the updateCrab() method so crabs gain 4 energy from eating kelp, lose 1 energy on empty water, and switch between "HUNGRY", "FULL", and "DEAD". This connected the crabs to the grid so they consume kelp and starve when food runs out.

WEEK 10 UML CLASS DIAGRAM (With Exam Question #2 Markers (1) and (2)):

+---------------------------------------+
| TidePoolUI                     *NEW*  |
+---------------------------------------+
| pool : TidePool                *NEW*  |
| crabsInput, kelpInput          *NEW*  |
+---------------------------------------+
| resetSimulation() : void       *NEW*  |
| drawGrid() : void              *NEW*  |
+---------------------------------------+
                   |
                   | creates & displays
                   v
+---------------------------------------+  <-- (2) CODE #2 LIVES HERE
| TidePool                              |
+---------------------------------------+
| size : int                            |          +----------------------------------+ <-- (1) CODE #1 LIVES HERE
| stepCount : int                       |          | Crab                             |
| regrowRate : double                   |   has    +----------------------------------+
| kelpGrid : boolean[][]                | -------> | row : int                        |
| crabs : ArrayList of Crab             |   many   | col : int                        |
+---------------------------------------+          | energy : int                     |
| TidePool(gridSize, startCrabs,        |          | status : String                  |
|          kelpSpacing)                 |          | alive : boolean                  |
| stepSimulation() : void               |          +----------------------------------+
+---------------------------------------+          | Crab(startRow, startCol,         |
                                                   |      startEnergy)                |
                                                   | updateCrab(hasKelp,              |
                                                   |   gridSize) : boolean  *MOVES*   |
                                                   +----------------------------------+


SIMULATION, STATE DIAGRAM & UI (*NEW* = added in Week 10):

Minimal User Interface Window (*NEW*):
+--------------------------------------------------------+
| Start Crabs: [ 5 ]  Kelp Spacing: [ 2 ]                |
| [ Start / Reset ]   [ Next Step ]                      |
+--------------------------------------------------------+
|  F(7)  --moves-->   .        K        .                |
|   .                 K       H(3) -->  K                |
|   .                 D        .        .                |
+--------------------------------------------------------+
| Step: 4 | Key: Green=Kelp, Orange=Crab, Gray=Dead      |
+--------------------------------------------------------+

Rules Added in Week 10 (*NEW*):
- Living crabs move 1 column right each step (col + 1) and wrap to the next row at the edge.
- User chooses starting crabs and kelp spacing in the UI before stepping the simulation.
                                   
Week 10, I added grid movement with edge wrapping inside updateCrab() and built TidePoolUI with input controls for starting crabs and kelp spacing. This completed the zero-player simulation so crabs actively roam the grid in search of regrowing kelp while the user watches.
