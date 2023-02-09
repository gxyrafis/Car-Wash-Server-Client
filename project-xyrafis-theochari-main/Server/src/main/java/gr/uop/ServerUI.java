package gr.uop;

import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class ServerUI {
    
    private TableView<ClientData> table = new TableView<>();
    private ObservableList<ClientData> clientList;
    SImplementation si;
    IncomeBook ib;

    Stage stage;
    Button paybtn,deletebtn;
    Label label;
    boolean key = true;

    public ServerUI(Stage stage, ObservableList<ClientData> clientList, SImplementation si , IncomeBook ib){
    
       this.stage = stage;
       this.clientList = clientList;
       this.si=si;
       this.ib=ib;

    }

    public void CreateTable(){

        label = new Label("Ταμείο");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);
        
        
        
        table.prefHeightProperty().bind(stage.heightProperty());
        table.prefWidthProperty().bind(stage.widthProperty());

        TableColumn<ClientData,String> plateCol = new TableColumn<>("Αριθμός Κυκλοφορίας");
        plateCol.setCellValueFactory(new PropertyValueFactory<>("plate"));
        plateCol.prefWidthProperty().bind(table.widthProperty().multiply(0.35));

        TableColumn<ClientData,String> costCol = new TableColumn<>("Κόστος");
        costCol.setCellValueFactory(new PropertyValueFactory<>("cost"));
        costCol.prefWidthProperty().bind(table.widthProperty().multiply(0.25));

        TableColumn<ClientData,String> dateCol = new TableColumn<>("Ημερομηνία/'Ωρα άφιξης");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateCol.prefWidthProperty().bind(table.widthProperty().multiply(0.39));

        table.getColumns().addAll(plateCol,costCol,dateCol);

        table.setItems(clientList);

    }

    public void Buttons(){

        paybtn= new Button("Πληρωμή");
        paybtn.setDisable(true);
        deletebtn= new Button("Ακύρωση");
        deletebtn.setDisable(true);

        paybtn.prefWidthProperty().bind(table.widthProperty().multiply(0.3));
        deletebtn.prefWidthProperty().bind(table.widthProperty().multiply(0.3));

        paybtn.setMinHeight(30);
        deletebtn.setMinHeight(30);

        ButtonActions();
    }

    public void ButtonActions(){

        
        table.setOnMouseClicked(e -> {

            if(table.getSelectionModel().getSelectedItem()== null){
                paybtn.setDisable(true);
                deletebtn.setDisable(true);
                key = true;
            }
            else{
                ClientData client = table.getSelectionModel().getSelectedItem();
                paybtn.setDisable(false);
                deletebtn.setDisable(false);
                key = false;
            }
            
        });

        paybtn.setOnAction(e -> {
            try {
                ConfirmAlert();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        deletebtn.setOnAction(e->{ 
            
            try {
                DeleteAlert();
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        });

        table.setOnKeyPressed(e -> {
            
            if(!key){
                if(e.getCode()== KeyCode.ENTER){
                    try {
                        ConfirmAlert();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
            
        });

        stage.setOnCloseRequest(e -> {
            ObservableList check = table.getItems();
            if(check==null || check.isEmpty()){
                System.exit(0);
            }
            else{
                CloseAlert(e);
            }        
        });
    }

    public void ConfirmAlert() throws Exception{
        ButtonType confirm = new ButtonType("Επιβεβαίωση",ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("Ακύρωση",ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert payment = new Alert(AlertType.CONFIRMATION,"", confirm, cancel);
        payment.setTitle("Προειδοποίηση");
        payment.setHeaderText("Επιβεβαίωση Πληρωμής");
        Optional<ButtonType> result = payment.showAndWait();
        if(result.orElse(cancel) == confirm ){

            ib.AddClientToIncomeBook(table.getSelectionModel().getSelectedItem());
            DeleteSelected();
            si.UpdateTempFile(clientList);
            ListCheck();
         }

    }

    public void DeleteAlert() throws Exception{
        ButtonType confirm = new ButtonType("Ναι", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("Πίσω",ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert payment = new Alert(AlertType.CONFIRMATION,"", confirm, cancel);
        payment.setTitle("Προειδοποίηση");
        payment.setHeaderText("Επιβεβαίωση Ακύρωσης");
        payment.setContentText("Eίστε σίγουροι ότι θέλετε να ακυρώσετε την συγκεκριμένη εγγραφή;");
        Optional<ButtonType> result = payment.showAndWait();
        if(result.orElse(cancel) == confirm ){
            DeleteSelected();
            si.UpdateTempFile(clientList);
            ListCheck();   
        }
    }

    public void CloseAlert(WindowEvent e){
       
        ButtonType confirm = new ButtonType("Ναι", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("Πίσω",ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert payment = new Alert(AlertType.CONFIRMATION,"", confirm, cancel);
        payment.setTitle("Προειδοποίηση");
        payment.setHeaderText("Κλείσιμο Παραθύρου");
        payment.setContentText("Υπάρχουν ακόμα οχήματα στον πίνακα. Είστε σίγουροι ότι θέλετε να κλείσετε το παράθυρο;");
        Optional<ButtonType> result = payment.showAndWait();
        if(result.orElse(cancel) == confirm ){
            System.exit(0);
        }
        else{
            e.consume();
        }

    }

    public void DeleteSelected(){
        table.getItems().remove(table.getSelectionModel().getSelectedItem());
    }

    public void ListCheck(){
        ObservableList list = table.getItems();
            if(list==null || list.isEmpty()){
                paybtn.setDisable(true);
                deletebtn.setDisable(true);
            }
            else{
                paybtn.setDisable(false);
                deletebtn.setDisable(false);
            }
    }


    public void Structure(){

        CreateTable();
        Buttons();
        HBox hb = new HBox();
        hb.setSpacing(10);
        hb.setPadding(new Insets(10,0,0,0));
        hb.getChildren().addAll(paybtn, deletebtn);
        hb.setAlignment(Pos.CENTER);
        final VBox vbox = new VBox();
        vbox.setSpacing(10);
        
        vbox.setPadding(new Insets(20, 15, 20, 15));
        vbox.getChildren().addAll(label, table, hb);
 
        Scene scene = new Scene(vbox,500,600);
        stage.setMinWidth(450);
        stage.setMinHeight(550);
        stage.setTitle("Server");
        stage.setScene(scene);
        stage.show();
    }
}


