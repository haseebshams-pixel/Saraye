package saraye;
import java.util.ArrayList;
import java.util.List;
import  java.io.*;
import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import  org.apache.poi.hssf.usermodel.HSSFRow;

public class M_Manager extends User{

    public int add_product(int product_id, String product_name, int product_price, String[] items_in_product, int[] items_in_product_quantity){

        Product p = new Product();
        p.product_id = product_id;
        p.product_name = product_name;
        p.price = product_price;

        Database db = new Database();
        int output = db.add_product(p.product_id, p.product_name, p.price);

        if(output==0){
            System.out.println("Product Name cannot be null.");
            return output;
        } else if(output==1){
            System.out.println("Product already added.");
            return output;
        } else if(output==2) {
            System.out.println("Product added Successfully.");
        }

        for(int i=0; i<items_in_product.length; i++) {
            productItem i1 = new productItem();
            Item current_item = new Item();
            i1.item_id = current_item.get_item_id(items_in_product[i]);
            i1.product_id = p.product_id;
            i1.quantity = items_in_product_quantity[i];
            output = p.add_item_to_product(i1);

            if(output==0){
                System.out.println("Product Does not exist.");
                return output;
            } else if(output==1){
                System.out.println("ProductItem already added.");
                return output;
            } else if(output==2) {
                System.out.println("ProductItem added Successfully.");
            }
        }
        return output;
    }

    public void get_all_items(){
        Database db = new Database();
        List<Item> items = new ArrayList<Item>();
        items = db.get_all_items();

        try{
            String filename = "C:/Users/Haseeb/Desktop/inventory.xlsx";
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Inventory");

            HSSFRow rowhead = sheet.createRow((short)0);
            rowhead.createCell(0).setCellValue("Item Id");
            rowhead.createCell(1).setCellValue("Item Name");
            rowhead.createCell(2).setCellValue("Quantity");

            for(int i=0; i<items.size(); i++){
                Item item = items.get(i);

                HSSFRow row = sheet.createRow((short)i+1);
                String id = String.valueOf(item.item_id);
                String quantity = String.valueOf(item.quantity);
                row.createCell(0).setCellValue(id);
                row.createCell(1).setCellValue(item.item_name);
                row.createCell(2).setCellValue(quantity);
            }

            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            System.out.println("Excel file has been generated successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int add_new_item(int item_id, String item_name, int quantity){
        Database db = new Database();
        return db.add_new_item(item_id, item_name, quantity);
    }

    public int update_item_quantity(String item_name, int quantity){
        Database db = new Database();
        return db.update_item_quantity(item_name, quantity);
    }

    public int remove_item(String item_name){
        Database db = new Database();
        return db.remove_item(item_name);
    }

}
