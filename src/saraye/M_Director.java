package saraye;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class M_Director extends User{

    public int create_user(String username, String password, int type){
        Database db = new Database();
        return db.create_user(username, password, type);
    }

    public int delete_user(String username){
        Database db = new Database();
        return db.delete_user(username);
    }

    public int modify_password(String username, String password){
        Database db = new Database();
        return db.modify_password(username, password);
    }

    public void get_all_items(){
        Database db = new Database();
        List<Item> items = new ArrayList<Item>();
        items = db.get_all_items();
        try{
            String filename = "D:\\inventory.xlsx";
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

    public Config get_tax_info(){

        Config configf = new Config();

        try{
            File config_file = new File("config-file.txt");
            Scanner myReader = new Scanner(config_file);

            String tax_card = myReader.nextLine();
            configf.tax_card = Double.parseDouble(tax_card);
            String tax_cash = myReader.nextLine();
            configf.tax_cash = Double.parseDouble(tax_cash);

            myReader.close();
        } catch (Exception e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return configf;
    }

    public boolean update_tax_info(String type, double val){

        Config config;

        if(type=="cash"){
            config = get_tax_info();
            config.tax_cash = val;
        } else if (type=="card"){
            config = get_tax_info();
            config.tax_card = val;
        } else {
            return false;
        }

        try{
            FileWriter myWriter = new FileWriter("config-file.txt");
            myWriter.write(Double.toString(config.tax_card));
            myWriter.write("\n");
            myWriter.write(Double.toString(config.tax_cash));
            myWriter.close();

        } catch (Exception e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return true;
    }
    public void get_all_invoices(){
        Database db = new Database();
        List<Order> orders = new ArrayList<Order>();
        orders = db.get_all_invoices();

        try{
            String filename = "D:\\invoices.xlsx";
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Invoices");

            HSSFRow rowhead = sheet.createRow((short)0);
            rowhead.createCell(0).setCellValue("Cashier ID");
            rowhead.createCell(1).setCellValue("Order ID");
            rowhead.createCell(2).setCellValue("Payment Amount");
            rowhead.createCell(3).setCellValue("Payment ID");
            rowhead.createCell(4).setCellValue("Payment Type");
            rowhead.createCell(5).setCellValue("Tax");

            for(int i=0; i<orders.size(); i++){
                Order o = orders.get(i);

                HSSFRow row = sheet.createRow((short)i+1);
                row.createCell(0).setCellValue(o.cashier_id);
                row.createCell(1).setCellValue(o.order_id);
                row.createCell(2).setCellValue(o.payment_amount);
                row.createCell(3).setCellValue(o.payment_id);
                row.createCell(4).setCellValue(o.payment_type);
                row.createCell(5).setCellValue(o.tax);
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
}
