import java.util.Scanner;
import java.util.Arrays;

public class Game {

    // Globals
    public static final boolean DEBUGGING = false;  // Debugging flag.
    public static final int MAX_LOCALES = 11;       // Total number of rooms/locations we have in the game.
    public static int currentLocale = 0;            // Player starts in locale 0.
    public static String command;                   // What the player types as he or she plays the game.
    public static boolean stillPlaying = true;      // Controls the game loop.
    public static Locale[] locations;               // An uninitialized array of type Locale. See init() for initialization.
    public static int[][]  nav;                     // An uninitialized array of type int int.
    public static int moves = 0;                    // Counter of the player's moves.
    public static int score = 0;                    // Tracker of the player's score.
    public static int achievement = 0;              // Calculates the achievement ratio
    public static Items[] item;                     // An uninitialized array of type Items. See init() for initialization.
    //public static MagicItems[] magicitem;         // Temporarily counted out, made these items a string.
    public static Items[] inventory;
    public static boolean map_check = false;
    public static boolean guitar_check = false;
    public static boolean drums_check = false;
    public static boolean synthesizer_check = false;
    public static boolean mic_check = false;



    public static void main(String[] args) {

        System.out.println("Game: BAND UP");


        if (DEBUGGING) {
            // Display the command line args.
            System.out.println("Starting with args:");
            for (int i = 0; i < args.length; i++) {
                System.out.println(i + ":" + args[i]);
            }
        }

        // Set starting locale, if it was provided as a command line parameter.
        if (args.length > 0) {
            int startLocation = Integer.parseInt(args[0]);
            if ( startLocation >= 0 && startLocation <= MAX_LOCALES) {
                currentLocale = startLocation;
            }
        }

        // Get the game started.
        init();
        updateDisplay();

        // Game Loop
        while (stillPlaying) {
            getCommand();
            navigate();
            updateDisplay();
        }

        // We're done. Thank the player and exit.
        System.out.println("Thank you for playing.");
    }


