package gr.uop;

public class InvalidPriceException extends Exception
{
    public InvalidPriceException()
    {
        super("Price can not be a negative number!");
    }
}
