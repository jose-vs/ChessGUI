/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.Observable;

/**
 *
 * @author Jose
 */
public class Chess_Model extends Observable {
    public Chess_DB db; 
    public Data data;
    
    public Chess_Model() { 
        this.db = new Chess_DB(); 
        this.db.dbsetup();
    }
}
