package saving_partner2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.Random;
/**
 *  This is the main class of Struggle for Partner in which player
 *  has to save partner from kidnapper, as she is kidnapped after
 *  late night party, they call player to save her by come to under construct 
 *  shopping mall with 1M$. However, he don't have enough money to save her partner,
 *  but he go at venue to save his partner with his techniques.
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Sarosh Saleem
 * @version 14/05/20
 */

public class Game 
{   //instance variable
    private Random random;
    private Stack preRoomStack;
    Room previousRoom;
    private int timeCount;
    private boolean finished;
    private Command command;
    private ArrayList<Room> room;
    private Parser parser;
    private Room currentRoom;
    private Items currentItem;        
    private ArrayList<Items>inventoryItems;
            
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {   //declaring varibales, collection and methods
        random=new Random();
        room=new ArrayList();
        timeCount=0;
        inventoryItems=new ArrayList();
        settingAndAdding();
        parser = new Parser();
        preRoomStack=new Stack();
    }
    /**
     * Create all the rooms
     */
    private void settingAndAdding()
    {   //create items
        Room transporterRoom,lobby,managerOffice,clothShop,gamingZone,securityOffice,theatre,sportsShop,foodCourt,conferenceRoom,roofTop;
        Items hammer,lightController,securityKey,toolKit,tradieTapCard,tradieSuit,walkieTalkie,conferenceKey,ledScreen,dummyGun,drill,steelRod,bag,bat,knife,handsCuff,table,chair,gun,axe;
        hammer=new Items("hammer","This is hammer to unlock the door",100);
        lightController=new Items("light-controller","This is to operate whole mall light");
        securityKey=new Items("security-key","This is use to open unlock",100);
        toolKit=new Items("tool-kit","With different tools to fix");
        tradieTapCard=new Items("tap-card","This is usefull to access important room");
        tradieSuit=new Items("tradie-suit","This is suit to hidden your identity");
        walkieTalkie=new Items("walkie-talkie","This to listen to voice of others");
        conferenceKey=new Items("conference-key","This is to access conference room",100);
        drill=new Items("drill","This is drill");
        steelRod=new Items("steel-rod","This is to hit someone");
        ledScreen=new Items("led-screen","This is for game to display");
        dummyGun=new Items("dummy-gun","This is gun to scar someone");
        bag=new Items("bag","This is important to divert someone");
        bat=new Items("bat","This is to hit someone");
        knife=new Items("knife","This is to hit someone");
        handsCuff=new Items("handscuff","To use for someone");
        table=new Items("table","This is table");
        chair=new Items("chair","This is chair");
        gun=new Items("Gun","oops! this is  may be you can use for your partner :p");
        axe=new Items("axe","this is axe to hit someone");
        
        // create the rooms
        lobby = new Room("lobby","Lobby of the shopping mall, good luck.. journey starts from here",false);
        managerOffice = new Room("manager-office","manager office, be carefull... ",true);
        clothShop = new Room("cloth-shop","cloth shop, with important things for you",true);
        gamingZone = new Room("gaming-zone","gaming zone of shopping mall.",false);
        securityOffice = new Room("security-office","security office of shopping mall, be carefull.",true);
        theatre= new Room("theatre","theatre, be quiet,",false);
        sportsShop= new Room("sports-shop","sports shop, here will you find item to distract kidnapper",false);
        foodCourt= new Room("food-court","food court, has important item to win game.",false);
        conferenceRoom= new Room("conference-room","Conference room.",true);
        roofTop= new Room("roof-top","roof top",false);
        transporterRoom=new Room("transporter", " tarnsporter room, and will send you somewhere else",false);       
        currentRoom = lobby;
        //addding items in room
        lobby.addItem(hammer);
        lobby.addItem(lightController);
        
        managerOffice.addItem(securityKey);
        managerOffice.addItem(toolKit);
        
        clothShop.addItem(tradieTapCard);
        clothShop.addItem(tradieSuit);
        
        gamingZone.addItem(ledScreen);
        gamingZone.addItem(dummyGun);
        
        theatre.addItem(steelRod);
        theatre.addItem(drill);
        
        securityOffice.addItem(conferenceKey);
        securityOffice.addItem(walkieTalkie);
        
        sportsShop.addItem(bag);
        sportsShop.addItem(bat);
        
        foodCourt.addItem(handsCuff);
        foodCourt.addItem(knife);
        
        conferenceRoom.addItem(table);
        conferenceRoom.addItem(chair);
        //adding roof rooms item
        roofTop.addItem(axe);
        roofTop.addItem(gun);
        
        // setting room exits
        lobby.setExit("east", clothShop);
        lobby.setExit("west", managerOffice);
        lobby.setExit("up", gamingZone);
        
        clothShop.setExit("west", lobby);
        clothShop.setExit("east",transporterRoom);
        
        transporterRoom.setExit("west",clothShop);
        
        managerOffice.setExit("east", lobby);
        
        gamingZone.setExit("down",lobby);
        gamingZone.setExit("west",securityOffice);
        gamingZone.setExit("east",theatre);
        gamingZone.setExit("south",sportsShop);
        
        theatre.setExit("west",gamingZone);
        
        securityOffice.setExit("east",gamingZone);
        
        sportsShop.setExit("north",gamingZone);
        sportsShop.setExit("up",foodCourt);
       
        foodCourt.setExit("down",sportsShop);
        foodCourt.setExit("east",conferenceRoom);
        foodCourt.setExit("up",roofTop);
        
        roofTop.setExit("down",foodCourt);
        
        conferenceRoom.setExit("west",foodCourt);
        //roomlist adding
        room.add(lobby);
        room.add(managerOffice);
        room.add(clothShop);
        room.add(gamingZone);
        room.add(securityOffice);
        room.add(theatre);
        room.add(sportsShop);
        room.add(foodCourt);
        room.add(roofTop);
    }
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();
                finished=false;
        //it will continue game while finished is not equals to true
        while (! finished) {
                //get command until loop is not finished
                Command command = parser.getCommand();
                finished = processCommand(command);
            // if moves get equal to 50 it will stop game and player will lose game
            if(counter()==50){
                finished=true;
                System.out.println("You loose game as you didn't finish in "+counter()+" moves.");
            }
            
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Method counter
     * it counts the move of player
     * @return timeCount 
     */
    private int counter(){
        timeCount++;
        return timeCount;
    }
    
    /**
     * Method printWelcome
     * to print Welcome note
     */
    private void printWelcome()
    {
        System.out.println("                                             ------------------------------");
        System.out.println("                                             Welcome to Struggle for Partner");
        System.out.println("                                             ------------------------------");
        System.out.println("                                Struggle for Partner is situated in under construction Shopping mall late night, ");
        System.out.println("                                however it is intellectual and strategical game to save your partner.");
        System.out.println("                                             **************GAME START***************");
        System.out.println("                                                    Type 'help' if you need help.");
        System.out.println("                                        Hint: when once item is use drop it where you see you can");
        System.out.println("                              If moves are equal to 50 you will lose game, so complete game in less than 50 moves");
        System.out.println("                              WINNING CONDITION: You have to find kidnapper in mall and wisely use items to catch kidnapper                      ");
        System.out.println(currentRoom.getLongDescription());
        System.out.println(currentRoom.itemNameList());
        
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @Sarosh command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */  
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if(commandWord.equals("take")){
            take(command);
        }
        else if(commandWord.equals("inventory")){
            inventoryItems(command);
        }
        else if(commandWord.equals("drop")){
            drop(command);
        }
        // it is looking if use item is hands cuff and you are in conference room 
        else if(commandWord.equals("use")){
            useItem(command);
            if(command.getSecondWord().equals("handscuff") && currentRoom.getRoomName().equals("conference-room")){
              wantToQuit=true;
            }
            else if((!command.getSecondWord().equals("handscuff") && !command.getSecondWord().equals("bag") && !command.getSecondWord().equals("conference-key")) && currentRoom.getRoomName().equals("conference-room")){
                wantToQuit=true;
            }
            else{
                wantToQuit=false;
            }
        }
        else if (commandWord.equals("back")) {
            back(command);
        }
        else if(commandWord.equals("moves")){
            moves(command);
        }
        else if(commandWord.equals("look")){
            look(command);
        }
        // else command not recognised.
        return wantToQuit;
    }
    // implementations of user commands:

    /**
     * Method printHelp
     * in this method there are commands which player can use
     */
    private void printHelp() 
    {
        System.out.println("Hello Player, you are in danger zone, and you are lost ");
        System.out.println("at the shopping mall.");
        System.out.println(currentRoom.getLongDescription());
        System.out.println(currentRoom.itemNameList());
        System.out.println("Your command words are:");
        parser.showCommands();      
    }


    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else{
            previousRoom=currentRoom;
            preRoomStack.push(previousRoom);
            currentRoom = nextRoom;
            random();
            counter();
            //checking whether room is closed or open
            if(nextRoom.getLocked()==true) {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
                System.out.println("This is locked and you need to use item to open the  door");
            }   
            else{
                System.out.println(currentRoom.getLongDescription());
                System.out.println(currentRoom.itemNameList());
            }
        }
    }
    /**
     * Method take
     *
     * @Sarosh command A parameter
     * it takes items in inventory arraylist
     * if items in inventory are less than 5 then player can add, otherwise it will show error message.
     */
    private void take(Command command){
        if(!command.hasSecondWord()){
            System.out.println("Take which item?");
            return;
        }
        String item2= command.getSecondWord();
        Items item=currentRoom.getItem(item2);
         //checking about inventory
        if(item != null){
            if(inventoryItems.size()<5){
                inventoryItems.add(item);
                System.out.println("item take by you: "+item2);
                currentRoom.removeItems(item2);
            }
            else{
                System.out.println("you can't add more item in your inventor you have to drop some items to add new items as you can't add more than 5 items in your inventory");
            }
            
        }
        else{
            System.out.println("There is no items in room");
        }
      }
        
