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

// class Level0Room

public class Level0Room extends Room{
    private CaveSite[] side0 = this.getSideArray();
    
    Level0Room() {
    side0[0] = new Level0Wall();
    side0[1] = new Level0Wall();
    side0[2] = new Level0Wall();
    side0[3] = new Level0Wall();
    side0[4] = new Level0Wall();
    side0[5] = new Level0Wall();
    }
}