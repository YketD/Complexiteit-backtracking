import java.util.ArrayList;

/**
 * Created by yketd on 22-12-2016.
 */
public class Path {
    private Doolhof.COLOR color;
    private int dest;

    public int getDest() {
        return dest;
    }



    public Path(Doolhof.COLOR color, int dest) {
        this.color = color;
        this.dest = dest;
    }

    public Doolhof.COLOR getColor() {
        return color;
    }
}