    private static void init() {
        // Initialize any uninitialized globals.
        command = new String();
        stillPlaying = true;



        // Set up item instances of the Items class.
        // To add: set Magic Shoppe items as a subclass, tried and said there was a problem with not
        //         having a constructor, but the constructor was there.
        Items item0 = new Items(0);
        item0.setName("map");
        item0.setDesc("You now have the map. Type m or map to take a look.");

        Items item1 = new Items(1);
        item1.setName("guitar");
        item1.setDesc("Wow, an old Gibson Les Paul! This thing will sound great once you brush off the dust!");

        Items item2 = new Items(2);
        item2.setName("drums");
        item2.setDesc("A high quality drum set. Complete with cowbell.");

        Items item3 = new Items(3);
        item3.setName("synthesizer");
        item3.setDesc("This old-school Roland Juno with get your band soundin' groovy in a jiffy.");

        Items item4 = new Items(4);
        item4.setName("microphone");
        item4.setDesc("Now the crowd can hear those pipes!");

        // Set up the Items array
        item = new Items[5];
        item[0] = item0;
        item[1] = item1;
        item[2] = item2;
        item[3] = item3;
        item[4] = item4;


        // Set up the location instances of the Locale class.
        Locale loc0 = new Locale(0);
        loc0.setName("Home");
        loc0.setDesc("You are in your home. A man comes to your door, and wishes to speak with you of an\n" +
                "urgent matter. There is an upcoming local \"Battle of the Bands\" that is a qualifier for a\n" +
                "nation-wide competition for the greatest band in all of the land. Many new musicians are\n" +
                "starting to collect gear and find bandmates to perform with them, to compete in this\n" +
                "local competition.  This competition happens once every twenty years, and you have just\n" +
                "come of age to participate.  You have been looking forward to this competition since you\n" +
                "were a young child. As the man leaves, he puts a map on the table. Now it is now time to \n" +
                "start your adventure to create the greatest band of your generation. Don't forget to take \n" +
                "the map!");
        loc0.setPossibleMoves("You can go south to leave the house.");
        loc0.setItem(item0);
        loc0.setHasTaken(false);
        loc0.setMagicItems("");

        Locale loc1 = new Locale(1);
        loc1.setName("Path");
        loc1.setDesc("You are outside. Grandpa's house is to the east. You don't yet have instruments, " +
                "why don't you hit up good ole' Gramps?");
        loc1.setPossibleMoves("Possible directions are north, south, and east.");
        loc1.setItem(null);
        loc1.setHasTaken(false);
        loc1.setMagicItems("");

        Locale loc2 = new Locale(2);
        loc2.setName("Grandpa's House");
        loc2.setDesc("You enter your Grandpa's house. He has been waiting for you. His shed is to the east.");
        loc2.setPossibleMoves("Possible directions are west and east.");
        loc2.setItem(null);
        loc2.setHasTaken(false);
        loc2.setMagicItems("");

        Locale loc3 = new Locale(3);
        loc3.setName("Grandpa's Shed");
        loc3.setDesc("You have entered the shed. In the corner of the room, you see a dusty guitar case." + "\n" +
               "Go back to the house.");
        loc3.setPossibleMoves("Possible direction is west.");
        loc3.setItem(item1);
        loc3.setHasTaken(false);
        loc3.setMagicItems("");

        Locale loc4 = new Locale(4);
        loc4.setName("Town");
        loc4.setDesc("The central hub of the community. To the east is the Magic Shoppe, south for the music shop, and west for the venue.");
        loc4.setPossibleMoves("Possible directions are north, south, east, and west.");
        loc4.setItem(null);
        loc4.setHasTaken(false);
        loc4.setMagicItems("");

        Locale loc5 = new Locale(5);
        loc5.setName("Magick Shoppe");
        loc5.setDesc("The following items are for sale: ");
        loc5.setPossibleMoves("Possible direction is west.");
        loc5.setItem(null);
        loc5.setHasTaken(false);
        loc5.setMagicItems("The following items are for sale: Super Sharp Strings" + "\n"
                            +  "Double Kick Pedal" + "\n" + "Guitar Pedal.");

        Locale loc6 = new Locale(6);
        loc6.setName("Music Store");
        loc6.setDesc("You have entered the music shop. The man behind the counter tells you there is a \n" +
               "drum room to the east, and a synthesizer room to the west, but not many are left.");
        loc6.setPossibleMoves("Possible directions are north, east, and west.");
        loc6.setHasTaken(false);
        loc6.setMagicItems("");

        Locale loc7 = new Locale(7);
        loc7.setName("Drum Room");
        loc7.setDesc("There is one drum set left. Take it while it is still there!");
        loc7.setPossibleMoves("Possible direction is west.");
        loc2.setItem(item2);
        loc7.setHasTaken(false);
        loc7.setMagicItems("");

        Locale loc8 = new Locale(8);
        loc8.setName("Synthesizer Room");
        loc8.setDesc("A final Roland Juno is hanging on the wall under a spotlight..what a beauty.");
        loc8.setPossibleMoves("Possible direction is east.");
        loc8.setItem(item3);
        loc8.setHasTaken(false);
        loc8.setMagicItems("");

        Venue loc9 = new Venue(9); // Locale(2);
        loc9.setName("Venue Entrance Hall");
        loc9.setDesc("To the north is a small closet that is cracked open. To the west, the main stage.");
        loc9.setPossibleMoves("Possible directions are north, east, and west.");
        loc9.setItem(null);
        loc9.setHasTaken(false);
        loc9.setMagicItems("");


        Venue loc10 = new Venue(10); // Locale(2);
        loc10.setName("Storage Closet");
        loc10.setDesc("There is a microphone placed on a mic stand, ready to go.");
        loc10.setPossibleMoves("Possible direction is south.");
        loc10.setItem(item4);
        loc10.setHasTaken(false);
        loc10.setMagicItems("");


        Venue loc11 = new Venue(11); // Locale(2);
        loc11.setName("Main Stage");
        loc11.setDesc("There is a large stage under blue lights, with a bar on the side wall.");
        loc11.setPossibleMoves("Possible direction is east.");
        loc11.setItem(null);
        loc11.setHasTaken(false);
        loc11.setMagicItems("");


        // Set up the location array.
        locations = new Locale[12];
        locations[0] = loc0;
        locations[1] = loc1;
        locations[2] = loc2;
        locations[3] = loc3;
        locations[4] = loc4;
        locations[5] = loc5;
        locations[6] = loc6;
        locations[7] = loc7;
        locations[8] = loc8;
        locations[9] = loc9;
        locations[10] = loc10;
        locations[11] = loc11;


        //Set up Magic Items
        MagicItems magicitem0 = new MagicItems(0);
        magicitem0.setName("Super Sharp Strings");
        magicitem0.setDesc("Allow for major string bend-age during ear-splitting solos.");

        MagicItems magicitem1 = new MagicItems(1);
        magicitem1.setName("Double Kick Pedal");
        magicitem1.setDesc("Get those Heavy Metal Bass hits on your drum kit, with this double pedal.");

        MagicItems magicitem2 = new MagicItems(2);
        magicitem2.setName("Guitar Pedal");
        magicitem2.setDesc("This pedal allows you to create your own effects to wow your audience.");

        /*magicitem = new MagicItems[3]; // temporarily commented out
        magicitem[0] = magicitem0;
        magicitem[1] = magicitem1;
        magicitem[2] = magicitem2;
        */

        // Set up inventory array
       inventory = new Items[5];
       inventory[0] = item0;
       inventory[1] = item1;
       inventory[2] = item2;
       inventory[3] = item3;
       inventory[4] = item4;


        if (DEBUGGING) {
            System.out.println("All game locations:");
            for (int i = 0; i < locations.length; ++i) {
                System.out.println(i + ":" + locations[i].toString());
            }
            System.out.println("All game items:");
            for (int i = 0; i < item.length; ++i) {
                System.out.println(i + ":" + item[i].toString());
            }
        }


        // Set up the navigation matrix.
        nav = new int[][] {
                                 /* N   S   E   W */
                                 /* 0   1   2   3 */
         /* nav[0] for loc 0 */  { -1,  1, -1, -1 },   // Home
         /* nav[1] for loc 1 */  {  0,  4,  2, -1 },   // Path
         /* nav[2] for loc 2 */  { -1, -1,  3,  1 },   // Grandpa's House
         /* nav[3] for loc 3 */  { -1, -1, -1,  2 },   // Grandpa's Shed
         /* nav[4] for loc 4 */  {  1,  6,  5,  9 },   // Town
         /* nav[5] for loc 5 */  { -1, -1, -1,  4 },   // Magick Shoppe
         /* nav[6] for loc 6 */  {  4, -1,  7,  8 },   // Music Shop
         /* nav[7] for loc 7 */  { -1, -1, -1,  6 },   // Drum Room
         /* nav[8] for loc 8 */  { -1, -1,  6, -1 },   // Synthesizer Room
         /* nav[9] for loc 9 */  { 10, -1,  4, 11 },   // Venue
         /* nav[10] for loc 10 */{ -1,  9, -1, -1 },   // Storage closet with microphones
         /* nav[11] for loc 11 */{ -1, -1,  9, -1 }   // Main Stage
        };

    }

