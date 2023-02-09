package gr.uop;
import javafx.collections.ObservableList;

public class ClientDataList {
     
    public static void addClientToList(ObservableList<ClientData> clientList, ClientData client){
        clientList.add(client);
    }
}
