/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bikeservicecenterproject;

import java.awt.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author パワーPower
 */
public class AddToRepair extends javax.swing.JInternalFrame {

    Connection con;
    Statement stmt;
    String user;
    String pw;
    DefaultTableModel tblmodel;
    String url = "jdbc:mysql://localhost:3306/bikeservicecenter";
    Dimension dialogSize=new Dimension(960, 742);
    ResultSet rs;
    int num;
    /**
     * /**
     * Creates new form Register
     */
    public void getServiceNumber() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "");
            stmt = con.createStatement();
            String sql = "Select * from service";
            rs = stmt.executeQuery(sql);
            if (rs.first() == false) {
                String sql1 = "ALTER TABLE service AUTO_INCREMENT=1";
                stmt.executeUpdate(sql1);
                serviceNumberLabel.setText("Service ID: 1");
            } else {
                if (rs.last()) {
                   int newServiceId=rs.getInt("serviceid")+1;
                   serviceNumberLabel.setText("Service ID: "+newServiceId);
                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddToRepairPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddToRepairPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void retriveTodayData() throws ClassNotFoundException, SQLException {
        JTableHeader header = jTable2.getTableHeader();
        header.setFont(new Font("Times New Roman", Font.BOLD, 24));
        DefaultTableCellRenderer d = ((DefaultTableCellRenderer) jTable2.getTableHeader().getDefaultRenderer());
        d.setHorizontalAlignment(JLabel.LEFT);
        tblmodel=(DefaultTableModel) jTable2.getModel();
        tblmodel.setRowCount(0);
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url, "root", "");
        if (con != null) {
            System.out.println("Database connected");
            stmt = con.createStatement();
            String sql = "Select s.serviceid,b.ownername,b.contact,s.numberplate,b.bikemodel,s.starttime,s.servicetype,m.mechanicsname,s.status from mechanics as m, bikeinfo as b,service as s where s.numberplate=b.numberplate and s.mechanicsassign=m.mechanicsid and s.servicedate=current_date()";
            rs = stmt.executeQuery(sql);
//            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            while (rs.next()) {
                String id = rs.getString("s.serviceid");
                String name = rs.getString("b.ownername");
                String model = rs.getString("b.bikemodel");
                String contact = rs.getString("b.contact");
                String numberplate = rs.getString("s.numberplate");
                String servicetime = rs.getString("s.starttime");
                String servicetype = rs.getString("s.servicetype");
                String mechanicsName = rs.getString("m.mechanicsname");
                String status = rs.getString("s.status");
                String data[] = {id, name, contact, numberplate, model, servicetime, servicetype, mechanicsName, status};
                tblmodel = (DefaultTableModel) jTable2.getModel();
                tblmodel.addRow(data);
            }
        } else {
            System.out.println("Database connection failed");
        }
    }
    public void retriveData() throws ClassNotFoundException, SQLException {
        JTableHeader header = jTable4.getTableHeader();
        header.setFont(new Font("Times New Roman", Font.BOLD, 24));
        DefaultTableCellRenderer d = ((DefaultTableCellRenderer) jTable4.getTableHeader().getDefaultRenderer());
        d.setHorizontalAlignment(JLabel.LEFT);
        tblmodel=(DefaultTableModel) jTable4.getModel();
        tblmodel.setRowCount(0);
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url, "root", "");
        if (con != null) {
            System.out.println("Database connected");
            stmt = con.createStatement();
            String sql = "Select s.serviceid,b.ownername,b.contact,s.numberplate,b.bikemodel,s.starttime,s.servicetype,m.mechanicsname,s.servicedate from mechanics as m, bikeinfo as b,service as s where s.numberplate=b.numberplate and s.mechanicsassign=m.mechanicsid";
            rs = stmt.executeQuery(sql);

//            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            while (rs.next()) {
                String id = rs.getString("s.serviceid");
                String name = rs.getString("b.ownername");
                String model = rs.getString("b.bikemodel");
                String contact = rs.getString("b.contact");
                String numberplate = rs.getString("s.numberplate");
                String servicetime = rs.getString("s.starttime");
                String servicetype = rs.getString("s.servicetype");
                String mechanicsName = rs.getString("m.mechanicsname");
                String status = rs.getString("s.servicedate");
                String data[] = {id, name, contact, numberplate, model, servicetime, servicetype, mechanicsName, status};
                tblmodel = (DefaultTableModel) jTable4.getModel();
                tblmodel.addRow(data);
            }
        } else {
            System.out.println("Database connection failed");
        }
    }
    public void retriveMechanicsData() {
        try {
            JTableHeader header = mechanicsTable.getTableHeader();
            header.setFont(new Font("Times New Roman", Font.BOLD, 21));
            DefaultTableCellRenderer d = ((DefaultTableCellRenderer) mechanicsTable.getTableHeader().getDefaultRenderer());
            d.setHorizontalAlignment(JLabel.LEFT);
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "");
            assignMechanicsCombo.removeAllItems();
            updateMechanicsAssignCombo.removeAllItems();
            tblmodel = (DefaultTableModel) mechanicsTable.getModel();
            tblmodel.setRowCount(0);
            String sql = "Select mechanicsid,mechanicsname,contact,experience from mechanics order by mechanicsname ";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Statement stmt1;
                stmt1=con.createStatement();
//                String id=rs.getString("s.serviceid");
                String name = rs.getString("mechanicsname");
                String sql1="Select count(*) as count from service where mechanicsassign='"+rs.getString("mechanicsid")+"' and servicedate=current_date() and status!='COMPLETED'";
                ResultSet a=stmt1.executeQuery(sql1);
                a.first();
//                String model = rs.getString("b.bikemodel");
                String contact = rs.getString("contact");
//                String experience = rs.getString("experience");
//                String sql1="Select count(s.serviceid) as count from service as s,mechanics as m where s.mechanicsassign='"+ rs.getString("mechanicsid")+ "' and s.servicedate=current_date()";
//                rs=stmt.executeQuery(sql1);
//                rs.next();
                assignMechanicsCombo.addItem(name);
                updateMechanicsAssignCombo.addItem(name);
                String work=a.getString("count");
//                String servicetime=rs.getString("s.starttime");
//                String servicetype=rs.getString("s.servicetype");
//                String mechanicsassign=rs.getString("s.mechanicsassign");
//                String status=rs.getString("s.status");
                String data[] = {name, contact,work};

                tblmodel = (DefaultTableModel) mechanicsTable.getModel();
                tblmodel.addRow(data);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddToRepairPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddToRepairPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void retriveCompleteData() throws ClassNotFoundException, SQLException {
        tblmodel=(DefaultTableModel) jTable3.getModel();
        tblmodel.setRowCount(0);
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url, "root", "");
        if (con != null) {
            JTableHeader header = jTable3.getTableHeader();
            header.setFont(new Font("Times New Roman", Font.BOLD, 24));
            DefaultTableCellRenderer d = ((DefaultTableCellRenderer) jTable3.getTableHeader().getDefaultRenderer());
            d.setHorizontalAlignment(JLabel.LEFT);
            System.out.println("Database connected");
            stmt = con.createStatement();
            String sql = "Select s.serviceid,b.ownername,b.contact,s.numberplate,b.bikemodel,s.completedtime,m.mechanicsname,s.status from mechanics as m,bikeinfo as b,service as s where s.numberplate=b.numberplate and s.status='Completed' and s.servicedate=current_date() and s.mechanicsassign=m.mechanicsid";
            ResultSet rs = stmt.executeQuery(sql);
//            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            while (rs.next()) {
                String id = rs.getString("s.serviceid");
                String name = rs.getString("b.ownername");
                String model = rs.getString("b.bikemodel");
                String contact = rs.getString("b.contact");
                String numberplate = rs.getString("s.numberplate");
                String completedtime = rs.getString("s.completedtime");
                String mechanicsassign = rs.getString("m.mechanicsname");
                String status = rs.getString("s.status");
                String data[] = {id, name, contact, numberplate, model, completedtime, mechanicsassign, status};
                tblmodel = (DefaultTableModel) jTable3.getModel();
                tblmodel.addRow(data);
            }
        } else {
            System.out.println("Database connection failed");
        }
    }
    public AddToRepair() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        try {
            System.out.println();
            jTable2.setShowGrid(true);
            jTable2.setGridColor(java.awt.Color.BLACK);
            jTable3.setShowGrid(true);
            jTable3.setGridColor(java.awt.Color.BLACK);
            mechanicsTable.setShowGrid(true);
            mechanicsTable.setGridColor(java.awt.Color.BLACK);
            plateList.setShowGrid(true);
            plateList.setGridColor(java.awt.Color.BLACK);
            jTable4.setShowGrid(true);
            jTable4.setGridColor(java.awt.Color.BLACK);
            jTable2.setRowHeight(jTable2.getRowHeight() + 20);
            jTable4.setRowHeight(jTable4.getRowHeight() + 20);
            jTable2.removeColumn(jTable2.getColumnModel().getColumn(0));
            jTable4.removeColumn(jTable4.getColumnModel().getColumn(0));
            jTable4.removeColumn(jTable4.getColumnModel().getColumn(4));
            jTable3.setRowHeight(jTable3.getRowHeight() + 20);
            mechanicsTable.setRowHeight(jTable2.getRowHeight() + 5);
            plateList.setRowHeight(jTable2.getRowHeight() + 5);
            jTable3.removeColumn(jTable3.getColumnModel().getColumn(0));
            jTable3.removeColumn(jTable3.getColumnModel().getColumn(6));
            JTableHeader header = plateList.getTableHeader();
            header.setFont(new Font("Times New Roman", Font.BOLD, 21));
            DefaultTableCellRenderer d = ((DefaultTableCellRenderer) plateList.getTableHeader().getDefaultRenderer());
            d.setHorizontalAlignment(JLabel.LEFT);
            retriveCompleteData();
            retriveData();
            retriveTodayData();
            retriveMechanicsData();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddToRepair.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddToRepair.class.getName()).log(Level.SEVERE, null, ex);
        }
        getServiceNumber();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        viewJDialog = new javax.swing.JDialog();
        jPanel10 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        numberPlateLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        ownerNameLabel = new javax.swing.JLabel();
        contactLabel = new javax.swing.JLabel();
        modelLabel = new javax.swing.JLabel();
        startTimeLabel = new javax.swing.JLabel();
        completionTimeLabel = new javax.swing.JLabel();
        serviceTypeLabel = new javax.swing.JLabel();
        mechanicsAssignLabel = new javax.swing.JLabel();
        serviceDateLabel = new javax.swing.JLabel();
        totalkmLabel = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        JLabl19 = new javax.swing.JLabel();
        status = new javax.swing.JLabel();
        updateJDialog = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        totalKMTTextField = new javax.swing.JTextField();
        updateServiceIDLabel = new javax.swing.JLabel();
        updateOwnernameLabel = new javax.swing.JLabel();
        updateModelLabel = new javax.swing.JLabel();
        updateContact = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel27 = new javax.swing.JLabel();
        updateMechanicsAssignCombo = new javax.swing.JComboBox<>();
        updateServiceTypeCombo = new javax.swing.JComboBox<>();
        update = new javax.swing.JButton();
        updateNumberPlateLabel = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        serviceDetailPanel = new javax.swing.JPanel();
        serviceTypeCombo = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        kmTextField = new javax.swing.JTextField();
        numberPlateTextField = new javax.swing.JTextField();
        serviceNumberLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        assignMechanicsCombo = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        fullDetailPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        detailProblemsTextArea = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        plateList = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        mechanicsTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        completeButton = new javax.swing.JButton();
        viewDetailButton = new javax.swing.JButton();
        searchBar = new javax.swing.JTextField();
        deleteButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        searchBar2 = new javax.swing.JTextField();

        viewJDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel10.setBackground(new java.awt.Color(204, 204, 204));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel11.setText("Number Plate");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel12.setText("Owner Name");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel13.setText("Contact");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel16.setText("Completion time");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel17.setText("Model");

        jLabel29.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel29.setText("Started time");

        jLabel32.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel32.setText("Email");

        jLabel33.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel33.setText("Service type");

        jLabel35.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel35.setText("Mechanics Assign");

        jLabel37.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel37.setText("Detail Problems");

        numberPlateLabel.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        numberPlateLabel.setText("Number Plate");

        emailLabel.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        emailLabel.setText("Email");

        ownerNameLabel.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        ownerNameLabel.setText("Owner Name");

        contactLabel.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        contactLabel.setText("Contact");

        modelLabel.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        modelLabel.setText("Model");

        startTimeLabel.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        startTimeLabel.setText("start time");

        completionTimeLabel.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        completionTimeLabel.setText("Completed time");

        serviceTypeLabel.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        serviceTypeLabel.setText("Service Type");

        mechanicsAssignLabel.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        mechanicsAssignLabel.setText("Mechanics Assign");

        serviceDateLabel.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        serviceDateLabel.setText("Serviced on:");

        totalkmLabel.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        totalkmLabel.setText("km");

        jLabel38.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel38.setText("Total KM");

        jScrollPane7.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTextArea2.setBackground(new java.awt.Color(153, 153, 153));
        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextArea2.setRows(5);
        jScrollPane7.setViewportView(jTextArea2);

        JLabl19.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        JLabl19.setText("Status");

        status.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(172, 172, 172)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(numberPlateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(contactLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ownerNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(modelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)))
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addComponent(completionTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JLabl19, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(startTimeLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(61, 61, 61)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mechanicsAssignLabel)
                    .addComponent(serviceTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalkmLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(73, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(serviceDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(283, 283, 283))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(numberPlateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(serviceTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ownerNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(contactLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(modelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(mechanicsAssignLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalkmLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLabl19, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(completionTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(66, 66, 66)
                .addComponent(serviceDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout viewJDialogLayout = new javax.swing.GroupLayout(viewJDialog.getContentPane());
        viewJDialog.getContentPane().setLayout(viewJDialogLayout);
        viewJDialogLayout.setHorizontalGroup(
            viewJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        viewJDialogLayout.setVerticalGroup(
            viewJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        updateJDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel14.setText("Service ID");

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel21.setText("Owner Name");

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel22.setText("Model");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel23.setText("Detail Problems");

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel24.setText("Total Km");

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel26.setText("Service Type");

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel28.setText("Contact");

        totalKMTTextField.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        updateServiceIDLabel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        updateServiceIDLabel.setText("jLabel3");

        updateOwnernameLabel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        updateOwnernameLabel.setText("jLabel3");

        updateModelLabel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        updateModelLabel.setText("jLabel3");

        updateContact.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        updateContact.setText("jLabel3");

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jScrollPane4.setViewportView(jTextArea1);

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel27.setText("Mechanics Assign ");

        updateMechanicsAssignCombo.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        updateMechanicsAssignCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        updateServiceTypeCombo.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        updateServiceTypeCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Servicing", "QCA" }));

        update.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        updateNumberPlateLabel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        updateNumberPlateLabel.setText("numberPlate");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel15.setText("Number Plate");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26)
                            .addComponent(jLabel21)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(65, 65, 65)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(totalKMTTextField)
                        .addComponent(updateModelLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(updateServiceIDLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(updateOwnernameLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(updateContact, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(updateServiceTypeCombo, 0, 228, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119)
                        .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(595, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(updateMechanicsAssignCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateNumberPlateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(314, 314, 314))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(updateNumberPlateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateMechanicsAssignCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(405, 405, 405)
                        .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(updateServiceIDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateOwnernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                            .addComponent(updateModelLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                            .addComponent(updateContact, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalKMTTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateServiceTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout updateJDialogLayout = new javax.swing.GroupLayout(updateJDialog.getContentPane());
        updateJDialog.getContentPane().setLayout(updateJDialogLayout);
        updateJDialogLayout.setHorizontalGroup(
            updateJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        updateJDialogLayout.setVerticalGroup(
            updateJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1220, 700));
        setRequestFocusEnabled(false);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(1220, 700));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1220, 700));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        serviceDetailPanel.setBackground(new java.awt.Color(255, 255, 255));

        serviceTypeCombo.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        serviceTypeCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Servicing", "QCA" }));
        serviceTypeCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serviceTypeComboActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setText("Service type");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("Total KM");

        kmTextField.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        kmTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kmTextFieldActionPerformed(evt);
            }
        });

        numberPlateTextField.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        numberPlateTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                numberPlateTextFieldKeyReleased(evt);
            }
        });

        serviceNumberLabel.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        serviceNumberLabel.setText("Service Number");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("Assign Mechanics");

        assignMechanicsCombo.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        assignMechanicsCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ramesh Yadav" }));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel10.setText("Bike Number-Plate");

        javax.swing.GroupLayout serviceDetailPanelLayout = new javax.swing.GroupLayout(serviceDetailPanel);
        serviceDetailPanel.setLayout(serviceDetailPanelLayout);
        serviceDetailPanelLayout.setHorizontalGroup(
            serviceDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(serviceDetailPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(serviceNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(serviceDetailPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(serviceDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, serviceDetailPanelLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(66, 66, 66))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, serviceDetailPanelLayout.createSequentialGroup()
                        .addGroup(serviceDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(80, 80, 80)))
                .addGroup(serviceDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(assignMechanicsCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(serviceTypeCombo, 0, 149, Short.MAX_VALUE)
                    .addComponent(kmTextField)
                    .addComponent(numberPlateTextField))
                .addGap(47, 47, 47))
        );
        serviceDetailPanelLayout.setVerticalGroup(
            serviceDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, serviceDetailPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(serviceNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(serviceDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(numberPlateTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(serviceDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(kmTextField)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(serviceDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(serviceTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(serviceDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(assignMechanicsCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );

        fullDetailPanel.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setToolTipText("");
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        detailProblemsTextArea.setColumns(20);
        detailProblemsTextArea.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        detailProblemsTextArea.setLineWrap(true);
        detailProblemsTextArea.setRows(5);
        jScrollPane1.setViewportView(detailProblemsTextArea);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setText("Detail Problems");

        addButton.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout fullDetailPanelLayout = new javax.swing.GroupLayout(fullDetailPanel);
        fullDetailPanel.setLayout(fullDetailPanelLayout);
        fullDetailPanelLayout.setHorizontalGroup(
            fullDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fullDetailPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fullDetailPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109))
        );
        fullDetailPanelLayout.setVerticalGroup(
            fullDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fullDetailPanelLayout.createSequentialGroup()
                .addGroup(fullDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fullDetailPanelLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(fullDetailPanelLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        plateList.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        plateList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bike number Plate", "Owner Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        plateList.setGridColor(new java.awt.Color(255, 255, 255));
        plateList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                plateListMousePressed(evt);
            }
        });
        jScrollPane6.setViewportView(plateList);

        mechanicsTable.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        mechanicsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mechanics Name", "Contact", "Number of Work"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(mechanicsTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(serviceDetailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fullDetailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 761, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(serviceDetailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fullDetailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );

        jTabbedPane1.addTab("Repair bike", null, jPanel2, "");

        jPanel3.setFont(new java.awt.Font("Tempus Sans ITC", 3, 18)); // NOI18N

        jTable2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "serviceNumber", "Owner Name", "Contact", "Number Plate", "Model", "Started Time", "Service Type", "Mechanics", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, false, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(1).setMinWidth(170);
        }

        completeButton.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        completeButton.setText("Complete");
        completeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                completeButtonActionPerformed(evt);
            }
        });

        viewDetailButton.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        viewDetailButton.setText("View Details");
        viewDetailButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewDetailButtonActionPerformed(evt);
            }
        });

        searchBar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        searchBar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchBarKeyReleased(evt);
            }
        });

        deleteButton.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        updateButton.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1236, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addComponent(viewDetailButton)
                .addGap(84, 84, 84)
                .addComponent(completeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 72, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(viewDetailButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(completeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Today's Record", null, jPanel3, "");

        jTable3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "serviceNumber", "Owner Name", "Contact", "Number Plate", "Model", "Completion Time", "Mechanics assign", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1236, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Completed", jPanel4);

        jTable4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "serviceNumber", "Owner Name", "Contact", "Number Plate", "Model", "Started Time", "Service Type", "Mechanics", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, false, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(jTable4);
        if (jTable4.getColumnModel().getColumnCount() > 0) {
            jTable4.getColumnModel().getColumn(1).setMinWidth(170);
        }

        searchBar2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        searchBar2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchBar2KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 1236, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(searchBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addComponent(searchBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("History", jPanel1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1241, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 701, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setBounds(0, 0, 1255, 731);
    }// </editor-fold>//GEN-END:initComponents

    private void serviceTypeComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serviceTypeComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_serviceTypeComboActionPerformed

    private void kmTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kmTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kmTextFieldActionPerformed

    private void numberPlateTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numberPlateTextFieldKeyReleased
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "");
            stmt = con.createStatement();
            String sql = "Select numberplate,ownername from bikeinfo where numberplate LIKE '%" + numberPlateTextField.getText() + "%'";
            //            String sql="Select numberplate,ownername from bikeinfo";
            rs = stmt.executeQuery(sql);
            //            System.out.println(rs.next());//The first name doesnt shows after using this
            DefaultTableModel dtm = (DefaultTableModel) plateList.getModel();
            dtm.setRowCount(0);
            //Removing all the row in Jtable
            while (rs.next()) {
                String name = rs.getString("ownername");
                String numberplate = rs.getString("numberplate");
                String data[] = {numberplate, name};
                DefaultTableModel tblmodel = (DefaultTableModel) plateList.getModel();
                if (numberPlateTextField.getText().equals("")) {
                    tblmodel.setRowCount(0);
                } else {
                    tblmodel.addRow(data);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddToRepairPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddToRepairPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_numberPlateTextFieldKeyReleased

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String assignMechanics = assignMechanicsCombo.getSelectedItem().toString();
            con = DriverManager.getConnection(url, "root", "");
            stmt = con.createStatement();
            String sql1="Select mechanicsid from mechanics where mechanicsname='"+assignMechanics+"'";
            rs=stmt.executeQuery(sql1);
            int id;
            rs.first();
            id=rs.getInt("mechanicsid");
            System.out.println(id);
            String numberPlate = numberPlateTextField.getText();
            System.out.println(numberPlate);
            String km = kmTextField.getText();
            String serviceType = serviceTypeCombo.getSelectedItem().toString();
            
            String detailProblems = detailProblemsTextArea.getText();
            

            String sql = "Insert into service (numberplate,totalkm,starttime,servicetype,mechanicsassign,detailproblems) Values('" + numberPlate + "','" + km + "',current_time(),'" + serviceType + "','" + id + "','" + detailProblems + "')";
            int result = stmt.executeUpdate(sql);
            if (result != -1) {
                JOptionPane.showMessageDialog(this, "Added ", "Register message", JOptionPane.INFORMATION_MESSAGE);
                retriveData();
                retriveCompleteData();
                retriveMechanicsData();
                retriveTodayData();
                numberPlateTextField.setText("");
                kmTextField.setText("");
                detailProblemsTextArea.setText("");
                plateList.removeAll();
                getServiceNumber();
                DefaultTableModel dtm = (DefaultTableModel) plateList.getModel();
                dtm.setRowCount(0);
//                this.dispose();
//                HomePage h=new HomePage();
//                h.setVisible(true);
//                h.addToRepair();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddToRepairPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddToRepairPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void plateListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plateListMousePressed
        JTable table = (JTable) evt.getSource();
        Point point = evt.getPoint();
        int row = plateList.getSelectedRow();
        if (evt.getClickCount() == 2) {
            numberPlateTextField.setText(plateList.getModel().getValueAt(row, 0).toString());
        }        // TODO add your handling code here:
    }//GEN-LAST:event_plateListMousePressed

    private void completeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_completeButtonActionPerformed
        int row = jTable2.getSelectedRow();
        if (row == -1) {
            row = 0;
        }
        String data = jTable2.getModel().getValueAt(row, 0).toString();
        System.out.println("data");
        System.out.println(data);
        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "");
            stmt = con.createStatement();
            if (con != null) {
                String sql = "Update service set status='Completed' , completedtime=curtime() where serviceid='" + data + "'";
                stmt.executeUpdate(sql);
                DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
                dtm.setRowCount(0);
                dtm = (DefaultTableModel) jTable3.getModel();
                dtm.setRowCount(0);
                dtm = (DefaultTableModel) mechanicsTable.getModel();
                dtm.setRowCount(0);
                retriveData();
                retriveCompleteData();
                retriveMechanicsData();
                retriveTodayData();
            } else {
                System.out.println("Database Not connected");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_completeButtonActionPerformed
    

//    public AddToRepairPage(String user, String pw) {
//        initComponents();
//
//        System.out.println("AddRepair");
//        System.out.println(user + " " + pw);
//        this.user = user;
//        this.pw = pw;
//        plateList.setRowHeight(mechanicsTable.getRowHeight() + 20);
//        
//        getServiceNumber();
//
//        try {
//            jTable2.setRowHeight(jTable2.getRowHeight() + 20);
//            jTable2.removeColumn(jTable2.getColumnModel().getColumn(0));
//            jTable3.setRowHeight(jTable2.getRowHeight() + 20);
//            jTable3.removeColumn(jTable3.getColumnModel().getColumn(0));
//            jTable3.removeColumn(jTable3.getColumnModel().getColumn(6));
//            retriveData();
//            retriveCompleteData();
//            retriveMechanicsData();
//
////        jTextField1.putClientProperty("JComponent.roundRect", true );
////        myButton.( "JButton.buttonType", "roundRect" );
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(AddToRepairPage.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(AddToRepairPage.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    

    private void viewDetailButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewDetailButtonActionPerformed
        viewJDialog.setVisible(true);
        viewJDialog.setSize(1200, 700);
        viewJDialog.setLocationRelativeTo(null);
        int row = jTable2.getSelectedRow();
        if (row == -1) {
            row = 0;
        }
        String sdata = jTable2.getModel().getValueAt(row, 0).toString();
        int data = Integer.parseInt(sdata);
        System.out.println(data);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "");
            stmt = con.createStatement();
            if (con != null) {
                String sql = "Select b.ownername,b.contact,b.email,b.bikemodel,s.servicedate,s.starttime,s.completedtime,s.servicetype,s.totalkm,s.detailproblems,m.mechanicsname,s.status,s.numberplate from mechanics as m,service as s,bikeinfo as b where s.numberplate=b.numberplate and s.mechanicsassign=m.mechanicsid and serviceid=" + data;
                rs = stmt.executeQuery(sql);
                if (rs.first()) {
                    mechanicsAssignLabel.setText(rs.getString("mechanicsname"));
                    modelLabel.setText(rs.getString("bikemodel"));
                    numberPlateLabel.setText(rs.getString("numberplate"));
                    emailLabel.setText(rs.getString("email"));
                    ownerNameLabel.setText(rs.getString("ownername"));
                    contactLabel.setText(rs.getString("contact"));
                    startTimeLabel.setText(rs.getString("starttime"));
                    completionTimeLabel.setText(rs.getString("completedtime"));
                    serviceTypeLabel.setText(rs.getString("servicetype"));
                    serviceDateLabel.setText("Serviced on: " + rs.getString("servicedate"));
                    jTextArea2.setEditable(false);
                    jTextArea2.setText(rs.getString("detailproblems"));
                    totalkmLabel.setText(rs.getString("totalkm"));
                    status.setText(rs.getString("status"));
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddToRepairPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddToRepairPage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_viewDetailButtonActionPerformed

    private void searchBarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchBarKeyReleased
        searchToday(jTable2);
    }//GEN-LAST:event_searchBarKeyReleased
    public void searchToday(JTable jtable) {
        try {
            String sql = "Select s.serviceid,b.ownername,b.contact,s.numberplate,b.bikemodel,s.starttime,m.mechanicsname,s.status,s.servicetype from bikeinfo as b,service as s,mechanics as m where s.numberplate=b.numberplate and s.mechanicsassign=m.mechanicsid and s.servicedate=current_date() and b.ownername like '" + searchBar.getText() + "%' Order by b.ownername";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            DefaultTableModel dtm = (DefaultTableModel) jtable.getModel();
            dtm.setRowCount(0);//Removing all the row in Jtable
            while (rs.next()) {
                String id = rs.getString("s.serviceid");
                String name = rs.getString("b.ownername");
                String model = rs.getString("b.bikemodel");
                String contact = rs.getString("b.contact");
                String numberplate = rs.getString("s.numberplate");
                String servicetime = rs.getString("s.starttime");
                String servicetype = rs.getString("s.servicetype");
                String mechanicsassign = rs.getString("m.mechanicsname");
                String status = rs.getString("s.status");
                String data[] = {id, name, contact, numberplate, model, servicetime, servicetype, mechanicsassign, status};
                tblmodel = (DefaultTableModel) jtable.getModel();
                tblmodel.addRow(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        int row = jTable2.getSelectedRow();
        if (row == -1) {
            row = 0;
        }
        String data = jTable2.getModel().getValueAt(row, 0).toString();

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "");
            stmt = con.createStatement();
            if (con != null) {
                String sql = "Select status from service where serviceid ='" + data + "'";
                rs=stmt.executeQuery(sql);
                if (rs.first()) {
                    if (rs.getString("status").equals("Completed")) {
                        JOptionPane.showMessageDialog(null, "This bike has already completed its servicing","Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        String sql1 = "Delete from service where serviceid='" + data + "'";
                        stmt.executeUpdate(sql1);
                        DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
                        dtm.setRowCount(0);
                        retriveData();
                    }
                }

            } else {
                System.out.println("Database Not connected");
            }
            retriveData();
            retriveTodayData();
            retriveCompleteData();
            retriveMechanicsData();
    
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        try {
            int row = jTable2.getSelectedRow();
            if (row == -1) {
            row = 0;
        }
            String data = jTable2.getModel().getValueAt(row, 0).toString();
            System.out.println(data);

            String sql = "Select s.serviceid,b.ownername,b.contact,b.bikemodel ,b.numberplate,s.mechanicsassign,s.totalkm,s.servicetype,s.detailproblems from bikeinfo as b,service as s where s.numberplate=b.numberplate and s.serviceid='"+data+"'";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.first()){
                updateServiceIDLabel.setText(rs.getString("serviceid"));
                updateOwnernameLabel.setText(rs.getString("ownername"));
                updateModelLabel.setText(rs.getString("bikemodel"));
                updateContact.setText(rs.getString("contact"));
                updateNumberPlateLabel.setText(rs.getString("numberplate"));
                updateServiceTypeCombo.setSelectedItem(rs.getString("servicetype"));
                updateMechanicsAssignCombo.setSelectedItem(rs.getString("mechanicsassign"));
                totalKMTTextField.setText(rs.getString("totalkm"));
                jTextArea1.setText(rs.getString("detailproblems"));
            }
            updateJDialog.setVisible(true);
            updateJDialog.setSize(1200, 700);
            updateJDialog.setLocationRelativeTo(null);
            updateJDialog.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(AddToRepairPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_updateButtonActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        try {
            int row = jTable2.getSelectedRow();
            if(row==-1){
                row=0;
            }
            String data = jTable2.getModel().getValueAt(row, 0).toString();
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "");
            if(con!=null){
                if (!totalKMTTextField.getText().isEmpty()) {
                    stmt = con.createStatement();
                    String sql = "Update service set totalkm='" + totalKMTTextField.getText() + "' where serviceid='" + data + "'";
                    int result = stmt.executeUpdate(sql);
                    if (result != -1){
                        System.out.println("Updated");
                    }
                }
                if (!jTextArea1.getText().isEmpty()) {
                    stmt = con.createStatement();
                    String sql = "Update service set detailproblems='" + jTextArea1.getText() + "' where serviceid='" + data + "'";
                    int result = stmt.executeUpdate(sql);
                    if (result != -1){
                        System.out.println("Updated");
                    }
                }
                String sql1="Select serviceType,mechanicsassign from service where serviceid='"+data+"'";
                rs=stmt.executeQuery(sql1);
                rs.first();
                if(!updateServiceTypeCombo.getSelectedItem().toString().equals(rs.getString("servicetype"))){
                    stmt = con.createStatement();
                    String sql = "Update service set servicetype='" + updateServiceTypeCombo.getSelectedItem().toString() + "' where serviceid='" + data + "'";
                    int result = stmt.executeUpdate(sql);
                    if (result != -1){
                        System.out.println("Updated");
                    }
                }
                if(!updateMechanicsAssignCombo.getSelectedItem().toString().equals(rs.getString("mechanicsassign"))){
                    stmt = con.createStatement();
                    String sql2="Select mechanicsid from mechanics where mechanicsname='"+ updateMechanicsAssignCombo.getSelectedItem().toString()+"'";
                    rs=stmt.executeQuery(sql2);
                    int id;
                    rs.first();
                    id=rs.getInt("mechanicsid");
                    String sql = "Update service set mechanicsassign='" + id + "' where serviceid='" + data + "'";
                    int result = stmt.executeUpdate(sql);
                    if (result != -1){
                        System.out.println("Updated");
                    }
                }
                retriveData();
                retriveTodayData();
                retriveCompleteData();
                retriveMechanicsData();
                JOptionPane.showMessageDialog(this,"","Sucessfully Updated",JOptionPane.INFORMATION_MESSAGE); 
                updateJDialog.dispose();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddToRepairPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddToRepairPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_updateActionPerformed

    private void searchBar2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchBar2KeyReleased
 try {
            String sql = "Select s.serviceid,b.ownername,b.contact,s.numberplate,b.bikemodel,s.starttime,m.mechanicsname,s.servicedate,s.servicetype from bikeinfo as b,service as s,mechanics as m where s.numberplate=b.numberplate and s.mechanicsassign=m.mechanicsid and b.ownername like '" + searchBar2.getText() + "%' Order by b.ownername";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            DefaultTableModel dtm = (DefaultTableModel) jTable4.getModel();
            dtm.setRowCount(0);//Removing all the row in jTable
            while (rs.next()) {
                String id = rs.getString("s.serviceid");
                String name = rs.getString("b.ownername");
                String model = rs.getString("b.bikemodel");
                String contact = rs.getString("b.contact");
                String numberplate = rs.getString("s.numberplate");
                String servicetime = rs.getString("s.starttime");
                String servicetype = rs.getString("s.servicetype");
                String mechanicsassign = rs.getString("m.mechanicsname");
                String status = rs.getString("s.servicedate");
                String data[] = {id, name, contact, numberplate, model, servicetime, servicetype, mechanicsassign, status};
                tblmodel = (DefaultTableModel) jTable4.getModel();
                tblmodel.addRow(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_searchBar2KeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLabl19;
    private javax.swing.JButton addButton;
    private javax.swing.JComboBox<String> assignMechanicsCombo;
    private javax.swing.JButton completeButton;
    private javax.swing.JLabel completionTimeLabel;
    private javax.swing.JLabel contactLabel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextArea detailProblemsTextArea;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JPanel fullDetailPanel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField kmTextField;
    private javax.swing.JLabel mechanicsAssignLabel;
    private javax.swing.JTable mechanicsTable;
    private javax.swing.JLabel modelLabel;
    private javax.swing.JLabel numberPlateLabel;
    private javax.swing.JTextField numberPlateTextField;
    private javax.swing.JLabel ownerNameLabel;
    private javax.swing.JTable plateList;
    private javax.swing.JTextField searchBar;
    private javax.swing.JTextField searchBar2;
    private javax.swing.JLabel serviceDateLabel;
    private javax.swing.JPanel serviceDetailPanel;
    private javax.swing.JLabel serviceNumberLabel;
    private javax.swing.JComboBox<String> serviceTypeCombo;
    private javax.swing.JLabel serviceTypeLabel;
    private javax.swing.JLabel startTimeLabel;
    private javax.swing.JLabel status;
    private javax.swing.JTextField totalKMTTextField;
    private javax.swing.JLabel totalkmLabel;
    private javax.swing.JButton update;
    private javax.swing.JButton updateButton;
    private javax.swing.JLabel updateContact;
    private javax.swing.JDialog updateJDialog;
    private javax.swing.JComboBox<String> updateMechanicsAssignCombo;
    private javax.swing.JLabel updateModelLabel;
    private javax.swing.JLabel updateNumberPlateLabel;
    private javax.swing.JLabel updateOwnernameLabel;
    private javax.swing.JLabel updateServiceIDLabel;
    private javax.swing.JComboBox<String> updateServiceTypeCombo;
    private javax.swing.JButton viewDetailButton;
    private javax.swing.JDialog viewJDialog;
    // End of variables declaration//GEN-END:variables
}
