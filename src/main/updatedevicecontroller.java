package main;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.application.Platform;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class updatedevicecontroller implements Initializable {
    private double xOffset = 0.0D;
    private double yOffset = 0.0D;





    @FXML
    private ComboBox scompany;
    @FXML
    private ComboBox sdepart;
    @FXML
    private ComboBox sperson;
    @FXML
    private ComboBox<String> processor;
    @FXML
    private ComboBox<String> device;
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
    private TextField search;
    @FXML
    private Button create;
    @FXML
    private Button s;
    @FXML
    public ImageView image;
    @FXML
    private Button state0;
    @FXML
    private Button state1;
    @FXML
    private Button state2;

    String filename;
    String dt;
    String state;
    String nv1 ;








    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        sdepart.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {

          nv1=String.valueOf(newValue);
          sperson.setItems(FXCollections.observableArrayList(getData()));
        });





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

        company.getItems().add("ABODONKOL");
        company.getItems().add("ARABIA");
        company.getItems().add("AAELA");
        company.getItems().add("MASRIA");
        company.getItems().add("ETHAD");
        company.getItems().add("feeds");
        company.getItems().add("makarona");
        company.getItems().add("madrab");


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


        List<String> persons = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + Main.ip + "/adit", "root", "");
            Statement statement = conn.createStatement();
            statement.execute("SELECT * FROM devices WHERE company='"+scompany.getValue()+"' AND depart='"+sdepart.getValue()+"'");
            ResultSet results = statement.getResultSet();
            while (results.next()) {
                persons.add(results.getString("name")+":"+results.getString("asscid"));
            }


        } catch (Exception e) {
            System.out.println(e);
        }

        return persons;
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
            model.setDisable(true);
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
            model.setDisable(true);
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
            model.setDisable(true);
            ports.setDisable(true);
        }

    }


    public  void search ()  {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://"+ Main.ip+"/adit", "root", "");
            Statement statement = conn.createStatement();
            String key = search.getText();
            statement.execute("SELECT * FROM devices where  asscid='" + String.valueOf(sperson).replaceAll("[^0-9]", "")+ "'");
            ResultSet results = statement.getResultSet();
            while (results.next()) {
                if(results.getString("type").equals("SWITCH")){
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
                    ports.setValue(results.getString("ports"));
                    model.setText(results.getString("model"));
                    device.setValue(results.getString("type"));
                    name.setText(results.getString("name"));
                   company.setValue(results.getString("company"));
                    type.setValue(results.getString("user_type"));
                    position.setText(results.getString("position"));
                    depart.setValue(results.getString("depart"));
                    if(results.getString("st").equals("on")){
                        state0.setStyle("-fx-background-color: #50B212; ");
                        state0.setText("ACTIVE");
                        state1.setText("HOLD");
                        state2.setText("OFF");
                        state1.setStyle("-fx-background-color: #E2E2E2; ");
                        state2.setStyle("-fx-background-color: #E2E2E2; ");
                    } else if (results.getString("st").equals("hold")){
                        state1.setStyle("-fx-background-color: #f2cd13; ");
                        state0.setText("ACTIVE");
                        state1.setText("HOLD");
                        state2.setText("OFF");
                        state0.setStyle("-fx-background-color: #E2E2E2; ");
                        state2.setStyle("-fx-background-color: #E2E2E2; ");
                    }else if (results.getString("st").equals("off")){
                        state2.setStyle("-fx-background-color: #BF1B28; ");
                        state0.setText("ACTIVE");
                        state1.setText("HOLD");
                        state2.setText("OFF");
                        state0.setStyle("-fx-background-color: #E2E2E2; ");
                        state1.setStyle("-fx-background-color: #E2E2E2; ");
                    }
                }else if(results.getString("type").equals("DVR")){
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
                    ports.setValue(results.getString("ports"));
                    model.setText(results.getString("model"));
                    ip.setText(results.getString("ip"));
                    device.setValue(results.getString("type"));
                    name.setText(results.getString("name"));
                    company.setValue(results.getString("company"));
                    type.setValue(results.getString("user_type"));
                    position.setText(results.getString("position"));
                    depart.setValue(results.getString("depart"));
                    if(results.getString("st").equals("on")){
                        state0.setStyle("-fx-background-color: #50B212; ");
                        state0.setText("ACTIVE");
                        state1.setText("HOLD");
                        state2.setText("OFF");
                        state1.setStyle("-fx-background-color: #E2E2E2; ");
                        state2.setStyle("-fx-background-color: #E2E2E2; ");
                    } else if (results.getString("st").equals("hold")){
                        state1.setStyle("-fx-background-color: #f2cd13; ");
                        state0.setText("ACTIVE");
                        state1.setText("HOLD");
                        state2.setText("OFF");
                        state0.setStyle("-fx-background-color: #E2E2E2; ");
                        state2.setStyle("-fx-background-color: #E2E2E2; ");
                    }else if (results.getString("st").equals("off")){
                        state2.setStyle("-fx-background-color: #BF1B28; ");
                        state0.setText("ACTIVE");
                        state1.setText("HOLD");
                        state2.setText("OFF");
                        state0.setStyle("-fx-background-color: #E2E2E2; ");
                        state1.setStyle("-fx-background-color: #E2E2E2; ");
                    }
                }else if(results.getString("type").equals("FINGERPRINT")){
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
                    ip.setText(results.getString("ip"));
                    model.setText(results.getString("model"));
                    device.setValue(results.getString("type"));
                    name.setText(results.getString("name"));
                    company.setValue(results.getString("company"));
                    type.setValue(results.getString("user_type"));
                    position.setText(results.getString("position"));
                    depart.setValue(results.getString("depart"));
                    if(results.getString("st").equals("on")){
                        state0.setStyle("-fx-background-color: #50B212; ");
                        state0.setText("ACTIVE");
                        state1.setText("HOLD");
                        state2.setText("OFF");
                        state1.setStyle("-fx-background-color: #E2E2E2; ");
                        state2.setStyle("-fx-background-color: #E2E2E2; ");
                    } else if (results.getString("st").equals("hold")){
                        state1.setStyle("-fx-background-color: #f2cd13; ");
                        state0.setText("ACTIVE");
                        state1.setText("HOLD");
                        state2.setText("OFF");
                        state0.setStyle("-fx-background-color: #E2E2E2; ");
                        state2.setStyle("-fx-background-color: #E2E2E2; ");
                    }else if (results.getString("st").equals("off")){
                        state2.setStyle("-fx-background-color: #BF1B28; ");
                        state0.setText("ACTIVE");
                        state1.setText("HOLD");
                        state2.setText("OFF");
                        state0.setStyle("-fx-background-color: #E2E2E2; ");
                        state1.setStyle("-fx-background-color: #E2E2E2; ");
                    }
                }else if(results.getString("type").equals("PRINTER")){
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
                    ip.setText(results.getString("ip"));
                    model.setText(results.getString("model"));
                    device.setValue(results.getString("type"));
                    name.setText(results.getString("name"));
                    company.setValue(results.getString("company"));
                    type.setValue(results.getString("user_type"));
                    position.setText(results.getString("position"));
                    depart.setValue(results.getString("depart"));
                    if(results.getString("st").equals("on")){
                        state0.setStyle("-fx-background-color: #50B212; ");
                        state0.setText("ACTIVE");
                        state1.setText("HOLD");
                        state2.setText("OFF");
                        state1.setStyle("-fx-background-color: #E2E2E2; ");
                        state2.setStyle("-fx-background-color: #E2E2E2; ");
                    } else if (results.getString("st").equals("hold")){
                        state1.setStyle("-fx-background-color: #f2cd13; ");
                        state0.setText("ACTIVE");
                        state1.setText("HOLD");
                        state2.setText("OFF");
                        state0.setStyle("-fx-background-color: #E2E2E2; ");
                        state2.setStyle("-fx-background-color: #E2E2E2; ");
                    }else if (results.getString("st").equals("off")){
                        state2.setStyle("-fx-background-color: #BF1B28; ");
                        state0.setText("ACTIVE");
                        state1.setText("HOLD");
                        state2.setText("OFF");
                        state0.setStyle("-fx-background-color: #E2E2E2; ");
                        state1.setStyle("-fx-background-color: #E2E2E2; ");
                    }
                }else if(results.getString("type").equals("CAMERA")){
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
                    dvr.setText(results.getString("dvr"));
                    model.setText(results.getString("model"));
                    device.setValue(results.getString("type"));
                    name.setText(results.getString("name"));
                    company.setValue(results.getString("company"));
                    type.setValue(results.getString("user_type"));
                    position.setText(results.getString("position"));
                    depart.setValue(results.getString("depart"));
                    if(results.getString("st").equals("on")){
                        state0.setStyle("-fx-background-color: #50B212; ");
                        state0.setText("ACTIVE");
                        state1.setText("HOLD");
                        state2.setText("OFF");
                        state1.setStyle("-fx-background-color: #E2E2E2; ");
                        state2.setStyle("-fx-background-color: #E2E2E2; ");
                    } else if (results.getString("st").equals("hold")){
                        state1.setStyle("-fx-background-color: #f2cd13; ");
                        state0.setText("ACTIVE");
                        state1.setText("HOLD");
                        state2.setText("OFF");
                        state0.setStyle("-fx-background-color: #E2E2E2; ");
                        state2.setStyle("-fx-background-color: #E2E2E2; ");
                    }else if (results.getString("st").equals("off")){
                        state2.setStyle("-fx-background-color: #BF1B28; ");
                        state0.setText("ACTIVE");
                        state1.setText("HOLD");
                        state2.setText("OFF");
                        state0.setStyle("-fx-background-color: #E2E2E2; ");
                        state1.setStyle("-fx-background-color: #E2E2E2; ");
                    }
                }else if (results.getString("type").equals("PC")){
                    BufferedImage bufferedImage = ImageIO.read(new File("Assets/pc.png"));
                    javafx.scene.image.Image pic = SwingFXUtils.toFXImage(bufferedImage, null);
                    image.setImage(pic);
                    model.setDisable(false);
                    ports.setDisable(true);
                    dvr.setDisable(true);
                    processor.setDisable(false);
                    hardtype.setDisable(false);
                    ram.setDisable(false);
                    hardcapacity.setDisable(false);
                    ip.setDisable(false);
                    processor.setValue(results.getString("processor"));
                    hardtype.setValue(results.getString("hardtype"));
                    ram.setText(results.getString("ram"));
                    hardcapacity.setText(results.getString("hardcapacity"));
                    ip.setText(results.getString("ip"));
                    device.setValue(results.getString("type"));
                    name.setText(results.getString("name"));
                    company.setValue(results.getString("company"));
                    type.setValue(results.getString("user_type"));
                    position.setText(results.getString("position"));
                    depart.setValue(results.getString("depart"));
                    if(results.getString("st").equals("on")){
                        state0.setStyle("-fx-background-color: #50B212; ");
                        state0.setText("ACTIVE");
                        state1.setText("HOLD");
                        state2.setText("OFF");
                        state1.setStyle("-fx-background-color: #E2E2E2; ");
                        state2.setStyle("-fx-background-color: #E2E2E2; ");
                    } else if (results.getString("st").equals("hold")){
                        state1.setStyle("-fx-background-color: #f2cd13; ");
                        state0.setText("ACTIVE");
                        state1.setText("HOLD");
                        state2.setText("OFF");
                        state0.setStyle("-fx-background-color: #E2E2E2; ");
                        state2.setStyle("-fx-background-color: #E2E2E2; ");
                    }else if (results.getString("st").equals("off")){
                        state2.setStyle("-fx-background-color: #BF1B28; ");
                        state0.setText("ACTIVE");
                        state1.setText("HOLD");
                        state2.setText("OFF");
                        state0.setStyle("-fx-background-color: #E2E2E2; ");
                        state1.setStyle("-fx-background-color: #E2E2E2; ");
                    }
                }else if(results.getString("type").equals("LAPTOP")){
                    BufferedImage bufferedImage = ImageIO.read(new File("Assets/laptop.png"));
                    javafx.scene.image.Image pic = SwingFXUtils.toFXImage(bufferedImage, null);
                    image.setImage(pic);
                    model.setDisable(false);
                    ports.setDisable(true);
                    dvr.setDisable(true);
                    processor.setDisable(false);
                    hardtype.setDisable(false);
                    ram.setDisable(false);
                    hardcapacity.setDisable(false);
                    ip.setDisable(false);
                    processor.setValue(results.getString("processor"));
                    hardtype.setValue(results.getString("hardtype"));
                    ram.setText(results.getString("ram"));
                    hardcapacity.setText(results.getString("hardcapacity"));
                    ip.setText(results.getString("ip"));
                    device.setValue(results.getString("type"));
                    name.setText(results.getString("name"));
                    company.setValue(results.getString("company"));
                    type.setValue(results.getString("user_type"));
                    position.setText(results.getString("position"));
                    depart.setValue(results.getString("depart"));
                    if(results.getString("st").equals("on")){
                        state0.setStyle("-fx-background-color: #50B212; ");
                        state0.setText("ACTIVE");
                        state1.setText("HOLD");
                        state2.setText("OFF");
                        state1.setStyle("-fx-background-color: #E2E2E2; ");
                        state2.setStyle("-fx-background-color: #E2E2E2; ");
                    } else if (results.getString("st").equals("hold")){
                        state1.setStyle("-fx-background-color: #f2cd13; ");
                        state0.setText("ACTIVE");
                        state1.setText("HOLD");
                        state2.setText("OFF");
                        state0.setStyle("-fx-background-color: #E2E2E2; ");
                        state2.setStyle("-fx-background-color: #E2E2E2; ");
                    }else if (results.getString("st").equals("off")){
                        state2.setStyle("-fx-background-color: #BF1B28; ");
                        state0.setText("ACTIVE");
                        state1.setText("HOLD");
                        state2.setText("OFF");
                        state0.setStyle("-fx-background-color: #E2E2E2; ");
                        state1.setStyle("-fx-background-color: #E2E2E2; ");
                    }
                }else if (results.getString("type").equals("SERVER")){
                    BufferedImage bufferedImage = ImageIO.read(new File("Assets/server.png"));
                    javafx.scene.image.Image pic = SwingFXUtils.toFXImage(bufferedImage, null);
                    image.setImage(pic);
                    model.setDisable(false);
                    ports.setDisable(true);
                    dvr.setDisable(true);
                    processor.setDisable(false);
                    hardtype.setDisable(false);
                    ram.setDisable(false);
                    hardcapacity.setDisable(false);
                    ip.setDisable(false);
                    processor.setValue(results.getString("processor"));
                    hardtype.setValue(results.getString("hardtype"));
                    ram.setText(results.getString("ram"));
                    hardcapacity.setText(results.getString("hardcapacity"));
                    ip.setText(results.getString("ip"));
                    device.setValue(results.getString("type"));
                    name.setText(results.getString("name"));
                    company.setValue(results.getString("company"));
                    type.setValue(results.getString("user_type"));
                    position.setText(results.getString("position"));
                    depart.setValue(results.getString("depart"));
                    if(results.getString("st").equals("on")){
                        state0.setStyle("-fx-background-color: #50B212; ");
                        state0.setText("ACTIVE");
                        state1.setText("HOLD");
                        state2.setText("OFF");
                        state1.setStyle("-fx-background-color: #E2E2E2; ");
                        state2.setStyle("-fx-background-color: #E2E2E2; ");
                    } else if (results.getString("st").equals("hold")){
                        state1.setStyle("-fx-background-color: #f2cd13; ");
                        state0.setText("ACTIVE");
                        state1.setText("HOLD");
                        state2.setText("OFF");
                        state0.setStyle("-fx-background-color: #E2E2E2; ");
                        state2.setStyle("-fx-background-color: #E2E2E2; ");
                    }else if (results.getString("st").equals("off")){
                        state2.setStyle("-fx-background-color: #BF1B28; ");
                        state0.setText("ACTIVE");
                        state1.setText("HOLD");
                        state2.setText("OFF");
                        state0.setStyle("-fx-background-color: #E2E2E2; ");
                        state1.setStyle("-fx-background-color: #E2E2E2; ");
                    }
                }
            }
        }
        catch(Exception e)
        {
            System.out.print(e);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR !");
            alert.setHeaderText("ERROR IN CONNECTION");
            alert.setContentText("PLEASE CHECK THE CONNECTION AND TRY AGAIN");
            alert.showAndWait();
        }
    }


    public void  clicked0() {

        state0.setStyle("-fx-background-color: #50B212; ");
        state0.setText("ACTIVE");
        state1.setText("HOLD");
        state2.setText("OFF");
        state1.setStyle("-fx-background-color: #E2E2E2; ");
        state2.setStyle("-fx-background-color: #E2E2E2; ");
        state="on";

    }
    public void  clicked1() {

        state1.setStyle("-fx-background-color: #f2cd13; ");
        state0.setText("ACTIVE");
        state1.setText("HOLD");
        state2.setText("OFF");
        state0.setStyle("-fx-background-color: #E2E2E2; ");
        state2.setStyle("-fx-background-color: #E2E2E2; ");
        state="hold";
    }
    public void  clicked2() {

        state2.setStyle("-fx-background-color: #BF1B28; ");
        state0.setText("ACTIVE");
        state1.setText("HOLD");
        state2.setText("OFF");
        state0.setStyle("-fx-background-color: #E2E2E2; ");
        state1.setStyle("-fx-background-color: #E2E2E2; ");
        state="off";
    }


    public void updateid (ActionEvent event) throws IOException, DocumentException {

        DateTimeFormatter dtr = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        dt = dtr.format(now);


        String id;
        DateTimeFormatter dtr1 = DateTimeFormatter.ofPattern("hh-mm-dd-MM-yyyy");
        LocalDateTime now1 = LocalDateTime.now();
        id = "DUD-"+dtr1.format(now1).replace("-", "");

        String key = search.getText();

        try
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://"+Main.ip+"/adit", "root", "");
            PreparedStatement stmt = conn.prepareStatement("UPDATE devices SET  type=? , picd=?, processor=? , ram=? , hardtype=? , hardcapacity=? , ip=? , model=? , ports=? , dvr=? , st=? , asscid=? , company=? , name=? , position=? , depart=? , password=? , user_type=? where  asscid='" + key + "' ");
            stmt.setString(1, device.getValue());
            stmt.setString(2, "abodonkol.myfirewall.co:3031/pics/'"+ device.getValue() +"'.png");
            stmt.setString(3, processor.getValue());
            stmt.setString(4, ram.getText());
            stmt.setString(5, hardtype.getValue());
            stmt.setString(6, hardcapacity.getText());
            stmt.setString(7, ip.getText());
            stmt.setString(8, model.getText());
            stmt.setString(9, dvr.getText());
            stmt.setString(10, type.getValue());
            stmt.setString(11, state);
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
        catch(Exception e)
        {
            e.printStackTrace();
        }



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




    public void print () throws IOException, DocumentException, SQLException, ClassNotFoundException {

     String id ;
        id = search.getText();



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
    }

    public void kpressed(KeyEvent kEvent) {
        if (kEvent.getCode() == KeyCode.ENTER) {
            name.setOnKeyPressed((KeyEvent event) ->{ switch (event.getCode()){
                case ENTER:ram.requestFocus();}});
            hardcapacity.setOnKeyPressed((KeyEvent event) ->{ switch (event.getCode()){
                case ENTER:position.requestFocus();}});
        }
    }

    public void home (ActionEvent event) throws IOException {
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