      /**
       * Method useItem
       *
       * @Sarosh command A parameter
       * in use if player is using correct item to open the locked it will open, otherwise it will show error message
       */
      private void useItem(Command command){
          if(!command.hasSecondWord()){
                System.out.println("what to use?");
        }
        String itemName= command.getSecondWord();
        Items item=null;
        for(int i=0;i<inventoryItems.size();i++){
            if(inventoryItems.get(i).getItemName().equals(itemName)){
                    item=inventoryItems.get(i);
            }
        }
            if (item==null){
                System.out.println("This is not in your inventory");
            }//checking that if use item can open that door or not by if condition
            else if(currentRoom.getRoomName().equals("cloth-shop") && item.getItemName().equals("hammer")){
               System.out.println("You open the "+currentRoom.getRoomName()+" by using correct item.");
               System.out.println(currentRoom.getLongDescription());
               System.out.println(currentRoom.itemNameList());
               
            }
            else if( item.getItemName().equals("tradie-suit")){
                System.out.println("Thats good you use tradie suit to hide your identity by ");
                for(int i=0;i<inventoryItems.size();i++){
                    if(inventoryItems.get(i).equals("tradie-suit")){
                        inventoryItems.remove(i);
                    }
                }
            }
            else if(currentRoom.getRoomName().equals("manager-office") && item.getItemName().equals("tap-card")){
               currentRoom.setLocked(false);
               System.out.println("You open the "+currentRoom.getRoomName()+" by using correct item.");
               System.out.println( currentRoom.getLongDescription());
               System.out.println(currentRoom.itemNameList());
            }
            else if(currentRoom.getRoomName().equals("security-office") && item.getItemName().equals("security-key")){
               currentRoom.setLocked(false);
               System.out.println("You open the "+currentRoom.getRoomName()+" by using correct item.");
               System.out.println( currentRoom.getLongDescription());
               System.out.println(currentRoom.itemNameList());
            }
            else if(currentRoom.getRoomName().equals("conference-room") && item.getItemName().equals("conference-key")){
               currentRoom.setLocked(false);
               System.out.println("You open the "+currentRoom.getRoomName()+" by using correct item.");
               System.out.println("OOps..! Here is the kidnapper of your partner and you partner is here too");
               System.out.println("show him something from your inventory to distract him");
               System.out.println("or use something from your inventory to hanscuff kidnapper and save your partner");
               System.out.println( currentRoom.getLongDescription());
               System.out.println(currentRoom.itemNameList());
            }
            else if((item.getItemName().equals("bag")&& currentRoom.getRoomName().equals("conference-room"))){
                  System.out.println("you successfully distract the kidnapper"); 
                  System.out.println("now use something to handscuff kidnapper and save your partner to win this game");
            }
            else if((item.getItemName().equals("handscuff") && currentRoom.getRoomName().equals("conference-room"))){
                    System.out.println("Congratulations!, You save your partner and win this game, Hurray");
            }
            else if((!item.getItemName().equals("handscuff") && currentRoom.getRoomName().equals("conference-room"))){
                    System.out.println("oh, sorry kidnapped saw you and run away with your partner");
            }
            else{
                System.out.println("this item is in your inventory but this need something else to unlock room");    
            } 
        }
        
