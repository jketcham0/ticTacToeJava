package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Font;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.text.FontWeight;
import javafx.scene.control.TextField;

public class SingleSettingsController {


    public Button cpu, easy, hard,  // cpu references the currently selected button,  easy and hard are buttons in the fxml file
                  playerChoice, x1, o1, // playerChoice references the currently selected button, x1 and o1 are buttons in the fxml file
                  firstMover, x2, o2;   // first mover references the currently selected button, x2 and o2 are buttons in the fxml file
    public TextField name;
    @FXML public void initialize(){
        // setting defaults
        cpu = easy;
        playerChoice = x1;
        firstMover = x2;
    }
    public void beginGame(MouseEvent click) throws java.io.IOException {
        nextWindow(click, "GameScreen.fxml");
    }
    public void back(MouseEvent click) throws java.io.IOException {
        nextWindow(click, "PlayerMode.fxml");
    }


    public void cpuField(MouseEvent click){  cpu = clicked(click, cpu, easy, hard); }
    public void playerField(MouseEvent click){  playerChoice = clicked(click, playerChoice, x1, o1); }
    public void firstMoverField(MouseEvent click){ firstMover = clicked(click, firstMover, x2, o2); }

    public Button clicked(MouseEvent click, Button current, Button option_1, Button option_2){
        Button bt = (Button)click.getSource();
        Button other;
        if(bt != current) {
            other = (bt != option_1) ? option_1 : option_2;
            bt.setTextFill(Color.valueOf("White"));
            bt.setStyle("-fx-background-color: #2b2a2a; -fx-border-color: white;");
            other.setTextFill(Color.web("#909090"));
            other.setStyle("-fx-background-color: black; -fx-border-color: gray;");
        }
        return bt;
    }

    public void beginHovered(MouseEvent e){
        ((Button)e.getSource()).setStyle("-fx-border-width: 2; -fx-background-insets: 3;   -fx-border-radius: 90; -fx-background-radius: 90; -fx-text-fill: black; -fx-border-color: black; -fx-background-color: white;");


    }
    public void beginExit(MouseEvent e){
        ((Button)e.getSource()).setStyle("-fx-background-radius: 90; -fx-background-insets: 3;   -fx-text-fill: white; -fx-background-color: black; -fx-border-color: white; -fx-border-radius: 90; -fx-border-width: 2;");
    }

    public void nextWindow(MouseEvent click, String xmlFile) throws java.io.IOException
    {
        // initialize loader
        FXMLLoader loader = new FXMLLoader(getClass().getResource(xmlFile));

        Scene GameScreenScene = new Scene((Pane)loader.load());

        GameState game = new GameState(name.getText(), playerChoice.getText().charAt(0), cpu.getText(), firstMover.getText().charAt(0));
        GameController gameUI;
        if(xmlFile.equals("GameScreen.fxml")) {
            gameUI = loader.<GameController>getController();
            game.setUI(gameUI);
        }

        // load the next scene


        // get the stage... getSource = get object that was clicked on (the button) from the event, getScene = get the scene the button is a part of, getWindow = get the stage the scene is a part of
        Stage window = (Stage)((Node)click.getSource()).getScene().getWindow();

        // set stage to display the next scene
        window.setScene(GameScreenScene);

        window.show();
    }
}
