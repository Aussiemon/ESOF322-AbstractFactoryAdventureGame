/**
 * @author Matt Rohrlach 
 */

/**
 * @SummaryOfChanges
 *  
 */



package esof322.a4;

import java.util.ArrayList;
import java.util.Random;

/**  Adventure Game  Program Code
     Copyright (c) 1999 James M. Bieman

     To compile: javac AdventureGame.java
     To run:     java AdventureGame

     The main routine is AdventureGame.main
     
     Update August 2010: refactored Vector contents into ArrayList<Item> contents.
      This gets rid of the use of obsolete Vector and makes it type safe.
				    
**/

// class Level1Room

public class Level1Room extends Room{
    private CaveSite[] side1 = this.getSideArray();
    private ArrayList<Item> contents1 = this.getRoomContentsList();
    private boolean justItemThief = false;
    private final String thiefStatus = "The bag opens up, revealing teeth!\n"
            + "An item mimic!\n"
            + "It eats the items in your hands...\n"
            + "...and scuttles away forever.\n";
    
    Level1Room() {
        side1[0] = new Level1Wall();
        side1[1] = new Level1Wall();
        side1[2] = new Level1Wall();
        side1[3] = new Level1Wall();
        side1[4] = new Level1Wall();
        side1[5] = new Level1Wall();

        Random random = new Random();
        if(random.nextInt(20) < 4){
            Treasure fakeTreasure = new Treasure(true);
            fakeTreasure.setDesc("A bag filled with gold bars.");
            this.addItem(fakeTreasure);
        }
    }
    
    @Override
    public void removeItem(Item theItem){
        if(theItem.getDesc().equalsIgnoreCase("A bag filled with gold bars.")){
            if(theItem.isFake()){
                justItemThief = true;
            }
        }
            contents1.remove(theItem);
    }
    
    @Override
    public boolean itemThief(){
        return justItemThief;
    }
    
    @Override
    public String getThiefStatus(){
        return thiefStatus;
    }
    
    @Override
    public void resetItemThief(){
        justItemThief = false;
    }
}
