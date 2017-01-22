import com.sun.org.apache.xpath.internal.operations.Or;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by yketd on 22-12-2016.
 */
public class Doolhof {

    private ArrayList<Tile> tiles;


    private Pawn pawnOne, pawnTwo;

    public Doolhof(){
        tiles = new ArrayList<>();
        fillTiles();
        connectTiles();
        addPawns();
    }

    enum COLOR {PINK, GREEN, BLACK, ORANGE, BLUE};


    private void fillTiles(){
        //  1-10    //
        tiles.add(new Tile(COLOR.PINK, 1));
        tiles.add(new Tile(COLOR.BLACK, 2));
        tiles.add(new Tile(COLOR.GREEN, 3));
        tiles.add(new Tile(COLOR.GREEN, 4));
        tiles.add(new Tile(COLOR.GREEN, 5));
        tiles.add(new Tile(COLOR.ORANGE, 6));
        tiles.add(new Tile(COLOR.ORANGE, 7));
        tiles.add(new Tile(COLOR.PINK, 8));
        tiles.add(new Tile(COLOR.PINK, 9));
        tiles.add(new Tile(COLOR.BLACK, 10));


        //  11-20   //
        tiles.add(new Tile(COLOR.ORANGE, 11));
        tiles.add(new Tile(COLOR.PINK, 12));
        tiles.add(new Tile(COLOR.ORANGE, 13));
        tiles.add(new Tile(COLOR.GREEN, 14));
        tiles.add(new Tile(COLOR.ORANGE, 15));
        tiles.add(new Tile(COLOR.GREEN, 16));
        tiles.add(new Tile(COLOR.GREEN, 17));
        tiles.add(new Tile(COLOR.BLACK, 18));
        tiles.add(new Tile(COLOR.ORANGE, 19));
        tiles.add(new Tile(COLOR.GREEN, 20));

        //  21-22   //

        tiles.add(new Tile(COLOR.BLACK, 21));
        tiles.add(new Tile(COLOR.BLACK, 22));

        //  finish  //
        tiles.add(new Tile(COLOR.BLUE, true, 23));
        System.out.println("added all the tiles");
    }

    private void connectTiles(){
        tiles.get(0).addPath(new Path(COLOR.PINK, 4));
        tiles.get(0).addPath(new Path(COLOR.BLACK, 5));

        tiles.get(1).addPath(new Path(COLOR.GREEN, 6));
        tiles.get(1).addPath(new Path(COLOR.PINK, 12));

        tiles.get(2).addPath(new Path(COLOR.ORANGE, 4));
        tiles.get(2).addPath(new Path(COLOR.ORANGE, 1));

        tiles.get(3).addPath(new Path(COLOR.BLACK, 13));

        tiles.get(4).addPath(new Path(COLOR.ORANGE,9));

        tiles.get(5).addPath(new Path(COLOR.GREEN, 9));
        tiles.get(5).addPath(new Path(COLOR.PINK, 10));

        tiles.get(6).addPath(new Path(COLOR.GREEN, 2));

        tiles.get(7).addPath(new Path(COLOR.PINK, 3));

        tiles.get(8).addPath(new Path(COLOR.GREEN, 4));

        tiles.get(9).addPath(new Path(COLOR.GREEN, 15));

        tiles.get(10).addPath(new Path(COLOR.PINK, 10));
        tiles.get(10).addPath(new Path(COLOR.GREEN, 12));

        tiles.get(11).addPath(new Path(COLOR.GREEN, 7));

        tiles.get(12).addPath(new Path(COLOR.GREEN,8));
        tiles.get(12).addPath(new Path(COLOR.GREEN, 18));

        tiles.get(13).addPath(new Path(COLOR.ORANGE, 20));
        tiles.get(13).addPath(new Path(COLOR.GREEN, 23));

        tiles.get(14).addPath(new Path(COLOR.GREEN,   22));
        tiles.get(14).addPath(new Path(COLOR.PINK,    23));

        tiles.get(15).addPath(new Path(COLOR.GREEN,   15));

        tiles.get(16).addPath(new Path(COLOR.BLACK,   16));
        tiles.get(16).addPath(new Path(COLOR.BLACK,   11));
        tiles.get(16).addPath(new Path(COLOR.PINK,    12));

        tiles.get(17).addPath(new Path(COLOR.ORANGE,  9));
        tiles.get(17).addPath(new Path(COLOR.ORANGE,  20));

        tiles.get(18).addPath(new Path(COLOR.GREEN,   18));

        tiles.get(19).addPath(new Path(COLOR.BLACK,   19));
        tiles.get(19).addPath(new Path(COLOR.ORANGE,  21));

        tiles.get(20).addPath(new Path(COLOR.ORANGE,  22));
        tiles.get(20).addPath(new Path(COLOR.BLACK,   23));

        tiles.get(21).addPath(new Path(COLOR.ORANGE,  17));

        System.out.println("added all the paths");
    }

    public void addPawns(){
        pawnOne = new Pawn(1);
        pawnTwo = new Pawn(2);
    }

    public Pawn getPawnOne() {
        return pawnOne;
    }

    public Pawn getPawnTwo() {
        return pawnTwo;
    }

    public void movePawn(Pawn pawnToMove, int goalTile){
        pawnToMove.setPos(goalTile);
    }

    public Tile getTile(int pos){
        return tiles.get(pos);
    }

}
