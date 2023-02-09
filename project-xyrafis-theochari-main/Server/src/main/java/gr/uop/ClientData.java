package gr.uop;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClientData {

    String plate,date,services;
    double cost;

    public ClientData(String plate,String services,double cost){
        this.plate=plate;
        this.services=services;
        this.cost=cost;
        this.date= Date();

    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate=plate;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services=services;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost= cost;
    }

    public String getDate() {
        return date;
    }

    public final void setDate(String date) {
        this.date= date;
    }

    public String Date(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        return dtf.format(now); 
    }

    public String toString(){
        return plate+ " "+ services + " "+ cost+ " "+ date;
    }
}
