import java.util.ArrayList;

/**
 * Created by yketd on 22-12-2016.
 */
public class State {


    int from1, from2, to1, to2;

    public State(int t1,int t2) {
        this.to1 = t1;
        this.to2 = t2;
    }

    public int getTo1() {
        return to1;
    }

    public int getTo2() {
        return to2;
    }

}
