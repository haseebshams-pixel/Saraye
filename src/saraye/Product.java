package saraye;

public class Product {
    int product_id;
    String product_name;
    int price;

    public int add_item_to_product(productItem p){
        Database db = new Database();
        return db.add_item_to_product(p.item_id, p.product_id, p.quantity);
    }
}
