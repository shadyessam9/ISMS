package main;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;



public class adddeviceController implements Initializable {

    private double xOffset = 0.0D;
    private double yOffset = 0.0D;

    @FXML
    private ComboBox<String> device;
    @FXML
    private ComboBox<String> processor;
    @FXML
    private ComboBox<String> company;
    @FXML
    private ComboBox<String> hardtype;
    @FXML
    private ComboBox<String> type;
    @FXML
    private ComboBox<String> ports;
    @FXML
    private ComboBox<String> depart;
    @FXML
    private TextField name;
    @FXML
    private TextField ram;
    @FXML
    private TextField hardcapacity;
    @FXML
    private TextField ip;
    @FXML
    private TextField model;
    @FXML
    private TextField position;
    @FXML
    private TextField dvr;
    @FXML
    private Button register;
    @FXML
    public ImageView image;

    String filename;
    String dt;








    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        device.getItems().add("PC");
        device.getItems().add("LAPTOP");
        device.getItems().add("SERVER");
        device.getItems().add("SWITCH");
        device.getItems().add("DVR");
        device.getItems().add("FINGERPRINT");
        device.getItems().add("PRINTER");
        device.getItems().add("CAMERA");


        processor.getItems().add("PENTIUM");
        processor.getItems().add("CLEREON");
        processor.getItems().add("CORE2DUO");
        processor.getItems().add("CORE I3");
        processor.getItems().add("CORE I5");
        processor.getItems().add("CORE I7");
        processor.getItems().add("INTELXENON");
        processor.getItems().add("PRINTER");



        company.setItems(FXCollections.observableArrayList(getData()));


        hardtype.getItems().add("HDD");
        hardtype.getItems().add("SSD");
        hardtype.getItems().add("hybrid");


        ports.getItems().add("4");
        ports.getItems().add("8");
        ports.getItems().add("16");
        ports.getItems().add("24");
        ports.getItems().add("32");
        ports.getItems().add("48");
        ports.getItems().add("64");






        depart.getItems().add("IT");
        depart.getItems().add("law affairs");
        depart.getItems().add("production");
        depart.getItems().add("quality");
        depart.getItems().add("safety");
        depart.getItems().add("security");
        depart.getItems().add("fleet");
        depart.getItems().add("gps");
        depart.getItems().add("ACCOUNTING");
        depart.getItems().add("HR");
        depart.getItems().add("MARKETING");
        depart.getItems().add("AUDIT");
        depart.getItems().add("SECRETARY");
        depart.getItems().add("SALES");
        depart.getItems().add("PURCHASE");
        depart.getItems().add("IMPORT&EXPORT");
        depart.getItems().add("ENGINEERING");
        depart.getItems().add("STORES");



        type.getItems().add("USER");
        type.getItems().add("IT");
        type.getItems().add("BARREN");


