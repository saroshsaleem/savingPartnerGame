package saving_partner2;
import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
/**
 * Class Room - a room in shopping mall.
 *
 * This class is part of the "saving_partner" application. 
 * "Saving_partner" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Sarosh Saleem
 * @version 2020.06.25
 */

public class Room 
{
    private String description;
    private String roomName;
    private HashMap<String, Room> exits;        // stores exits of this room.
    public boolean isLocked;
    private Items item;
    private String direction;
    private ArrayList<Items>itemList;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @Sarosh description The room's description.
     */
    public Room(String roomName,String description,boolean isLocked) 
    {
        this.roomName=roomName;
        this.isLocked=isLocked;
        this.description = description;
        exits = new HashMap<>();
        itemList=new ArrayList();
    }
    
    /**
     * Define an exit from this room.
     * @sarosh direction The direction of the exit.
     * @sarosh neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);// adding value in hashmap
    }
    
    /**
     * Method getRoomName
     * it will give you room name
     * @return room name
     */
    public String getRoomName(){
        return roomName;
    }
    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "This is " + description + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {//collection of exits
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }
    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @Sarosh direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }  
    /**
     * Method addItem
     *  this will add itemin item colection of room
     * @Sarosh item A parameter
     */
    public void addItem(Items item){
        itemList.add(item);
    }
    /**
     * Method getDescription
     * this method will show item description
     * @return item description
     */
    public String getDescription(){
        return item.getItemDescription();
    }    
    /**
     * Method removeItems
     *this method will remove item fromm list
     * @Sarosh itemName A parameter
     */
    public void removeItems(String itemName){
        //loop for removing item from room
        for(int i=0; i<itemList.size();i++){
            if(itemList.get(i).getItemName().equals(itemName)&& itemList.size()>0){
                itemList.remove(i);
            }
            else{
            }
        }
    }
    /**
     * Method getItem
     *
     * @Sarosh itemName A parameter
     * 
     * this method will get item name from item list
     * @return item name from list
     */
    public Items getItem(String itemName){
        //getting item 
        for(int i=0; i<itemList.size();i++){
            if(itemList.get(i).getItemName().equals(itemName) || itemList.get(i).getItemDescription().equals(itemName)){
                return itemList.get(i);
            }
        }
        return null;
    }
    /**
     * Method itemNameList
     * this method will show collectoin of items in item list
     * @return itemlist
     */
    public String itemNameList(){
        String items="Items:  ";
        for(int i=0; i<itemList.size(); i++){
            items+=" "+ itemList.get(i).getItemName()+" ";
        }
        return items;
    } 
    /**
     * Method itemListSize
     *
     * @return item list size
     */
    public int itemListSize(){
       //getting item array list size
        return itemList.size();
    }
    /**
     * Method getLocked
     *
     * @return boolean is locked
     */
    public boolean getLocked(){
        return isLocked;
    }
    /**
     * Method setLocked
     * this method will set lock
     * @Sarosh lock A parameter
     */
    public void setLocked(boolean lock){
        boolean isLocked=lock;
    }
}