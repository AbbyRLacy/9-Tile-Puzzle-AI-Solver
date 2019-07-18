/**
 * This defines the displacement heuristic for the 8-Puzzle.
 * @author Steven Bogaerts
 */
public class DisplacementHeuristic implements Heuristic {
    
    private PuzzleState goalState;
    
    public DisplacementHeuristic(PuzzleState goalState) {
        this.goalState = goalState;
    }
    
    public int distance(PuzzleState state) {
        // *************************** TO DO
        int DisplacementDistance = 0;
        //handles if number 1-8 are out of place
        for(int i = 0; i < 8; i++){
            if (state.posOf(i+1) != i){
                DisplacementDistance++;
            }
        }

        return DisplacementDistance;
    }
    
}