import java.util.LinkedList;
import java.util.Arrays;

/**
 * Represents a state (placement of tiles) of the puzzle.
 * Starter code.
 * @author Steven Bogaerts
 */
public class PuzzleState {
    
    /**
     * A 1-D array representation of the tile placement.
     */
    private int[] tiles;
    
    /**
     * Assumes that initialTiles is the valid format:
     * length 9,
     * containing the integers 0 through 8 inclusive in some order
     * with no repeats
     * where 0 represents the blank.
     */
    public PuzzleState(int[] initialTiles) {
        tiles = initialTiles;
    }

    /**
     * Returns the position of a given tile in the puzzle state.
     * Returns -1 if not found. (Should never happen for valid input.)
     */
    public int posOf(int tile) {
        // *************************** TO DO
        for(int i = 0; i<tiles.length; i++){
            
            if(tiles[i] == tile){
                return i;
            }
        
        }
        return -1;
    }
    
    
    /**
     * Returns the tile value at the given index (0 through 8, inclusive).
     */
    public int getTileAt(int index) {
        // *************************** TO DO
        if(tiles[index] == 0){
            return 0;
        }
        return this.tiles[index];
    }
    
    /**
     * Returns a list of PuzzleStates that can be reached from this PuzzleState
     * in one move. It's easiest to think of this as moving the blank.
     */
    public LinkedList<PuzzleState> expand() {
        //initialize puzzle arrays for each move
        LinkedList<PuzzleState> finalList = new LinkedList(); 
        int[] upMove = Arrays.copyOf(tiles, 9); 
        int[] downMove = Arrays.copyOf(tiles, 9);
        int[] leftMove = Arrays.copyOf(tiles, 9);
        int[] rightMove = Arrays.copyOf(tiles, 9);
        
        // actually start moving things around
        //upmove
        if (posOf(0) > 2){
            int indexPositionOfZero = posOf(0);
            int indexToBeSwapped = indexPositionOfZero - 3;
            int valueToBeSwapped = upMove[indexToBeSwapped];
            upMove[indexPositionOfZero] = valueToBeSwapped;
            upMove[indexToBeSwapped] = 0;
            PuzzleState finalUpMove= new PuzzleState(upMove);
            finalList.add(finalUpMove);
        }
        
        //downmove
        if (posOf(0) < 6){
            int indexPositionOfZero = posOf(0);
            int indexToBeSwapped = indexPositionOfZero + 3;
            int valueToBeSwapped = downMove[indexToBeSwapped];
            downMove[indexPositionOfZero] = valueToBeSwapped;
            downMove[indexToBeSwapped] = 0;
            PuzzleState finalDownMove= new PuzzleState(downMove);
            finalList.add(finalDownMove);
        }
        
        //leftmove
        if (posOf(0) != 0 && posOf(0) != 3 && posOf(0) != 6){
            int indexPositionOfZero = posOf(0);
            int indexToBeSwapped = indexPositionOfZero - 1;
            int valueToBeSwapped = leftMove[indexToBeSwapped];
            leftMove[indexPositionOfZero] = valueToBeSwapped;
            leftMove[indexToBeSwapped] = 0;
            PuzzleState finalLeftMove= new PuzzleState(leftMove);
            finalList.add(finalLeftMove);
        }
        
        //rightmove
        if (posOf(0) != 2 && posOf(0) != 5 && posOf(0) != 8){
            int indexPositionOfZero = posOf(0);
            int indexToBeSwapped = indexPositionOfZero + 1;
            int valueToBeSwapped = rightMove[indexToBeSwapped];
            rightMove[indexPositionOfZero] = valueToBeSwapped;
            rightMove[indexToBeSwapped] = 0;
            PuzzleState finalRightMove= new PuzzleState(rightMove);
            finalList.add(finalRightMove);
        }
        
        

        return finalList;
    }
    
    
    
    /**
     * Returns true if two PuzzleState objects are equivalent,
     * false otherwise. Required for proper operation of a HashSet of PuzzleStates.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof PuzzleState))
            return false;
        else
            return Arrays.equals(this.tiles, ((PuzzleState) other).tiles);
    }
    
    /**
     * Required to be able to have a HashSet of PuzzleState objects.
     * Objects for which .equals() returns true must have the same hashCode.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(tiles);
    }
    
    /**
     * Returns a String representation of the state.
     */
    @Override
    public String toString() {
        String result = "\n";
        for(int i = 0; i < tiles.length; i++) {
            if ((i > 0) && i % 3 == 0)
                result += "\n";
                
            if (tiles[i] == 0)
                result += "_ ";
            else
                result += tiles[i] + " ";
        }
                
        return result + "\n";
    }
}