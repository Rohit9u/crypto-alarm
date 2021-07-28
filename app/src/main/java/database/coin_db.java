package database;

public class coin_db {
    String Name;
    String Id;
    String targetPrice;

    public coin_db(String name, String id, String targetPrice) {
        Name = name;
        Id = id;
        this.targetPrice = targetPrice;
    }
    public coin_db(){

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(String targetPrice) {
        this.targetPrice = targetPrice;
    }
}
