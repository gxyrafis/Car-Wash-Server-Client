package gr.uop;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import javafx.collections.ObservableList;

public class SImplementation{
    
    private ObservableList<ClientData> clientList;
    private File temp; 
    FileWriter tempWritter;
    BufferedWriter tempBufferWritter;

    public SImplementation(ObservableList<ClientData> clientList) throws Exception{

        this.clientList = clientList;
        temp = new File("Temporary.txt");  
    }
    

    public void newClient(String data) throws Exception{
        
        ClientDataList.addClientToList(clientList, CreateClient(data)); //create object and add to list
        UpdateTempFile(clientList); //update temp file
        
    }  

    public ClientData CreateClient(String data){
        String[] ClientData = data.split(",");
        ClientData client = new ClientData( ClientData[0], ClientData[1], Double.parseDouble(ClientData[2]));
        System.out.println("client: "+ client);
        return client;
    }

    public void UpdateTempFile(ObservableList<ClientData> clientList) throws Exception{
    
        tempWritter = new FileWriter(temp,false);
        tempBufferWritter = new BufferedWriter(tempWritter);

        for(int i=0; i< clientList.size(); i++){
            if(i!=0){
                tempBufferWritter.newLine();
            }
            tempBufferWritter.write(clientList.get(i).getPlate()+",");
            tempBufferWritter.write(clientList.get(i).getServices()+",");
            tempBufferWritter.write(String.valueOf(clientList.get(i).getCost())+",");
            tempBufferWritter.write(clientList.get(i).getDate());
            tempBufferWritter.flush();
        }
    }

    public void TempToList() throws Exception{
        if(temp.exists()){
            Scanner sc= new Scanner(temp);
            while(sc.hasNextLine()){
                String d= sc.nextLine();
                ClientDataList.addClientToList(clientList,CreateClient(d));
            }  
        sc.close();
        }
    }


}
