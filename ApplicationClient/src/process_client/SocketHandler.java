package process_client;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketHandler {
    private Socket socket;
    
    private DataInputStream in;
    private PrintWriter out;
    
    public SocketHandler(Socket socket){
        this.socket = socket;
       
        try{
            in = new DataInputStream(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream());
        }
        catch(IOException e){
            
        }
    }
    
    public byte[] receive(){
        int size = 0;
        byte[] b = null;
        
        try{
            size = in.readInt();
            b = new byte[size];
            in.read(b,0, b.length);
        }
        catch(IOException e){
            e.printStackTrace();
            return null;
        }
        
        return b;
    }
    
    public void send(String message){
        out.println(message);
        out.flush();
        System.out.println("send completely");
    }
}
