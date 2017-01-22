import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by yketd on 22-12-2016.
 */
public class MazeSolver {
    Doolhof doolhof;
    Tile pawnone, pawntwo;
    boolean moveWorked = true;
    int historysize = 0;
    boolean solved = false;
    int index = 0;

    ArrayList<State> history = new ArrayList<>();
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
            System.out.println("failed to solve..");
        }

    }

    public boolean solve(Doolhof doolhof){
        while (pawnone.getPos() != 23 || pawntwo.getPos() != 23) {
            State newState = null;
            newState = getPath(pawnone, pawntwo);
            if (newState == null) {

                boolean failed = revertPosition();
                if (failed){
                    return failed;
                }
            } else {
//                System.out.println("moving.. pawn one go to: " + newState.to1 + " pawn two go to "+ newState.to2);
                pawnone = doolhof.getTile(newState.to1-1);
                pawntwo = doolhof.getTile(newState.to2-1);
            }
        }
        return true;



    }

    private boolean revertPosition() {
        historysize--;
        if (!(history.size() <0)){
            State falseState = new State(pawnone.getPos(), pawntwo.getPos());
            State oldState = history.get(historysize);
            wrongStates.add(falseState);


//            history.remove(history.size() - 1);
//            System.out.println("history size: " + historysize);
            System.out.println("reverting..: " + falseState.getTo1() + " -> " + oldState.getTo1()  + " & " + falseState.getTo2() + "->" + oldState.getTo2());

            pawnone = doolhof.getTile(oldState.to1-1);
            pawntwo = doolhof.getTile(oldState.to2-1);
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
                for (State state1 : history) {
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
                    System.out.println("state: " + pawntwo.getPos() + "->" + state.getTo1()  + " & " + pawnone.getPos() + "->" + state.getTo2());
                    history.add(state);
                    historysize++;
                    return state;
                }
            }
        ArrayList<Path> paths2 = p2.getPossiblePaths(p1.getColor());
        for (Path path : paths2){
            State state = new State(p1.getPos() , path.getDest());

            boolean goodpath = true;
            for (State state1 : history) {
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
                history.add(state);
                historysize++;
                return state;

            }
        }

        return null;
    }



}
