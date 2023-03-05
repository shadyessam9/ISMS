package main;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


class a {

}

public class homeController implements Initializable {

    @FXML
    private Label name;
    @FXML
    private Label depart;
    @FXML
    private Label nactive;
    @FXML
    private Label nmaintain;
    @FXML
    private Label ndepreciate;
    @FXML
    private Label dayn;
    @FXML
    private Label month;
    @FXML
    private Label day;
    @FXML
    private Label time;
    @FXML
    private Label zone;
    @FXML
    private TableView dashboard;
    @FXML
    private TableColumn device;
    @FXML
    private TableColumn person;
    @FXML
    private TableColumn info;
    @FXML
    private TableColumn rdate;
    @FXML
    private ComboBox c;
    @FXML
    private Button report;
    @FXML
    private Button devices;

    private double xOffset = 0.0D;
    private double yOffset = 0.0D;

    String key;
    String filename;
    String dt;
    String hour;

    int na;
    int nm;
    int nd;
    Connection conn ;
    Connection conn1 ;
    Statement statement;
    Statement statement1;
    Statement statement2;

    private ObservableList<homeController.ShowData> data;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        c.setItems(FXCollections.observableArrayList(getData()));

        Timeline Date = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
            dayn.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        Date.setCycleCount(Animation.INDEFINITE);
        Date.play();

        Timeline Month = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM");
            month.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        Month.setCycleCount(Animation.INDEFINITE);
        Month.play();

