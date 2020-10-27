/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_2.GUI;

import javax.swing.JButton;

/**
 *
 * @author Jose
 */
public class Chess_Square_Button extends JButton{
    
    private int xPos, yPos;

    public void square_coordinates(int xPos, int yPos) { 
        
        this.xPos = xPos; 
        this.yPos = yPos;

    } 
    
    public int getxCoordinate() { 
        return xPos; 
    } 
    
    public int getyCoordinate() { 
        return yPos; 
    } 
}
