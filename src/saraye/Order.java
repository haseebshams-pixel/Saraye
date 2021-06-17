package saraye;
import java.util.ArrayList;
import java.util.List;

public class Order {
    int order_id;
    int cashier_id;
    int payment_id;
    float payment_amount;
    String payment_type;
    int tax;
    List<OrderProduct> order_products;
    int total;

    public int getOrder_id() {
        return order_id;
    }

    public int getCashier_id() {
        return cashier_id;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public float getPayment_amount() {
        return payment_amount;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public int getTax() {
        return tax;
    }

    public List<OrderProduct> getOrder_products() {
        return order_products;
    }

    public int getTotal() {
        return total;
    }
}
