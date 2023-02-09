package gr.uop;

import java.io.IOException;
import java.net.Socket;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class Keyboardhandler implements EventHandler<ActionEvent>{
    private Label display, price;
    private Stage stage;
    private GridPane serviceChoices;
    private Scene scene, scene2;
    Button back, ok;

    public Keyboardhandler(Label display, Label price, Stage stage, GridPane serviceChoices, Scene scene, Scene scene2, Button back, Button ok)
    {
        this.display = display;
        this.stage = stage;
        this.serviceChoices = serviceChoices;
        this.price = price;
        this.scene = scene;
        this.scene2 = scene2;
        this.back = back;
        this.ok = ok;
    }

    @Override
    public void handle(ActionEvent event) {
        String tmp = display.getText();
        Button clickedbtn = (Button) event.getTarget();
        
        if(clickedbtn.getText().length() == 1)
        {
            tmp += clickedbtn.getText();
            display.setText(tmp);
        }
        else if(clickedbtn.getId().equals("Backspace"))
        {
            if(display.getText().length() > 0){
                tmp = tmp.substring(0, tmp.length()-1);
                display.setText(tmp);
            }
        }
        else if(clickedbtn.getId().equals("Space"))
        {
            if(display.getText().length() > 0)
            {
                tmp += " ";
                display.setText(tmp);
            }
        }
        else if(clickedbtn.getId().equals("Enter"))
        {
            if(display.getText().length() < 2)
            {
                Alert error = new Alert(AlertType.ERROR);
                error.initOwner(stage);
                error.initModality(Modality.WINDOW_MODAL);
                error.setTitle("Error");
                error.setHeaderText("Invalid Plate Number");
                error.setContentText("Error: Invalid Plate Number.\nA plate number must consist of atleast two (2) letters or numbers.");

                error.showAndWait();
            }
            else
            {
                ChoiceDialog<String> vehicleTypeSelection = new ChoiceDialog<>("Αυτοκίνητο", "Αυτοκίνητο", "Τζιπ", "Μοτοσυκλέτα");
                vehicleTypeSelection.initOwner(stage);
                vehicleTypeSelection.initModality(Modality.WINDOW_MODAL);
                vehicleTypeSelection.setTitle("Vehicle Type Selection");
                vehicleTypeSelection.setContentText("Please select the type\nthat matches your vehicle: ");
                vehicleTypeSelection.setHeaderText(null);
                vehicleTypeSelection.setGraphic(null);

                Optional<String> result = vehicleTypeSelection.showAndWait();

                if(result.isPresent())  //Set up the checkboxes of the next menu, put them in place etc
                {
                    ServiceMenu srvcmn = new ServiceMenu();
                    srvcmn.setFile("src/main/java/gr/uop/services.txt");
                    CheckBox[] services = new CheckBox[srvcmn.getNumberOfServices()];
                    int[] id_storage = new int[srvcmn.getNumberOfServices()];
                    for(int i = 0 ; i < srvcmn.getNumberOfServices() ; i++)
                    {
                        id_storage[i] = 0;
                    }
                    int nr_srvc = 0;
                    if(result.get().equals("Αυτοκίνητο"))
                    {
                        for(int i = 1 ; i <= srvcmn.getNumberOfServices() ; i++)
                        {
                            if(srvcmn.getServiceById(i).getCarPrice() != -1)
                            {
                                services[nr_srvc] = new CheckBox(srvcmn.getServiceById(i).getName() + " " + srvcmn.getServiceById(i).getCarPrice() + " €");
                                id_storage[nr_srvc] = i;
                                nr_srvc++;
                            }
                        }
                        for(int i = 0, k = 0 ; k < nr_srvc ; i++)
                        {
                            for(int j = 0 ; j < 2 ; j++, k++)
                            {
                                serviceChoices.add(services[k], j, i);
                            }
                        }
                    }
                    else if(result.get().equals("Τζιπ"))
                    {
                        for(int i = 1 ; i <= srvcmn.getNumberOfServices() ; i++)
                        {
                            if(srvcmn.getServiceById(i).getJeepPrice() != -1)
                            {
                                services[nr_srvc] = new CheckBox(srvcmn.getServiceById(i).getName() + " " + srvcmn.getServiceById(i).getJeepPrice() + " €");
                                id_storage[nr_srvc] = i;
                                nr_srvc++;
                            }
                        }
                        for(int i = 0, k = 0 ; k < nr_srvc ; i++)
                        {
                            for(int j = 0 ; j < 2 ; j++, k++)
                            {
                                serviceChoices.add(services[k], j, i);
                            }
                        }
                    }
                    else if(result.get().equals("Μοτοσυκλέτα"))
                    {
                        for(int i = 1 ; i <= srvcmn.getNumberOfServices() ; i++)
                        {
                            if(srvcmn.getServiceById(i).getMotorbikePrice() != -1)
                            {
                                services[nr_srvc] = new CheckBox(srvcmn.getServiceById(i).getName() + " " + srvcmn.getServiceById(i).getMotorbikePrice() + " €");
                                id_storage[nr_srvc] = i;
                                nr_srvc++;
                            }
                        }
                        for(int i = 0, k = 0 ; k < nr_srvc ; i++)
                        {
                            for(int j = 0 ; j < 2 ; j++, k++)
                            {
                                serviceChoices.add(services[k], j, i);
                            }
                        }
                    }

                    for(int i = 0 ; i < nr_srvc ; i++)
                    {
                        services[i].setPadding(new Insets(30, 30, 30, 30));
                        services[i].setStyle("-fx-font-size: 15;");
                        final int index = i;
                        final int nrsrvctmp = nr_srvc;

                        //Checkboxes eventhandler
                        CheckboxHandler checkboxhandler = new CheckboxHandler(result, srvcmn, id_storage, index, services, price, nrsrvctmp);
                        services[i].setOnAction(checkboxhandler);
                    }
                    final int nrsrvctmp = nr_srvc;
                    back.setOnAction((ev)->{
                        price.setText("Total: 0 €");
                        display.setText("");
                        for(int i = 0 ; i < nrsrvctmp ; i++)
                        {
                            services[i].setVisible(false);
                        }
                        stage.setScene(scene);
                    });
                    ok.setOnAction((ev)->{
                        Alert alert = new Alert(AlertType.CONFIRMATION);
                        alert.setGraphic(null);
                        alert.setHeaderText("Order Confirmation");
                        String[] labelcontents = price.getText().split(" ");
                        alert.setContentText("Would you like to buy our services for " + labelcontents[1] + " €  ?");
                        Optional<ButtonType> res =  alert.showAndWait();
                        alert.setTitle("Order Confirmation");
                        if(res.get() == ButtonType.OK)
                        {
                            String toServer = display.getText()+",";
                            for(int i = 0 ; i < nrsrvctmp ; i++)
                            {
                                if(services[i].isSelected())
                                {
                                    toServer += id_storage[i] + "-";
                                }
                            }
                            toServer = toServer.substring(0, toServer.length()-1);
                            toServer += "," + labelcontents[1];
                            Socket clientSocket = functionality.clientConnect("localhost", 3858);
                            functionality.sendStringToServer(clientSocket, toServer);
                            price.setText("Total: 0 €");
                            display.setText("");
                            for(int i = 0 ; i < nrsrvctmp ; i++)
                            {
                                services[i].setVisible(false);
                            }
                            stage.setScene(scene);
                            try {
                                clientSocket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    stage.setScene(scene2);
                }
            }
        }
    }

}