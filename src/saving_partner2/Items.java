package saving_partner2;
/**
 * This is item class.
 * In this class item has mutator accessor has important part in game,
 * as it is two item in each room.
 * @author (Sarosh Saleem)
 * @version (25.06.20)
 */
public class Items
{
    // instance variables
    private String itemDescription;
    private String itemName;
    private int power;
    /**
     * Constructor for objects of class Items
     */
    public Items(String itemName,String itemDescription)
    {
        // initialise instance variables
        this.itemDescription=itemDescription;
        this.itemName=itemName;
    }
    public Items(String itemName,String itemDescription,int power)
    {
        // initialise instance variables
        this.itemDescription=itemDescription;
        this.itemName=itemName;
        this.power=power;
    }
    /**
     * Method getItemDescription
     *
     * @return item description
     */
    public String getItemDescription(){
    return itemDescription;
    }
    /**
     * Method getItemPower
     *
     * @return power
     */
    public int getItemPower(){
    return power;
    }
    /**
     * Method getItemName
     *
     * @return item name
     */
    public   String getItemName(){
    return itemName;
    }
}
