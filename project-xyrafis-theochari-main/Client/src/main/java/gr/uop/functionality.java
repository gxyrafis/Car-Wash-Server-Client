package gr.uop;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class functionality {

    public static Socket clientConnect(String ip, int port)
    {
        Socket clientSocket;
        try
        {
            clientSocket = new Socket(ip, port);
            return clientSocket;
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        return null;
    }

    public static void sendStringToServer(Socket clientSocket, String str)
    {
        try(PrintWriter toServer = new PrintWriter(clientSocket.getOutputStream());)
            {
            toServer.println(str);
            toServer.flush();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }

    }

    
}
