/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bikeservicecenterproject;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.*;
import java.util.*;
import javax.swing.table.*;

/**
 *
 * @author パワーPower
 */
public class AddToRepairPage extends javax.swing.JFrame {

    String user, pw;
    Connection con;
    ResultSet rs;
    Statement stmt;
    String url = "jdbc:mysql://localhost:3306/bikeservicecenter";
    DefaultTableModel tblmodel;

    /**
     * Creates new form AddToRepairPage
     */
    public AddToRepairPage() {
        this("admin", "admim");
//        jTextField1.putClientProperty("JComponent.roundRect", true );
//        myButton.( "JButton.buttonType", "roundRect" );
    }

    public void clock() {
        Thread clock = new Thread() {
            @Override
            public void run() {
                for (;;) {
                    try {

                        Calendar c = new GregorianCalendar();
                        int minute = c.get(Calendar.MINUTE);
                        int hour = c.get(Calendar.HOUR);
                        int second = c.get(Calendar.SECOND);
                        int amPm = c.get(Calendar.AM_PM);
                        if (amPm == Calendar.PM) {

                            if (minute < 10 && second < 10) {
                                timeLabel.setText("Time:  " + hour + ":0" + minute + ":0" + second + " PM");
                            } else if (minute < 10) {
                                timeLabel.setText("Time:  " + hour + ":0" + minute + ":" + second + " PM");
                            } else if (second < 10) {
                                timeLabel.setText("Time:  " + hour + ":" + minute + ":0" + second + " PM");
                            } else {
                                timeLabel.setText("Time:  " + hour + ":" + minute + ":" + second + " PM");
                            }

                        } else {

                            timeLabel.setText("Time:  " + hour + ":" + minute + ":" + second + " AM");
                            if (minute < 10 && second < 10) {
                                timeLabel.setText("Time:  " + hour + ":0" + minute + ":0" + second + " PM");
                            } else if (minute < 10) {
                                timeLabel.setText("Time:  " + hour + ":0" + minute + ":" + second + " PM");
                            } else if (second < 10) {
                                timeLabel.setText("Time:  " + hour + ":" + minute + ":0" + second + " PM");
                            } else {
                                timeLabel.setText("Time:  " + hour + ":" + minute + ":" + second + " PM");
                            }

                            sleep(1000);
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(AddToRepairPage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        clock.start();
    }

    public void retriveData() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url, "root", "");
        if (con != null) {
            System.out.println("Database connected");
            stmt = con.createStatement();
            String sql = "Select s.serviceid,b.ownername,b.contact,s.numberplate,b.bikemodel,s.starttime,s.servicetype,s.mechanicsassign,s.status from bikeinfo as b,service as s where s.numberplate=b.numberplate";
            rs = stmt.executeQuery(sql);

//            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            JTableHeader header = jTable2.getTableHeader();
            header.setFont(new Font("Times New Roman", Font.BOLD, 18));
            while (rs.next()) {
                String id = rs.getString("s.serviceid");
                String name = rs.getString("b.ownername");
                String model = rs.getString("b.bikemodel");
                String contact = rs.getString("b.contact");
                String numberplate = rs.getString("s.numberplate");
                String servicetime = rs.getString("s.starttime");
                String servicetype = rs.getString("s.servicetype");
                String mechanicsassign = rs.getString("s.mechanicsassign");
                String status = rs.getString("s.status");
                String data[] = {id, name, contact, numberplate, model, servicetime, servicetype, mechanicsassign, status};
                tblmodel = (DefaultTableModel) jTable2.getModel();
                tblmodel.addRow(data);
            }
        } else {
            System.out.println("Database connection failed");
        }
    }

    public void retriveMechanicsData() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "");
            assignMechanicsCombo.removeAllItems();
            updateMechanicsAssignCombo.removeAllItems();
            String sql = "Select mechanicsname,contact,experience from mechanics order by mechanicsname ";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
//                String id=rs.getString("s.serviceid");
                String name = rs.getString("mechanicsname");
//                String model = rs.getString("b.bikemodel");
                String contact = rs.getString("contact");
                String experience = rs.getString("experience");

                assignMechanicsCombo.addItem(name);
                updateMechanicsAssignCombo.addItem(name);
//                String servicetime=rs.getString("s.starttime");
//                String servicetype=rs.getString("s.servicetype");
//                String mechanicsassign=rs.getString("s.mechanicsassign");
//                String status=rs.getString("s.status");
                String data[] = {name, contact, experience};

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
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url, "root", "");
        if (con != null) {
            System.out.println("Database connected");
            stmt = con.createStatement();
            String sql = "Select s.serviceid,b.ownername,b.contact,s.numberplate,b.bikemodel,s.completedtime,s.mechanicsassign,s.status from bikeinfo as b,service as s where s.numberplate=b.numberplate and s.status='Completed'";
            ResultSet rs = stmt.executeQuery(sql);
//            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            JTableHeader header = jTable3.getTableHeader();
            header.setFont(new Font("Times New Roman", Font.BOLD, 18));
            while (rs.next()) {
                String id = rs.getString("s.serviceid");
                String name = rs.getString("b.ownername");
                String model = rs.getString("b.bikemodel");
                String contact = rs.getString("b.contact");
                String numberplate = rs.getString("s.numberplate");
                String completedtime = rs.getString("s.completedtime");
                String mechanicsassign = rs.getString("s.mechanicsassign");
                String status = rs.getString("s.status");
                String data[] = {id, name, contact, numberplate, model, completedtime, mechanicsassign, status};
                tblmodel = (DefaultTableModel) jTable3.getModel();
                tblmodel.addRow(data);
            }
        } else {
            System.out.println("Database connection failed");
        }
    }

    public AddToRepairPage(String user, String pw) {
        initComponents();

        System.out.println("AddRepair");
        System.out.println(user + " " + pw);
        this.user = user;
        this.pw = pw;
        plateList.setRowHeight(mechanicsTable.getRowHeight() + 20);
        clock();
        getServiceNumber();

        try {
            retriveData();
            retriveCompleteData();
            retriveMechanicsData();
            jTable2.setRowHeight(jTable2.getRowHeight() + 20);
            jTable2.removeColumn(jTable2.getColumnModel().getColumn(0));
            System.out.println(jTable2.getColumnCount());
            jTable3.setRowHeight(jTable2.getRowHeight() + 20);
            jTable3.removeColumn(jTable3.getColumnModel().getColumn(0));
            jTable3.removeColumn(jTable3.getColumnModel().getColumn(6));
            

//        jTextField1.putClientProperty("JComponent.roundRect", true );
//        myButton.( "JButton.buttonType", "roundRect" );
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddToRepairPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddToRepairPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
                   serviceNumberLabel.setText("Service ID:"+newServiceId);
                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddToRepairPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddToRepairPage.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        statusLabel = new javax.swing.JLabel();
        serviceDateLabel = new javax.swing.JLabel();
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
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
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
        timeLabel = new javax.swing.JLabel();

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setText("Number Plate");

        jLabel12.setText("Owner Name");

        jLabel13.setText("Contact");

        jLabel16.setText("Completion time");

        jLabel17.setText("Model");

        jLabel29.setText("Started time");

        jLabel32.setText("Email");

        jLabel33.setText("Service type");

        jLabel35.setText("Mechanics Assign");

        jLabel37.setText("Status");

        numberPlateLabel.setText("Number Plate");

        emailLabel.setText("Email");

        ownerNameLabel.setText("Owner Name");

        contactLabel.setText("Contact");

        modelLabel.setText("Model");

        startTimeLabel.setText("start time");

        completionTimeLabel.setText("Completed time");

        serviceTypeLabel.setText("Service Type");

        mechanicsAssignLabel.setText("Mechanics Assign");

        statusLabel.setText("Status");

        serviceDateLabel.setText("Serviced on:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(223, 223, 223)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(startTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(completionTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(numberPlateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(contactLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ownerNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(modelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35)
                            .addComponent(jLabel37))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(serviceTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mechanicsAssignLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(serviceDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(174, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(numberPlateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(serviceTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ownerNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(contactLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(modelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(mechanicsAssignLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(31, 31, 31)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(completionTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(117, 117, 117)
                .addComponent(serviceDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout viewJDialogLayout = new javax.swing.GroupLayout(viewJDialog.getContentPane());
        viewJDialog.getContentPane().setLayout(viewJDialogLayout);
        viewJDialogLayout.setHorizontalGroup(
            viewJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1123, Short.MAX_VALUE)
            .addGroup(viewJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(viewJDialogLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        viewJDialogLayout.setVerticalGroup(
            viewJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 736, Short.MAX_VALUE)
            .addGroup(viewJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(viewJDialogLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setText("Service ID");

        jLabel21.setText("Owner Name");

        jLabel22.setText("Model");

        jLabel23.setText("Detail Problems");

        jLabel24.setText("Total Km");

        jLabel26.setText("ServiceType");

        jLabel28.setText("Contact");

        updateServiceIDLabel.setText("jLabel3");

        updateOwnernameLabel.setText("jLabel3");

        updateModelLabel.setText("jLabel3");

        updateContact.setText("jLabel3");

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jScrollPane4.setViewportView(jTextArea1);

        jLabel27.setText("Mechanics Assign ");

        updateMechanicsAssignCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        updateServiceTypeCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Servicing", "QCA" }));

        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        updateNumberPlateLabel.setText("numberPlate");

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
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(81, 81, 81)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(totalKMTTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                                    .addComponent(updateModelLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(updateServiceIDLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(updateOwnernameLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(updateContact, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(updateServiceTypeCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(96, 96, 96)
                                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(52, 52, 52)
                                        .addComponent(updateMechanicsAssignCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(46, 46, 46)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(47, 47, 47)
                                        .addComponent(updateNumberPlateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(553, 553, 553)
                        .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(352, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                        .addComponent(updateServiceIDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(updateNumberPlateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateOwnernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(updateModelLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(updateContact, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalKMTTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateMechanicsAssignCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateServiceTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout updateJDialogLayout = new javax.swing.GroupLayout(updateJDialog.getContentPane());
        updateJDialog.getContentPane().setLayout(updateJDialogLayout);
        updateJDialogLayout.setHorizontalGroup(
            updateJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateJDialogLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        updateJDialogLayout.setVerticalGroup(
            updateJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updateJDialogLayout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 102, 0));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Add bike");

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        serviceTypeCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Servicing", "QCA" }));
        serviceTypeCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serviceTypeComboActionPerformed(evt);
            }
        });

        jLabel7.setText("Service type");

        jLabel2.setText("Total KM");

        kmTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kmTextFieldActionPerformed(evt);
            }
        });

        numberPlateTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                numberPlateTextFieldKeyReleased(evt);
            }
        });

        serviceNumberLabel.setText("Service Number");

        jLabel9.setText("Assign Mechanics");

        assignMechanicsCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ramesh Yadav" }));

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
                    .addGroup(serviceDetailPanelLayout.createSequentialGroup()
                        .addGroup(serviceDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(80, 80, 80))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, serviceDetailPanelLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(66, 66, 66))
                    .addGroup(serviceDetailPanelLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(serviceDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numberPlateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(serviceDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(kmTextField)
                        .addComponent(assignMechanicsCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(serviceTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47))
        );
        serviceDetailPanelLayout.setVerticalGroup(
            serviceDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, serviceDetailPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(serviceNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(serviceDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(serviceDetailPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(serviceDetailPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(numberPlateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setToolTipText("");
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        detailProblemsTextArea.setColumns(20);
        detailProblemsTextArea.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        detailProblemsTextArea.setLineWrap(true);
        detailProblemsTextArea.setRows(5);
        jScrollPane1.setViewportView(detailProblemsTextArea);

        jLabel8.setText("Detail Problems");

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
                .addGap(20, 20, 20)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
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
                        .addGap(45, 45, 45)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(fullDetailPanelLayout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

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
        plateList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                plateListMousePressed(evt);
            }
        });
        jScrollPane6.setViewportView(plateList);

        mechanicsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mechanics Name", "Contact", "Experience"
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
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(serviceDetailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fullDetailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(315, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(serviceDetailPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fullDetailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Repair bike", null, jPanel2, "");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Service Number", "Owner Name", "Contact", "Number Plate", "Model", "Started Time", "Service Type", "Mechanics assign", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable2);

        completeButton.setText("Complete");
        completeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                completeButtonActionPerformed(evt);
            }
        });

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

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

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
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1045, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(viewDetailButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(completeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 67, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(completeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(viewDetailButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Today's Record", null, jPanel3, "");

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
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1045, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 75, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Completed", jPanel4);

        timeLabel.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        timeLabel.setText("Time");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1048, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(403, 403, 403)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1469, 818));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void kmTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kmTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kmTextFieldActionPerformed
    public boolean isCellEditable(int row, int column) {
        return false;
    }
    private void numberPlateTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numberPlateTextFieldKeyReleased
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "");
            stmt = con.createStatement();

            String sql = "Select numberplate,ownername from bikeinfo where numberplate LIKE '" + numberPlateTextField.getText() + "%'";
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

    private void plateListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plateListMousePressed
        JTable table = (JTable) evt.getSource();
        Point point = evt.getPoint();
        int row = plateList.getSelectedRow();

        if (evt.getClickCount() == 2) {
            numberPlateTextField.setText(plateList.getModel().getValueAt(row, 0).toString());
        }        // TODO add your handling code here:
    }//GEN-LAST:event_plateListMousePressed

    private void serviceTypeComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serviceTypeComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_serviceTypeComboActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        try {
            String numberPlate = numberPlateTextField.getText();
            System.out.println(numberPlate);
            String km = kmTextField.getText();
            String serviceType = serviceTypeCombo.getSelectedItem().toString();
            String assignMechanics = assignMechanicsCombo.getSelectedItem().toString();
            String detailProblems = detailProblemsTextArea.getText();
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "");
            stmt = con.createStatement();
            String sql = "Insert into service (numberplate,totalkm,starttime,servicetype,mechanicsassign,detailproblems) Values('" + numberPlate + "','" + km + "',current_time(),'" + serviceType + "','" + assignMechanics + "','" + detailProblems + "')";
            int result = stmt.executeUpdate(sql);
            if (result != -1) {
                JOptionPane.showMessageDialog(this, "Register message", "Added", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                new AddToRepairPage(user, pw).setVisible(true);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddToRepairPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddToRepairPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void completeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_completeButtonActionPerformed
        int row = jTable2.getSelectedRow();
        if (row == -1) {
            row = 0;
        }
        String data = jTable2.getModel().getValueAt(row, 0).toString();
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

            } else {
                System.out.println("Database Not connected");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_completeButtonActionPerformed

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
                String sql = "Select b.ownername,b.contact,b.email,b.bikemodel,s.servicedate,s.starttime,s.completedtime,s.servicetype,s.mechanicsassign,s.status,s.numberplate from service as s,bikeinfo as b where s.numberplate=b.numberplate and serviceid=" + data;
                rs = stmt.executeQuery(sql);
                if (rs.first()) {
                    mechanicsAssignLabel.setText(rs.getString("mechanicsassign"));
                    modelLabel.setText(rs.getString("bikemodel"));
                    numberPlateLabel.setText(rs.getString("numberplate"));
                    emailLabel.setText(rs.getString("email"));
                    ownerNameLabel.setText(rs.getString("ownername"));
                    contactLabel.setText(rs.getString("contact"));
                    startTimeLabel.setText(rs.getString("starttime"));
                    completionTimeLabel.setText(rs.getString("completedtime"));
                    serviceTypeLabel.setText(rs.getString("servicetype"));
                    serviceDateLabel.setText("Serviced on: " + rs.getString("servicedate"));
                    statusLabel.setText(rs.getString("status"));
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddToRepairPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddToRepairPage.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_viewDetailButtonActionPerformed

    private void searchBarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchBarKeyReleased
        search(jTable2);
    }//GEN-LAST:event_searchBarKeyReleased

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
            String sql = "Select s.serviceid,b.ownername,b.contact,b.bikemodel ,b.numberplate,s.mechanicsassign,s.servicetype from bikeinfo as b,service as s where s.numberplate=b.numberplate and s.serviceid='"+data+"'";
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
            
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "");
            int row = jTable2.getSelectedRow();
            if(row==-1){
                row=0;
            }
            System.out.println("row");
            String data = jTable2.getModel().getValueAt(row, 1).toString();
            System.out.println(data);
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
                updateJDialog.dispose();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddToRepairPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddToRepairPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_updateActionPerformed
    public void search(JTable jtable) {
        try {
            String sql = "Select s.serviceid,b.ownername,b.contact,s.numberplate,b.bikemodel,s.starttime,s.mechanicsassign,s.status,s.servicetype from bikeinfo as b,service as s where s.numberplate=b.numberplate and b.ownername like '" + searchBar.getText() + "%' Order by b.ownername";
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
                String mechanicsassign = rs.getString("s.mechanicsassign");
                String status = rs.getString("s.status");
                String data[] = {id, name, contact, numberplate, model, servicetime, servicetype, mechanicsassign, status};
                tblmodel = (DefaultTableModel) jtable.getModel();
                tblmodel.addRow(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
            * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        FlatLightLaf.setup();
        UIManager.put("TabbedPane.tabSeparatorsFullHeight", true);
//            UIManager.put( "Button.arc", 999 );
//            UIManager.put( "Component.arc", 999 );
//            UIManager.put( "ProgressBar.arc", 999 );
//            UIManager.put( "TextComponent.arc", 999 );
//            UIManager.put( "Component.focusWidth", 1 );
//            try {
//            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//            } catch (ClassNotFoundException ex) {
//            Logger.getLogger(AddToRepairPage.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            Logger.getLogger(AddToRepairPage.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(AddToRepairPage.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(AddToRepairPage.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(AddToRepairPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(AddToRepairPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(AddToRepairPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(AddToRepairPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddToRepairPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField kmTextField;
    private javax.swing.JLabel mechanicsAssignLabel;
    private javax.swing.JTable mechanicsTable;
    private javax.swing.JLabel modelLabel;
    private javax.swing.JLabel numberPlateLabel;
    private javax.swing.JTextField numberPlateTextField;
    private javax.swing.JLabel ownerNameLabel;
    private javax.swing.JTable plateList;
    private javax.swing.JTextField searchBar;
    private javax.swing.JLabel serviceDateLabel;
    private javax.swing.JPanel serviceDetailPanel;
    private javax.swing.JLabel serviceNumberLabel;
    private javax.swing.JComboBox<String> serviceTypeCombo;
    private javax.swing.JLabel serviceTypeLabel;
    private javax.swing.JLabel startTimeLabel;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JTextField totalKMTTextField;
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
