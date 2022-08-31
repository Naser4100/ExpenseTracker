
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.util.Vector;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author naser
 */
public class MainFrame extends javax.swing.JFrame {
    
     Connection con;
     PreparedStatement pst;
    
      public void ConnectDB() {
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
            // Use for docker container
//            String url1 = "jdbc:mysql://localhost:3306/expense";
//            String user = "root";
//            String password = "root";

            // Using remote database
            String url1 = "jdbc:mysql://sql.freedb.tech:3306/freedb_expense_data";
            String user = "freedb_naser";
            String password = "S7EDpnybFhWRVn$";

            con= DriverManager.getConnection(url1, user, password);
            
            if (con != null) {
                dbStatusText.setText("Database Connected!");
                System.out.println("Database Connected!");
            }
   } catch (SQLException ex) {
           dbStatusText.setText("Not Connnect");
            System.out.println("Failed to connect database");
            ex.printStackTrace();
   } catch (ClassNotFoundException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   }
       
   }
      
    public void getExpenseData(){
      int coloumlength ;
      try {
            String sql = "SELECT * FROM expense_data ORDER BY date DESC";
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(sql);
            ResultSetMetaData  RSMD = result.getMetaData();
            coloumlength = RSMD.getColumnCount();
            
            DefaultTableModel model = (DefaultTableModel) expenseTable.getModel();
            model.setRowCount(0);
            
            while (result.next()){
                Vector v2 = new Vector();
                for(int i =1; i<= coloumlength; i++){
                     v2.add(result.getString("id"));
                     v2.add(result.getString("purpose"));
                     v2.add(result.getString("type"));
                     v2.add(result.getString("amount"));
                     v2.add(result.getString("date"));       
                }
                model.addRow(v2);
            }
       System.out.println("Data fetch");
       calculateTotalExpenses();
    } catch (Exception e) {
        System.out.println(e);
        System.err.print("Something went wrong!");
    }
    }
    
    public void refreshUI() {
        getExpenseData();
        purposeTXT.setText("");
        amountTXT.setText("");
        jButton1.setEnabled(true);
        jButton2.setEnabled(false);
        editBtn.setVisible(false);
    }

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        ConnectDB();
        getExpenseData();
        editBtn.setVisible(false);
    }
    
    public void filterDataByDate(String startDate, String endDate) {
        int coloumlength ;
        try {
//            String sql = "SELECT * FROM expense_data WHERE date = " + startDate;
            String sql = "SELECT * FROM expense_data WHERE date BETWEEN " + startDate + " AND " + endDate + "ORDER BY date DESC";
            System.out.println(sql);
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(sql);
            ResultSetMetaData  RSMD = result.getMetaData();
            coloumlength = RSMD.getColumnCount();

            DefaultTableModel model = (DefaultTableModel) expenseTable.getModel();
            model.setRowCount(0);

            while (result.next()){
                Vector v2 = new Vector();
                for(int i =1; i<= coloumlength; i++){
                     v2.add(result.getString("id"));
                     v2.add(result.getString("purpose"));
                     v2.add(result.getString("type"));
                     v2.add(result.getString("amount"));
                     v2.add(result.getString("date"));

                }
                model.addRow(v2);
            }
        System.out.println("Data fetch");
        calculateTotalExpenses();
        } catch (Exception e) {
            System.out.println(e);
            System.err.print("Something went wrong!");
        }
    }