         /**
          * Method drop
          *
          * @Sarosh command A parameter
          * if drop is use then player will drop item from it inventory,
          * otherwise it will show error message.
          */
         private void drop(Command command){
          if(!command.hasSecondWord()){
                System.out.println("drop what?");
            }
            String dropItem=command.getSecondWord();
            Items item=null;
            int indexNumber=0;
          for(int i=0; i<inventoryItems.size();i++){
                if(inventoryItems.get(i).getItemName().equals(dropItem)){
                    item=inventoryItems.get(i);
                    indexNumber=i;
                }
            }
          if( item==null){
                System.out.println("This item is not in inventory");
          }//checking that if currentroom has already two items or not
          else if(item!=null && currentRoom.itemListSize()<2){
                    currentRoom.addItem(item);
                    inventoryItems.remove(indexNumber);
                    System.out.println("Item droped ");
          }
            else{
                 System.out.println("This room can't contain more than 2 items");
            }
        }
        
    /**
     * Method inventoryItems
     *
     * @Sarosh command A parameter
     * it will show items in inventory if invventory is written
     */
    private void inventoryItems(Command command){
        if(command.hasSecondWord()){
            System.out.println("what can't get that");
        }// adding in arraylist of inventory
        else{
            System.out.print("Inventory: ");
            for(int i=0; i<inventoryItems.size();i++){
                System.out.println(inventoryItems.get(i).getItemName()+" ");
            }
        }
    }
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * Method moves
     *
     * @Sarosh command A parameter
     * moves command will show how many moves are taken by user
     */
    private void moves(Command command){// show the moves of player
        if(command.hasSecondWord()) {
           System.out.println("move what?");
        }
        else {
            System.out.println("your total move you have done till now are: "+counter());
        }
    }
    