    private static void updateDisplay() {
        System.out.println(locations[currentLocale].getText());
    }

    private static void getCommand() {
        System.out.print("[ moves " + moves + ", score " + score + ", achievement " + achievement + "]"); //achievement " + achievement); check line 15
        System.out.println();
        Scanner inputReader = new Scanner(System.in);
        command = inputReader.nextLine();  // command is global.
    }

    private static void navigate() {
        final int INVALID = -1;
        int dir = INVALID;  // This will get set to a value > 0 if a direction command was entered.

        if (        command.equalsIgnoreCase("north") || command.equalsIgnoreCase("n") ) {
            dir = 0;
        } else if ( command.equalsIgnoreCase("south") || command.equalsIgnoreCase("s") ) {
            dir = 1;
        } else if ( command.equalsIgnoreCase("east")  || command.equalsIgnoreCase("e") ) {
            dir = 2;
        } else if ( command.equalsIgnoreCase("west")  || command.equalsIgnoreCase("w") ) {
            dir = 3;

        } else if ( command.equalsIgnoreCase("quit")  || command.equalsIgnoreCase("q")) {
            quit();
        } else if ( command.equalsIgnoreCase("help")  || command.equalsIgnoreCase("h")) {
            help();
        } else if ( command.equalsIgnoreCase("map")   || command.equalsIgnoreCase("m")) {
            map();
        } else if ( command.equalsIgnoreCase("inventory") || command.equalsIgnoreCase("i")) {
            inventory();
        } else if ( command.equalsIgnoreCase("take")  || command.equalsIgnoreCase("t")) {
            take();
        }
        //} // else if (command.equalsIgnoreCase("play") || command.equalsIgnoreCase("p")) {
            //play(item.name);

        if (dir > -1) {   // This means a dir was set.
            int newLocation = nav[currentLocale][dir];
            if (newLocation == INVALID) {
                System.out.println("You cannot go that way.");
            } else {
                currentLocale = newLocation;
                moves = moves + 1;
                Locale currLoc = locations[currentLocale];
                if (! currLoc.getHasVisited()) {
                   score = score + 5;
                   currLoc.setHasVisited(true);
                   achievement = score/moves;
                }
            }
        }
    }




