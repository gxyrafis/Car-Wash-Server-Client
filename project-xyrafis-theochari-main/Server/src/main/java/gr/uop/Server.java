package gr.uop;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.stage.Stage;


public class Server extends Application  {

    private ObservableList<ClientData> clientList = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {
        try{

            
            SImplementation try1 = new SImplementation(clientList);
            IncomeBook ib= new IncomeBook(clientList);
            ServerUI ui= new ServerUI(stage,clientList,try1,ib);
            
            ui.Structure();

            
            try1.TempToList();
            ServerSocket serversocket = null;
            try
            {
                serversocket = new ServerSocket(3858);
            }
            catch(IOException e)
            {
                System.out.println(e);
            }
            ServerThread st= new ServerThread(serversocket,try1);

            st.start();
            //try1.newClient("mpa,nai,24");
            //try1.newClient("ZKZ2412,4-6-8,334");
            // try1.newClient("HMN6566,2-6-5,52");
            // try1.newClient("ITN3,3-3-3,15");
            // try1.newClient("KAl4399,4-5-6,12");

        }
        catch(Exception e){
            System.out.println(e);
        }
    }    

    public static void main(String[] args) {
        launch(args);
    }

}
