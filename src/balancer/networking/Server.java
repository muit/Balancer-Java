/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package balancer.networking;

import balancer.networking.conections.Client;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel
 */
public class Server {
    private URL url;
    private int charge;
    
    public ArrayList<Client> clients;
    
    public Server(String url){
        try {
            this.url = new URL(url);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.charge = 0;
    }
    public Server(String url, int charge){
        try {
            this.url = new URL(url);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.charge = charge;
    }
    public URL getUrl(){ return url; }
    public int getCharge(){ return charge; }
}
