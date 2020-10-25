/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_2;

import Game.*;
import java.util.*;

/**
 *
 * @author Jose
 */
public class User_Data {
    
    public ArrayList<Game> storedGames; 
    public ArrayList<String> gameID;
    
    
    public User_Data(String username) {
        storedGames = new ArrayList<>();
        gameID = new ArrayList<>();
    }
    
    
}
