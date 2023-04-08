/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bikeservicecenterproject;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.*;
import static java.lang.Thread.sleep;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.table.*;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author パワーPower
 */
public class HomePage extends javax.swing.JFrame {

    private JTextField tfield;
    private String nameTField;
    private int count;
    String user;
    String pw;
    Connection con;
    Statement stmt;
    DefaultTableModel tblmodel;
    String url = "jdbc:mysql://localhost:3306/bikeservicecenter";

    /**
     * Creates new form HomePage
     */
    public void retriveData() throws ClassNotFoundException, SQLException {
       
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url, "root", "");
        if (con != null) {
            System.out.println("Database connected");
            stmt = con.createStatement();
            String sql = "Select ownername,bikemodel,contact,email,numberplate from bikeinfo order by ownername";
            ResultSet rs = stmt.executeQuery(sql);
            jTable1.setRowHeight(jTable1.getRowHeight() + 20);
            JTableHeader header = jTable1.getTableHeader();
            header.setFont(new Font("Times New Roman", Font.BOLD, 18));
            while (rs.next()) {
                String name = rs.getString("ownername");
                String model = rs.getString("bikemodel");
                String contact = rs.getString("contact");
                String email = rs.getString("email");
                String numberplate = rs.getString("numberplate");
                String data[] = {name, numberplate, model, email, contact};
                tblmodel = (DefaultTableModel) jTable1.getModel();
                tblmodel.addRow(data);
            }
        } else {
            System.out.println("Database connection failed");
        }
        
    }

    public HomePage() throws ClassNotFoundException, SQLException {
        this("admin","admin");
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
                                timeLabel.setText("Time:  " + hour + ":0" + minute +":0"+second+" PM");
                            } else if (minute < 10) {
                                timeLabel.setText("Time:  " + hour + ":0" + minute +":"+second+" PM");
                            } else if (second < 10) {
                                timeLabel.setText("Time:  " + hour + ":" + minute +":0"+second+ " PM");
                            } else {
                                timeLabel.setText("Time:  " + hour + ":" + minute +":"+second+ " PM");
                            }

                        } else {

                            if (minute < 10 && second < 10) {
                                timeLabel.setText("Time:  " + hour + ":0" + minute +":0"+second+" AM");
                            } else if (minute < 10) {
                                timeLabel.setText("Time:  " + hour + ":0" + minute +":"+second+" AM");
                            } else if (second < 10) {
                                timeLabel.setText("Time:  " + hour + ":" + minute +":0"+second+ " AM");
                            } else {
                                timeLabel.setText("Time:  " + hour + ":" + minute +":"+second+ " AM");
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

    public HomePage(String user, String pw) throws ClassNotFoundException, SQLException {
        initComponents();
        nameTField = "tField";
        count = 0;
        clock();
        register();
        System.out.println("HomePage");
        this.user = user;
        this.pw = pw;
        System.out.println(user);
        System.out.println(pw);
        retriveData();
    }
    public void register(){
        Register register=new Register();
        jDesktopPane1.removeAll();
        jDesktopPane1.add(register).setVisible(true);
        titleLabel.setText(registerLabel.getText());
        titleLabel.setIcon(registerLabel.getIcon());
    }
    public void accountUpdate(){
        FAccount obj=new FAccount();
        jDesktopPane1.removeAll();
        jDesktopPane1.add(obj).setVisible(true);
        titleLabel.setText(accountUpdateLabel.getText());
        titleLabel.setIcon(accountUpdateLabel.getIcon());
    }
//    public void scaleImage(){
//        ImageIcon icon = new ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\BikeServiceCenterProject\\src\\image\\HomeLogo.jpg");
//        Image img= icon.getImage();
//        System.out.println(ImageLabel.getHeight());
//        //Image imgScale = img.getScaledInstance(jLabel3.getWidth(), jLabel3.getHeight(), Image.SCALE_DEFAULT);
////        ImageIcon scaledIcon= new ImageIcon(imgScale);
//        ImageLabel.setIcon(icon);
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        viewDetailFrame = new javax.swing.JFrame();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        bikeModelLabel = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        registerDateLabel = new javax.swing.JLabel();
        numberPlateLabel = new javax.swing.JLabel();
        ownerNameLabel = new javax.swing.JLabel();
        bikeTypeLabel = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        contactLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        chasisNumberLabel = new javax.swing.JLabel();
        engineNumberLabel = new javax.swing.JLabel();
        kGradientPanel2 = new com.k33ptoo.components.KGradientPanel();
        detailImageLabel = new javax.swing.JLabel();
        detailRegisterLabel = new javax.swing.JLabel();
        detailAddRepairLabel = new javax.swing.JLabel();
        detailMakeBillLabel = new javax.swing.JLabel();
        detailAccountUpdateLabel = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        logoutLabel2 = new javax.swing.JLabel();
        updateFrame = new javax.swing.JFrame();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        numberPlateTextField = new javax.swing.JTextField();
        ownerNameTextField = new javax.swing.JTextField();
        bikeTypeTextField = new javax.swing.JTextField();
        bikeModelTextField = new javax.swing.JTextField();
        contactTextField = new javax.swing.JTextField();
        emailTextField = new javax.swing.JTextField();
        chasisNumberTextField = new javax.swing.JTextField();
        engineNumberTextField = new javax.swing.JTextField();
        update = new javax.swing.JButton();
        kGradientPanel4 = new com.k33ptoo.components.KGradientPanel();
        jLabel2 = new javax.swing.JLabel();
        ImageLabel1 = new javax.swing.JLabel();
        registerLabel1 = new javax.swing.JLabel();
        addRepairLabel1 = new javax.swing.JLabel();
        makeBillLabel1 = new javax.swing.JLabel();
        accountUpdateLabel1 = new javax.swing.JLabel();
        logoutLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        registerationPanel = new javax.swing.JPanel();
        ownerName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        bikeType = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        registerButton = new javax.swing.JButton();
        bikeModel = new javax.swing.JComboBox<>();
        contact = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        numberPlate = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        chasisNumber = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        engineNumber = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        recordPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        searchBar = new javax.swing.JTextField();
        updateButton = new javax.swing.JButton();
        detailButton = new javax.swing.JButton();
        kGradientPanel1 = new com.k33ptoo.components.KGradientPanel();
        registerLabel = new javax.swing.JLabel();
        addRepairLabel = new javax.swing.JLabel();
        makeBillLabel = new javax.swing.JLabel();
        accountUpdateLabel = new javax.swing.JLabel();
        logoutLabel = new javax.swing.JLabel();
        ImageLabel = new javax.swing.JLabel();
        accountUpdateLabel2 = new javax.swing.JLabel();
        kGradientPanel3 = new com.k33ptoo.components.KGradientPanel();
        jLabel29 = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();

        viewDetailFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        viewDetailFrame.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        viewDetailFrame.setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 102, 0));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Number Plate");

        jLabel4.setText("Owner Name");

        jLabel5.setText("Two wheeler Type");

        bikeModelLabel.setText("Model");

        jLabel14.setText("Chasis number");

        jLabel15.setText("Contact");

        jLabel19.setText("Email");

        jLabel20.setText("Engine number");

        registerDateLabel.setText("Registered on:");

        numberPlateLabel.setText("Number Plate");

        ownerNameLabel.setText("Owner Name");

        bikeTypeLabel.setText("Two wheeler Type");

        jLabel25.setText("Model");

        contactLabel.setText("Contact");

        emailLabel.setText("Email");

        chasisNumberLabel.setText("Chasis number");

        engineNumberLabel.setText("Engine number");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(223, 223, 223)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(202, 202, 202)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(registerDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(contactLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ownerNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numberPlateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(bikeTypeLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                        .addComponent(bikeModelLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(engineNumberLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                        .addComponent(chasisNumberLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(262, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numberPlateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ownerNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bikeTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bikeModelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(contactLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chasisNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(engineNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addComponent(registerDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );

        kGradientPanel2.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel2.setkStartColor(new java.awt.Color(0, 0, 0));

        detailImageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        detailImageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/HomeLogo (2).jpg"))); // NOI18N
        detailImageLabel.setToolTipText("");
        detailImageLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        detailRegisterLabel.setBackground(new java.awt.Color(255, 255, 255));
        detailRegisterLabel.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        detailRegisterLabel.setForeground(new java.awt.Color(255, 255, 255));
        detailRegisterLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        detailRegisterLabel.setText("Bike Register");
        detailRegisterLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                detailRegisterLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                detailRegisterLabelMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                detailRegisterLabelMouseReleased(evt);
            }
        });

        detailAddRepairLabel.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        detailAddRepairLabel.setForeground(new java.awt.Color(255, 255, 255));
        detailAddRepairLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        detailAddRepairLabel.setText("Add for Repair");
        detailAddRepairLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                detailAddRepairLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                detailAddRepairLabelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                detailAddRepairLabelMousePressed(evt);
            }
        });

        detailMakeBillLabel.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        detailMakeBillLabel.setForeground(new java.awt.Color(255, 255, 255));
        detailMakeBillLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        detailMakeBillLabel.setText("Make Bill");
        detailMakeBillLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                detailMakeBillLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                detailMakeBillLabelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                detailMakeBillLabelMousePressed(evt);
            }
        });

        detailAccountUpdateLabel.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        detailAccountUpdateLabel.setForeground(new java.awt.Color(255, 255, 255));
        detailAccountUpdateLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        detailAccountUpdateLabel.setText("Account Update");
        detailAccountUpdateLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                detailAccountUpdateLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                detailAccountUpdateLabelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                detailAccountUpdateLabelMousePressed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("D&D Service Center");

        logoutLabel2.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        logoutLabel2.setForeground(new java.awt.Color(255, 255, 255));
        logoutLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoutLabel2.setText("Log out");
        logoutLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutLabel2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                logoutLabel2MousePressed(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel2Layout = new javax.swing.GroupLayout(kGradientPanel2);
        kGradientPanel2.setLayout(kGradientPanel2Layout);
        kGradientPanel2Layout.setHorizontalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logoutLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(detailRegisterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(detailAddRepairLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(detailMakeBillLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(kGradientPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(detailImageLabel))
                    .addComponent(detailAccountUpdateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        kGradientPanel2Layout.setVerticalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(detailImageLabel)
                .addGap(18, 18, 18)
                .addComponent(detailRegisterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(detailAddRepairLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(detailMakeBillLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(detailAccountUpdateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(logoutLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout viewDetailFrameLayout = new javax.swing.GroupLayout(viewDetailFrame.getContentPane());
        viewDetailFrame.getContentPane().setLayout(viewDetailFrameLayout);
        viewDetailFrameLayout.setHorizontalGroup(
            viewDetailFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewDetailFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(kGradientPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        viewDetailFrameLayout.setVerticalGroup(
            viewDetailFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(kGradientPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        updateFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        updateFrame.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        updateFrame.setResizable(false);

        jPanel4.setBackground(new java.awt.Color(255, 102, 0));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setText("Number Plate");

        jLabel21.setText("Owner Name");

        jLabel22.setText("Two wheeler Type");

        jLabel23.setText("Chasis number");

        jLabel24.setText("Contact");

        jLabel26.setText("Email");

        jLabel27.setText("Engine number");

        jLabel28.setText("Model");

        numberPlateTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberPlateTextFieldActionPerformed(evt);
            }
        });

        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(109, 109, 109)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(engineNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chasisNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(contactTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bikeModelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bikeTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ownerNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(numberPlateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(507, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(numberPlateTextField)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ownerNameTextField))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bikeTypeTextField))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bikeModelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(contactTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chasisNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(engineNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(71, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );

        kGradientPanel4.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel4.setkStartColor(new java.awt.Color(0, 0, 0));

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("D&D Service Center");

        ImageLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ImageLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/HomeLogo (2).jpg"))); // NOI18N
        ImageLabel1.setToolTipText("");
        ImageLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        registerLabel1.setBackground(new java.awt.Color(255, 255, 255));
        registerLabel1.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        registerLabel1.setForeground(new java.awt.Color(255, 255, 255));
        registerLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        registerLabel1.setText("Bike Register");
        registerLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerLabel1MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                registerLabel1MouseReleased(evt);
            }
        });

        addRepairLabel1.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        addRepairLabel1.setForeground(new java.awt.Color(255, 255, 255));
        addRepairLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addRepairLabel1.setText("Add for Repair");
        addRepairLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addRepairLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addRepairLabel1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                addRepairLabel1MousePressed(evt);
            }
        });

        makeBillLabel1.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        makeBillLabel1.setForeground(new java.awt.Color(255, 255, 255));
        makeBillLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        makeBillLabel1.setText("Make Bill");
        makeBillLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                makeBillLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                makeBillLabel1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                makeBillLabel1MousePressed(evt);
            }
        });

        accountUpdateLabel1.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        accountUpdateLabel1.setForeground(new java.awt.Color(255, 255, 255));
        accountUpdateLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        accountUpdateLabel1.setText("Account Update");
        accountUpdateLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                accountUpdateLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                accountUpdateLabel1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                accountUpdateLabel1MousePressed(evt);
            }
        });

        logoutLabel1.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        logoutLabel1.setForeground(new java.awt.Color(255, 255, 255));
        logoutLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoutLabel1.setText("Log out");
        logoutLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutLabel1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                logoutLabel1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel4Layout = new javax.swing.GroupLayout(kGradientPanel4);
        kGradientPanel4.setLayout(kGradientPanel4Layout);
        kGradientPanel4Layout.setHorizontalGroup(
            kGradientPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addGap(18, 18, 18))
            .addGroup(kGradientPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(kGradientPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logoutLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addRepairLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(registerLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(makeBillLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(kGradientPanel4Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(ImageLabel1))
                    .addComponent(accountUpdateLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        kGradientPanel4Layout.setVerticalGroup(
            kGradientPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel4Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(ImageLabel1)
                .addGap(30, 30, 30)
                .addComponent(registerLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(addRepairLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(makeBillLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(accountUpdateLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(logoutLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(279, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout updateFrameLayout = new javax.swing.GroupLayout(updateFrame.getContentPane());
        updateFrame.getContentPane().setLayout(updateFrameLayout);
        updateFrameLayout.setHorizontalGroup(
            updateFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateFrameLayout.createSequentialGroup()
                .addComponent(kGradientPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        updateFrameLayout.setVerticalGroup(
            updateFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(kGradientPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 102, 0));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Bike Registeration");

        jTabbedPane1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        ownerName.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        ownerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ownerNameActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setText("Contact");

        bikeType.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        bikeType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "    ", "Scooter", "Bike" }));
        bikeType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bikeTypeActionPerformed(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jSeparator1.setDoubleBuffered(true);
        jSeparator1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        registerButton.setText("Register");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        bikeModel.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        bikeModel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bikeModelActionPerformed(evt);
            }
        });

        contact.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        contact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel17.setText("Two-Wheeler type");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel18.setText("Bike model");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setText("Bike Number Plate");

        numberPlate.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        numberPlate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberPlateActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("Chasis number");

        email.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel10.setText("Email");

        chasisNumber.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        chasisNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chasisNumberActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel11.setText("Engine number");

        engineNumber.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        engineNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                engineNumberActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel16.setText("Owner Name");

        javax.swing.GroupLayout registerationPanelLayout = new javax.swing.GroupLayout(registerationPanel);
        registerationPanel.setLayout(registerationPanelLayout);
        registerationPanelLayout.setHorizontalGroup(
            registerationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(registerationPanelLayout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(registerationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registerationPanelLayout.createSequentialGroup()
                        .addGroup(registerationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(registerationPanelLayout.createSequentialGroup()
                                .addGap(410, 410, 410)
                                .addComponent(jLabel12))
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(558, Short.MAX_VALUE))
                    .addGroup(registerationPanelLayout.createSequentialGroup()
                        .addGroup(registerationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(registerationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(registerationPanelLayout.createSequentialGroup()
                                    .addGroup(registerationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                                    .addGap(1, 1, 1))
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(registerationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(registerationPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 685, Short.MAX_VALUE))
                            .addGroup(registerationPanelLayout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addGroup(registerationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bikeType, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ownerName, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bikeModel, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chasisNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(numberPlate, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(contact, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(engineNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 410, Short.MAX_VALUE))))))
        );
        registerationPanelLayout.setVerticalGroup(
            registerationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerationPanelLayout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(registerationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ownerName, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addGroup(registerationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bikeType, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(registerationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bikeModel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(registerationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(contact, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(registerationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(numberPlate, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(registerationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(registerationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chasisNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(registerationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(engineNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
        );

        jTabbedPane1.addTab("Registeration", registerationPanel);

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Owner Name", "Number Plate", "Model", "Email", "Contact"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(3).setMinWidth(250);
        }

        searchBar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        searchBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBarActionPerformed(evt);
            }
        });
        searchBar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchBarKeyReleased(evt);
            }
        });

        updateButton.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        detailButton.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        detailButton.setText("View details");
        detailButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout recordPanelLayout = new javax.swing.GroupLayout(recordPanel);
        recordPanel.setLayout(recordPanelLayout);
        recordPanelLayout.setHorizontalGroup(
            recordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1045, Short.MAX_VALUE)
            .addGroup(recordPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(detailButton, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        recordPanelLayout.setVerticalGroup(
            recordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, recordPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(recordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchBar)
                    .addComponent(updateButton, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(detailButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Record", recordPanel);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        setModalExclusionType(null);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kGradientPanel1.setkBorderRadius(0);
        kGradientPanel1.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel1.setkStartColor(new java.awt.Color(0, 112, 118));

        registerLabel.setBackground(new java.awt.Color(255, 255, 255));
        registerLabel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        registerLabel.setForeground(new java.awt.Color(255, 255, 255));
        registerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        registerLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_dirt_bike_50px_4.png"))); // NOI18N
        registerLabel.setText("Bike Register");
        registerLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registerLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerLabelMouseExited(evt);
            }
        });

        addRepairLabel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        addRepairLabel.setForeground(new java.awt.Color(255, 255, 255));
        addRepairLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addRepairLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_wrench_30px.png"))); // NOI18N
        addRepairLabel.setText("Add for Repair");
        addRepairLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addRepairLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addRepairLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addRepairLabelMouseExited(evt);
            }
        });

        makeBillLabel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        makeBillLabel.setForeground(new java.awt.Color(255, 255, 255));
        makeBillLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        makeBillLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_bill_64px.png"))); // NOI18N
        makeBillLabel.setText("Make Bill");
        makeBillLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                makeBillLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                makeBillLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                makeBillLabelMouseExited(evt);
            }
        });

        accountUpdateLabel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        accountUpdateLabel.setForeground(new java.awt.Color(255, 255, 255));
        accountUpdateLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        accountUpdateLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_account_32px.png"))); // NOI18N
        accountUpdateLabel.setText("    Account");
        accountUpdateLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                accountUpdateLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                accountUpdateLabelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                accountUpdateLabelMousePressed(evt);
            }
        });

        logoutLabel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        logoutLabel.setForeground(new java.awt.Color(255, 255, 255));
        logoutLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoutLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Logout_24px.png"))); // NOI18N
        logoutLabel.setText("Log out");
        logoutLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutLabelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                logoutLabelMousePressed(evt);
            }
        });

        ImageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ImageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/HomeLogo (2).jpg"))); // NOI18N
        ImageLabel.setToolTipText("");
        ImageLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        accountUpdateLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        accountUpdateLabel2.setForeground(new java.awt.Color(255, 255, 255));
        accountUpdateLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        accountUpdateLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_product_management_64px.png"))); // NOI18N
        accountUpdateLabel2.setText("Product");
        accountUpdateLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                accountUpdateLabel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                accountUpdateLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                accountUpdateLabel2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                accountUpdateLabel2MousePressed(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(ImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(registerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(addRepairLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(makeBillLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(logoutLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(accountUpdateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(accountUpdateLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(ImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(registerLabel)
                .addGap(30, 30, 30)
                .addComponent(addRepairLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(makeBillLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(accountUpdateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(accountUpdateLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(logoutLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(kGradientPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 104, 280, 720));

        kGradientPanel3.setkBorderRadius(0);
        kGradientPanel3.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel3.setkGradientFocus(1500);
        kGradientPanel3.setkStartColor(new java.awt.Color(0, 112, 118));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Close_64px_1.png"))); // NOI18N
        jLabel29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel29MouseClicked(evt);
            }
        });

        timeLabel.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        timeLabel.setForeground(new java.awt.Color(255, 255, 255));
        timeLabel.setText("Time");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("D & D Service Center");

        titleLabel.setBackground(new java.awt.Color(255, 255, 255));
        titleLabel.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Title");
        titleLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                titleLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                titleLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                titleLabelMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                titleLabelMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel3Layout = new javax.swing.GroupLayout(kGradientPanel3);
        kGradientPanel3.setLayout(kGradientPanel3Layout);
        kGradientPanel3Layout.setHorizontalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 489, Short.MAX_VALUE)
                .addComponent(jLabel29)
                .addGap(24, 24, 24))
        );
        kGradientPanel3Layout.setVerticalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel29)
                    .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel3Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(kGradientPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1510, 110));

        jDesktopPane1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1230, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );

        getContentPane().add(jDesktopPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, 1230, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addRepairLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addRepairLabelMouseEntered
        // TODO add your handling code here:
        addRepairLabel.setForeground(Color.BLACK);
    }//GEN-LAST:event_addRepairLabelMouseEntered

    private void addRepairLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addRepairLabelMouseExited
        // TODO add your handling code here:
        addRepairLabel.setForeground(Color.white);
    }//GEN-LAST:event_addRepairLabelMouseExited

    private void makeBillLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_makeBillLabelMouseEntered
        // TODO add your handling code here:
        makeBillLabel.setForeground(Color.BLACK);
    }//GEN-LAST:event_makeBillLabelMouseEntered

    private void makeBillLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_makeBillLabelMouseExited
        // TODO add your handling code here:
        makeBillLabel.setForeground(Color.white);
    }//GEN-LAST:event_makeBillLabelMouseExited

    private void accountUpdateLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accountUpdateLabelMouseEntered
        accountUpdateLabel.setForeground(Color.black);        // TODO add your handling code here:
    }//GEN-LAST:event_accountUpdateLabelMouseEntered

    private void accountUpdateLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accountUpdateLabelMouseExited
        accountUpdateLabel.setForeground(Color.white); // TODO add your handling code here:
    }//GEN-LAST:event_accountUpdateLabelMouseExited

    private void accountUpdateLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accountUpdateLabelMousePressed
        accountUpdate();
    }//GEN-LAST:event_accountUpdateLabelMousePressed

    private void detailAddRepairLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detailAddRepairLabelMouseEntered
        
        detailAddRepairLabel.setForeground(Color.BLACK);// TODO add your handling code here:
    }//GEN-LAST:event_detailAddRepairLabelMouseEntered

    private void detailAddRepairLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detailAddRepairLabelMouseExited
        detailAddRepairLabel.setForeground(Color.WHITE);// TODO add your handling code here:
    }//GEN-LAST:event_detailAddRepairLabelMouseExited

    private void detailAddRepairLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detailAddRepairLabelMousePressed
        viewDetailFrame.dispose();
        AddToRepairPage frame = new AddToRepairPage(user, pw);
        frame.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_detailAddRepairLabelMousePressed

    private void detailMakeBillLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detailMakeBillLabelMouseEntered
        detailMakeBillLabel.setForeground(Color.BLACK);// TODO add your handling code here:
    }//GEN-LAST:event_detailMakeBillLabelMouseEntered

    private void detailMakeBillLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detailMakeBillLabelMouseExited
        detailMakeBillLabel.setForeground(Color.WHITE);// TODO add your handling code here:
    }//GEN-LAST:event_detailMakeBillLabelMouseExited

    private void detailMakeBillLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detailMakeBillLabelMousePressed
        viewDetailFrame.dispose();
        MakeBill frame = new MakeBill(user, pw);
        frame.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_detailMakeBillLabelMousePressed

    private void detailAccountUpdateLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detailAccountUpdateLabelMouseEntered
        detailAccountUpdateLabel.setForeground(Color.BLACK);// TODO add your handling code here:
    }//GEN-LAST:event_detailAccountUpdateLabelMouseEntered

    private void detailAccountUpdateLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detailAccountUpdateLabelMouseExited
        detailAccountUpdateLabel.setForeground(Color.WHITE);// TODO add your handling code here:
    }//GEN-LAST:event_detailAccountUpdateLabelMouseExited

    private void detailAccountUpdateLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detailAccountUpdateLabelMousePressed
        viewDetailFrame.dispose();
        try {
            new Account(user,pw).setVisible(true);        // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_detailAccountUpdateLabelMousePressed

    private void numberPlateTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numberPlateTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numberPlateTextFieldActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        try {
            int row = jTable1.getSelectedRow();
            String data = jTable1.getModel().getValueAt(row, 1).toString();
            System.out.println(data);

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "");
            if (con != null) {
                if (!ownerNameTextField.getText().isEmpty()) {
                    stmt = con.createStatement();
                    String sql = "Update bikeinfo set ownername='" + ownerNameTextField.getText() + "' where numberplate='" + data + "'";
                    int result = stmt.executeUpdate(sql);
                    if (result != -1){
                        System.out.println("Updated");
                    }
                }
                if (!contactTextField.getText().isEmpty()) {
                    stmt = con.createStatement();
                    String sql = "Update bikeinfo set contact='" + contactTextField.getText() + "' where numberplate='" + data + "'";
                    int result = stmt.executeUpdate(sql);
                    if (result != -1){
                        System.out.println("Updated");
                    }
                }
                if (!emailTextField.getText().isEmpty()) {
                    stmt = con.createStatement();
                    String sql = "Update bikeinfo set email='" + emailTextField.getText() + "' where numberplate='" + data + "'";
                    int result = stmt.executeUpdate(sql);
                    if (result != -1){
                        System.out.println("Updated");
                    }
                }
                if (!chasisNumberTextField.getText().isEmpty()) {
                    stmt = con.createStatement();
                    String sql = "Update bikeinfo set chasisnumber='" + chasisNumberTextField.getText() + "' where numberplate='" + data + "'";
                    int result = stmt.executeUpdate(sql);
                    if (result != -1){
                        System.out.println("Updated");
                    }
                }
                if (!engineNumberTextField.getText().isEmpty()) {
                    stmt = con.createStatement();
                    String sql = "Update bikeinfo set enginenumber='" + engineNumberTextField.getText() + "' where numberplate='" + data + "'";
                    int result = stmt.executeUpdate(sql);
                    if (result != -1){
                        System.out.println("Updated");
                    }
                }

                if (!numberPlateTextField.getText().isEmpty()) {
                    stmt = con.createStatement();
                    String sql = "Update bikeinfo set numberplate='" + numberPlateTextField.getText() + "' where numberplate='" + data + "'";
                    int result = stmt.executeUpdate(sql);
                    if (result != -1){
                        System.out.println("Updated");
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }// TODO add your handling code here:
    }//GEN-LAST:event_updateActionPerformed

    private void detailRegisterLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detailRegisterLabelMouseEntered
      detailRegisterLabel.setForeground(Color.BLACK);// TODO add your handling code here:
    }//GEN-LAST:event_detailRegisterLabelMouseEntered

    private void detailRegisterLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detailRegisterLabelMouseExited
        // TODO add your handling code here:
        detailRegisterLabel.setForeground(Color.WHITE);
    }//GEN-LAST:event_detailRegisterLabelMouseExited

    private void detailRegisterLabelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detailRegisterLabelMouseReleased
        viewDetailFrame.dispose();
        
        try {
            new HomePage(user,pw).setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_detailRegisterLabelMouseReleased

    private void registerLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerLabel1MouseEntered
        registerLabel1.setForeground(Color.BLACK);        // TODO add your handling code here:
    }//GEN-LAST:event_registerLabel1MouseEntered

    private void registerLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerLabel1MouseExited
        // TODO add your handling code here:
        registerLabel1.setForeground(Color.WHITE); 
    }//GEN-LAST:event_registerLabel1MouseExited

    private void registerLabel1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerLabel1MouseReleased
        // TODO add your handling code here:
        updateFrame.dispose();
        try {
            new HomePage(user,pw).setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_registerLabel1MouseReleased

    private void addRepairLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addRepairLabel1MouseEntered
        // TODO add your handling code here:
        addRepairLabel1.setForeground(Color.BLACK);
    }//GEN-LAST:event_addRepairLabel1MouseEntered

    private void addRepairLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addRepairLabel1MouseExited
        // TODO add your handling code here:
        addRepairLabel1.setForeground(Color.WHITE);
    }//GEN-LAST:event_addRepairLabel1MouseExited

    private void addRepairLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addRepairLabel1MousePressed
        // TODO add your handling code here:
        updateFrame.dispose();
        new AddToRepairPage(user,pw).setVisible(true);
    }//GEN-LAST:event_addRepairLabel1MousePressed

    private void makeBillLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_makeBillLabel1MouseEntered
        // TODO add your handling code here:
        makeBillLabel1.setForeground(Color.BLACK);
    }//GEN-LAST:event_makeBillLabel1MouseEntered

    private void makeBillLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_makeBillLabel1MouseExited
        // TODO add your handling code here:
        makeBillLabel1.setForeground(Color.WHITE);
    }//GEN-LAST:event_makeBillLabel1MouseExited

    private void makeBillLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_makeBillLabel1MousePressed
        // TODO add your handling code here:
        updateFrame.dispose();
        new MakeBill(user,pw).setVisible(true);
    }//GEN-LAST:event_makeBillLabel1MousePressed

    private void accountUpdateLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accountUpdateLabel1MouseEntered
        // TODO add your handling code here:
        accountUpdateLabel1.setForeground(Color.BLACK);
    }//GEN-LAST:event_accountUpdateLabel1MouseEntered

    private void accountUpdateLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accountUpdateLabel1MouseExited
        // TODO add your handling code here:
        accountUpdateLabel1.setForeground(Color.WHITE);
    }//GEN-LAST:event_accountUpdateLabel1MouseExited

    private void accountUpdateLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accountUpdateLabel1MousePressed
        // TODO add your handling code here:
         updateFrame.dispose();
        try {
            new Account(user,pw).setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_accountUpdateLabel1MousePressed

    private void logoutLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutLabelMouseEntered
        // TODO add your handling code here:
        logoutLabel.setForeground(Color.BLACK);
        
    }//GEN-LAST:event_logoutLabelMouseEntered

    private void logoutLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutLabelMouseExited
        // TODO add your handling code here:
        logoutLabel.setForeground(Color.WHITE);
    }//GEN-LAST:event_logoutLabelMouseExited

    private void logoutLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutLabelMousePressed
        // TODO add your handling code here:
        this.dispose();
        new FLogin().setVisible(true);
    }//GEN-LAST:event_logoutLabelMousePressed

    private void logoutLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutLabel1MouseEntered
        logoutLabel1.setForeground(Color.BLACK);// TODO add your handling code here:
    }//GEN-LAST:event_logoutLabel1MouseEntered

    private void logoutLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutLabel1MouseExited
        // TODO add your handling code here:
        logoutLabel1.setForeground(Color.WHITE);
    }//GEN-LAST:event_logoutLabel1MouseExited

    private void logoutLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutLabel1MousePressed
        // TODO add your handling code here:
        updateFrame.dispose();
        new FLogin().setVisible(true);
    }//GEN-LAST:event_logoutLabel1MousePressed

    private void logoutLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutLabel2MouseEntered
                // TODO add your handling code here:
                logoutLabel2.setForeground(Color.BLACK);
    }//GEN-LAST:event_logoutLabel2MouseEntered

    private void logoutLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutLabel2MouseExited
        // TODO add your handling code here:
         logoutLabel2.setForeground(Color.BLACK);
    }//GEN-LAST:event_logoutLabel2MouseExited

    private void logoutLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutLabel2MousePressed
        // TODO add your handling code here:
        viewDetailFrame.dispose();
        new FLogin().setVisible(true);
    }//GEN-LAST:event_logoutLabel2MousePressed

    private void detailButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailButtonActionPerformed

        this.dispose();
        //        jTable1.changeSelection(0, 0, false, false);
        int row = jTable1.getSelectedRow();
        System.out.println(row);
        if(row==-1){
            row=0;
        }
        String data = jTable1.getModel().getValueAt(row, 1).toString();

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "");
            stmt = con.createStatement();
            if (con != null) {
                String sql = "Select * from bikeinfo where numberplate='" + data + "'";
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.first()) {
                    numberPlateLabel.setText(rs.getString(1));
                    ownerNameLabel.setText(rs.getString(2));
                    bikeTypeLabel.setText(rs.getString(3));
                    bikeModelLabel.setText(rs.getString(4));
                    contactLabel.setText(rs.getString(5));
                    emailLabel.setText(rs.getString(6));
                    chasisNumberLabel.setText(rs.getString(7));
                    engineNumberLabel.setText(rs.getString(8));
                    registerDateLabel.setText("Rsgisterd on: " + rs.getString(9));
                }
            } else {
                System.out.println("Database Not connected");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }

        viewDetailFrame.setSize(1469, 818);
        viewDetailFrame.setLocationRelativeTo(null);
        viewDetailFrame.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_detailButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        this.dispose();
        jTable1.changeSelection(0, 0, false, false);
        int row = jTable1.getSelectedRow();
        if(row==-1){
            row=0;
        }
        String data = jTable1.getModel().getValueAt(row, 1).toString();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "");
            stmt = con.createStatement();
            if (con != null) {
                String sql1 = "Select * from bikeinfo where numberplate='" + data + "'";
                ResultSet rs = stmt.executeQuery(sql1);
                if (rs.first()) {
                    numberPlateTextField.setText(rs.getString(1));
                    ownerNameTextField.setText(rs.getString(2));
                    bikeTypeTextField.setText(rs.getString(3));
                    bikeModelTextField.setText(rs.getString(4));
                    contactTextField.setText(rs.getString(5));
                    emailTextField.setText(rs.getString(6));
                    chasisNumberTextField.setText(rs.getString(7));
                    engineNumberTextField.setText(rs.getString(8));
                }
            } else {
                System.out.println("Database Not connected");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
        updateFrame.setSize(1469, 818);
        updateFrame.setLocationRelativeTo(null);
        updateFrame.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_updateButtonActionPerformed

    private void searchBarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchBarKeyReleased
        // TODO add your handling code here:
        search(jTable1);
    }//GEN-LAST:event_searchBarKeyReleased

    private void searchBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBarActionPerformed
        searchBar.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_searchBarActionPerformed

    private void engineNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_engineNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_engineNumberActionPerformed

    private void chasisNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chasisNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chasisNumberActionPerformed

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void numberPlateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numberPlateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numberPlateActionPerformed

    private void contactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contactActionPerformed

    private void bikeModelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bikeModelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bikeModelActionPerformed

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String ownername = ownerName.getText();
            String numberplate = numberPlate.getText();
            String biketype = bikeType.getSelectedItem().toString();
            String bikemodel = bikeModel.getSelectedItem().toString();
            String Contact = contact.getText();
            String Email = email.getText();
            String chasisnumber = chasisNumber.getText();
            String enginenumber = engineNumber.getText();
            System.out.println(ownername + " " + numberplate + " " + biketype + " " + bikemodel + " " + Contact + " " + Email + " " + chasisnumber + " " + enginenumber);
            con = DriverManager.getConnection(url, "root", "");
            if (con != null) {
                System.out.println("Database connected");
                stmt = con.createStatement();
                String sql = "Insert into bikeinfo (numberplate,ownername,biketype,bikemodel,contact,email,chasisnumber,enginenumber) Values('" + numberplate + "','" + ownername + "','" + biketype + "','" + bikemodel + "','" + Contact + "','" + Email + "','" + chasisnumber + "','" + enginenumber + "')";
                int result;
                result = stmt.executeUpdate(sql);
                if (result != -1) {
                    JOptionPane.showMessageDialog(this, "Register message", "Resgistered", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    new HomePage(user,pw).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Sorry couldnt Register", "Register messahe", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                System.out.println("Database connection failed");
            }

            //        JOptionPane j=new JOptionPane();
            //        int res = JOptionPane.showOptionDialog(this, "Registered", "Registered Message", JOptionPane.DEFAULT_OPTION,
                //        JOptionPane.INFORMATION_MESSAGE, null, null, null);
            //        if(res!=-1){
                //        this.dispose();
                //        new HomePage().setVisible(true);
                //        }
        } catch (SQLException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_registerButtonActionPerformed

    private void bikeTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bikeTypeActionPerformed
        // TODO add your handling code here:
        if (bikeType.getSelectedItem().toString().equals("Bike")) {
            bikeModel.removeAllItems();
            bikeModel.addItem("Fz-V2");
            bikeModel.addItem("R15");
            bikeModel.addItem("Mt-15");
            bikeModel.addItem("FZ-V3");
        }
        if (bikeType.getSelectedItem().toString().equals("Scooter")) {
            bikeModel.removeAllItems();
            bikeModel.addItem("Fascino");
            bikeModel.addItem("RayZr");
            bikeModel.addItem("Alpha");
        }
    }//GEN-LAST:event_bikeTypeActionPerformed

    private void ownerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ownerNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ownerNameActionPerformed

    private void jLabel29MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel29MouseClicked
        System.exit(0);  
    }//GEN-LAST:event_jLabel29MouseClicked

    private void addRepairLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addRepairLabelMouseClicked
        addToRepair();
    }//GEN-LAST:event_addRepairLabelMouseClicked
    public void addToRepair(){
        AddToRepair addToRepair=new AddToRepair();
        jDesktopPane1.removeAll();
        jDesktopPane1.add(addToRepair).setVisible(true);
        titleLabel.setText(addRepairLabel.getText());
        titleLabel.setIcon(addRepairLabel.getIcon());
    }
    private void makeBillLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_makeBillLabelMouseClicked
        makeBill();
    }//GEN-LAST:event_makeBillLabelMouseClicked
    public void makeBill(){
        FMakeBill makeBill=new FMakeBill();
        jDesktopPane1.removeAll();
        jDesktopPane1.add(makeBill).setVisible(true);
        titleLabel.setText(makeBillLabel.getText());
        titleLabel.setIcon(makeBillLabel.getIcon());
    }
    private void registerLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerLabelMouseExited
        // TODO add your handling code here:
        registerLabel.setForeground(Color.white);
    }//GEN-LAST:event_registerLabelMouseExited

    private void registerLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerLabelMouseEntered
        // TODO add your handling code here:
        registerLabel.setForeground(Color.BLACK);
    }//GEN-LAST:event_registerLabelMouseEntered

    private void registerLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerLabelMouseClicked
        register();
    }//GEN-LAST:event_registerLabelMouseClicked

    private void titleLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titleLabelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_titleLabelMouseClicked

    private void titleLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titleLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_titleLabelMouseEntered

    private void titleLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titleLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_titleLabelMouseExited

    private void titleLabelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titleLabelMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_titleLabelMouseReleased

    private void accountUpdateLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accountUpdateLabel2MouseEntered
       accountUpdateLabel2.setForeground(Color.BLACK);
    }//GEN-LAST:event_accountUpdateLabel2MouseEntered

    private void accountUpdateLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accountUpdateLabel2MouseExited
       accountUpdateLabel2.setForeground(Color.WHITE);
    }//GEN-LAST:event_accountUpdateLabel2MouseExited

    private void accountUpdateLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accountUpdateLabel2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_accountUpdateLabel2MousePressed

    private void accountUpdateLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accountUpdateLabel2MouseClicked
        Product obj=new Product();
        jDesktopPane1.removeAll();
        jDesktopPane1.add(obj).setVisible(true);
        titleLabel.setText(accountUpdateLabel2.getText());
        titleLabel.setIcon(accountUpdateLabel2.getIcon());
    }//GEN-LAST:event_accountUpdateLabel2MouseClicked

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
            UIManager.put( "TabbedPane.tabSeparatorsFullHeight", true );
        //</editor-fold>u
            java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new HomePage().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    public void search(JTable jtable) {
        try {
            String sql = "Select  ownername,bikemodel,contact,email,numberplate from bikeinfo where ownername like '" + searchBar.getText() + "%' Order by ownername"; //e.g P type garepachi pachadi j bhaye ni huncha bhaneko 
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            DefaultTableModel dtm = (DefaultTableModel) jtable.getModel();
            dtm.setRowCount(0);//Removing all the row in Jtable
            while(rs.next()){
            String name = rs.getString("ownername");
                String model = rs.getString("bikemodel");
                String contact = rs.getString("contact");
                String email = rs.getString("email");
                String numberplate = rs.getString("numberplate");
                String data[] = {name, numberplate, model, email, contact};
                tblmodel = (DefaultTableModel) jtable.getModel();
                tblmodel.addRow(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ImageLabel;
    private javax.swing.JLabel ImageLabel1;
    private javax.swing.JLabel accountUpdateLabel;
    private javax.swing.JLabel accountUpdateLabel1;
    private javax.swing.JLabel accountUpdateLabel2;
    private javax.swing.JLabel addRepairLabel;
    private javax.swing.JLabel addRepairLabel1;
    private javax.swing.JComboBox<String> bikeModel;
    private javax.swing.JLabel bikeModelLabel;
    private javax.swing.JTextField bikeModelTextField;
    private javax.swing.JComboBox<String> bikeType;
    private javax.swing.JLabel bikeTypeLabel;
    private javax.swing.JTextField bikeTypeTextField;
    private javax.swing.JTextField chasisNumber;
    private javax.swing.JLabel chasisNumberLabel;
    private javax.swing.JTextField chasisNumberTextField;
    private javax.swing.JTextField contact;
    private javax.swing.JLabel contactLabel;
    private javax.swing.JTextField contactTextField;
    private javax.swing.JLabel detailAccountUpdateLabel;
    private javax.swing.JLabel detailAddRepairLabel;
    private javax.swing.JButton detailButton;
    private javax.swing.JLabel detailImageLabel;
    private javax.swing.JLabel detailMakeBillLabel;
    private javax.swing.JLabel detailRegisterLabel;
    private javax.swing.JTextField email;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JTextField engineNumber;
    private javax.swing.JLabel engineNumberLabel;
    private javax.swing.JTextField engineNumberTextField;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private com.k33ptoo.components.KGradientPanel kGradientPanel1;
    private com.k33ptoo.components.KGradientPanel kGradientPanel2;
    private com.k33ptoo.components.KGradientPanel kGradientPanel3;
    private com.k33ptoo.components.KGradientPanel kGradientPanel4;
    private javax.swing.JLabel logoutLabel;
    private javax.swing.JLabel logoutLabel1;
    private javax.swing.JLabel logoutLabel2;
    private javax.swing.JLabel makeBillLabel;
    private javax.swing.JLabel makeBillLabel1;
    private javax.swing.JTextField numberPlate;
    private javax.swing.JLabel numberPlateLabel;
    private javax.swing.JTextField numberPlateTextField;
    private javax.swing.JTextField ownerName;
    private javax.swing.JLabel ownerNameLabel;
    private javax.swing.JTextField ownerNameTextField;
    private javax.swing.JPanel recordPanel;
    private javax.swing.JButton registerButton;
    private javax.swing.JLabel registerDateLabel;
    private javax.swing.JLabel registerLabel;
    private javax.swing.JLabel registerLabel1;
    private javax.swing.JPanel registerationPanel;
    private javax.swing.JTextField searchBar;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JButton update;
    private javax.swing.JButton updateButton;
    private javax.swing.JFrame updateFrame;
    private javax.swing.JFrame viewDetailFrame;
    // End of variables declaration//GEN-END:variables
}
