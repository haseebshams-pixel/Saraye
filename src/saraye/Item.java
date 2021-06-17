package saraye;

public class Item {
    int item_id;
    String item_name;
    int quantity;

    public int get_item_id(String item_name){
        Database db = new Database();
        return db.get_item_id(item_name);
    }
}
