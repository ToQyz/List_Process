package process_client;

import java.io.IOException;
import java.net.Socket;
import javax.swing.WindowConstants;

public class Main {

    public static void main(String[] args) {
           /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        Socket socket;
        SocketHandler socketHandler = null;
        try{
            socket = new Socket("127.0.0.1", 9998);
            socketHandler = new SocketHandler(socket);
            System.out.println("Successfully connect");
            GUI gui = new GUI(socketHandler);
            gui.setVisible(true);
            gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            StartGUI startgui = new StartGUI(socketHandler);
            startgui.setVisible(true);
            startgui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }
        catch(IOException e){
            
        }
        
       
        
    }
}
