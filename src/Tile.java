import java.awt.*;
import java.util.ArrayList;

/**
 * Created by yketd on 22-12-2016.
 */
public class Tile{
    Doolhof.COLOR color;
    ArrayList<Path> possiblePaths = new ArrayList<>();
    boolean isFinish = false;
    int pos;

    public Tile(Doolhof.COLOR color, int pos) {
        this.color = color;
        this.pos = pos;
    }

    public Tile(Doolhof.COLOR color, boolean isFinish, int pos) {
        this.color = color;
        this.isFinish = isFinish;
        this.pos = pos;
    }

    public void addPath(Path path){
        possiblePaths.add(path);
    }

    public int getPos() {
        return pos;
    }

    public Doolhof.COLOR getColor(){
        return color;
    }

    public ArrayList<Path> getPossiblePaths(Doolhof.COLOR color) {
        ArrayList<Path> result = new ArrayList<>();
        for (Path path : possiblePaths){
//            System.out.println("checking " + path.getColor() + " with destination " + path.getDest() + " against " + color);
            if (path.getColor() == color){
                result.add(path);

            }
        }
        return result;
    }
}
