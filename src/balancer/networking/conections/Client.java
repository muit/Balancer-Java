/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package balancer.networking.conections;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel
 */
public class Client extends Thread{
    
    protected Socket clientSocket = null;
    protected InputStream input;
    protected OutputStream output;
    public Client(Socket sc) {
        this.clientSocket = sc;
    }

    public void run() {
        try {
            input  = clientSocket.getInputStream();
            output = clientSocket.getOutputStream();
            
            while(true){
                input.read();
            }
            
        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }
    
    private void close(){
        try {
            input.close();
            output.close();
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
