import java.util.Scanner;

public class Game {

    // Globals
    public static final boolean DEBUGGING = true;   // Debugging flag.
    public static final int MAX_LOCALES = 11;        // Total number of rooms/locations we have in the game.
    public static int currentLocale = 0;            // Player starts in locale 0.
    public static String command;                   // What the player types as he or she plays the game.
    public static boolean stillPlaying = true;      // Controls the game loop.
    public static Locale[] locations;               // An uninitialized array of type Locale. See init() for initialization.
    public static int[][]  nav;                     // An uninitialized array of type int int.
    public static int moves = 0;                    // Counter of the player's moves.
    public static int score = 0;                    // Tracker of the player's score.
    // problem with divides by zero ~ public static float achievement = score/moves;  // Calculates the achievement ratio
    public static Items[] item;                     // An uninitialized array of type Items. See init() for initialization.
    //public static boolean hasVisited = false;
    public static void main(String[] args) {
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

        // Set up the location instances of the Locale class.
        Locale loc0 = new Locale(0);
        loc0.setName("Home");
        loc0.setDesc("You are in your home. A man comes to your door, and wishes to speak with you of an\n" +
                "urgent matter. There is an upcoming local \"Battle of the Bands\" that is a qualifier for a\n" +
                "nation-wide competition for the greatest band in all of the land. Many new musicians are\n" +
                "starting to collect gear and find bandmates to perform with them, to compete in this\n" +
                "local competition.  This competition happens once every twenty years, and you have just\n" +
                "come of age to participate.  You have been looking forward to this competition since you\n" +
                "were a young child. The man leaves, and it is now time to start your adventure to create\n" +
                "the greatest band of your generation.");

        Locale loc1 = new Locale(1);
        loc1.setName("Path");
        loc1.setDesc("You are outside. Grandpa's house is to the east. You don't yet have instruments, " +
                "why don't you hit up good ole' Gramps?");

        Locale loc2 = new Locale(2);
        loc2.setName("Grandpa's House");
        loc2.setDesc("You enter your Grandpa's house. He has been waiting for you.");

        Locale loc3 = new Locale(3);
        loc3.setName("Grandpa's Shed");
        loc3.setDesc("You have entered the shed. In the corner of the room, you see a dusty guitar case.");

        Locale loc4 = new Locale(4);
        loc4.setName("Town");
        loc4.setDesc("To the east is the Magic Shoppe, south for the music shop, and west for the venue.");

        Locale loc5 = new Locale(5);
        loc5.setName("Magick Shoppe");
        loc5.setDesc("The following items are for sale:");

        Locale loc6 = new Locale(6);
        loc6.setName("Music Store");
        loc6.setDesc("You have entered the music shop. The man behind the counter tells you there is a \n" +
               "drum room to the east, and a synthesizer room to the west, but not many are left.");

        Locale loc7 = new Locale(7);
        loc7.setName("Drum Room");
        loc7.setDesc("There is one drum set left. Take it while it is still there!");

        Locale loc8 = new Locale(8);
        loc8.setName("Synthesizer Room");
        loc8.setDesc("A final Roland Juno is hanging on the wall under a spotlight..what a beauty.");

        Venue loc9 = new Venue(9); // Locale(2);
        loc9.setName("Venue Entrance Hall");
        loc9.setDesc("To the north is a small closet that is cracked open. To the west, the main stage.");


        Venue loc10 = new Venue(10); // Locale(2);
        loc10.setName("Storage Closet");
        loc10.setDesc("There is a microphone placed on a mic stand, ready to go.");


        Venue loc11 = new Venue(11); // Locale(2);
        loc11.setName("Main Stage");
        loc11.setDesc("There is a large stage under blue lights, with a bar on the side wall.");


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


        Items item5 = new Items(5);
        item5.setName("Super Sharp Strings");
        item5.setDesc("Allow for major string bend-age during ear-splitting solos.");


        Items item6 = new Items(6);
        item6.setName("Double Kick Pedal");
        item6.setDesc("Get those Heavy Metal Bass hits on your drum kit, with this double pedal.");


        Items item7 = new Items(7);
        item7.setName("Guitar Pedal");
        item7.setDesc("This pedal allows you to create your own effects to wow your audience.");


        // Set up the Items array
        item = new Items[8];
        item[0] = item0;
        item[1] = item1;
        item[2] = item2;
        item[3] = item3;
        item[4] = item4;
        item[5] = item5;
        item[6] = item6;
        item[7] = item7;



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
        System.out.println(locations[currentLocale].getName());
        System.out.println(locations[currentLocale].getDesc());
        System.out.println("hasVisited = " + locations[currentLocale].getHasVisited());
    }

    private static void getCommand() {
        System.out.print("[" + moves + " moves, score " + score + "], "); //achievement " + achievement); check line 15
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
        } /*else if ( command.equalsIgnoreCase("take")  || command.equalsIgnoreCase("t")) {
            take();
        } */

        if (dir > -1) {   // This means a dir was set.
            int newLocation = nav[currentLocale][dir];
            if (newLocation == INVALID) {
                System.out.println("You cannot go that way.");
            } else {
                currentLocale = newLocation;
                moves = moves + 1;
                // below is attempt to add 5 when you haven't been to this location, but
                // I get a 'cannot resolve symbol hasVisited' error message.
                if (hasVisited = false) {
                   score = score + 5;
                 } else {
                    // hasVisited cannot find symbol. if I say Locale.hasVisited = true; it says it is private in Locale
                    hasVisited = true;
                    }
                }
        // MORE ATTEMPT        if (hasVisited == false) {
        //                     score = score + 5;
        //                     hasVisited == true;
        //                     } else { }
                // TODO: Deal with hasVisited and the score here.
            } //else { }
        }


    private static void help() {
        System.out.println("The commands are as follows:");
        System.out.println("   n/north");
        System.out.println("   s/south");
        System.out.println("   q/quit");
        System.out.println("   t/take");
        System.out.println("   m/map");
    }

    // private static void take(item.name) {}  Take an item and hold it in inventory array.

    private static void quit() {
        stillPlaying = false;
    }
}
