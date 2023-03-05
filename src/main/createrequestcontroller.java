package main;
import com.itextpdf.text.DocumentException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class createrequestcontroller {

    @FXML
    private TextField asscid;
    @FXML
    private TextField request;
    @FXML
    private Button create;

    String dt;
    private double xOffset = 0;
    private double yOffset = 0;

    public void createrequest (ActionEvent event) throws IOException, DocumentException {

        DateTimeFormatter dtr = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        dt = dtr.format(now);


        String id;
        DateTimeFormatter dtr1 = DateTimeFormatter.ofPattern("hh-mm-dd-MM-yyyy");
        LocalDateTime now1 = LocalDateTime.now();
        id = "DISR-"+dtr1.format(now1).replace("-", "");

        try
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://"+Main.ip+"/adit", "root", "");
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO requests (id , asscid, request , r_date , c_date , state ) VALUES (?, ?, ?, ?, ?, ?) ");
            stmt.setString(1, id);
            stmt.setString(2, asscid.getText());
            stmt.setString(3, request.getText());
            stmt.setString(4, dt);
            stmt.setString(5, " ");
            stmt.setString(6, "0");
            stmt.executeUpdate();
           PreparedStatement stmt1 = conn.prepareStatement("UPDATE devices SET   st=?  where  asscid='" +  asscid.getText() + "'");
            stmt1.setString(1, "hold");
            stmt1.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("DONE");
            alert.setHeaderText("INFORMATION IS UPDATED SUCCESSFULLY");
            alert.showAndWait();
            conn.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR !");
            alert.setHeaderText("ERROR IN CONNECTION");
            alert.setContentText("PLEASE CHECK THE CONNECTION AND TRY AGAIN");
            alert.showAndWait();
        }
        asscid.setText(" ");
        request.setText(" ");

    }

    public void exit (ActionEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UNDECORATED);
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        stage.getIcons().add(new javafx.scene.image.Image(getClass().getResourceAsStream("Assets/logo.png")));
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

}
