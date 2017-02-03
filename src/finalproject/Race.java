/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

/**
 *
 * @author PriyankaPrabhu
 */
public class Race extends Thread{
    
    private int x;
    private int y;
    private char participant;
    Running r;
    
    public Race(char p, Running running){
        
        r = running;
        participant = p;
        if(participant == 'T'){
            x = -70;
            y = 150;
        }else
        {
            x = -70;
            y = 250;
        }
    }
    
    public void run(){
        
        r.running(participant, x, y);
    }
}
