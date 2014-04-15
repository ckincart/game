/**
 * Created by conorkincart on 3/14/14.
 */
public class Items {

        //
        // -- PUBLIC --
        //

        // Constructor
        public Items(int id) {
            this.id = id;
        }

        // Getters and Setters
        public int getId() {
            return this.id;
        }

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

        public boolean getHasTaken() {
            return hasTaken;
        }
        public void setHasTaken(boolean hasTaken) {
            this.hasTaken = hasTaken;
        }


        // Other methods
        public String toString() {
            return "[Item object: id=" + this.id + " name="+ this.name + " desc=" + this.desc + "]";
        }


        //
        // -- PRIVATE --
        //
        private int     id;
        private String  name;
        private String  desc;
        private boolean hasTaken = false;


}
