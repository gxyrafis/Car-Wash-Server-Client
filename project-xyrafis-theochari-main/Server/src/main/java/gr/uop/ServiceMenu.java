package gr.uop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class ServiceMenu {
    
    HashMap<Integer, Service> services = new HashMap<>();
    int mapId=0;
    public ServiceMenu(){

    }

    public void setFile(String fileName){

        int id;
        double car,jeep,motorbike;
        String name,incompatible;
        try{
            Scanner readFile = new Scanner (new FileInputStream(fileName), "UTF-8");
            readFile.nextLine();
            
            while (readFile.hasNextLine())
            {       
                String tmp[] = readFile.nextLine().split("--");
                id = Integer.parseInt(tmp[0]);
                
                name = tmp[1];
                
                String carCheck = tmp[2];
                if( carCheck.equals("x")){
                    car = -1;
                }
                else{
                    car= Double.parseDouble(carCheck);
                }
                

                String jeepCheck = tmp[3];
                if( jeepCheck.equals("x")){
                    jeep = -1;
                }
                else{
                    jeep= Double.parseDouble(jeepCheck);
                }
                

                String motorCheck = tmp[4];
                if(motorCheck.equals("x")){
                    motorbike= -1;
                }
                else{
                    motorbike= Double.parseDouble(motorCheck);
                }
                
                incompatible = tmp[5];
                
                try{
                    putService(new Service(id,name,car,jeep,motorbike,incompatible));
                }
                catch(InvalidPriceException e){
                    System.out.println(e);
                }
            }
        readFile.close();
        }
        catch(FileNotFoundException e){
            System.out.println(e);
        } 
        
    }

    public void putService(Service  service){
        mapId++;
        services.put(mapId,service);
    }

    public void printMap(){

        for (Integer i : services.keySet()) {
            System.out.println("key: " + i + " value: " + services.get(i));
          }
    }

    public Double getPriceService(Integer id, String type){
         
        Service s = services.get(id);
        if(type.equals("car")){
            return s.getCarPrice();
        }
        else if(type.equals("jeep")){
            return s.getJeepPrice();
        }
        else{
            return s.getMotorbikePrice();
        }
    }

    public Service getServiceById(Integer id){

        Service s = services.get(id);
        return s;
    }

    public int getNumberOfServices(){
        return services.size();
    }
}
