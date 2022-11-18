package process_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    
    public static void main(String[] args) {
        
      
        
        SocketHandler socketHandler = null;
        ServerSocket serverSocket = null;
        Socket socket;
        try{
            serverSocket = new ServerSocket(9998);
            socket = serverSocket.accept();
            System.out.println("Successfully connect");
            socketHandler = new SocketHandler(socket);
            
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