//    public void AddNewRecord (){
//        
//        
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        purposeTXT = new javax.swing.JTextField();
        typeCombo = new javax.swing.JComboBox<>();
        dbStatusText = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        amountTXT = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        expenseTable = new javax.swing.JTable();
        datePicker = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        totalExpensesLbl = new javax.swing.JLabel();
        totalExpensesLbl1 = new javax.swing.JLabel();
        remaining = new javax.swing.JLabel();
        startDate = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        filterBtn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        endDate = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        editBtn = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Expense Tracker");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));
        jPanel1.setForeground(new java.awt.Color(204, 255, 255));

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Type:");

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Purpose:");

        typeCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Expense", "Income" }));

        dbStatusText.setFont(new java.awt.Font("Liberation Sans", 3, 13)); // NOI18N
        dbStatusText.setForeground(new java.awt.Color(0, 153, 102));
        dbStatusText.setText("Database not connected!");
        dbStatusText.setToolTipText("");

        jButton1.setText("Add new record");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Amount:");

        expenseTable.setBackground(new java.awt.Color(255, 255, 255));
        expenseTable.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        expenseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Purpose", "Type", "Amount", "Date"
            }
        ));
        expenseTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                expenseTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(expenseTable);

        datePicker.setDateFormatString("YYYY-MM-dd");

        jLabel4.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Date:");

        jButton2.setText("Delete record");
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        totalExpensesLbl.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        totalExpensesLbl.setForeground(new java.awt.Color(255, 51, 0));
        totalExpensesLbl.setText("Total Expenses");

        totalExpensesLbl1.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        totalExpensesLbl1.setForeground(new java.awt.Color(0, 153, 51));
        totalExpensesLbl1.setText("Total Expenses");

        remaining.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        remaining.setForeground(new java.awt.Color(255, 153, 0));
        remaining.setText("Total Expenses");

        startDate.setDateFormatString("YYYY-MM-dd");

        jLabel5.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel5.setText("Filter data between date range");

        filterBtn.setText("Filter");
        filterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterBtnActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        jLabel6.setText("Start Date");

        endDate.setDateFormatString("YYYY-MM-dd");

        jLabel7.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        jLabel7.setText("End Date");

        jButton3.setText("Refresh");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Liberation Sans", 3, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 153));
        jLabel8.setText("Expense Tracker");

        editBtn.setText("Update record");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        jLabel9.setForeground(new java.awt.Color(0, 102, 102));
        jLabel9.setText("Track your daily expenses");

        jLabel10.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 0));
        jLabel10.setText("Developer: Abdullah Naser; Id: 212015027");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dbStatusText)
                        .addGap(25, 25, 25))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(typeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(purposeTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jButton1)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jButton2))
                                                .addComponent(datePicker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(amountTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(45, 45, 45)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(totalExpensesLbl1)
                                    .addComponent(totalExpensesLbl)
                                    .addComponent(remaining)
                                    .addComponent(jLabel5)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(startDate, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel7)
                                                    .addComponent(jButton3))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(filterBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(endDate, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))))))
                                .addGap(0, 54, Short.MAX_VALUE))
                            .addComponent(jScrollPane1))
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(231, 231, 231)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(6, 6, 6)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(totalExpensesLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalExpensesLbl1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(remaining)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(startDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(endDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(editBtn)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(filterBtn)
                                .addComponent(jButton3)))
                        .addGap(12, 12, 12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(purposeTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(typeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(amountTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(datePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addGap(47, 47, 47)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dbStatusText)
                    .addComponent(jLabel10))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String purpose = purposeTXT.getText();
        String amount = amountTXT.getText();
        String type = typeCombo.getSelectedItem().toString();
        String date = ((JTextField)datePicker.getDateEditor().getUiComponent()).getText();
        
        if(purpose.isEmpty() || amount.isEmpty() || type.isEmpty() || date.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please fill all the fields", "Validation failed", JOptionPane.ERROR_MESSAGE);
        }else {
            float floatAmount;
            try {
                floatAmount = Float.parseFloat(amount);
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(this, "Amount should be number", "Validation failed", JOptionPane.ERROR_MESSAGE);
                 return;
            }
            
            try {
                pst = con.prepareStatement("INSERT INTO expense_data (purpose, type, amount, date)" + " VALUES (?, ?, ?, ?)");
                pst.setString (1, purpose);
                pst.setString (2, type);
                pst.setFloat(3, floatAmount);
                pst.setString(4, date);
                
                pst.executeUpdate();
                
                purposeTXT.setText("");
                amountTXT.setText("");
 
                JOptionPane.showMessageDialog(this, "Record Added!");
                getExpenseData();
            } catch (Exception e) {
                System.err.print("Failed To add records");
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Please Try Again","Error saving data", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void calculateTotalExpenses () {
        float totalExpenses = 0;
        float totalIncome = 0;
        
        for(int i = 0; i < expenseTable.getRowCount(); i++){
            if(expenseTable.getValueAt(i, 2).equals("Expense")){
          
                totalExpenses = totalExpenses + Float.parseFloat(expenseTable.getValueAt(i, 3).toString());
            }else{
                totalIncome = totalIncome + Float.parseFloat(expenseTable.getValueAt(i, 3).toString());
            }
        }

        System.out.println(totalExpenses);
//      totalExpensesLbl.setText("Total Expenses : " + String.format("%.2f", Float.toString(totalExpenses)));
        totalExpensesLbl.setText("Total Expenses : " + Float.toString(totalExpenses));

        totalExpensesLbl1.setText("Total Income : " + Float.toString(totalIncome));
        remaining.setText("Remaining balance : " + Float.toString(totalIncome - totalExpenses));
    }
    
    
    private void expenseTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_expenseTableMouseClicked
        jButton1.setEnabled(false);
        jButton2.setEnabled(true);
          editBtn.setVisible(true);
        DefaultTableModel model = (DefaultTableModel) expenseTable.getModel();
        int selectIndex = expenseTable.getSelectedRow();

        int id = Integer.parseInt(model.getValueAt(selectIndex,0).toString());
        purposeTXT.setText(model.getValueAt(selectIndex,1).toString());
        typeCombo.setSelectedItem(model.getValueAt(selectIndex,2).toString());
        amountTXT.setText(model.getValueAt(selectIndex,3).toString());
//        datePicker.getDateEditor().setDate((Date) model.getValueAt(selectIndex,4));

    }//GEN-LAST:event_expenseTableMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        DefaultTableModel model = (DefaultTableModel) expenseTable.getModel();
        int selectIndex = expenseTable.getSelectedRow();

        int id = Integer.parseInt(model.getValueAt(selectIndex,0).toString());

        try {
            pst = con.prepareStatement("DELETE from expense_data WHERE id = ?");
            pst.setInt(1, id);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Record deleted!");
//            getExpenseData();
            refreshUI();

        } catch (Exception e) {
            System.err.print("Failed To Delete record");
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Something went wrong!","Error on deleting record",JOptionPane.ERROR_MESSAGE);
        }  
    }//GEN-LAST:event_jButton2ActionPerformed

    private void filterBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterBtnActionPerformed
        // TODO add your handling code here:
        String sDate = ((JTextField)startDate.getDateEditor().getUiComponent()).getText();
        String eDate = ((JTextField)endDate.getDateEditor().getUiComponent()).getText();
        
        if(sDate.isEmpty() || eDate.isBlank()){
            JOptionPane.showMessageDialog(this, "Please select date range", "Validation failed", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        filterDataByDate("'" + sDate + "'", "'" + eDate + "'");
    }//GEN-LAST:event_filterBtnActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
       refreshUI();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        String purpose = purposeTXT.getText();
        String amount = amountTXT.getText();
        String type = typeCombo.getSelectedItem().toString();
        String date = ((JTextField)datePicker.getDateEditor().getUiComponent()).getText();
        
        DefaultTableModel model = (DefaultTableModel) expenseTable.getModel();
        int selectIndex = expenseTable.getSelectedRow();

        int id = Integer.parseInt(model.getValueAt(selectIndex,0).toString());
        
        if(purpose.isEmpty() || amount.isEmpty() || type.isEmpty() || date.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please select date", "Validation failed", JOptionPane.ERROR_MESSAGE);
        }else {
            
            float floatAmount;
            try {
                floatAmount = Float.parseFloat(amount);
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(this, "Amount should be number", "Validation failed", JOptionPane.ERROR_MESSAGE);
                 return;
            }
            
            try {
                pst = con.prepareStatement("UPDATE expense_data SET purpose = ?, type = ?, amount = ?, date = ? WHERE id = ?");
                pst.setString (1, purpose);
                pst.setString (2, type);
                pst.setFloat(3, floatAmount);
                pst.setString(4, date);
                pst.setInt(5, id);
                
                pst.executeUpdate();
                
                purposeTXT.setText("");
                amountTXT.setText("");
 
                JOptionPane.showMessageDialog(this, "Record Updated!");
                refreshUI();
            } catch (Exception e) {
                System.err.print("Failed to update records");
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Something went wrong!","Error updating  data", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_editBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField amountTXT;
    private com.toedter.calendar.JDateChooser datePicker;
    private javax.swing.JLabel dbStatusText;
    private javax.swing.JButton editBtn;
    private com.toedter.calendar.JDateChooser endDate;
    private javax.swing.JTable expenseTable;
    private javax.swing.JButton filterBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField purposeTXT;
    private javax.swing.JLabel remaining;
    private com.toedter.calendar.JDateChooser startDate;
    private javax.swing.JLabel totalExpensesLbl;
    private javax.swing.JLabel totalExpensesLbl1;
    private javax.swing.JComboBox<String> typeCombo;
    // End of variables declaration//GEN-END:variables
}