        Timeline Day = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE");
            day.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        Day.setCycleCount(Animation.INDEFINITE);
        Day.play();


        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" hh:mm ");
            time.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

        Timeline Zone = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("a");
            zone.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        Zone.setCycleCount(Animation.INDEFINITE);
        Zone.play();



        ScheduledExecutorService executor1 = Executors.newScheduledThreadPool(1);
        executor1.scheduleAtFixedRate(companyRunnable, 0, 1, TimeUnit.SECONDS);


        ScheduledExecutorService executor3 = Executors.newScheduledThreadPool(1);
        executor3.scheduleAtFixedRate(dataRunnable, 0, 5, TimeUnit.SECONDS);

        ScheduledExecutorService executor4 = Executors.newScheduledThreadPool(1);
        executor4.scheduleAtFixedRate(uiRunnable, 0, 5, TimeUnit.SECONDS);

        ScheduledExecutorService executor2 = Executors.newScheduledThreadPool(1);
        executor2.scheduleAtFixedRate(tableRunnable, 0, 5, TimeUnit.SECONDS);


    }

    Runnable companyRunnable = new Runnable() {
        public void run() { key = (String) c.getValue(); }
    };

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

    Runnable dataRunnable = new Runnable() {

        public void run() {

            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://" + Main.ip + "/adit", "root", "");
                Statement statement = conn.createStatement();
                statement.execute("select count(*) from devices WHERE st = 'on'  AND company = '" + key + "' ");
                ResultSet results = statement.getResultSet();
                while (results.next()) {
                    na = results.getInt(1);
                    conn.close();
                }

            } catch (Exception e) {

            }

            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://" + Main.ip + "/adit", "root", "");
                Statement statement = conn.createStatement();
                statement.execute("select count(*) from devices WHERE st = 'hold'  AND company = '" + key + "' ");
                ResultSet results = statement.getResultSet();
                while (results.next()) {
                    nm = results.getInt("count(*)");
                }


            } catch (Exception e) {

            }
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://" + Main.ip + "/adit", "root", "");
                Statement statement = conn.createStatement();
                statement.execute("select count(*) from devices WHERE st = 'off'  AND company = '" + key + "' ");
                ResultSet results = statement.getResultSet();
                while (results.next()) {
                    nd = results.getInt("count(*)");
                }


            } catch (Exception e) {

            }


        }
    };

    Runnable uiRunnable = new Runnable() {
        public void run() {
            try {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        report.setDisable(true);
                        devices.setDisable(true);
                        nactive.setText(String.valueOf(na));
                        nmaintain.setText(String.valueOf(nm));
                        ndepreciate.setText(String.valueOf(nd));
                        report.setDisable(false);
                        devices.setDisable(false);
                    }
                });
            } catch (Exception e) {

            }
        }
    };



    Runnable tableRunnable = new Runnable() {
        public void run() {

            try {
                conn = DriverManager.getConnection("jdbc:mysql://"+Main.ip+"/adit", "root", "");
                statement = conn.createStatement();
                statement.execute("SELECT * FROM requests AS r  LEFT JOIN devices AS d ON r.asscid = d.asscid  WHERE  d.company = '" +key+ "' AND r.state = '0' ");
                ResultSet results = statement.getResultSet();
                data= FXCollections.observableArrayList();
                while (results.next()) {
                    data.add(new homeController.ShowData(results.getString("type"),results.getString("name"),results.getString("request"),results.getString("r_date"),results.getString("company")));
                }
                device.setCellValueFactory(new PropertyValueFactory("object"));
                person.setCellValueFactory(new PropertyValueFactory("owner"+","+"Company"));
                info.setCellValueFactory(new PropertyValueFactory("problem"));
                rdate.setCellValueFactory(new PropertyValueFactory("requestdate"));
                dashboard.setItems(null);
                dashboard.setItems(data);
                conn.close();
            } catch (Exception e) {
                System.out.print(e);
            }

        }};


    public class ShowData {

        private final StringProperty object;
        private final StringProperty owner;
        private final StringProperty problem;
        private final StringProperty requestdate;
        private final StringProperty company;



        public ShowData(String object, String owner, String problem , String requestdate, String company) {
            this.object = new SimpleStringProperty(object);
            this.owner= new SimpleStringProperty(owner);
            this.problem = new SimpleStringProperty(problem);
            this.requestdate = new SimpleStringProperty(requestdate);
            this.company = new SimpleStringProperty(company);
        }

        public String getobject() { return object.get();}
        public void setobject(String value) { object.setValue(value); }
        public StringProperty objectProperty(){
            return object;
        }

        public String getowner() { return owner.get();}
        public void setowner(String value) { owner.setValue(value); }
        public StringProperty ownerProperty(){
            return owner;
        }

        public String getproblem() { return problem.get(); }
        public void setproblem(String value) { problem.setValue(value); }
        public StringProperty problemProperty(){
            return problem;
        }

        public String getrequestdate() { return requestdate.get(); }
        public void setrequestdate(String value) { requestdate.setValue(value); }
        public StringProperty requestdateProperty(){ return requestdate;
        }

        public String getcompany() { return company.get(); }
        public void setcompany(String value) { company.setValue(value); }
        public StringProperty companyProperty(){
            return company;
        }

    }


    public void createrep() throws  Exception{
        DateTimeFormatter t = DateTimeFormatter.ofPattern("hh:mm a");
        LocalDateTime now1 = LocalDateTime.now();
        hour = t.format(now1);

        DateTimeFormatter dtr = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        dt = dtr.format(now);

        filename = UUID.randomUUID().toString();


        FontFactory.registerDirectories();

        Font largeBold = new Font(Font.FontFamily.COURIER, 25, Font.BOLD| Font.UNDERLINE);


        String state = " ";
        String type = " ";
        String specs = " ";
        String name = " ";
        String position = " ";
        String depart = " ";


        try {

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://"+Main.ip+"/adit", "root", "");
            Statement stmt1 = conn2.createStatement();
            stmt1.execute("SELECT * FROM state");
            ResultSet resultsd1 = stmt1.getResultSet();


            Statement stmt2 = conn2.createStatement();
            Statement stmt3 = conn2.createStatement();
            Statement stmt4 = conn2.createStatement();
            Statement stmt5 = conn2.createStatement();




            Document my_pdf_report = new Document();
            PdfWriter.getInstance(my_pdf_report, new FileOutputStream("reports/report" + filename + ".pdf"));
            my_pdf_report.open();
            Font font = FontFactory.getFont("arial", BaseFont.IDENTITY_H, BaseFont.EMBEDDED,11);

            Paragraph Title = new Paragraph(" DONKOL INFRASTRUCTURE REPORT");
            Title.setAlignment(Element.ALIGN_CENTER);

            Paragraph details = new Paragraph("Company Name :  " + key + " ,  On : " + hour + " ," + dt);
            details.setAlignment(Element.ALIGN_CENTER);
            Title.setSpacingBefore(30);
            Title.setSpacingAfter(30);
            details.setSpacingAfter(50);
            my_pdf_report.add(Title);
            my_pdf_report.add(details);



            while (resultsd1.next()) {

                state=resultsd1.getString("state");

                Paragraph carmovment = new Paragraph("DEVICES STATE : " + state,largeBold);
                carmovment.setAlignment(Element.ALIGN_LEFT);
                carmovment.setSpacingAfter(20);
                my_pdf_report.add(carmovment);
                stmt3.execute("SELECT * FROM devices where st='" + state + "' AND company='" + key + "'");
                ResultSet resultsd3 = stmt3.getResultSet();

                PdfPTable my_first_table = new PdfPTable(5);
                PdfPCell table_cell1;
                PdfPCell table_cell2;
                PdfPCell table_cell3;
                PdfPCell table_cell4;
                PdfPCell table_cell5;

                while (resultsd3.next()) {


                    type=resultsd3.getString("type");
                    specs =resultsd3.getString("processor")+" , "+resultsd3.getString("ram")+" , "+resultsd3.getString("hardtype")+" , "+resultsd3.getString("hardcapacity")+"GB";
                    name=resultsd3.getString("name");
                    position=resultsd3.getString("position");
                    depart=resultsd3.getString("depart");

                    table_cell1 = new PdfPCell(new Phrase(new Chunk(type, font)));
                    table_cell1.setUseDescender(true);
                    table_cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table_cell1.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
                    my_first_table.addCell(table_cell1);

                    table_cell2 = new PdfPCell(new Phrase(new Chunk(specs, font)));
                    table_cell2.setUseDescender(true);
                    table_cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table_cell2.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
                    my_first_table.addCell(table_cell2);

                    table_cell3 = new PdfPCell(new Phrase(new Chunk(name, font)));
                    table_cell1.setUseDescender(true);
                    table_cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table_cell3.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
                    my_first_table.addCell(table_cell3);

                    table_cell4 = new PdfPCell(new Phrase(position, font));
                    table_cell4.setUseDescender(true);
                    table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table_cell4.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
                    table_cell4.setRowspan(1);
                    my_first_table.addCell(table_cell4);

                    table_cell5 = new PdfPCell(new Phrase(depart,FontFactory.getFont(FontFactory.defaultEncoding, 11)));
                    table_cell5.setUseDescender(true);
                    table_cell5.setExtraParagraphSpace(5);
                    table_cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table_cell5.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
                    my_first_table.addCell(table_cell5);


                }

                my_pdf_report.add(my_first_table);
                my_first_table.setSpacingAfter(25);
                LineSeparator ls3 = new LineSeparator();
                my_pdf_report.add(new Chunk(ls3));

            }



            File file = new File("reports/report" + filename + ".pdf");
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);
            my_pdf_report.close();
            conn2.close();


        } catch (SQLException e) {
            System.out.print(e);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR !");
            alert.setHeaderText("ERROR IN CONNECTION");
            alert.setContentText("PLEASE CHECK THE CONNECTION AND TRY AGAIN");
            alert.showAndWait();

        }



    }

    public void choice (ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("split.fxml")));
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
        catch (IOException e) {
        System.out.println(e);
        }


    }

    public void choice2 (ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("split2.fxml")));
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
        catch (IOException e) {
            System.out.println(e);
        }


    }
    public void exit() { System.exit(0); }
}





