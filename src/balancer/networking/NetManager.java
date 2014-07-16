/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package balancer.networking;

import balancer.Balancer;
import balancer.Util;
import balancer.networking.conections.Client;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel
 */
public class NetManager {
    private static boolean done = false;
    private static ArrayList<Server> servers = new ArrayList<>();
    
    private static Server getNext(){
        Server sv = new Server("");
        if(Balancer.config.selectionByCharge)
        {
            //Selection by Server Charge
            for(int i = 0, len = servers.size(); i < len; i++)
                if(sv.getCharge() > servers.get(i).getCharge()) 
                    sv = servers.get(i);
        }
        else
        {
            sv = servers.get(0);
            //Selection by next position
            servers.add(servers.get(0));
            servers.remove(0);
        }
        return sv;
    }
    
    public static void addServer(Server server, boolean secure){
        if (!secure || servers.indexOf(server)!= -1)
            servers.add(server);
        else
            Util.Log.printError("Ese servidor ya existe.");
    }
    public static void loadServersFromConfig(){
        for(int i = 0, len = Balancer.config.URLs.size(); i < len; i++)
            servers.add(new Server(Balancer.config.URLs.get(i)));
    }
    
    public static void start(int serverPort){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port "+serverPort, e);
        }
        while(!done){
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                if(done) {
                    System.out.println("Server Stopped.") ;
                    return;
                }
                throw new RuntimeException(
                    "Error accepting client connection", e);
            }
            
            new Thread(new Client(clientSocket)).start();
        }
        
    }
}
