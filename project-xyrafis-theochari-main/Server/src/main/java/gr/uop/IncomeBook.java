package gr.uop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javafx.collections.ObservableList;



public class IncomeBook {
    
    private ObservableList<ClientData> clientList;
    private File incomeBook; 
    FileWriter ibWritter;
    BufferedWriter ibBufferWritter;

    public IncomeBook(ObservableList<ClientData> clientList){
        
        incomeBook = new File("IncomeBook.txt");
        try {
            ibWritter = new FileWriter(incomeBook,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ibBufferWritter = new BufferedWriter(ibWritter);
        this.clientList = clientList;
        
    }

    public void AddClientToIncomeBook(ClientData client) throws Exception{

        if(incomeBook.length() != 0){
            ibBufferWritter.newLine();
        }
        ibBufferWritter.write(client.getPlate()+",");
        ibBufferWritter.write(client.getServices()+",");
        ibBufferWritter.write(client.getCost()+",");
        ibBufferWritter.write(client.getDate()+",");
        ibBufferWritter.write(client.Date());
        ibBufferWritter.flush();
    }   

    public String Date(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        return dtf.format(now); 
    }



}