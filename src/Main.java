/**
 * Created by yketd on 22-12-2016.
 */
public class Main {
    public static void main(String[] args) {
        new Main().run();
    }
    public void run(){
        Doolhof doolhof = new Doolhof();
        MazeSolver solver = new MazeSolver(doolhof);
    }
}
