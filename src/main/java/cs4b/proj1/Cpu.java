package cs4b.proj1;

import javafx.scene.image.ImageView;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Cpu extends Player {

    boolean easy = true;

    Cpu(Icon icon, Game g){

        super("CPU", icon, g);
    }
    @Override
    void move(){
        if(game.gameIsOver() ) {
            board.removeGameControls();
            return;
        }
        int []m = {0,0};
        try{ TimeUnit.SECONDS.sleep(1); }
        catch(Exception e){e.printStackTrace();}
        if(easy) {
            do m = randomMove();
            while (game.occupied(m[0], m[1]));
        }
        else {
            do m = bestMove();
            while (game.occupied(m[0], m[1]));
        }
        execute(m[0],m[1]);

    }
    static int[] randomMove(){

        Random ran = new Random();
        int []choice = {ran.nextInt(3),ran.nextInt(3)};
        return choice;

    }
    static int[] bestMove(){
        try{ TimeUnit.SECONDS.sleep(1); }
        catch(Exception e){e.printStackTrace();}
        int[] choice = {0,0};
        return choice;
    }
    void setUp(){}
}
