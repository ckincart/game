import sun.util.logging.resources.logging_zh_CN;

public class Locale {

    //
    // -- PUBLIC --
    //

    // Constructor
    public Locale(int id) {
        this.id = id;
    }

    // Getters and Setters
    public int getId() {
        return this.id;
    }

    public String getText() { return this.name + "\n" + this.desc + "\n" + this.posmoves + "\n" + this.MagicItems;}
    public void setText(String value) {}

    public String getName() {
        return this.name;
    }
    public void setName(String value) {
        this.name = value;
    }

    public String getDesc() {
        return this.desc;
    }
    public void setDesc(String value) {
        this.desc = value;
    }

    public String getPossibleMoves() {return this.posmoves;}
    public void setPossibleMoves(String value) {this.posmoves = value;}

    public boolean getHasVisited() {
        return hasVisited;
    }
    public void setHasVisited(boolean hasVisited) {
        this.hasVisited = hasVisited;
    }

    public boolean getHasTaken() { return hasTaken;}
    public void setHasTaken(boolean hasTaken) {this.hasTaken = hasTaken;}

    public Items getItem() {return this.item;}
    public void setItem(Items value) {this.item = value; }

    public String getMagicItems() {return this.MagicItems; }
    public void setMagicItems(String value) { this.MagicItems = value; }

    public Locale getHead() {
        return head;
    }
    public void setHead(Locale head) {
        this.head = head;
    }


    // Other methods
    public String toString() {
        return "[Locale object: id=" + this.id + " name="+ this.name + " desc=" + this.desc + "]";
    }


    //
    // -- PRIVATE --
    //
    private int     id;
    private String  name;
    private String  desc;
    private String  posmoves;
    private boolean hasVisited = false;
    private boolean hasTaken = false;
    private Items item;
    private String MagicItems;
    private Locale head;
}

   /* private static void nav() {

        loc0.gonorth = null;
        loc0.gosouth = loc1;
        loc0.goeast = null;
        loc0.gowest = null;

        loc1.gonorth = loc0;
        loc1.gosouth = loc4;
        loc1.goeast = loc2;
        loc1.gowest = null;

        loc2.gonorth = null;
        loc2.gosouth = null;
        loc2.goeast = loc3;
        loc2.gowest = loc1;

        loc3.gonorth = null;
        loc3.gosouth = null;
        loc3.goeast = null;
        loc3.gowest = loc2;

        loc4.gonorth = loc1;
        loc4.gosouth = loc6;
        loc4.goeast = loc5;
        loc4.gowest = loc9;

        loc5.gonorth = null;
        loc5.gosouth = null;
        loc5.goeast = null;
        loc5.gowest = loc4;

        loc6.gonorth = loc4;
        loc6.gosouth = null;
        loc6.goeast = loc7;
        loc6.gowest = loc8;

        loc7.gonorth = null;
        loc7.gosouth = null;
        loc7.goeast = null;
        loc7.gowest = loc6;

        loc8.gonorth = null;
        loc8.gosouth = null;
        loc8.goeast = loc6;
        loc8.gowest = null;

        loc9.gonorth = loc10;
        loc9.gosouth = null;
        loc9.goeast = loc4;
        loc9.gowest = loc11;

        loc10.gonorth = null;
        loc10.gosouth = loc9;
        loc10.goeast = null;
        loc10.gowest = null;

        loc11.gonorth = null;
        loc11.gosouth = null;
        loc11.goeast = loc9;
        loc11.gowest = null;

    }
       */
