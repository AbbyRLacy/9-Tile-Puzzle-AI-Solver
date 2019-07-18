import java.util.Queue;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Comparator;

/**
 * Represents a search problem to be solved.
 * 
 * This is somewhat specific to the 8-Puzzle, since a more general solution would require increased use of
 * generics, which we're avoiding in this course for now.
 * @author Steven Bogaerts
 */
public class SearchProblem {

    // *************************** TO DO - define whatever fields you need here
    private Queue<PuzzlePath> frontier;
    //private frontier;
    private PuzzleState initState;
    private PuzzleState goalstate;
    private String queueType;
    private int goalCheckLimit;
    private Heuristic h;
   
        
    
    /**
     * This Comparator object is an instance of an anonymous class.
     * It compares two paths based on cost, for use in the PriorityQueue, for ordering.
     */
    public static Comparator<PuzzlePath> pathComparator = new Comparator<PuzzlePath>() {
        public int compare(PuzzlePath p1, PuzzlePath p2) {
            return p1.getCost() - p2.getCost();
        }
    };
    
    /**
     * Constructs a new SearchProblem.
     * 
     * @param queueType For our purposes for now, assume that queueType is either "FIFO" (meaning BFS will be done)
     * or "Ordered" (meaning A* will be done).
     * 
     * @param goalCheckLimit Check if some state is a goal up to this many times, after which you give up the search.
     * This is useful for problems that turn out to be impossible to solve.
     * 
     * @param h The Heuristic, should be an instance of the NoHeuristic class if you're using FIFO (BFS) as the queueType.
     * Otherwise pass an appropriate Heuristic object.
     */
    public SearchProblem(PuzzleState initState1, PuzzleState goalState1, String queueType1, int goalCheckLimit1, Heuristic h1) {
        // *************************** TO DO - do whatever initialization you need here
        initState = initState1;
        goalstate = goalState1;
        queueType = queueType1;
        goalCheckLimit = goalCheckLimit1;
        h = h1;
        if (queueType.equals("FIFO")){
            frontier = new LinkedList<PuzzlePath>();
            PuzzlePath p = new PuzzlePath(initState, h);
            //System.out.println(p.toString());
            frontier.add(p);
        }
        else if(queueType.equals("Ordered")){
            //init frontier as a priorityQueue
            frontier = new PriorityQueue<PuzzlePath>(1000, pathComparator);
            PuzzlePath p = new PuzzlePath(initState, h);
            frontier.add(p);
        }
        else{
            System.out.println("error");
            System.exit(0);
        }
        

        // Check the value of queueType, and set the frontier to the correct type.
    }
    
    /**
     * Solve this search problem.
     */
    public boolean solve() {
        // *************************** TO DO
        LinkedList<PuzzleState> expandedSet = new LinkedList();
        int giveUpCounter = 0;
        
        while (!frontier.isEmpty()){
            if(giveUpCounter == goalCheckLimit){
                System.out.println("give up its pointless");
                return false;
            }
            giveUpCounter++;
            PuzzlePath n = frontier.poll();
            PuzzleState stateAtEndOfCurrPath = n.stateAtEndOfPath();
            
            if(stateAtEndOfCurrPath.equals(goalstate)){
                System.out.println(giveUpCounter);
                return true;
            }
            
            //add n to expanded
            expandedSet.add(stateAtEndOfCurrPath);
            
        
            //all the things n expands into
            LinkedList<PuzzleState> temp = stateAtEndOfCurrPath.expand();
            
            //check to only add things to the frontier if they are not in the expanded set
            //loop thru all the things in temp to check if they are in the expanded set
            for(int i= 0; i< temp.size(); i++){
                if(!expandedSet.contains(temp.get(i))){
                   PuzzlePath nCopy = n.makeCopy();
                   nCopy.addState(temp.get(i));
                   //ClassCastException - if the specified element cannot be compared 
                   //with elements currently in this priority queue according to the priority queue's ordering
                   frontier.add(nCopy);
                }
            }

        
        }

        return false;
    }
        
}