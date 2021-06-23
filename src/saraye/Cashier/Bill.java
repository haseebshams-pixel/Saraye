/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saraye.Cashier;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import javax.swing.*;
import saraye.M_Cashier;
import saraye.Order;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import saraye.OrderProduct;
import saraye.Config;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;  
import saraye.M_Director;
/**
 *
 * @author Haseeb
 */
public class Bill extends javax.swing.JFrame {

    /**
     * Creates new form Bill
     */
    M_Cashier c;
    String n;
    Order order = new Order();
    public Bill(M_Cashier cashr,String name,Order orderrr) {
        initComponents();
        c=cashr;
        n=name;
        order = orderrr;
        if(order==null){
            order = c.generate_new_invoice(c.get_usertype());
        } else {
            order=orderrr;
        }
        if(order!=null){
            M_Director d = new M_Director();
            Config config = d.get_tax_info();
            List<OrderProduct> orders = order.getOrder_products();
            for(int i=0;i<orders.size();i++){
                DefaultTableModel tbModel = (DefaultTableModel) billtable.getModel();
                tbModel.insertRow(tbModel.getRowCount(), new Object[]{i+1,orders.get(i).getProduct_name(),orders.get(i).getQuantity(),orders.get(i).getPrice(),orders.get(i).getQuantity()*orders.get(i).getPrice()});
            }
            total.setText(Integer.toString(order.getTotal()));
            int res = 0;
            if(order.getPayment_type().equals("cash")){
                double taxx = config.getTax_cash()/100;
                taxrate.setText(Double.toString(taxx));
                res = (int)(order.getTotal() *taxx); 
            } else if (order.getPayment_type().equals("card")){
                double taxx = config.getTax_card()/100;
                taxrate.setText(Double.toString(taxx));
                res = (int)(order.getTotal() *taxx);
            }
            order.setTax(res);
            tax.setText(Integer.toString(order.getTax()));
            order.setPayment_amount(order.getTax()+order.getTotal());
            netamount.setText(Double.toString(order.getPayment_amount()));
        }
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	Date currentdate = new Date();
        String formatDate=formatter.format(currentdate);
        date.setText(formatDate);
        billNumber.setText(Integer.toString(order.getOrder_id()));
        generatedby.setText(n);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new RoundedPanel(70,new Color(255,255,255));
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        billNumber = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        generatedby = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        billtable = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        taxrate = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        tax = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        netamount = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        cashradio = new javax.swing.JRadioButton();
        creditcardradio = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(860, 530));
        setMinimumSize(new java.awt.Dimension(860, 530));

