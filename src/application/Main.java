package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    //public static Book bookHead = null;
    public static MyHash<Book> booksHash = new MyHash<>(10);
    public static MyHash<BookCharacter> charactersHash = new MyHash<>(10);

    public static Scene scene1, scene2, scene3;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
//			Parent root =FXMLLoader.load(getClass().getResource("FirstPage.fxml"));
//			Scene scene = new Scene(root,670,800);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//
//			scene1=scene;

            loadAllScenes();

            primaryStage.setScene(scene1);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadAllScenes() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("FirstPage.fxml"));
            scene1 = new Scene(root, 670, 800);
            scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            root = FXMLLoader.load(getClass().getResource("Book.fxml"));
            scene2 = new Scene(root);

            root = FXMLLoader.load(getClass().getResource("BookCharacter.fxml"));
            scene3 = new Scene(root);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
