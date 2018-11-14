import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> exits;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(String direction, Room exit) 
    {
        exits.put(direction, exit);
    }
    
    /**
     * @param direction uma String com a dire��o
     * @return A sa�da adequada
     */
    public Room getExit(String direction)
    {	
    	return exits.get(direction);
    }
    
    /**
     * Retorna uma descri��o das sa�das da sala,
     * por exemplo, "Sa�das: norte oeste".
     * @return Uma descri��o das sa�das dispon�veis
     */
    public String getExitString() 
    {
    	String returnString = "Sa�das: ";
        
    	for (String exit : exits.keySet()) {
    		returnString += exit + " ";
    	}
        
        return returnString;
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Retorna uma descri��o longa desta sala, na forma:
     *   Voc� est� na cozinha.
     *   Sa�das: norte oeste
     * @return Uma descri��o da sala, incluindo sa�das.
     */
    public String getLongDescription()
    {
    	return "Voc� est� " + description + ".\n" +
    			getExitString();
    }

}