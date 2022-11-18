package process_server;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketHandler {
    private Socket socket;
    private Processes app;
    
    BufferedReader in;
    DataOutputStream out;
    
    public SocketHandler(Socket socket){
        this.socket = socket;
        
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(SocketHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        app = new Processes(this);
        // first get app
        app.getInitial();
         // receive to get installed
        receive();
    }
    
    public void receive(){
        Thread thread = new Thread(){
            public void run(){
                while(true){
                    String command;
                    try{
                        command = in.readLine();
                        System.out.println(command);
                        command.trim();
                        if(command.equals("")) continue;
                        app.executeCommand(command);
                    }
                    catch(IOException e){

                    } 
                }
            }
        };
        
        thread.start();
    }
    
    public void send(ByteArrayOutputStream byteArray) {
        
        try{
            out.writeInt(byteArray.size());
            out.write(byteArray.toByteArray());
            out.flush();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("Completely sent");
    }
}