        processor.setValue(" ");
        ram.setText(" ");
        hardcapacity.setText(" ");
        ip.setText(" ");
        model.setText(" ");
        dvr.setText(" ");
        name.setText(" ");
        position.setText(" ");
        ram.setText(" ");
        hardtype.setValue(" ");
        ports.setValue(" ");
        company.setValue(" ");
        type.setValue(" ");
        depart.setValue(" ");


    }


    private List<String> getData() {


        List<String> companies = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + Main.ip + "/adit", "root", "");
            Statement statement = conn.createStatement();
            statement.execute("SELECT * FROM companies");
            ResultSet results = statement.getResultSet();
            while (results.next()) {
                companies.add(results.getString("company"));
            }


        } catch (Exception e) {
            System.out.println(e);
        }

        return companies;
    }




    @FXML
    private void change(ActionEvent event) throws IOException {
      if(device.getValue().equals("SWITCH")){
          BufferedImage bufferedImage = ImageIO.read(new File("Assets/switch.png"));
          javafx.scene.image.Image pic = SwingFXUtils.toFXImage(bufferedImage, null);
          image.setImage(pic);
          processor.setDisable(true);
          hardtype.setDisable(true);
          ram.setDisable(true);
          hardcapacity.setDisable(true);
          dvr.setDisable(true);
          ip.setDisable(true);
          ports.setDisable(false);
          model.setDisable(false);
      }else if(device.getValue().equals("DVR")){
          BufferedImage bufferedImage = ImageIO.read(new File("Assets/dvr.png"));
          javafx.scene.image.Image pic = SwingFXUtils.toFXImage(bufferedImage, null);
          image.setImage(pic);
          processor.setDisable(true);
          hardtype.setDisable(true);
          ram.setDisable(true);
          hardcapacity.setDisable(true);
          dvr.setDisable(true);
          ports.setDisable(false);
          ip.setDisable(false);
          model.setDisable(false);
      }else if(device.getValue().equals("FINGERPRINT")){
          BufferedImage bufferedImage = ImageIO.read(new File("Assets/fingerprint.png"));
          javafx.scene.image.Image pic = SwingFXUtils.toFXImage(bufferedImage, null);
          image.setImage(pic);
          processor.setDisable(true);
          hardtype.setDisable(true);
          ram.setDisable(true);
          hardcapacity.setDisable(true);
          ports.setDisable(true);
          dvr.setDisable(true);
          ip.setDisable(false);
          model.setDisable(false);
      }else if(device.getValue().equals("PRINTER")){
          BufferedImage bufferedImage = ImageIO.read(new File("Assets/printer.png"));
          javafx.scene.image.Image pic = SwingFXUtils.toFXImage(bufferedImage, null);
          image.setImage(pic);
          processor.setDisable(true);
          hardtype.setDisable(true);
          ram.setDisable(true);
          hardcapacity.setDisable(true);
          ports.setDisable(true);
          dvr.setDisable(true);
          ip.setDisable(false);
          model.setDisable(false);
      }else if(device.getValue().equals("CAMERA")){
          BufferedImage bufferedImage = ImageIO.read(new File("Assets/camera.png"));
          javafx.scene.image.Image pic = SwingFXUtils.toFXImage(bufferedImage, null);
          image.setImage(pic);
          processor.setDisable(true);
          hardtype.setDisable(true);
          ram.setDisable(true);
          hardcapacity.setDisable(true);
          ip.setDisable(true);
          ports.setDisable(true);
          model.setDisable(false);
          dvr.setDisable(false);
      }else if (device.getValue().equals("PC")){
          BufferedImage bufferedImage = ImageIO.read(new File("Assets/pc.png"));
          javafx.scene.image.Image pic = SwingFXUtils.toFXImage(bufferedImage, null);
          image.setImage(pic);
          processor.setDisable(false);
          hardtype.setDisable(false);
          ram.setDisable(false);
          hardcapacity.setDisable(false);
          ip.setDisable(false);
          dvr.setDisable(true);
          model.setDisable(false);
          ports.setDisable(true);
      }else if(device.getValue().equals("LAPTOP")){
          BufferedImage bufferedImage = ImageIO.read(new File("Assets/laptop.png"));
          javafx.scene.image.Image pic = SwingFXUtils.toFXImage(bufferedImage, null);
          image.setImage(pic);
          processor.setDisable(false);
          hardtype.setDisable(false);
          ram.setDisable(false);
          hardcapacity.setDisable(false);
          ip.setDisable(false);
          dvr.setDisable(true);
          model.setDisable(false);
          ports.setDisable(true);
      }else if (device.getValue().equals("SERVER")){
          BufferedImage bufferedImage = ImageIO.read(new File("Assets/server.png"));
          javafx.scene.image.Image pic = SwingFXUtils.toFXImage(bufferedImage, null);
          image.setImage(pic);
          processor.setDisable(false);
          hardtype.setDisable(false);
          ram.setDisable(false);
          hardcapacity.setDisable(false);
          ip.setDisable(false);
          dvr.setDisable(true);
          model.setDisable(false);
          ports.setDisable(true);
      }

    }


    public void createid (ActionEvent event) throws IOException, DocumentException {


        DateTimeFormatter dtr = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        dt = dtr.format(now);


        String id;
        DateTimeFormatter dtr1 = DateTimeFormatter.ofPattern("hh-mm-dd-MM-yyyy");
        LocalDateTime now1 = LocalDateTime.now();
        id = dtr1.format(now1).replace("-", "");

        try
        {

            Connection conn = DriverManager.getConnection("jdbc:mysql://"+Main.ip+"/adit", "root", "");
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO devices (type , picd, processor , ram , hardtype , hardcapacity , ip , model , ports , dvr , st , asscid , company , name , position , depart , password , user_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, device.getValue());
            stmt.setString(2, "abodonkol.myfirewall.co:3031/pics/'"+ device.getValue() +"'.png");
            stmt.setString(3, processor.getValue());
            stmt.setString(4, ram.getText());
            stmt.setString(5, hardtype.getValue());
            stmt.setString(6, hardcapacity.getText());
            stmt.setString(7, ip.getText());
            stmt.setString(8, model.getText());
            stmt.setString(9, dvr.getText());
            stmt.setString(10, model.getText());
            stmt.setString(11, "on");
            stmt.setString(12, id);
            stmt.setString(13, company.getValue());
            stmt.setString(14, name.getText());
            stmt.setString(15, position.getText());
            stmt.setString(16, depart.getValue());
            stmt.setString(17, "adit");
            stmt.setString(18, type.getValue());
            stmt.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("DONE");
            alert.setHeaderText("DATA IS SAVED SUCCESSFULLY");
            alert.showAndWait();
            conn.close();
            Parent root;
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml")));
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
            stage.getIcons().add(new javafx.scene.image.Image(Objects.requireNonNull(getClass().getResourceAsStream("Assets/logo.png"))));
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


        filename = name.getText();

        Document document = new Document(new Rectangle(PageSize.A4));
        PdfWriter.getInstance(document, new FileOutputStream("qrcodes/qrcode"+filename+".pdf"));
        document.open();

        FontFactory.registerDirectories();
        Font font = FontFactory.getFont("arial", BaseFont.IDENTITY_H, BaseFont.EMBEDDED,15);



        BarcodeQRCode qrcode = new BarcodeQRCode(
                id, 500, 500  , null);
        com.itextpdf.text.Image image = qrcode.getImage();
        image.setAlignment(Element.ALIGN_CENTER);
        document.add(image);




        File file = new File("qrcodes/qrcode"+filename+".pdf");
        Desktop desktop = Desktop.getDesktop();
        desktop.open(file);
        document.close();

        processor.setValue(" ");
        ram.setText(" ");
        hardcapacity.setText(" ");
        ip.setText(" ");
        model.setText(" ");
        dvr.setText(" ");
        name.setText(" ");
        position.setText(" ");
        ram.setText(" ");
        hardtype.setValue(" ");
        ports.setValue(" ");
        company.setValue(" ");
        type.setValue(" ");
        depart.setValue(" ");
        ports.setValue(" ");
        dvr.setText(" ");



    }



    public void home (ActionEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml")));
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
        stage.getIcons().add(new javafx.scene.image.Image(Objects.requireNonNull(getClass().getResourceAsStream("Assets/logo.png"))));
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }


}

