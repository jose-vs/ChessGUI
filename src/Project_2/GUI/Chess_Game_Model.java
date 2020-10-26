/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_2.GUI;

import Project_2.Chess_DB;
import Project_2.Data;
import java.util.Observable;

/**
 *
 * @author Jose
 */
public class Chess_Game_Model extends Observable{
    
    public Chess_DB db;
    public Data data = new Data();
    
    public Chess_Game_Model() { 
        this.data = new Data(); 
        this.db = new Chess_DB();
        
    }
    
}
