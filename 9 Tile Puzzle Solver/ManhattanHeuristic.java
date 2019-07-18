/**
 * This defines the Manhattan heuristic for the 8-Puzzle.
 * @author Steven Bogaerts
 */
public class ManhattanHeuristic implements Heuristic {
    
    private PuzzleState goalState;
    
    public ManhattanHeuristic(PuzzleState goalState) {
        this.goalState = goalState;
    }
   
    
    public int distance(PuzzleState state) {
        // *************************** TO DO
        int totalDistance = 0;
        
        
        for( int i = 1; i < 9; i++){
        
            // gets the index of values 1-8        
            int currValue = state.posOf(i);
            int goalValue = goalState.posOf(i);

            //checks to see if the positions of the indexs are then same, if they are we want to ignore them, else calc a distance
            if (currValue != goalValue){
                int positionOfIndexVal = state.posOf(i);
                int positionOfGoalIndexVal = goalState.posOf(i);
                
                int currRow = (positionOfIndexVal / 3) + 1;
                int currCol = (positionOfIndexVal % 3) + 1;
                
                int goalRow = (positionOfGoalIndexVal / 3) + 1;
                int goalCol = (positionOfGoalIndexVal % 3) + 1;
                
                totalDistance += Math.abs(currRow - goalRow) + Math.abs(currCol - goalCol);
                
            }

        
        }
        
        
        return totalDistance;
    }
    
}