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
    public void setText(String value) {this.text = value;}

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

    //public Locale getHead() {return head; }
    //public void setHead(Locale head) {
       // this.head = head;
  //  }


    // Other methods
    public String toString() {
        return "[Locale object: id=" + this.id + " name="+ this.name + " desc=" + this.desc + "]";
    }


    //
    // -- PRIVATE --
    //
    private int     id;
   // private String text;
    private String  name;
    private String  desc;
    private String  posmoves;
    private boolean hasVisited = false;
    private boolean hasTaken = false;
    private Items item;
    private String MagicItems;
    private Locale head;
}




