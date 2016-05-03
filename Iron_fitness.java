/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iron_fitness;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
/**
 *
 * @author wwwfa
 */
public class Iron_fitness extends Application {
    
    Connection conn;
    PreparedStatement statement,statement2;
    ResultSet result,result2;
    TextField gym_id,name,age,height,weight,location,contract,ocupation,bloodgroup,search,memberid,amount,username,allsearch,name_up,age_up,height_up,
                weight_up,location_up,contract_up,ocupation_up,bloodgroup_up,deleteid;
    PasswordField passfield;
    DatePicker regdate,regdate_up;
    DatePicker paydate,paydate_update;
    DatePicker exdate,exdate_update;
    String formatedDate;
    ComboBox paytype,paytype_update;
    public Image profileimage;
    public ImageView proimageview;
    public Image image;
    public ImageView imageview;
    ScrollPane table_contain_2;
    StringConverter converter = new StringConverter<LocalDate>() {
                DateTimeFormatter dateFormatter = 
                    DateTimeFormatter.ofPattern(pattern);
                @Override
                public String toString(LocalDate date) {
                    if (date != null) {
                        return dateFormatter.format(date);
                    } else {
                        return "";
                    }
                }
                @Override
                public LocalDate fromString(String string) {
                    if (string != null && !string.isEmpty()) {
                        return LocalDate.parse(string, dateFormatter);
                    } else {
                        return null;
                    }
                }
            };
    
    
    TableView<User> table = new TableView<>();
    final ObservableList <User> data = FXCollections.observableArrayList();
    Label preview,info_label,member_id,member_id_text,name_label,name_text,age_label,age_text,gender_label,gender_text,height_label,height_text,
                                weight_label,weidht_text,ocupation_label,ocupation_text,location_label,
                                location_text,blood_label,blood_text,contract_label,contract_text,addate_label,addate_text;
    TableView<Payment> paytable = new TableView<>();
    final ObservableList <Payment> data_2 = FXCollections.observableArrayList();
    final ObservableList options = FXCollections.observableArrayList();
    final ObservableList options_up = FXCollections.observableArrayList();
    private final String pattern = "MM/dd/yyyy";
    public RadioButton male,male_up,female_up;
    public RadioButton female;
    String genderSelect;
    public File file;
    public FileChooser filechooser;
    public static String tempuserid,temptid;
    BorderPane layout = new BorderPane();
    public FileInputStream fis;
    HBox image_wrap;
    Button goback;
    Stage second_stage;
    Stage third_stage;
    Scene updatepay;
    BorderPane payview;
    Scene updatescene;
    BorderPane updateview;
    @Override
    public void start(Stage primaryStage) {
        
        layout.setStyle("-fx-background-color: #2E2E2E");
        
    
       
       
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        second_stage = new Stage();
        second_stage.setTitle("Profile and Update");
        
        second_stage.initStyle(StageStyle.UTILITY);
        second_stage.getIcons().add(new Image("file:iron_fitness.png"));
        updateview = new BorderPane();
        
        updatescene = new Scene(updateview,550,650,Color.rgb(0, 0, 0, 0));
        //third stage
        third_stage = new Stage();
        third_stage.setTitle("Update payment");
        third_stage.getIcons().add(new Image("file:iron_fitness.png"));
        payview  = new BorderPane();
        updatepay = new Scene(payview, 300, 400, Color.rgb(0, 0, 0, 0));
        third_stage.initStyle(StageStyle.UTILITY);
        //third stage
        primaryStage.setTitle("Login");
        primaryStage.getIcons().add(new Image("file:iron_fitness.png"));
        
        
        
        paytable.setTableMenuButtonVisible(true);
        checkConnection();
        
        BorderPane user_info = new BorderPane();
        Scene user_window = new Scene(user_info,1300,800,Color.rgb(0, 0, 0, 0));
        
        
                    
        Scene mainView = new Scene(layout,1300,800,Color.rgb(0, 0, 0, 0));
        Group root= new Group();
        
        Scene scene = new Scene(root,320,250,Color.rgb(0, 0, 0, 0));
        
        
        //Rectangila Background
        Rectangle background = new Rectangle(320,250);
        background.setX(0);
        background.setY(0);
        background.setArcHeight(15);
        background.setArcWidth(15);
        background.setFill(new ImagePattern(new Image("file:iron_fitness.png"), 0, 0, 1, 1, true));
        
        background.setStrokeWidth(1.5);
        VBox vbox = new VBox(5);
        vbox.setPadding(new Insets(10,0,0,10));
        Label label = new Label();
        label.setTextFill(Color.WHITESMOKE);
        label.setText("Log in");
        label.setFont(new Font("sanSerif",20));
        username = new TextField();
        username.setFont(Font.font("SanSerif", 20));
        username.setPromptText("Username");
        passfield = new PasswordField();
        passfield.setFont(Font.font("SanSerif", 20));
        passfield.setPromptText("Password");
        passfield.setOnKeyPressed(e ->
        {
            if(e.getCode() == KeyCode.ENTER)
            {
                try{
                String query = "select * from admin where username=? and password=?";
                statement = conn.prepareStatement(query);
                statement.setString(1, username.getText());
                statement.setString(2, passfield.getText());
                result = statement.executeQuery();
                if(result.next())
                {
                    primaryStage.setTitle("Iron Fitness");
                    primaryStage.setScene(mainView);
                    primaryStage.show();
                    refreshTable();
                }
                else
                {
                    label.setText("Login Failed");
                }
                username.clear();
                passfield.clear();
                statement.close();
                result.close();
            }
            catch( Exception e1)
            {
                System.out.println(e1);
            }
            }
        });
        Button btn = new Button();
        btn.setText("Login");
        btn.setFont(Font.font("SanSerif", 20));
        btn.setOnAction(e ->
        {
            try{
                String query = "select * from admin where username=? and password=?";
                statement = conn.prepareStatement(query);
                statement.setString(1, username.getText());
                statement.setString(2, passfield.getText());
                result = statement.executeQuery();
                if(result.next())
                {
                    primaryStage.setTitle("Iron Fitness");
                    primaryStage.setScene(mainView);
                    primaryStage.show();
                    refreshTable();
                }
                else
                {
                    label.setText("Login Failed");
                }
                username.clear();
                passfield.clear();
                statement.close();
                result.close();
            }
            catch( Exception e1)
            {
                System.out.println(e1);
            }
        });
        Button logout = new Button("Logout");
        
        logout.setFont(Font.font("SanSerif",15));
        Label power_by = new Label();
          power_by.setText("Owned By: Iron Fitness,year: 2015-2016"+"\n"+"Developed By: Faz13 || gmail: www.faz13@gmail.com");
          power_by.setWrapText(true);
          power_by.setFont(Font.font("SanSerif", FontWeight.BOLD, 10));
          power_by.setTextAlignment(TextAlignment.RIGHT);
          power_by.setPadding(new Insets(20,0,0,0));
          power_by.setTextFill(Color.web("#FFFFFF"));
        logout.setOnAction(e ->
        {
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("");
                    alert.setContentText("You will be logged out and application will be closed. Are you ok with this?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        Platform.exit();
                    } else {
                        
                        alert.close();
                    }
                
            
        });
        
      //second stage GUI
      //second stage top
      image_wrap = new HBox();
      
      Rectangle image_pos = new Rectangle(100, 100);
      image_pos.setFill(Color.WHITE);
      
      goback = new Button();
      goback.setOnAction(e ->
      {
          clearfields();
          image_wrap.getChildren().removeAll(imageview,goback);
          second_stage.close();
      });
      second_stage.setOnCloseRequest(e ->
        {
            image_wrap.getChildren().removeAll(imageview,goback);
        });
      
      
      image_wrap.setSpacing(250);
      
      updateview.setTop(image_wrap);
      BorderPane.setMargin(image_wrap, new Insets(10,10,10,10));
      filechooser = new FileChooser();
      filechooser.getExtensionFilters().addAll(
              new FileChooser.ExtensionFilter("Image File", "*.png","*.jpg","*.gif"),
              new FileChooser.ExtensionFilter("Text Files", "*.txt"),
             
              new FileChooser.ExtensionFilter("All Files", "*")
      );
      //second stage to ends
      
      //third stage starts
      VBox payupdate = new VBox(5);
      
      TextField tran_id = new TextField();
      tran_id.setPromptText("Transaction Id");
      tran_id.setMaxWidth(200);
      
      paydate_update = new DatePicker();
      paydate_update.setPromptText("Payment date");
      paydate_update.setMaxWidth(200);

      exdate_update = new DatePicker();
      exdate_update.setPromptText("Expire date");
      exdate_update.setMaxWidth(200);
      
      paytype_update = new ComboBox(options_up);
      paytype_update.setMaxHeight(30);
      paytype_update.setPromptText("Type");
      options_up.addAll("Monthly","Half yearly","Yearly","Registration","-");
      
      TextField amount_update = new TextField();
      amount_update.setPromptText("amount");
      amount_update.setMaxWidth(200);
      
      Button update_pay = new Button();
      update_pay.setText("Update Payment");
      
      payupdate.getChildren().addAll(tran_id,paydate_update,exdate_update,paytype_update,amount_update,update_pay);
      
      payview.setCenter(payupdate);
      
      BorderPane.setAlignment(payupdate, Pos.CENTER);
      BorderPane.setMargin(payupdate, new Insets(10,10,10,10));
      //third stage ends
      
      VBox info = new VBox(5);
      info.setPadding(new Insets(10,10,10,10));
      info.setMaxWidth(300);
      info.setMinWidth(300);
      info.setStyle("-fx-background-color: #FFFFFF;");
      ScrollPane info_contain = new ScrollPane();
      info_contain.setContent(info);
      info_contain.setPrefSize(300, 600);
      info_contain.setFitToHeight(true);
      info_contain.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
      info_contain.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
      info_label = new Label();
      info_label.setText("Member Information");
      info_label.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
      
      member_id = new Label();
      member_id.setText("Member ID:");
      member_id.setWrapText(true);
      member_id.setFont(Font.font("SanSerif", FontWeight.BOLD, 10));
      member_id.setTextAlignment(TextAlignment.LEFT);
      
      member_id_text = new Label();
      
      member_id_text.setWrapText(true);
      member_id_text.setTextAlignment(TextAlignment.LEFT);
      
      name_label = new Label();
      name_label.setText("Member name:");
      name_label.setWrapText(true);
      name_label.setFont(Font.font("sanSerif", FontWeight.BOLD, 10));
      name_label.setTextAlignment(TextAlignment.LEFT);
      
      name_text = new Label();
      
      name_text.setWrapText(true);
      name_text.setTextAlignment(TextAlignment.LEFT);
      
      age_label = new Label();
      age_label.setText("Age:");
      age_label.setWrapText(true);
      age_label.setFont(Font.font("sanSerif", FontWeight.BOLD, 10));
      age_label.setTextAlignment(TextAlignment.LEFT);
      
      age_text = new Label();
      
      age_text.setWrapText(true);
      age_text.setTextAlignment(TextAlignment.LEFT);
      
      height_label = new Label();
      height_label.setText("Height:");
      height_label.setWrapText(true);
      height_label.setFont(Font.font("sanSerif", FontWeight.BOLD, 10));
      height_label.setTextAlignment(TextAlignment.LEFT);
      
      height_text = new Label();
      
      height_text.setWrapText(true);
      height_text.setTextAlignment(TextAlignment.LEFT);
      
      weight_label = new Label();
      weight_label.setText("Weight:");
      weight_label.setWrapText(true);
      weight_label.setFont(Font.font("sanSerif", FontWeight.BOLD, 10));
      weight_label.setTextAlignment(TextAlignment.LEFT);
      
      weidht_text = new Label();
      
      weidht_text.setWrapText(true);
      weidht_text.setTextAlignment(TextAlignment.LEFT);
      
      location_label = new Label();
      location_label.setText("Locaion:");
      location_label.setWrapText(true);
      location_label.setFont(Font.font("sanSerif", FontWeight.BOLD, 10));
      location_label.setTextAlignment(TextAlignment.LEFT);
      
      
      
      location_text = new Label();
      
      location_text.setWrapText(true);
      location_text.setTextAlignment(TextAlignment.LEFT);
      
      gender_label = new Label();
      gender_label.setText("Gender:");
      gender_label.setFont(Font.font("sanSerif", FontWeight.BOLD, 10));
      gender_label.setWrapText(true);
      gender_label.setTextAlignment(TextAlignment.LEFT);
      
      
      
      gender_text = new Label();
      
      gender_text.setWrapText(true);
      gender_text.setTextAlignment(TextAlignment.LEFT);
      
      contract_label = new Label();
      contract_label.setText("Contract No:");
      contract_label.setWrapText(true);
      contract_label.setFont(Font.font("sanSerif", FontWeight.BOLD, 10));
      contract_label.setTextAlignment(TextAlignment.LEFT);
      
      contract_text = new Label();
      
      contract_text.setWrapText(true);
      contract_text.setTextAlignment(TextAlignment.LEFT);
      
      blood_label = new Label();
      blood_label.setText("Blood Group:");
      blood_label.setWrapText(true);
      blood_label.setFont(Font.font("sanSerif", FontWeight.BOLD, 10));
      blood_label.setTextAlignment(TextAlignment.LEFT);
      
      blood_text = new Label();
      
      blood_text.setWrapText(true);
      blood_text.setTextAlignment(TextAlignment.LEFT);
      
      addate_label = new Label();
      addate_label.setText("registration date:");
      addate_label.setWrapText(true);
      addate_label.setFont(Font.font("sanSerif", FontWeight.BOLD, 10));
      addate_label.setTextAlignment(TextAlignment.LEFT);
      
      addate_text = new Label();
      
      addate_text.setWrapText(true);
      addate_text.setTextAlignment(TextAlignment.LEFT);
      
      ocupation_label = new Label();
      ocupation_label.setText("Ocupation:");
      ocupation_label.setWrapText(true);
      ocupation_label.setFont(Font.font("sanSerif", FontWeight.BOLD, 10));
      ocupation_label.setTextAlignment(TextAlignment.LEFT);
      
      ocupation_text = new Label();
      
      ocupation_text.setWrapText(true);
      ocupation_text.setTextAlignment(TextAlignment.LEFT);
      
      
      
      info.getChildren().addAll(info_label,member_id,member_id_text,name_label,name_text,age_label,age_text,gender_label,gender_text,height_label,height_text,
                                weight_label,weidht_text,ocupation_label,ocupation_text,location_label,
                                location_text,blood_label,blood_text,contract_label,contract_text,addate_label,addate_text);
      
      
      BorderPane.setMargin(info, new Insets(10,10,10,10));
      BorderPane.setAlignment(info, Pos.CENTER);
      updateview.setTop(image_wrap);
      updateview.setLeft(info_contain);
      //update form
      VBox update_form = new VBox();
      Label update_label = new Label();
      update_label.setText("Update Information");
      BorderPane.setMargin(update_form, new Insets(10,10,10,10));
      update_label.setFont(new Font("SanSerif",20));
      
        
        
        name_up = new TextField();
        name_up.setFont(Font.font("SanSerif", 15));
        name_up.setPromptText("Full Name");
        name_up.setMaxWidth(200);
        
        age_up = new TextField();
        age_up.setFont(Font.font("SanSerif", 15));
        age_up.setPromptText("Age");
        age_up.setMaxWidth(200);
        
        height_up = new TextField();
        height_up.setFont(Font.font("SanSerif", 15));
        height_up.setPromptText("Height");
        height_up.setMaxWidth(200);
        
        weight_up = new TextField();
        weight_up.setFont(Font.font("SanSerif", 15));
        weight_up.setPromptText("Weight");
        weight_up.setMaxWidth(200);
        
        location_up = new TextField();
        location_up.setFont(Font.font("SanSerif", 15));
        location_up.setPromptText("Address");
        location_up.setMaxWidth(200);
        
        contract_up = new TextField();
        contract_up.setFont(Font.font("SanSerif", 15));
        contract_up.setPromptText("Contract");
        contract_up.setMaxWidth(200);
        
        ocupation_up = new TextField();
        ocupation_up.setFont(Font.font("SanSerif", 15));
        ocupation_up.setPromptText("Ocupation");
        ocupation_up.setMaxWidth(200);
        
        bloodgroup_up = new TextField();
        bloodgroup_up.setFont(Font.font("SanSerif", 15));
        bloodgroup_up.setPromptText("Blood Group");
        bloodgroup_up.setMaxWidth(200);
        
        regdate_up = new DatePicker();
        regdate_up.setPromptText("registration date");
        regdate_up.setMaxWidth(200);
        
        ToggleGroup update_ugender =new ToggleGroup();
        male_up=new RadioButton("Male");
        male_up.setToggleGroup(update_ugender);
        
        male_up.setOnAction(e ->
        {
            genderSelect = male_up.getText();
        });
        female_up=new RadioButton("Female");
        female_up.setToggleGroup(update_ugender);
        ;
        female_up.setOnAction(e ->
        {
            genderSelect = female_up.getText();
        });
       Button update_image = new Button("Set Image");
      
      update_image.setOnAction(e ->
      {
          file = filechooser.showOpenDialog(second_stage);
          if(file != null)
          {
             
                  preview = new Label("Preview of choosen image");
                  profileimage = new Image(file.toURI().toString(), 100, 100, true, true);
                  
                  proimageview = new ImageView(profileimage);
                  proimageview.setFitHeight(100);
                  proimageview.setFitWidth(100);
                  proimageview.setPreserveRatio(true);
                  update_form.getChildren().add(proimageview);
                  update_form.getChildren().add(preview);
                  
                  
          }
      });
      
      Button update = new Button("Update");
      update.setOnAction(e ->
      {
          try{
              System.out.println(tempuserid);
              System.out.println(name_up.getText());
              System.out.println(height_up.getText());
                
                String query = "update memberinfo set name=?, age=?, height=?, weight=?, location=?, contract=?, ocupation=?, bloodgroup=?, addate=?, gender=? where memid='"+tempuserid+"'";
                
                statement = conn.prepareStatement(query);
                
                statement.setString(1, name_up.getText());
                statement.setString(2, age_up.getText());
                statement.setString(3, height_up.getText());
                statement.setString(4, weight_up.getText());
                statement.setString(5, location_up.getText());
                statement.setString(6, contract_up.getText());
                statement.setString(7, ocupation_up.getText());
                statement.setString(8, bloodgroup_up.getText());
                regdate.setConverter(converter);
                statement.setString(9, ((TextField)regdate_up.getEditor()).getText());
                    
                
                statement.setString(10, genderSelect);
                
                
                
                Alert aleart = new Alert(Alert.AlertType.INFORMATION);
                aleart.setTitle("Info");
                aleart.setHeaderText(null);
                aleart.setContentText("Information has been updated");
                aleart.showAndWait();
                statement.execute();
                clearfields();
                statement.close();
                
                if(file!=null)
                {
                    String query2 = "update memberinfo set image=? where memid='"+tempuserid+"'";
                    statement2 = conn.prepareStatement(query2);
                    
                    fis = new FileInputStream(file);
                    statement2.setBinaryStream(1, (InputStream)fis, (int)file.length());
                   
                    statement2.execute();
                    statement2.close();
                }
                
                file = null;
                proimageview.setImage(null);
                System.gc();
                update_form.getChildren().remove(proimageview);
                update_form.getChildren().remove(preview);
                
                image_wrap.getChildren().removeAll(imageview,goback);
                clearfields();
                second_stage.close();
            }
            catch( Exception e1)
            {
                System.out.println(e1);
            }
         
      });
      
      update_form.getChildren().addAll(update_label,name_up,age_up,height_up,
                weight_up,location_up,contract_up,ocupation_up,bloodgroup_up,regdate_up,male_up,female_up,update_image,update);
      updateview.setCenter(update_form);
      
      
      
      //update form ends
      //second stage GUI ends
        
        
        TableColumn memid = new TableColumn("Member name");
        memid.setMinWidth(100);
        memid.setCellValueFactory(new PropertyValueFactory<>("memid"));
        
        TableColumn mem_name = new TableColumn("Member name");
        mem_name.setMinWidth(100);
        mem_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn ages = new TableColumn("Age");
        ages.setMinWidth(100);
        ages.setCellValueFactory(new PropertyValueFactory<>("age"));
        
        TableColumn heights = new TableColumn("Heights");
        heights.setMinWidth(100);
        heights.setCellValueFactory(new PropertyValueFactory<>("height"));
        
        TableColumn weights = new TableColumn("Weight");
        weights.setMinWidth(100);
        weights.setCellValueFactory(new PropertyValueFactory<>("weight"));
        
        TableColumn genders = new TableColumn("Gender");
        genders.setMinWidth(100);
        genders.setCellValueFactory(new PropertyValueFactory<>("gender"));
        
        TableColumn locations = new TableColumn("Location");
        locations.setMinWidth(200);
        locations.setCellValueFactory(new PropertyValueFactory<>("location"));
        
        TableColumn contracts = new TableColumn("Contract No.");
        contracts.setMinWidth(100);
        contracts.setCellValueFactory(new PropertyValueFactory<>("contract"));
        
        TableColumn bloodgroups = new TableColumn("Bloodgroup");
        bloodgroups.setMinWidth(100);
        bloodgroups.setCellValueFactory(new PropertyValueFactory<>("bloodgroup"));
        
        TableColumn ocupations = new TableColumn("Ocupation");
        ocupations.setMinWidth(100);
        ocupations.setCellValueFactory(new PropertyValueFactory<>("ocupation"));
        
        TableColumn reg_dates = new TableColumn("Registration date");
        reg_dates.setMinWidth(100);
        reg_dates.setCellValueFactory(new PropertyValueFactory<>("addate"));
        
        table.getColumns().addAll(memid,mem_name,ages,heights,weights,genders,
                locations,contracts,bloodgroups,ocupations,reg_dates);
        ScrollPane table_contain = new ScrollPane();
        table_contain.setContent(table);
        table_contain.setPrefSize(900, 600);
        table_contain.setFitToHeight(true);
        
        table_contain.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        table_contain.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        layout.setRight(table_contain);
        
        table.setTableMenuButtonVisible(true);
        BorderPane.setMargin(table_contain, new Insets(0,10,10,10));
        
        //Payment table
        TableColumn tid_col = new TableColumn("Transiction ID");
        tid_col.setMinWidth(150);
        tid_col.setCellValueFactory(new PropertyValueFactory<>("tid"));
        
        TableColumn memid_col = new TableColumn("Member ID");
        memid_col.setMinWidth(150);
        memid_col.setCellValueFactory(new PropertyValueFactory<>("memidinfo"));
        
        TableColumn paydate_col = new TableColumn("Payment Date");
        paydate_col.setMinWidth(150);
        paydate_col.setCellValueFactory(new PropertyValueFactory<>("pdate"));
        
        TableColumn exdate_col = new TableColumn("Expire date");
        exdate_col.setMinWidth(150);
        exdate_col.setCellValueFactory(new PropertyValueFactory<>("exdate"));
        
        TableColumn paytype_col = new TableColumn("Pay type");
        paytype_col.setMinWidth(150);
        paytype_col.setCellValueFactory(new PropertyValueFactory<>("ptype"));
        
        TableColumn amount_col = new TableColumn("Amount(taka)");
        amount_col.setMinWidth(150);
        amount_col.setCellValueFactory(new PropertyValueFactory<>("amount"));
        
        paytable.getColumns().addAll(tid_col,memid_col,paydate_col,exdate_col,paytype_col,amount_col);
        table_contain_2 = new ScrollPane();
        table_contain_2.setContent(paytable);
        table_contain_2.setPrefSize(900, 600);
        table_contain_2.setFitToHeight(true);
        table_contain_2.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        table_contain_2.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        //payment table
        
        Button load_all = new Button("Load All");
        load_all.setFont(Font.font("SanSerif", 15));
        load_all.setOnAction(e ->
        {
            layout.setRight(table_contain);
            refreshTable();
        });
        
        Button payment_due = new Button("Due payment");
        payment_due.setFont(Font.font("SanSerif", 15));
        payment_due.setOnAction(e ->
        {
            data.clear();
            try
            {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                
                String query = "SELECT * FROM memberinfo where addate<'"+sdf.format(cal.getTime())+"'";
                statement = conn.prepareStatement(query);
                
                result = statement.executeQuery();
                while(result.next())
                {
                    data.add(new User(
                            
                            result.getString("memid"),
                            result.getString("name"),
                            result.getString("age"),
                            result.getString("height"),
                            result.getString("weight"),
                            result.getString("gender"),
                            result.getString("location"),
                            result.getString("contract"),
                            result.getString("bloodgroup"),
                            result.getString("ocupation"),
                            result.getString("addate")
                    ));
                    table.setItems(data);
                }
                
                    statement.close();
                    result.close();
            }
            catch(Exception e4)
            {
                System.out.println(e4);
            }
        });
        
        
        
        //search fields
        
        search=new TextField();
        search.setFont(Font.font("SanSerif", 15));
        search.setPromptText("Search Payment");
        search.setMaxWidth(200);
        Button search_btn = new Button("Search");
        search_btn.setFont(Font.font("SanSerif", 15));
        search_btn.setOnAction(e ->
        {
            paytableRefresh();
        });
        //search ends
        //top menu
        HBox top_menu = new HBox(5);
        top_menu.setSpacing(10);
        allsearch = new TextField();
        allsearch.setFont(Font.font("SanSerif", 15));
        allsearch.setPromptText("Valid ID");
        allsearch.setMaxWidth(200);
        
        deleteid = new TextField();
        deleteid.setFont(Font.font("SanSerif", 15));
        deleteid.setPromptText("Delete Member");
        deleteid.setMaxWidth(200);
        
        Button delete = new Button("Delete");
        delete.setFont(Font.font("SanSerif", 15));
        delete.setOnAction(e ->
        {
            try {
                String query2 = "select memid from memberinfo where memid=?";
                statement2 = conn.prepareStatement(query2);
                statement2.setString(1, deleteid.getText());
                result2 = statement2.executeQuery();
                if(result2.next())
                {
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Look, a Confirmation Dialog");
                    alert.setContentText("This action will delete all the information conserning the given ID. Are you ok with this?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        String query = "delete from memberinfo where memid=?";
                        statement = conn.prepareStatement(query);
                        statement.setString(1, deleteid.getText());
                        statement.execute();
                        statement.close();
                        String query3 = "delete from userinfo where memid=?";
                        statement = conn.prepareStatement(query3);
                        statement.setString(1, deleteid.getText());
                        statement.execute();
                        statement.close();
                        deleteid.clear();
                    } else {
                        deleteid.clear();
                        alert.close();
                        
                    }
                }
                else
                {
                        Alert aleart = new Alert(Alert.AlertType.INFORMATION);
                        aleart.setTitle("Info");
                        aleart.setHeaderText(null);
                        aleart.setContentText("This member ID does not exists ");
                        aleart.showAndWait();
                }
                statement2.close();
                result2.close();
                refreshTable();
                
            } catch (SQLException ex) {
                Logger.getLogger(Iron_fitness.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        Button viewinfo = new Button("Info and Update");
        viewinfo.setFont(Font.font("SanSerif", 15));
        viewinfo.setOnAction(e ->
        {
            viewupdate();
                    
        });
        
        Button updatepay = new Button("Update Payment");
        updatepay.setFont(Font.font("SanSerif", 15));
        updatepay.setOnAction(e ->
        {
            temptid = allsearch.getText();
            third_stage.setScene(this.updatepay);
            third_stage.show();
        });
        //Update payment
        update_pay.setOnAction(e ->
        {
            if(tran_id.getText() != null && 
                    ((TextField)paydate_update.getEditor()).getText() != null && 
                    ((TextField)exdate_update.getEditor()).getText() != null && 
                    amount_update.getText() != null)
            {
                try {
                    String query2 = "select id from userinfo where id=?";
                    statement2 = conn.prepareStatement(query2);
                    statement2.setString(1, tran_id.getText());
                    result2 = statement2.executeQuery();
                    if(result2.next())
                    {
                    try {
                        String query = "update userinfo set id=?, paydate=?, exdate=?, paytype=?,amount=? where id='"+tran_id.getText()+"'";
                        statement = conn.prepareStatement(query);
                        
                        statement.setString(1, tran_id.getText());
                        paydate_update.setConverter(converter);
                        statement.setString(2, ((TextField)paydate_update.getEditor()).getText());
                        exdate_update.setConverter(converter);
                        statement.setString(3, ((TextField)exdate_update.getEditor()).getText());
                        statement.setString(4, (String)paytype_update.getSelectionModel().getSelectedItem());
                        statement.setString(5, amount_update.getText());
                        statement.execute();
                        tran_id.clear();
                        paydate_update.setValue(null);
                        exdate_update.setValue(null);
                        amount_update.clear();
                        paytype_update.getSelectionModel().clearSelection();
                        third_stage.close();
                        statement.close();
                        result.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Iron_fitness.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    statement2.close();
                    result2.close();
                    }
                    else
                    {
                        Alert aleart = new Alert(Alert.AlertType.ERROR);
                        aleart.setTitle("Info");
                        aleart.setHeaderText(null);
                        aleart.setContentText("Transaction ID not valid");
                        aleart.showAndWait();
                    }
                    
                } catch (SQLException ex) {
                    Logger.getLogger(Iron_fitness.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            else
            {
                        Alert aleart = new Alert(Alert.AlertType.ERROR);
                        aleart.setTitle("Info");
                        aleart.setHeaderText(null);
                        aleart.setContentText("Some fields are empty");
                        aleart.showAndWait();
            }
        });
        //updatePayment ends
        top_menu.getChildren().addAll(search,search_btn,allsearch,viewinfo,updatepay,deleteid,delete,load_all,logout);
        layout.setTop(top_menu);
        
        BorderPane.setMargin(top_menu, new Insets(10,10,10,15));
        BorderPane.setAlignment(top_menu, Pos.TOP_RIGHT);
        
        //top menu ends
        vbox.getChildren().addAll(label,username,passfield,btn,power_by);
        root.getChildren().addAll(background,vbox);
        VBox fields = new VBox(5);
        fields.setMaxWidth(200);
        Label create_user_label = new Label();
        create_user_label.setText("Create User");
        create_user_label.setTextFill(Color.web("#FFFFFF"));
        create_user_label.setFont(Font.font("SanSerif", 17));
        
        gym_id = new TextField();
        gym_id.setFont(Font.font("SanSerif", 13));
        gym_id.setPromptText("ID");
        gym_id.setMaxWidth(200);
        
        name = new TextField();
        name.setFont(Font.font("SanSerif", 13));
        name.setPromptText("Full Name");
        name.setMaxWidth(200);
        
        age = new TextField();
        age.setFont(Font.font("SanSerif", 13));
        age.setPromptText("Age");
        age.setMaxWidth(200);
        
        height = new TextField();
        height.setFont(Font.font("SanSerif", 13));
        height.setPromptText("Height");
        height.setMaxWidth(200);
        
        weight = new TextField();
        weight.setFont(Font.font("SanSerif", 13));
        weight.setPromptText("Weight");
        weight.setMaxWidth(200);
        
        location = new TextField();
        location.setFont(Font.font("SanSerif", 13));
        location.setPromptText("Address");
        location.setMaxWidth(200);
        
        contract = new TextField();
        contract.setFont(Font.font("SanSerif", 13));
        contract.setPromptText("Contract");
        contract.setMaxWidth(200);
        
        ocupation = new TextField();
        ocupation.setFont(Font.font("SanSerif", 13));
        ocupation.setPromptText("Ocupation");
        ocupation.setMaxWidth(200);
        
        bloodgroup = new TextField();
        bloodgroup.setFont(Font.font("SanSerif", 13));
        bloodgroup.setPromptText("Blood Group");
        bloodgroup.setMaxWidth(200);
        
        regdate = new DatePicker();
        regdate.setPromptText("registration date");
        regdate.setMaxWidth(200);
        
        ToggleGroup gender =new ToggleGroup();
        male=new RadioButton("Male");
        male.setTextFill(Color.web("#FFFFFF"));
        male.setToggleGroup(gender);
        male.setOnAction(e ->
        {
            genderSelect = male.getText();
        });
        female=new RadioButton("Female");
        female.setToggleGroup(gender);
        female.setTextFill(Color.web("#FFFFFF"));
        female.setOnAction(e ->
        {
            genderSelect = female.getText();
        });
        
        Button set_image = new Button("Set Image");
      
      set_image.setOnAction(e ->
      {
          file = filechooser.showOpenDialog(second_stage);
          if(file != null)
          {
              
            Label preview = new Label("Preview of choosen image");
            profileimage = new Image(file.toURI().toString(), 100, 100, true, true);
            proimageview = new ImageView(profileimage);
            proimageview.setFitHeight(100);
            proimageview.setFitWidth(100);
            proimageview.setPreserveRatio(true);
            fields.getChildren().add(proimageview);
            fields.getChildren().add(preview);
          }
      });
        Button create_member = new Button();
        create_member.setText("Save");
        create_member.setFont(Font.font("SanSerif", 13));
        
        create_member.setOnAction(e ->
        {
            try{
                if (gym_id.getText() == null || gym_id.getText().trim().isEmpty()) {
                    // your code here
                    Alert aleart = new Alert(Alert.AlertType.ERROR);
                    aleart.setTitle("Info");
                    aleart.setHeaderText(null);
                    aleart.setContentText("Member Id not given");
                    aleart.showAndWait();
                    file = null;
                    proimageview.setImage(null);
                    System.gc();
                    clearfields();
                }
                String query2 = "select memid from memberinfo where memid=?";
                statement2 = conn.prepareStatement(query2);
                statement2.setString(1, gym_id.getText());
                result2 = statement2.executeQuery();
                if(result2.next())
                {
                    Alert aleart = new Alert(Alert.AlertType.ERROR);
                    aleart.setTitle("Info");
                    aleart.setHeaderText(null);
                    aleart.setContentText("This id is already registered");
                    aleart.showAndWait();
                    file = null;
                    proimageview.setImage(null);
                    System.gc();
                    clearfields();
                }
                else
                {
                    if(file != null)
                {
                String query = "insert into memberinfo (memid,name,age,height,"
                        + "weight,location,contract,ocupation,bloodgroup,addate,gender,image) "
                        + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
                
                statement = conn.prepareStatement(query);
                
                statement.setString(1, gym_id.getText());
                statement.setString(2, name.getText());
                statement.setString(3, age.getText());
                statement.setString(4, height.getText());
                statement.setString(5, weight.getText());
                statement.setString(6, location.getText());
                statement.setString(7, contract.getText());
                statement.setString(8, ocupation.getText());
                statement.setString(9, bloodgroup.getText());
                    
                regdate.setConverter(converter);
                statement.setString(10, ((TextField)regdate.getEditor()).getText());
                statement.setString(11, genderSelect);
                
                fis = new FileInputStream(file);
                statement.setBinaryStream(12, (InputStream)fis, (int)file.length());
                Alert aleart = new Alert(Alert.AlertType.INFORMATION);
                aleart.setTitle("Info");
                aleart.setHeaderText(null);
                aleart.setContentText("A new gym member has been created");
                aleart.showAndWait();
                
                statement.execute();
                
                clearfields();
                statement.close();
                file = null;
                proimageview.setImage(null);
                System.gc();
                
                }
                else
                {
                    Alert aleart = new Alert(Alert.AlertType.INFORMATION);
                    aleart.setTitle("Info");
                    aleart.setHeaderText(null);
                    aleart.setContentText("Image not given");
                    aleart.showAndWait();
                    file = null;
                    proimageview.setImage(null);
                    System.gc();
                    clearfields();
                    clearfields();
                }
                }
                statement2.close();
                result2.close();
                
            }
            catch( Exception e1)
            {
                System.out.println(e1);
            }
            refreshTable();
        });
        
        fields.getChildren().addAll(create_user_label,gym_id,name,age,height,
                weight,location,contract,ocupation,bloodgroup,regdate,male,female,set_image,create_member);
        fields.setStyle("-fx-padding: 10;" + 
                      "-fx-border-style: solid inside;" + 
                      "-fx-border-width: 2;" +
                      "-fx-border-insets: 5;" + 
                      "-fx-border-radius: 5; -fx-border-color: #FFFFFF" );
        BorderPane.setMargin(fields, new Insets(0,0,0,10));
        layout.setLeft(fields);
        primaryStage.setScene(scene);
        primaryStage.show();
        //payment
        VBox payment = new VBox(5);
        payment.setPadding(new Insets(5,5,5,5));
        payment.setMaxWidth(200);
        payment.setMaxHeight(700);
        payment.setStyle("-fx-padding: 10;" + 
                      "-fx-border-style: solid inside;" + 
                      "-fx-border-width: 2;" +
                      "-fx-border-insets: 5;" + 
                      "-fx-border-radius: 5; -fx-border-color: #FFFFFF" );
        
        memberid = new TextField();
        memberid.setFont(Font.font("SanSerif", 15));
        memberid.setPromptText("ID");
        memberid.setMaxWidth(200);
        memberid.setMinWidth(200);
        
        Label payment_label = new Label();
        payment_label.setText("Payment");
        payment_label.setFont(Font.font("SanSerif", 20));
        payment_label.setTextFill(Color.web("#FFFFFF"));
        
        
        paydate = new DatePicker();
        paydate.setPromptText("Payment date");
        paydate.setMaxWidth(200);
        
        exdate = new DatePicker();
        exdate.setPromptText("Expire date");
        exdate.setMaxWidth(200);
        
        amount = new TextField();
        amount.setFont(Font.font("SanSerif", 15));
        amount.setPromptText("Amount");
        amount.setMaxWidth(200);
        amount.setMinWidth(200);
        
        paytype = new ComboBox(options);
        paytype.setMaxHeight(30);
        paytype.setPromptText("Type");
        options.addAll("Monthly","Half yearly","Yearly","Registration","-");
        
        Button add_pay_btn = new Button("Paid");
        add_pay_btn.setFont(Font.font("SanSerif", 15));
        add_pay_btn.setOnAction(e ->
        {
            try
            {
                String query2 = "select memid from memberinfo where memid=?";
                statement2 = conn.prepareStatement(query2);
                statement2.setString(1, memberid.getText());
                result2 = statement2.executeQuery();
                if(result2.next())
                {
                    String query = "insert into userinfo (memid,paydate,exdate,paytype,amount) values(?,?,?,?,?)";
                    statement = conn.prepareStatement(query);
                    paydate.setConverter(converter);
                    exdate.setConverter(converter);
                    statement.setString(1, memberid.getText());
                    statement.setString(2, ((TextField)paydate.getEditor()).getText());
                    statement.setString(3, ((TextField)exdate.getEditor()).getText());
                    statement.setString(4, (String)paytype.getSelectionModel().getSelectedItem());
                    statement.setString(5, amount.getText());
                    statement.execute();
                    Alert aleart = new Alert(Alert.AlertType.INFORMATION);
                    aleart.setTitle("Info");
                    aleart.setHeaderText(null);
                    aleart.setContentText("A new payment added");
                    aleart.showAndWait();
                    paytype.getSelectionModel().clearSelection();
                    clearfields();
                    statement.close();
                    result.close();
                    statement2.close();
                    result2.close();
                }
                else
                {
                    Alert aleart = new Alert(Alert.AlertType.ERROR);
                    aleart.setTitle("No member Found!");
                    aleart.setHeaderText(null);
                    aleart.setContentText("Sorry no member found by "+memberid.getText()+" id");
                    aleart.showAndWait();
                    paytype.setValue(null);
                    clearfields();
                    statement.close();
                    result.close();
                    statement2.close();
                    result2.close();
                }
            }
            catch(Exception e7)
            {
                System.out.println(e7);
            }
        });
        
        payment.getChildren().addAll(payment_label,memberid,paydate,exdate,paytype,amount,add_pay_btn);
        layout.setCenter(payment);
        BorderPane.setAlignment(payment, Pos.CENTER);
        //payment ends
    }
    
    public  void paytableRefresh()
    {
        
        data_2.clear();
            try
            {
            String query = "select * from userinfo where memid=? order by id desc";
            statement = conn.prepareStatement(query);
            statement.setString(1, search.getText());
            result = statement.executeQuery();
            tempuserid = search.getText();
            layout.setRight(table_contain_2);
                
                    
                    while(result.next())
                {
                    data_2.add(new Payment(
                            result.getString("id"),
                            result.getString("memid"),
                            result.getString("paydate"),
                            result.getString("exdate"),
                            result.getString("paytype"),
                            result.getString("amount")
                    ));
                    paytable.setItems(data_2);
                }
                   statement.close();
                   result.close();
                   search.clear();
            }
            catch(Exception e5)
            {
                System.out.println(e5);
            }
            search.clear();
    }
    public void viewupdate()
    {
        try {
                tempuserid = allsearch.getText();
                String query = "select * from memberinfo where memid=?";
                statement = conn.prepareStatement(query);
                statement.setString(1, allsearch.getText());
                result = statement.executeQuery();
                if(result.next())
                {
                    member_id_text.setText(result.getString("memid"));
                    name_text.setText(result.getString("name"));
                    age_text.setText(result.getString("age"));
                    height_text.setText(result.getString("height"));
                    weidht_text.setText(result.getString("weight"));
                    ocupation_text.setText(result.getString("ocupation"));
                    location_text.setText(result.getString("location"));
                    blood_text.setText(result.getString("bloodgroup"));
                    contract_text.setText(result.getString("contract"));
                    addate_text.setText(result.getString("addate"));
                    gender_text.setText(result.getString("gender"));
                    
                    InputStream is = result.getBinaryStream("image");
                    OutputStream os = new FileOutputStream(new File("photo.jpg"));
                    byte[] content = new byte[1024];
                    int size = 0;
                    while((size = is.read(content))!= -1)
                    {
                        os.write(content,0,size);
                    }
                    os.close();
                    is.close();
                    image = new Image("file:photo.jpg",150,150,true,true);

                    imageview = new ImageView(image);
                    imageview.setFitHeight(150);
                    imageview.setFitWidth(150);
                    imageview.setPreserveRatio(true);
                    goback.setText("Go Back");
                    
                    second_stage.setScene(updatescene);
                    second_stage.show();
                    image_wrap.getChildren().addAll(imageview,goback);
                }
                else
                {
                    Alert aleart = new Alert(Alert.AlertType.ERROR);
                    aleart.setTitle("Not Found");
                    aleart.setHeaderText(null);
                    aleart.setContentText("Memeber id "+allsearch.getText()+" does not exists");
                    aleart.showAndWait();
                }
                
                
                statement.close();
                result.close();
                allsearch.clear();
            } catch (SQLException ex) {
                Logger.getLogger(Iron_fitness.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Iron_fitness.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Iron_fitness.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }
    public void refreshTable()
    {
        data.clear();
            try
            {
                String query = "SELECT * FROM memberinfo order by id desc";
                statement = conn.prepareStatement(query);
                result = statement.executeQuery();
                while(result.next())
                {
                    data.add(new User(
                            
                            result.getString("memid"),
                            result.getString("name"),
                            result.getString("age"),
                            result.getString("height"),
                            result.getString("weight"),
                            result.getString("gender"),
                            result.getString("location"),
                            result.getString("contract"),
                            result.getString("bloodgroup"),
                            result.getString("ocupation"),
                            result.getString("addate")
                    ));
                    table.setItems(data);
                }
                
                    statement.close();
                    result.close();
            }
            catch(Exception e3)
            {
                System.out.println(e3);
            }
    }
    public void clearfields()
    {
        gym_id.clear();
        name.clear();
        age.clear();
        height.clear();
        weight.clear();
        location.clear();
        contract.clear();
        ocupation.clear();
        bloodgroup.clear();
        regdate.setValue(null);
        male.setSelected(false);
        female.setSelected(false);
        memberid.clear();
        paydate.setValue(null);
        exdate.setValue(null);
        amount.clear();
        name_up.clear();
        age_up.clear();
        height_up.clear();
        weight_up.clear();
        location_up.clear();
        contract_up.clear();
        ocupation_up.clear();
        bloodgroup_up.clear();
        regdate_up.setValue(null);
        male_up.setSelected(false);
        female_up.setSelected(false);
        
       
    }
    public void dateFormate()
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        formatedDate = sdf.format(cal.getTime());
        
    }
    public void checkConnection()
    {
        conn = sqlConnection.DBConnect();
        if(conn == null)
        {
            System.out.println("Not connected");
            System.exit(1);
        }
        else
           System.out.println("Connected"); 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
