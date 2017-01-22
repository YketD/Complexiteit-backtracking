import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/**
 * Created by yketd on 22-12-2016.
 */
public class MazeSolver {
    Doolhof doolhof;
    Tile pawnone, pawntwo;
    boolean moveWorked = true;
    State currentState;
    int historysize = 0;
    boolean solved = false;
    int index = 0;

    Stack<State> history = new Stack<>();
    ArrayList<State> wrongStates = new ArrayList<>();

    public MazeSolver(Doolhof doolhof){
//        State state = new State(1,2,3,4);
//        State state2 = new State(1,2,3,4);
//        State state3 = new State(2,1,4,3);
//        State state4 = new State(1,1,4,3);
//        System.out.println(checkLegalState(state, state2));
//        System.out.println(checkLegalState(state, state3));
//        System.out.println(checkLegalState(state, state4));

        this.doolhof = doolhof;
        pawnone = doolhof.getTile(0);
        pawntwo = doolhof.getTile(1);
        System.out.println("starting to solve the puzzle");
        if (solve(doolhof)){
            System.out.println("solved!");
        }   else{

            for (State state : wrongStates){
                System.out.println(state.getTo1() + " & " +  state.getTo2());
            }
        }

    }

    public boolean solve(Doolhof doolhof){
        currentState = new State(pawnone.getPos(),pawntwo.getPos());

        while (pawnone.getPos() != 23 || pawntwo.getPos() != 23) {
            System.out.println(pawnone.getPos() + "  "  + pawntwo.getPos());
            State newState = null;
            newState = getPath(pawnone, pawntwo);
            if (newState == null) {

                boolean failed = revertPosition();
                if (failed){
                    return false;
                }
            } else {
                history.push(currentState);
                currentState = newState;
                pawnone = doolhof.getTile(newState.to1 - 1);
                pawntwo = doolhof.getTile(newState.to2 - 1);

            }
        }
        return true;



    }

    private boolean revertPosition() {
        if (!(history.size() <1)){
            State falseState = currentState;
            wrongStates.add(currentState);
            currentState = history.pop();

            System.out.println("reverting..: " + pawnone.getPos() + " -> " + currentState.getTo1()  + " & " + pawntwo.getPos() + "->" + currentState.getTo2());

            pawnone = doolhof.getTile(currentState.to1 - 1);
            pawntwo = doolhof.getTile(currentState.to2 - 1);
            return false;
        }
        else{
            System.out.println("Last position reached and could not find another path!");
            return true;

        }

    }

    public boolean checkLegalState(State oldState, State newState){
//        System.out.println("checking... state: " + oldState.getTo1() + " against " + newState.getTo1()  + " & " + oldState.getTo2() + " against " + oldState.getTo2());
        if (
                oldState.getTo1()   == newState.getTo1() &&
                        oldState.getTo2()   == newState.getTo2() ||
                        oldState.getTo1()   == newState.getTo2() &&
                                oldState.getTo2()   == newState.getTo1() ){
            return false;
        }
        return true;
    }

    public State getPath(Tile p1, Tile p2){
        ArrayList<Path> paths1 = p1.getPossiblePaths(p2.getColor());
            for (Path path : paths1){
                State state = new State(path.getDest(), p2.getPos());
                System.out.print("");
                boolean goodpath = true;
                Iterator iterator = history.iterator();
                while (iterator.hasNext()){
                    State state1 = (State) iterator.next();
                    if (!checkLegalState(state1, state)) {
                        goodpath = false;
                    }
                }
                for (State state1: wrongStates){
                    if (!checkLegalState(state1, state)) {
                        goodpath = false;
                    }
                }
                if (goodpath){
                    System.out.println("state: " + pawnone.getPos() + "->" + state.getTo1()  + " & " + pawntwo.getPos() + "->" + state.getTo2());
                    return state;
                }
            }
        ArrayList<Path> paths2 = p2.getPossiblePaths(p1.getColor());
        for (Path path : paths2){
            State state = new State(p1.getPos() , path.getDest());

            boolean goodpath = true;
            Iterator iterator = history.iterator();
            while (iterator.hasNext()){
                State state1 = (State) iterator.next();
                if (!checkLegalState(state1, state)) {
                    goodpath = false;
                }
            }
            for (State state1: wrongStates){
                if (!checkLegalState(state1, state)) {
                    goodpath = false;
                }
            }
            if (goodpath){
                System.out.println("state: " + pawnone.getPos() + "->" + state.getTo1()  + " & " + pawntwo.getPos() + "->" + state.getTo2());
                return state;

            }
        }

        return null;
    }



}
