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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class updaterequestcontroller {

    @FXML
    private TextField id;
    @FXML
    private TextField asscid;
    @FXML
    private TextField request;
    @FXML
    private Button create;
    @FXML
    private Button search;
    @FXML
    private Label d;


    String dt;
    private double xOffset = 0;
    private double yOffset = 0;

    public void search(ActionEvent event) throws IOException, DocumentException, SQLException, ClassNotFoundException {
        create.setDisable(true);

        DateTimeFormatter dtr = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        dt = dtr.format(now);


        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + Main.ip + "/adit", "root", "");
            Statement stmt = conn.createStatement();
            String key = search.getText();
            stmt.execute("SELECT * FROM requests where  asscid='" + asscid.getText() + "' AND state='0' ");
            ResultSet results = stmt.getResultSet();
            while (results.next()) {
                id.setText(results.getString("id"));
                request.setText(results.getString("request"));
                d.setText(results.getString("r_date"));
                conn.close();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR !");
            alert.setHeaderText("NOT A VALID RECORD");
            alert.setContentText("PLEASE CHECK THE DATA AND TRY AGAIN");
            alert.showAndWait();
        }
    }

    public void update(ActionEvent event) throws IOException, DocumentException, SQLException, ClassNotFoundException {
        create.setDisable(true);

        DateTimeFormatter dtr = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        dt = dtr.format(now);
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + Main.ip + "/adit", "root", "");
            PreparedStatement stmt = conn.prepareStatement("UPDATE requests SET  c_date=? , state=?  where  asscid='" + asscid.getText() + "' ");
            stmt.setString(1, dt);
            stmt.setString(1, "1");
            stmt.executeUpdate();
            PreparedStatement stmt1 = conn.prepareStatement("UPDATE devices SET   st=?  where  asscid='" + asscid.getText() + "'");
            stmt1.setString(1, "on");
            stmt1.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("DONE");
            alert.setHeaderText("INFORMATION IS UPDATED SUCCESSFULLY");
            alert.showAndWait();
            conn.close();
        }catch (Exception e) {
            System.out.println(e);
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
