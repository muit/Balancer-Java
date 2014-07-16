/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package balancer;

import balancer.config.ConfigManager;
import balancer.config.ConfigPacket;
import balancer.networking.NetManager;

/**
 *
 * @author Miguel
 */
public class Balancer {
    public static ConfigPacket config;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        config = ConfigManager.loadConfig(args[0]);
        
        NetManager.loadServersFromConfig();
    }
}
