package saraye;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;

public class M_Cashier extends User{
    
    public Order generate_new_invoice(int cashier_id){
        Order order = new Order();
        List<OrderProduct> orderproducts = new ArrayList<>();

        order.cashier_id = cashier_id;
        order.order_id = ThreadLocalRandom.current().nextInt();
        order.order_products = orderproducts;
        order.payment_amount = 0;
        order.payment_id = ThreadLocalRandom.current().nextInt();
        order.payment_type = "cash";
        order.total = 0;
        order.tax = 0;

        return order;
    }

    public Order add_product(String product_name, int quantity, Order order){
        Database db = new Database();
        Product p = db.get_product_info(product_name);
        if(p!=null){
            OrderProduct op = new OrderProduct();
            op.product_id = p.product_id;
            op.product_name = p.product_name;
            op.price = p.price;
            op.quantity = quantity;
            order.order_products.add(op);

            order.total += op.price * quantity;
            return order;
        }
        return order;
    }

    public Order remove_product(String product_name, Order order){
        for(int i=0; i<order.order_products.size(); i++){
            OrderProduct op = order.order_products.get(i);
            if(op.product_name==product_name){
                order.order_products.remove(op);
                break;
            }
        }
        return order;
    }

    public Order tax_of_invoice(Order order){
        M_Director d = new M_Director();
        Config config = d.get_tax_info();

        if(order.payment_type=="cash"){
            double tax = config.tax_cash/100;
            double t1 = tax * order.total;
            order.tax = (int) t1;

        } else if (order.payment_type=="card"){
            double tax = config.tax_card/100;
            double t1 = tax * order.total;
            order.tax = (int) t1;
        }
        return order;
    }

    public Order total_of_invoice(Order order){
        order.payment_amount = order.total + order.tax;
        return order;
    }

    public Order change_pay_type(Order order, String payment_type){
        order.payment_type = payment_type;
        return order;
    }


    public int add_invoice(Order order){
        Database db = new Database();
        int status = db.add_invoice(order.order_id, order.cashier_id, order.payment_id, order.payment_amount, order.payment_type, order.tax);
        if (status!=2){
            return status;
        }

        for(int i=0; i<order.order_products.size(); i++){
            OrderProduct op = order.order_products.get(i);
            status = db.add_order_product(op.product_id, order.order_id, op.quantity);
            if(status!=1){
                return status;
            }
        }

        return status;
    }

}
