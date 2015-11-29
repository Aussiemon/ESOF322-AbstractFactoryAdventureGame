/*
 * Test class for Door.java of ESOF322-AssignmentP2
 */
package esof322.a4;

import esof322.a4.Door;
import esof322.a4.Room;
import esof322.a4.Key;
import esof322.a4.Player;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Matthew Rohrlach
 * @date 10/26/15
 */
public class DoorTest {
    Door dTest;
    Player pTest;
    Room rTestOut, rTestIn, rTest;
    Key kTest;
    
    public DoorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        //Initialize objects to test method
        rTestOut = new Room();
        rTestIn = new Room();
        rTest = new Room();
        
        kTest = new Key();
        
        dTest = new Door(rTestOut, rTestIn, kTest);
        
        pTest = new Player();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of enter method, of class Door.
     */
    @Test
    public void testEnter() {
        System.out.println("Testing: enter() method");
        
        //No key for door, inside door's room
        pTest.setLoc(rTestIn);
        dTest.enter(pTest);
        assertSame(pTest.currentStatus(),"You don't have the key for this door!\nSorry.");
        assertSame(pTest.getLoc(), rTestIn);
        
        //No key for door, outside door's room
        pTest.setLoc(rTestOut);
        dTest.enter(pTest);
        assertSame(pTest.currentStatus(),"You don't have the key for this door!\nSorry.");
        assertSame(pTest.getLoc(), rTestOut);
        
        //Somewhere, going in with key
        pTest.pickUp(kTest);
        pTest.setLoc(rTest);
        dTest.enter(pTest);
        assertSame(pTest.currentStatus(),"Your key works! The door creaks open,\n"
            + "and slams behind you after you pass through.");
        assertSame(pTest.getLoc(), rTest);
        
        //Outside going in with key
        pTest.pickUp(kTest);
        pTest.setLoc(rTestOut);
        dTest.enter(pTest);
        assertSame(pTest.currentStatus(),"Your key works! The door creaks open,\n"
            + "and slams behind you after you pass through.");
        assertSame(pTest.getLoc(), rTestIn);
        
        //Inside going out with key
        pTest.pickUp(kTest);
        pTest.setLoc(rTestIn);
        dTest.enter(pTest);
        assertSame(pTest.currentStatus(),"Your key works! The door creaks open,\n"
            + "and slams behind you after you pass through.");
        assertSame(pTest.getLoc(), rTestOut);
        
        
    }
}
