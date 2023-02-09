package gr.uop;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerThread extends Thread implements Runnable{

    ServerSocket serversocket;
    SImplementation try1;

    public ServerThread(ServerSocket serversocket, SImplementation try1){
        this.serversocket = serversocket;
        this.try1 = try1;
    }

    public void run(){

        while(true)
        {
            try {
                Socket clientsocket = serversocket.accept();
                Scanner fromClient = new Scanner(clientsocket.getInputStream());
                String data = fromClient.nextLine();
                try{
                    try1.newClient(data);
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
