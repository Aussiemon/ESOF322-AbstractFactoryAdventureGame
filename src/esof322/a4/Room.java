/**
 * @author Andrew Jenkins, Stephanie McLaren, David Schwehr,
 * Kay Spokas, Matt Rohrlach 
 */

/**
 * @SummaryOfChanges
 *  added method call to set player status when room is successfully entered
 *  in enter method
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

// class Room


import java.util.ArrayList;
import java.util.ListIterator;


public class Room implements CaveSite {

  private String description;

  private CaveSite[] side = new CaveSite[6];

  private ArrayList<Item> contents = new ArrayList<Item>();

  Room() {
    side[0] = new Wall();
    side[1] = new Wall();
    side[2] = new Wall();
    side[3] = new Wall();
    side[4] = new Wall();
    side[5] = new Wall();
    }

  public void setDesc(String d){
    description = d;
    }

  public CaveSite[] getSideArray(){
      return side;
  }
  
  public void setSide(int direction, CaveSite m){
   side[direction] = m;
   }

  public void addItem(Item theItem){
   contents.add(theItem); 
   }

  public void removeItem(Item theItem){
   contents.remove(theItem);
   }

  public boolean roomEmpty(){
	 return contents.isEmpty();
  }

  public Item[] getRoomContents(){
   Item[] contentsArray = new Item[contents.size()];
   contentsArray = contents.toArray(contentsArray);
   return contentsArray;
  }
  
  public ArrayList<Item> getRoomContentsList(){
      return contents;
  }

  public void enter(Player p) {
   p.setLoc(this); 
   p.setCurrentStatus("You proceed through the dark.");
  }

  public void exit(int direction, Player p){
   side[direction].enter(p);
 }

  public String getDesc(){
   ListIterator<Item> roomContents = contents.listIterator(); 
   String contentString = "";
   while(roomContents.hasNext())
     contentString = 
	contentString + (roomContents.next()).getDesc() + " ";

     return description + '\n' + '\n' +
     "Room Contents: " + contentString + '\n';
   }
  
    //Do-nothing methods to override in Level1Room
    public boolean itemThief(){
        return false;
    }
    
    public String getThiefStatus(){
        return "";
    }
    
    public void resetItemThief(){
    }
}
