/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package balancer.config;

import java.util.ArrayList;

/**
 *
 * @author Miguel
 */
public class ConfigPacket {
    public ArrayList<String> URLs;
    public int maxConections;
    public boolean selectionByCharge;
    public ConfigPacket(){
        URLs = new ArrayList<>();
        maxConections = 20;
        selectionByCharge = false;
    }
}