  /*  private void take(Items item) {         //put items into inventory array
         if (! item.getHasTaken() ) {
             item.setHasTaken(true);
             for (int i=0; i <=4; i++)
                 inventory[X] = item;
             }
             else {
             System.out.println("There isn't an item to take.");
            }
         }  */

    private static void take() {

        switch(currentLocale) {
            case 0:
                if (!map_check) {
                    map_check = true;
                    inventory[0] = item[0];
                System.out.println("You now have the map.");
                }  else {
                    System.out.println("You already have the map.");
                }

            break;
            case 3:
                if (!guitar_check) {
                    guitar_check = true;
                    inventory[1] = item[1];
                    System.out.println("You now have the guitar!");
                }  else {
                    System.out.println("You already have the guitar.");
                }
            break;
            case 7:
                if (!drums_check) {
                    drums_check = true;
                    inventory[2] = item[2];
                    System.out.println("You now have the drums!");
                }  else {
                    System.out.println("You already have the drums.");
                }
            break;
            case 8:
                if (!synthesizer_check) {
                    synthesizer_check = true;
                    inventory[3] = item[3];
                    System.out.println("You now have the synthesizer!");
                }  else {
                    System.out.println("You already have the synthesizer.");
                }
            break;
            case 10:
                if (!mic_check) {
                    mic_check = true;
                    inventory[4] = item[4];
                    System.out.println("You now have the microphone!");
                }  else {
                    System.out.println("You already have the microphone.");
                }
            break;
        }
    }


    private static void help() {
        System.out.println("The commands are as follows:");
        System.out.println("   n/north");
        System.out.println("   s/south");
        System.out.println("   q/quit");
        System.out.println("   t/take");
        System.out.println("   m/map");
        System.out.println("   i/inventory");
    }

    private static void inventory() {

        System.out.println("The following items are in your inventory:");
        System.out.println(Arrays.toString(inventory));

    }


    private static void map() {
        System.out.print(
                 "..........................Home.........................." + "\n" +
                 "...........................|............................" + "\n" +
                 "........ Storage Closet ...|............................" + "\n" +
                 "................|.........Path---Grandpa's house---Shed." + "\n" +
                 "................|..........|............................" + "\n" +
                 "................|..........|............................" + "\n" +
                 "Main Stage -- Venue ----- Town----Magick Shoppe........." + "\n" +
                 "...........................|............................" + "\n" +
                 ".................... ......|............................" + "\n" +
                 "..Synthesizer Room --- Music Shop ------ Drum Room......" + "\n" + "\n");

    }


    public play(item.name) {

        if(currentLocale = 11) {

            if(getCommand(play());)
        }

    }

    private static void quit() {
        stillPlaying = false;
    }

}
