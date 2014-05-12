import javax.xml.stream.Location;

/**
 * Created by conorkincart on 5/11/14.
 */
public class LocationList {

    public LocationList(Locale n) {
        thisLocale = n;
    }

    public LocationList getNorth() {
        return north;
    }

    public void setNorth(LocationList north) {
        this.north = north;
    }

    public LocationList getSouth() {
        return south;
    }

    public void setSouth(LocationList south) {
        this.south = south;
    }

    public LocationList getEast() {
        return east;
    }

    public void setEast(LocationList east) {
        this.east = east;
    }

    public LocationList getWest() {
        return west;
    }

    public void setWest(LocationList west) {
        this.west = west;
    }

    public Locale getThisLocale () {
        return thisLocale;
    }

    public void setThisLocale (Locale variable) {
        this.thisLocale = variable;
    }


    private Locale thisLocale;
    private LocationList north = null;
    private LocationList south = null;
    private LocationList east = null;
    private LocationList west = null;






}