    /**
     * Method look
     *
     * @Sarosh command A parameter
     * look command will show internal items of room and room descriptoin
     * if only look is written then whole description otherwise, just for specific item.
     */
    private void look(Command command){
     if(command.hasSecondWord() && currentRoom.getLocked()== false){
         String itemName=command.getSecondWord();
         Items item=currentRoom.getItem(itemName);
         if(item!=null){
            System.out.println(item.getItemDescription());   
            }
         else{
             System.out.println("This item is not in this room");
            }
        }
     else if (currentRoom.getLocked()==false){
           System.out.println(currentRoom.getLongDescription());
           System.out.println(currentRoom.itemNameList());
      }
      else{
          System.out.println("use specific item to open this room");
        }
    }
    
    /**
     * Method back
     *
     * @Sarosh command A parameter
     * back is used to send player in previous room
     * if previous room is there, otherwise will print error message
     */
    //it will move player in previous room
    private void back(Command command){
        if(command.hasSecondWord()){
          System.out.println("Back what?");
          return;
        }
        else{
            if(preRoomStack.isEmpty()){
                System.out.println("Sorry, there are no any previous room beside this point.");
                return;
            }
            else{
                previousRoom=currentRoom;
                currentRoom=(Room) preRoomStack.pop();
                System.out.println("You are in now back to previous room");
                System.out.println(currentRoom.getLongDescription());
            }
            
        }
     }
     
    /**
     * Method random
     * random is sending player in any room of shoping mall by random function 
     * @return  Room
     */
    private Room random(){//move player in random roomS
        if(currentRoom.getRoomName().equals("transporter")){
          System.out.println(currentRoom.getLongDescription());  
          currentRoom= room.get(random.nextInt(room.size()+1));
        }
        return currentRoom;
    }
}