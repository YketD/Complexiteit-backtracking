/**
 * Created by yketd on 22-12-2016.
 */
public class Pawn {


    int pos;

    public Pawn(int pos){
        this.pos = pos;
    }

    public int getCurrentPos(){
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public boolean isOnFinish(){
        return pos == 23;
    }

}
