package main;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;


public class Main extends Application {
    private double xOffset = 0.0D;
    private double yOffset = 0.0D;
    public static String ip = "localhost";

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = (Parent)FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("home.fxml")));
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("Assets/logo.png"))));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                Main.this.xOffset = event.getSceneX();
                Main.this.yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - Main.this.xOffset);
                primaryStage.setY(event.getScreenY() - Main.this.yOffset);
            }
        });
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
