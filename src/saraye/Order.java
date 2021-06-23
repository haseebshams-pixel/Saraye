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

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public void setCashier_id(int cashier_id) {
        this.cashier_id = cashier_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public void setPayment_amount(float payment_amount) {
        this.payment_amount = payment_amount;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public void setOrder_products(List<OrderProduct> order_products) {
        this.order_products = order_products;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
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
