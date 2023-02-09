package gr.uop;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class CheckboxHandler implements EventHandler<ActionEvent>{
    private Optional<String> result;
    private ServiceMenu srvcmn;
    private int[] id_storage;
    private final int index;
    private CheckBox[] services;
    private Label price;
    private final int nrsrvctmp;

    public CheckboxHandler(Optional<String> result, ServiceMenu srvcmn, int[] id_storage, int index, CheckBox[] services, Label price, int nrsrvctmp)
    {
        this.result = result;
        this.srvcmn = srvcmn;
        this.id_storage = id_storage;
        this.index = index;
        this.services = services;
        this.price = price;
        this.nrsrvctmp = nrsrvctmp;
    }

    @Override
    public void handle(ActionEvent event) {
        double pricetmp;
        if(result.get().equals("Αυτοκίνητο"))
        {
            pricetmp = srvcmn.getServiceById(id_storage[index]).getCarPrice();
        }
        else if(result.get().equals("Τζιπ"))
        {
            pricetmp = srvcmn.getServiceById(id_storage[index]).getJeepPrice();
        }
        else
        {
            pricetmp = srvcmn.getServiceById(id_storage[index]).getMotorbikePrice();
        }

        if(services[index].isSelected())
        {
            String[] labelcontents = price.getText().split(" ");
            double totalprice = Double.parseDouble(labelcontents[1]);
            totalprice += pricetmp;
            price.setText("Total: " + totalprice + " €");
            String incomp = srvcmn.getServiceById(id_storage[index]).getIncompatibleServices();
            for(int x = 0 ; x < nrsrvctmp ; x++)
            {
                if(incomp.contains(id_storage[x] + ""))
                {
                    services[x].setDisable(true);
                }
            }
        }
        else if(!services[index].isSelected())
        {
            String[] labelcontents = price.getText().split(" ");
            double totalprice = Double.parseDouble(labelcontents[1]);
            totalprice -= pricetmp;
            price.setText("Total: " + totalprice + " €");
            String incomp = srvcmn.getServiceById(id_storage[index]).getIncompatibleServices();
            for(int x = 0 ; x < nrsrvctmp ; x++)
            {
                if(incomp.contains(id_storage[x] + ""))
                {
                    services[x].setDisable(false);
                }
            }
            for(int x = 0 ; x < nrsrvctmp ; x++)
            {
                if(services[x].isSelected())
                {
                    incomp = srvcmn.getServiceById(id_storage[x]).getIncompatibleServices();
                    for(int y = 0 ; y < nrsrvctmp ; y++)
                    {
                        if(incomp.contains(id_storage[y] + ""))
                        {
                            services[y].setDisable(true);
                        }
                    }
                }
            }
        }
        
    }
    
}
