package saraye;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    String conn_string = "jdbc:sqlserver://localhost:1433;databaseName=se;integratedSecurity=true";
    String user = "";
    String pass = "";
    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    Connection get_conn(){
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(conn_string, user, pass);
            return conn;
        } catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    boolean login(String username, String password, int type){

        Connection conn = get_conn();
        int output = -1;

        if(conn!=null){
            try {
                CallableStatement call = conn.prepareCall("{call USER_LOGIN(?,?,?,?)}");
                call.setString(1, username);
                call.setString(2, password);
                call.setInt(3, type);
                call.registerOutParameter(4, Types.INTEGER);
                call.executeQuery();

                output = call.getInt(4);

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        try {
            conn.close();
        } catch(Exception e) {
            System.out.println(e);
        }

        if(output==1){
            return true;
        } else {
            return false;
        }
    }

    int create_user(String username, String password, int type){
        Connection conn = get_conn();
        int output = -1;

        if(conn!=null){
            try {
                CallableStatement call = conn.prepareCall("{call SignUp(?,?,?,?)}");
                call.setString(1, username);
                call.setString(2, password);
                call.setInt(3, type);
                call.registerOutParameter(4, Types.INTEGER);
                call.executeQuery();

                output = call.getInt(4);

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        try {
            conn.close();
        } catch(Exception e) {
            System.out.println(e);
        }
        return output;
    }
    
    int add_product(int product_id, String product_name, int price){
        Connection conn = get_conn();
        int output = -1;

        if(conn!=null){
            try {
                CallableStatement call = conn.prepareCall("{call Add_product_details(?,?,?,?)}");
                call.setInt(1, product_id);
                call.setString(2, product_name);
                call.setInt(3, price);
                call.registerOutParameter(4, Types.INTEGER);
                call.executeQuery();

                output = call.getInt(4);

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        try {
            conn.close();
        } catch(Exception e) {
            System.out.println(e);
        }
        return output;
    }

    int get_item_id(String item_name){
        Connection conn = get_conn();
        int output = -1;

        if(conn!=null){
            try {
                Statement stmt = conn.createStatement();
                String sql = "select ingredient_id from item where ingredient_name = '" + item_name + "'";
                ResultSet rs = stmt.executeQuery(sql);
                if(rs.next()) {
                    output = rs.getInt("ingredient_id");
                }
                stmt.close();

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        try {
            conn.close();
        } catch(Exception e) {
            System.out.println(e);
        }

        return output;
    }

    int add_item_to_product(int item_id, int product_id, int quantity){
        Connection conn = get_conn();
        int output = -1;

        if(conn!=null){
            try {
                CallableStatement call = conn.prepareCall("{call ADD_items_in_product(?,?,?,?)}");
                call.setInt(1, product_id);
                call.setInt(2, item_id);
                call.setInt(3, quantity);
                call.registerOutParameter(4, Types.INTEGER);
                call.executeQuery();

                output = call.getInt(4);

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        try {
            conn.close();
        } catch(Exception e) {
            System.out.println(e);
        }

        return output;
    }

    int delete_user(String username){
        Connection conn = get_conn();
        int output = -1;

        if(conn!=null){
            try {
                CallableStatement call = conn.prepareCall("{call Delete_User(?,?)}");
                call.setString(1, username);
                call.registerOutParameter(2, Types.INTEGER);
                call.executeQuery();

                output = call.getInt(2);

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        try {
            conn.close();
        } catch(Exception e) {
            System.out.println(e);
        }

        return output;
    }

    int modify_password(String username, String password){
        Connection conn = get_conn();
        int output = -1;

        if(conn!=null){
            try {
                CallableStatement call = conn.prepareCall("{call modify_password(?,?,?)}");
                call.setString(1, username);
                call.setString(2, password);
                call.registerOutParameter(3, Types.INTEGER);
                call.executeQuery();

                output = call.getInt(3);

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        try {
            conn.close();
        } catch(Exception e) {
            System.out.println(e);
        }

        return output;
    }

    List<Item> get_all_items(){
        Connection conn = get_conn();
        List<Item> items = new ArrayList<Item>();

        if(conn!=null){
            try {
                Statement stmt = conn.createStatement();
                String sql = "select * from item";
                ResultSet rs = stmt.executeQuery(sql);

                while(rs.next()) {
                    Item i = new Item();
                    i.item_id = rs.getInt("ingredient_id");
                    i.item_name = rs.getString("ingredient_name");
                    i.quantity = rs.getInt("quantity");
                    items.add(i);
                }
                stmt.close();

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        try {
            conn.close();
        } catch(Exception e) {
            System.out.println(e);
        }
        return items;
    }

    int add_new_item(int item_id, String item_name, int quantity){
        Connection conn = get_conn();
        int output = -1;

        if(conn!=null){
            try {
                CallableStatement call = conn.prepareCall("{call Add_inventory(?,?,?,?)}");
                call.setInt(1, item_id);
                call.setString(2, item_name);
                call.setInt(3, quantity);
                call.registerOutParameter(4, Types.INTEGER);
                call.executeQuery();

                output = call.getInt(4);

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        try {
            conn.close();
        } catch(Exception e) {
            System.out.println(e);
        }

        return output;
    }

    int update_item_quantity(String item_name, int quantity){
        Connection conn = get_conn();
        int output = -1;

        if(conn!=null){
            try {
                CallableStatement call = conn.prepareCall("{call Update_inventory(?,?,?)}");
                call.setString(1, item_name);
                call.setInt(2, quantity);
                call.registerOutParameter(3, Types.INTEGER);
                call.executeQuery();

                output = call.getInt(3);

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        try {
            conn.close();
        } catch(Exception e) {
            System.out.println(e);
        }

        return output;
    }

    int remove_item(String item_name){
        Connection conn = get_conn();
        int output = -1;

        if(conn!=null){
            try {
                CallableStatement call = conn.prepareCall("{call remove_inventory(?,?)}");
                call.setString(1, item_name);
                call.registerOutParameter(2, Types.INTEGER);
                call.executeQuery();

                output = call.getInt(2);

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        try {
            conn.close();
        } catch(Exception e) {
            System.out.println(e);
        }

        return output;
    }
    public int add_invoice(int order_id, int cashier_id, int payment_id, float payment_amount, String payment_type, int tax){
        Connection conn = get_conn();
        int output = -1;

        if(conn!=null){
            try {
                CallableStatement call = conn.prepareCall("{call insert_into_order(?,?,?,?,?,?,?)}");
                call.setInt(1, order_id);
                call.setInt(2, cashier_id);
                call.setInt(3, payment_id);
                call.setFloat(4, payment_amount);
                call.setString(5, payment_type);
                call.setInt(6, tax);
                call.registerOutParameter(7, Types.INTEGER);
                call.executeQuery();

                output = call.getInt(7);

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        try {
            conn.close();
        } catch(Exception e) {
            System.out.println(e);
        }

        return output;
    }

    public Product get_product_info(String product_name){
        Connection conn = get_conn();
        Product product = null;

        if(conn!=null){
            try {
                Statement stmt = conn.createStatement();
                String sql = "if exists(select * from product where product.poduct_name='" + product_name + "') select product.product_id,product.price from product where product.poduct_name = '" + product_name + "'";
                ResultSet rs = stmt.executeQuery(sql);

                if(rs.next()) {
                    product = new Product();
                    product.product_name = product_name;
                    product.product_id = rs.getInt("product_id");
                    product.price = rs.getInt("price");
                }
                stmt.close();

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        try {
            conn.close();
        } catch(Exception e) {
            System.out.println(e);
        }
        return product;
    }

    public int add_order_product(int product_id, int order_id, int quantity){
        Connection conn = get_conn();
        int output = -1;

        if(conn!=null){
            try {
                CallableStatement call = conn.prepareCall("{call Add_Product_in_order_product(?,?,?,?)}");
                call.setInt(1, product_id);
                call.setInt(2, order_id);
                call.setInt(3, quantity);
                call.registerOutParameter(4, Types.INTEGER);
                call.executeQuery();

                output = call.getInt(4);

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        try {
            conn.close();
        } catch(Exception e) {
            System.out.println(e);
        }

        return output;
    }

    public List<Order> get_all_invoices(){
        Connection conn = get_conn();
        List<Order> orders = new ArrayList<Order>();

        if(conn!=null){
            try {
                Statement stmt = conn.createStatement();
                String sql = "select orders.cashier_id,orders.order_date,orders.order_id,payment.payment_amount,payment.patment_id,payment.payment_type,payment.tax from orders join payment on orders.order_id=payment.order_id";
                ResultSet rs = stmt.executeQuery(sql);

                while(rs.next()) {
                    Order o = new Order();

                    o.cashier_id = rs.getInt("cashier_id");
                    o.order_id = rs.getInt("order_id");
                    o.payment_amount = rs.getFloat("payment_amount");
                    o.payment_id = rs.getInt("patment_id");
                    o.payment_type = rs.getString("payment_type");
                    o.tax = rs.getInt("tax");

                    orders.add(o);
                }
                stmt.close();

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        try {
            conn.close();
        } catch(Exception e) {
            System.out.println(e);
        }
        return orders;
    }

}
