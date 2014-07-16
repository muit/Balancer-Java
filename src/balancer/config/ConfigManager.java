/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package balancer.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Miguel
 */
public class ConfigManager {
    private static enum ReadingState{
        nothing,
        serverURLs, 
        maxConections,
        selectionByCharge
    }
    
    public static ConfigPacket loadConfig(String path){
        path = (path.equals(""))?"Config.info": path;
        
        ConfigPacket cp = new ConfigPacket();
        BufferedReader br = null;
        try {
                String sCurrentLine;

                br = new BufferedReader(new FileReader(path));
                ReadingState state = ReadingState.nothing;
                
                while ((sCurrentLine = br.readLine()) != null) {
                    switch(sCurrentLine){
                        case "[serverURLs]":
                            state = ReadingState.serverURLs;
                            break;
                        case "[maxConections]":
                            state = ReadingState.maxConections;
                            break;
                        case "[selectionByChargeEnabled]":
                            state = ReadingState.selectionByCharge;
                            break;
                        default:
                            readAtributes(state, sCurrentLine, cp);
                            break;
                    }
                }
                
        } catch (IOException e) {
                e.printStackTrace();
                return null;
        } finally {
                try {
                        if (br != null) br.close();
                } catch (IOException ex) {
                        ex.printStackTrace();
                        return null;
                }
        }
        
        return cp;
    }
    private static void readAtributes(ReadingState state, String line, ConfigPacket cp)
    {
        switch(state){
            case serverURLs:
                if(cp.URLs.indexOf(line) == -1)
                    cp.URLs.add(line);
                break;
            case maxConections:
                cp.maxConections = Integer.parseInt(line);
                break;
            case selectionByCharge:
                cp.selectionByCharge = Boolean.parseBoolean(line);
                break;
        }
    }
}
