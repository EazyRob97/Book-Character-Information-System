package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstPageController {
    @FXML
    private Button bookButton, characterButton;

    @FXML
    private void bookSceneChange(ActionEvent event) throws IOException {

        //Parent bookscene_parent =FXMLLoader.load(getClass().getResource("Book.fxml"));
        //Scene bookscene_scene = new Scene(bookscene_parent);
        Stage first_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        first_stage.setScene(Main.scene2);
        first_stage.show();

    }

    @FXML
    private void bookCharacterSceneChange(ActionEvent event) throws IOException {

        //Parent bookscene_parent =FXMLLoader.load(getClass().getResource("Book.fxml"));
        //Scene bookscene_scene = new Scene(bookscene_parent);
        Stage first_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        first_stage.setScene(Main.scene3);
        first_stage.show();

    }
}
