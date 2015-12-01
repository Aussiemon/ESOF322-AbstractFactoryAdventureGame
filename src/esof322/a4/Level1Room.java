/**
 * @author Matt Rohrlach 
 */

/**
 * @SummaryOfChanges
 *  
 */



package esof322.a4;

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
    
    Level1Room() {
    side1[0] = new Level1Wall();
    side1[1] = new Level1Wall();
    side1[2] = new Level1Wall();
    side1[3] = new Level1Wall();
    side1[4] = new Level1Wall();
    side1[5] = new Level1Wall();
    }
}
