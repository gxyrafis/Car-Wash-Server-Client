package gr.uop;

public class Service {
    private int id;
    private String name;
    private double car;
    private double jeep;
    private double motorbike;
    private String incompatible;

    public Service(int id, String name, double car, double jeep, double motorbike, String incompatible_services) throws InvalidPriceException
    {
        if(((car < 0)&&(car != -1))||((jeep < 0)&&(jeep != -1))||((motorbike < 0)&&(motorbike != -1)))
        {
            throw new InvalidPriceException();
        }
        else
        {
            this.id = id;
            this.name = name;
            this.car = car;
            this.jeep = jeep;
            this.motorbike = motorbike;
            this.incompatible = incompatible_services;
        }
    }

    public void setId(int id)
    {
        this.id = id;
    }
    public int getId()
    {
        return this.id;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return this.name;
    }
    public void setCarPrice(double price) throws InvalidPriceException
    {
        if((price < 0)&&(price != -1))
        {
            throw new InvalidPriceException();
        }
        else
        {
            this.car = price;
        }
    }
    public double getCarPrice()
    {
        return this.car;
    }
    public void setJeepPrice(double price) throws InvalidPriceException
    {
        if((price < 0)&&(price != -1))
        {
            throw new InvalidPriceException();
        }
        else
        {
            this.jeep = price;
        }
    }
    public double getJeepPrice()
    {
        return this.jeep;
    }
    public void setMotorbikePrice(double price) throws InvalidPriceException
    {
        if((price < 0)&&(price != -1))
        {
            throw new InvalidPriceException();
        }
        else
        {
            this.motorbike = price;
        }
    }
    public double getMotorbikePrice()
    {
        return this.motorbike;
    }
    public void setIncompatibleServices(String serviceIds)
    {
        this.incompatible = serviceIds;
    }
    public String getIncompatibleServices()
    {
        return this.incompatible;
    }
    
    public String toString(){
        return id+" "+name+" "+car+" "+jeep+" "+motorbike+" "+incompatible;
    }
}