        jPanel1.setBackground(new java.awt.Color(41, 61, 28));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Saraye");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/left-arrow (1).png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel4)
                .addGap(277, 277, 277)
                .addComponent(jLabel1)
                .addContainerGap(356, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(860, 530));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(41, 61, 28));
        jPanel3.setMaximumSize(new java.awt.Dimension(380, 109));
        jPanel3.setMinimumSize(new java.awt.Dimension(380, 109));
        jPanel3.setPreferredSize(new java.awt.Dimension(787, 408));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(41, 61, 28));
        jLabel2.setText("Bill No:");

        jButton2.setBackground(new java.awt.Color(41, 61, 28));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Print Bill");
        jButton2.setBorderPainted(false);
        jButton2.setFocusPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        billNumber.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        billNumber.setForeground(new java.awt.Color(41, 61, 28));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(41, 61, 28));
        jLabel5.setText("Generated By:");

        generatedby.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        generatedby.setForeground(new java.awt.Color(41, 61, 28));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(41, 61, 28));
        jLabel7.setText("Date:");

        date.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        date.setForeground(new java.awt.Color(41, 61, 28));

        billtable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        billtable.setForeground(new java.awt.Color(61, 41, 28));
        billtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sr#", "Product", "Quantity", "Price", "Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        billtable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(billtable);
        if (billtable.getColumnModel().getColumnCount() > 0) {
            billtable.getColumnModel().getColumn(0).setResizable(false);
            billtable.getColumnModel().getColumn(0).setPreferredWidth(2);
            billtable.getColumnModel().getColumn(0).setHeaderValue("Sr#");
            billtable.getColumnModel().getColumn(1).setResizable(false);
            billtable.getColumnModel().getColumn(1).setPreferredWidth(80);
            billtable.getColumnModel().getColumn(1).setHeaderValue("Product");
            billtable.getColumnModel().getColumn(2).setResizable(false);
            billtable.getColumnModel().getColumn(2).setPreferredWidth(10);
            billtable.getColumnModel().getColumn(2).setHeaderValue("Quantity");
            billtable.getColumnModel().getColumn(3).setResizable(false);
            billtable.getColumnModel().getColumn(3).setPreferredWidth(20);
            billtable.getColumnModel().getColumn(3).setHeaderValue("Price");
            billtable.getColumnModel().getColumn(4).setResizable(false);
            billtable.getColumnModel().getColumn(4).setPreferredWidth(20);
            billtable.getColumnModel().getColumn(4).setHeaderValue("Amount");
        }

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(41, 61, 28));
        jLabel9.setText("Total:");

        total.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        total.setForeground(new java.awt.Color(41, 61, 28));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(41, 61, 28));
        jLabel13.setText("Tax Rate:");

        taxrate.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        taxrate.setForeground(new java.awt.Color(41, 61, 28));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(41, 61, 28));
        jLabel15.setText("Tax Applied:");

        tax.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tax.setForeground(new java.awt.Color(41, 61, 28));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(41, 61, 28));
        jLabel19.setText("Net Amount:");

        netamount.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        netamount.setForeground(new java.awt.Color(41, 61, 28));

        jButton3.setBackground(new java.awt.Color(41, 61, 28));
        jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Add Product");
        jButton3.setBorderPainted(false);
        jButton3.setFocusPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(41, 61, 28));
        jLabel11.setText("Payment Type:");

        buttonGroup1.add(cashradio);
        cashradio.setForeground(new java.awt.Color(41, 61, 28));
        cashradio.setSelected(true);
        cashradio.setText("CASH");
        cashradio.setContentAreaFilled(false);
        cashradio.setFocusPainted(false);
        cashradio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cashradioMouseClicked(evt);
            }
        });
        cashradio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cashradioActionPerformed(evt);
            }
        });

        buttonGroup1.add(creditcardradio);
        creditcardradio.setForeground(new java.awt.Color(41, 61, 28));
        creditcardradio.setText("CREDIT CARD");
        creditcardradio.setContentAreaFilled(false);
        creditcardradio.setFocusPainted(false);
        creditcardradio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                creditcardradioMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(billNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(generatedby, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(26, 26, 26)
                        .addComponent(cashradio)
                        .addGap(18, 18, 18)
                        .addComponent(creditcardradio)
                        .addGap(212, 242, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(netamount, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(taxrate, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addComponent(tax, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(14, 14, 14))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(147, 147, 147)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(billNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(generatedby, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(taxrate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tax, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cashradio)
                                .addComponent(creditcardradio)
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(netamount, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        getAccessibleContext().setAccessibleName("Bill");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        new Home(n).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int status = c.add_invoice(order);
        JOptionPane.showMessageDialog(null, "Bill Printed Successfully!","SUCCESS",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
        new AddProduct(c,n,order).setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cashradioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cashradioMouseClicked
        M_Director d = new M_Director();
        Config config = d.get_tax_info();
        order = c.change_pay_type(order,"cash");
        order = c.tax_of_invoice(order);
    	order = c.total_of_invoice(order);
        double taxx = config.getTax_cash()/100;
        taxrate.setText(Double.toString(taxx));
        int res = (int)(order.getTotal() *taxx);
        order.setTax(res);
        tax.setText(Integer.toString(order.getTax()));
        order.setPayment_amount(order.getTax()+order.getTotal());
        netamount.setText(Double.toString(order.getPayment_amount()));
    }//GEN-LAST:event_cashradioMouseClicked

    private void creditcardradioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_creditcardradioMouseClicked
        M_Director d = new M_Director();
        Config config = d.get_tax_info();
        order = c.change_pay_type(order,"card");
        order = c.tax_of_invoice(order);
    	order = c.total_of_invoice(order);
        double taxx = config.getTax_card()/100;
        taxrate.setText(Double.toString(taxx));
        int res = (int)(order.getTotal() *taxx);
        order.setTax(res);
        tax.setText(Integer.toString(order.getTax()));
        order.setPayment_amount(order.getTax()+order.getTotal());
        netamount.setText(Double.toString(order.getPayment_amount()));
    }//GEN-LAST:event_creditcardradioMouseClicked

    private void cashradioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cashradioActionPerformed

    }//GEN-LAST:event_cashradioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[],M_Cashier cashr,String name,Order orderr) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Bill(cashr,name,orderr).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel billNumber;
    private javax.swing.JTable billtable;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton cashradio;
    private javax.swing.JRadioButton creditcardradio;
    private javax.swing.JLabel date;
    private javax.swing.JLabel generatedby;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel netamount;
    private javax.swing.JLabel tax;
    private javax.swing.JLabel taxrate;
    private javax.swing.JLabel total;
    // End of variables declaration//GEN-END:variables
    class RoundedPanel extends JPanel {

        private Color backgroundColor;
        private int cornerRadius = 15;

        public RoundedPanel(LayoutManager layout, int radius) {
            super(layout);
            cornerRadius = radius;
        }

        public RoundedPanel(LayoutManager layout, int radius, Color bgColor) {
            super(layout);
            cornerRadius = radius;
            backgroundColor = bgColor;
        }

        public RoundedPanel(int radius) {
            super();
            cornerRadius = radius;
        }

        public RoundedPanel(int radius, Color bgColor) {
            super();
            cornerRadius = radius;
            backgroundColor = bgColor;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Dimension arcs = new Dimension(cornerRadius, cornerRadius);
            int width = getWidth();
            int height = getHeight();
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            //Draws the rounded panel with borders.
            if (backgroundColor != null) {
                graphics.setColor(backgroundColor);
                System.out.println(backgroundColor);
            } else {
                graphics.setColor(getBackground());
            }
            graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); //paint background
            graphics.setColor(getForeground());
            graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint border
        }
    }

}
