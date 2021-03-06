package sample;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;

public class Outcome {

    @FXML
    private Circle userImage;

    @FXML
    private Text status;

    @FXML
    private Text pkill;

    @FXML
    private Text pdeath;

    @FXML
    private Text pname;

    @FXML
    private ImageView p;

    @FXML
    Pane info;

    @FXML
    void close(MouseEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }

    @FXML
    void minimize(MouseEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.toBack();
    }

    public void initialize() {

        Gson gson = new Gson();
        try {
            PlayerInfo playerInfo = gson.fromJson(new FileReader("../Model/Output.json"), PlayerInfo.class);
            pname.setText(playerInfo.pname);
            pkill.setText("Kills: " + playerInfo.kills);
            pdeath.setText("Deaths: " + playerInfo.deaths);
            status.setText("Team: " + playerInfo.team);
            if(playerInfo.isHacker == 1) {
                p.setImage(new Image("sample/cross.png"));
            } else {
                p.setImage(new Image("sample/tick.png"));
            }
            userImage.setFill(new ImagePattern(new Image("sample/1.jpg")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
   void back(ActionEvent event){

        Parent home_parent = null;
        try {
            home_parent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene Home = new Scene(home_parent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(Home);
        window.show();
    }

}
