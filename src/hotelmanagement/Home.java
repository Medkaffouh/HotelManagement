/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagement;

import DBconnect.OracleConnect;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Medkaffouh
 */
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    ReservationUpdate rsvup = new ReservationUpdate();
    UserUpdate urup = new UserUpdate();
    ClientUpdate clup = new ClientUpdate();
    RoomsUpdate rmup = new RoomsUpdate();
    OracleConnect oc = new OracleConnect();
    Connection con = oc.getConnect();
    Statement st, st2;
    ResultSet rs, rs2, rs3, rs4, rs5, rs6, rs7, rs8, r1, r2, r3, r4, r5, r6, r7;
    PreparedStatement pst, pst1, pst2, pst3, pst4, ps1, ps2, ps3, ps4, ps5, ps6, ps7;
    DefaultTableModel dtm, dtm2, dtm3, dtm4;

    public Home() {
        initComponents();
        clickedColor(jPanel4);
        setComboboxone();
        printDataRes();
        printroomdispo();
        printDataRooms();
        printDataUser();
        printDataClient();
        setTableStyle();
    }

    public final void printAll() {
        printDataRes();
        printroomdispo();
        printDataRooms();
        printDataUser();
        printDataClient();
    }

    private void setTableStyle() {
        jTable1.getTableHeader().setFont(new Font("Segon UI", Font.BOLD, 12));
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setBackground(new Color(32, 136, 203));
        jTable1.getTableHeader().setForeground(new Color(255, 255, 255));
        jTable1.setRowHeight(25);
        jTable2.getTableHeader().setFont(new Font("Segon UI", Font.BOLD, 12));
        jTable2.getTableHeader().setOpaque(false);
        jTable2.getTableHeader().setBackground(new Color(32, 136, 203));
        jTable2.getTableHeader().setForeground(new Color(255, 255, 255));
        jTable2.setRowHeight(25);
        jTable4.getTableHeader().setFont(new Font("Segon UI", Font.BOLD, 12));
        jTable4.getTableHeader().setOpaque(false);
        jTable4.getTableHeader().setBackground(new Color(32, 136, 203));
        jTable4.getTableHeader().setForeground(new Color(255, 255, 255));
        jTable4.setRowHeight(25);
        jTable5.getTableHeader().setFont(new Font("Segon UI", Font.BOLD, 12));
        jTable5.getTableHeader().setOpaque(false);
        jTable5.getTableHeader().setBackground(new Color(32, 136, 203));
        jTable5.getTableHeader().setForeground(new Color(255, 255, 255));
        jTable5.setRowHeight(25);
    }

    private void printroomdispo() {
        try {
            String q1 = "select count(*) from rooms where status=0 and roomtype_id=100";
            String q2 = "select count(*) from rooms where status=0 and roomtype_id=110";
            String q3 = "select count(*) from rooms where status=0 and roomtype_id=120";
            String q4 = "select count(*) from rooms where status=0 and roomtype_id=130";
            String q5 = "select count(*) from rooms where status=0 and roomtype_id=140";
            String q6 = "select count(*) from rooms where status=0 and roomtype_id=150";
            String q7 = "select count(*) from rooms where status=0";
            ps1 = con.prepareStatement(q1);
            ps2 = con.prepareStatement(q2);
            ps3 = con.prepareStatement(q3);
            ps4 = con.prepareStatement(q4);
            ps5 = con.prepareStatement(q5);
            ps6 = con.prepareStatement(q6);
            ps7 = con.prepareStatement(q7);
            r1 = ps1.executeQuery();
            r2 = ps2.executeQuery();
            r3 = ps3.executeQuery();
            r4 = ps4.executeQuery();
            r5 = ps5.executeQuery();
            r6 = ps6.executeQuery();
            r7 = ps7.executeQuery();
            while (r1.next() && r2.next() && r3.next() && r4.next() && r5.next() && r6.next() && r7.next()) {
                jLabel29.setText(r1.getString(1));
                jLabel32.setText(r2.getString(1));
                jLabel30.setText(r3.getString(1));
                jLabel33.setText(r4.getString(1));
                jLabel31.setText(r5.getString(1));
                jLabel34.setText(r6.getString(1));
                jLabel36.setText(r7.getString(1));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void printDataRes() {
        dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);
        try {
            String query = "select res_id,to_char(date_in,'dd-MON-yyyy'),"
                    + "to_char(date_out,'dd-MON-yyyy'),customer_id,user_id,"
                    + "roomtype_id,room_id from reservation";
            st = con.createStatement();
            rs2 = st.executeQuery(query);
            while (rs2.next()) {
                dtm.addRow(new Object[]{rs2.getString(1), rs2.getString(2), rs2.getString(3),
                    rs2.getString(4), rs2.getString(6), rs2.getString(7), rs2.getString(5)});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "probleme de afficher les reservations " + e);
        }
    }

    private void printDataRooms() {
        dtm2 = (DefaultTableModel) jTable2.getModel();
        dtm2.setRowCount(0);
        try {
            String query2 = "select room_id,decode(status,0,'disponible',1,'occupé'),phone_number,roomtype_id,hotel_id from rooms";
            st = con.createStatement();
            rs4 = st.executeQuery(query2);
            while (rs4.next()) {
                dtm2.addRow(new Object[]{rs4.getString(1), rs4.getString(2), rs4.getString(3),
                    rs4.getString(4), rs4.getString(5)});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "probleme de afficher les chambres info " + e);
        }
    }

    private void printDataClient() {
        dtm3 = (DefaultTableModel) jTable4.getModel();
        dtm3.setRowCount(0);
        try {
            String query3 = "select * from customers";
            st = con.createStatement();
            rs6 = st.executeQuery(query3);
            while (rs6.next()) {
                dtm3.addRow(new Object[]{rs6.getString(1), rs6.getString(2), rs6.getString(3),
                    rs6.getString(4), rs6.getString(5), rs6.getString(6), rs6.getString(7),
                    rs6.getString(8)});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void printDataUser() {
        dtm4 = (DefaultTableModel) jTable5.getModel();
        dtm4.setRowCount(0);
        try {
            String query4 = "select user_id,first_name,last_name,phone_number,email,"
                    + "to_char(dateofbirth,'dd-MON-yyyy'),decode(isadmin,0,'Réception',1,'Admin'),password,qcheck,admin_id"
                    + " from users";
            st = con.createStatement();
            rs5 = st.executeQuery(query4);
            while (rs5.next()) {
                dtm4.addRow(new Object[]{rs5.getString(1), rs5.getString(2), rs5.getString(3),
                    rs5.getString(4), rs5.getString(5), rs5.getString(6), rs5.getString(7),
                    rs5.getString(8), rs5.getString(9), rs5.getString(10)});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void setComboboxone() {
        try {

            String sql1 = "select type from roomtypes";
            st = con.createStatement();
            rs = st.executeQuery(sql1);
            while (rs.next()) {
                jComboBox1.addItem(rs.getString(1));
                jComboBox2.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public static void setColor(JPanel p) {
        p.setBackground(new Color(204, 204, 204));
    }

    public static void resetColor(JPanel p) {
        p.setBackground(new Color(240, 240, 240));
    }

    public static void clickedColor(JPanel p) {
        p.setBackground(new Color(0, 204, 0));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel30 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jPanel44 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPnl1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPnl2 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel23 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPnl3 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jTextField6 = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jButton19 = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel52 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jButton20 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jSeparator10 = new javax.swing.JSeparator();
        jPanel34 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel35 = new javax.swing.JPanel();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jPnl4 = new javax.swing.JPanel();
        jPanel39 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        jTextField8 = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jButton24 = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel57 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jButton25 = new javax.swing.JButton();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jButton29 = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JSeparator();
        jComboBox3 = new javax.swing.JComboBox<>();
        jPanel41 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jPanel42 = new javax.swing.JPanel();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();

        jPanel30.setPreferredSize(new java.awt.Dimension(141, 59));

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 141, Short.MAX_VALUE)
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 59, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setPreferredSize(new java.awt.Dimension(1200, 40));

        jLabel1.setFont(new java.awt.Font("Serif", 1, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Atlantic");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("14-11-2021");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Mini_hotel_30px.png"))); // NOI18N

        jPanel14.setBackground(new java.awt.Color(102, 102, 102));

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_exit_25px.png"))); // NOI18N
        jLabel3.setToolTipText("Se déconnecter");
        jLabel3.setPreferredSize(new java.awt.Dimension(83, 32));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel3MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(424, 424, 424)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(522, 522, 522)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/calendar_plus_grey_30px.png"))); // NOI18N
        jLabel17.setText("Réservations");
        jLabel17.setMaximumSize(new java.awt.Dimension(141, 46));
        jLabel17.setMinimumSize(new java.awt.Dimension(141, 46));
        jLabel17.setPreferredSize(new java.awt.Dimension(141, 46));
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel17MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel17MouseExited(evt);
            }
        });
        jPanel11.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 9, 141, 50));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 7, Short.MAX_VALUE)
        );

        jPanel11.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 141, -1));

        jPanel6.setPreferredSize(new java.awt.Dimension(141, 59));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_sleeping_in_bed_30px.png"))); // NOI18N
        jLabel5.setText("Chambres");
        jLabel5.setMaximumSize(new java.awt.Dimension(141, 46));
        jLabel5.setMinimumSize(new java.awt.Dimension(141, 46));
        jLabel5.setPreferredSize(new java.awt.Dimension(141, 46));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel5MouseExited(evt);
            }
        });
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 9, 141, 50));

        jPanel7.setPreferredSize(new java.awt.Dimension(141, 7));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 141, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 7, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 141, -1));

        jPanel9.setPreferredSize(new java.awt.Dimension(141, 59));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_user_group_30px.png"))); // NOI18N
        jLabel6.setText("Clients");
        jLabel6.setMaximumSize(new java.awt.Dimension(141, 46));
        jLabel6.setMinimumSize(new java.awt.Dimension(141, 46));
        jLabel6.setPreferredSize(new java.awt.Dimension(141, 46));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel6MouseExited(evt);
            }
        });
        jPanel9.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 140, 50));

        jPanel13.setMaximumSize(new java.awt.Dimension(141, 7));
        jPanel13.setMinimumSize(new java.awt.Dimension(141, 7));
        jPanel13.setPreferredSize(new java.awt.Dimension(141, 7));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 141, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 7, Short.MAX_VALUE)
        );

        jPanel9.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel28.setPreferredSize(new java.awt.Dimension(141, 59));

        jPanel36.setMaximumSize(new java.awt.Dimension(141, 7));
        jPanel36.setMinimumSize(new java.awt.Dimension(141, 7));

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 7, Short.MAX_VALUE)
        );

        jLabel37.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(102, 102, 102));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_admin_settings_male_30px.png"))); // NOI18N
        jLabel37.setText("Admin");
        jLabel37.setMaximumSize(new java.awt.Dimension(141, 46));
        jLabel37.setMinimumSize(new java.awt.Dimension(141, 46));
        jLabel37.setPreferredSize(new java.awt.Dimension(141, 46));
        jLabel37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel37MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel37MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel37MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE))
        );

        jPanel29.setPreferredSize(new java.awt.Dimension(141, 59));

        jPanel37.setMaximumSize(new java.awt.Dimension(141, 7));
        jPanel37.setMinimumSize(new java.awt.Dimension(141, 7));

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 7, Short.MAX_VALUE)
        );

        jLabel38.setMaximumSize(new java.awt.Dimension(141, 46));
        jLabel38.setMinimumSize(new java.awt.Dimension(141, 46));
        jLabel38.setPreferredSize(new java.awt.Dimension(141, 46));

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE))
        );

        jPanel31.setPreferredSize(new java.awt.Dimension(141, 59));

        jPanel38.setMaximumSize(new java.awt.Dimension(141, 7));
        jPanel38.setMinimumSize(new java.awt.Dimension(141, 7));

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 7, Short.MAX_VALUE)
        );

        jLabel39.setMaximumSize(new java.awt.Dimension(141, 46));
        jLabel39.setMinimumSize(new java.awt.Dimension(141, 46));
        jLabel39.setPreferredSize(new java.awt.Dimension(141, 46));

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE))
        );

        jLabel41.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_refresh_26px_3.png"))); // NOI18N
        jLabel41.setToolTipText("Se déconnecter");
        jLabel41.setPreferredSize(new java.awt.Dimension(83, 32));
        jLabel41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel41MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel41MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel41MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new java.awt.CardLayout());

        jPanel5.setMaximumSize(new java.awt.Dimension(367, 455));
        jPanel5.setMinimumSize(new java.awt.Dimension(367, 455));
        jPanel5.setPreferredSize(new java.awt.Dimension(367, 455));

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtrage par", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 14), new java.awt.Color(102, 102, 102))); // NOI18N
        jPanel15.setMaximumSize(new java.awt.Dimension(354, 284));
        jPanel15.setMinimumSize(new java.awt.Dimension(354, 284));
        jPanel15.setPreferredSize(new java.awt.Dimension(354, 284));

        jLabel8.setText("Type de chambre");

        jLabel9.setMaximumSize(new java.awt.Dimension(200, 14));
        jLabel9.setMinimumSize(new java.awt.Dimension(200, 14));
        jLabel9.setPreferredSize(new java.awt.Dimension(200, 14));

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jLabel7.setText("Numéro de client");
        jLabel7.setMaximumSize(new java.awt.Dimension(96, 14));
        jLabel7.setMinimumSize(new java.awt.Dimension(96, 14));
        jLabel7.setPreferredSize(new java.awt.Dimension(96, 14));

        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setMaximumSize(new java.awt.Dimension(200, 14));
        jLabel12.setMinimumSize(new java.awt.Dimension(200, 14));
        jLabel12.setPreferredSize(new java.awt.Dimension(200, 14));

        jLabel15.setMaximumSize(new java.awt.Dimension(25, 25));
        jLabel15.setMinimumSize(new java.awt.Dimension(25, 25));
        jLabel15.setName(""); // NOI18N
        jLabel15.setPreferredSize(new java.awt.Dimension(25, 25));

        jLabel16.setMaximumSize(new java.awt.Dimension(25, 25));
        jLabel16.setMinimumSize(new java.awt.Dimension(25, 25));
        jLabel16.setPreferredSize(new java.awt.Dimension(25, 25));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_search_19px.png"))); // NOI18N
        jButton4.setMaximumSize(new java.awt.Dimension(50, 25));
        jButton4.setMinimumSize(new java.awt.Dimension(50, 25));
        jButton4.setPreferredSize(new java.awt.Dimension(50, 25));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_search_19px.png"))); // NOI18N
        jButton6.setMaximumSize(new java.awt.Dimension(50, 25));
        jButton6.setMinimumSize(new java.awt.Dimension(50, 25));
        jButton6.setPreferredSize(new java.awt.Dimension(50, 25));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel13.setText("Numéro de chambre");

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });

        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setMaximumSize(new java.awt.Dimension(200, 14));
        jLabel14.setMinimumSize(new java.awt.Dimension(200, 14));
        jLabel14.setPreferredSize(new java.awt.Dimension(200, 14));

        jLabel19.setMaximumSize(new java.awt.Dimension(25, 25));
        jLabel19.setMinimumSize(new java.awt.Dimension(25, 25));
        jLabel19.setName(""); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(25, 25));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_search_19px.png"))); // NOI18N
        jButton7.setMaximumSize(new java.awt.Dimension(50, 25));
        jButton7.setMinimumSize(new java.awt.Dimension(50, 25));
        jButton7.setPreferredSize(new java.awt.Dimension(50, 25));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Afficher tout");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(97, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField2)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(22, 22, 22))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(3, 3, 3)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(1, 1, 1)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addGap(3, 3, 3)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField2)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel8.setMaximumSize(new java.awt.Dimension(868, 449));
        jPanel8.setMinimumSize(new java.awt.Dimension(868, 449));
        jPanel8.setPreferredSize(new java.awt.Dimension(868, 449));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code Reservation", "Arrivée", "Départ", "Numéro de client", "Type de chambre", "Numéro de chambre", "Réservé par"
            }
        ));
        jTable1.setFocusable(false);
        jTable1.setRowHeight(25);
        jTable1.setSelectionBackground(new java.awt.Color(0, 204, 0));
        jTable1.setShowVerticalLines(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_update_left_rotation_24px.png"))); // NOI18N
        jButton1.setText("Modifier");
        jButton1.setMaximumSize(new java.awt.Dimension(300, 30));
        jButton1.setMinimumSize(new java.awt.Dimension(300, 30));
        jButton1.setPreferredSize(new java.awt.Dimension(300, 30));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_remove_24px.png"))); // NOI18N
        jButton2.setText("Supprimer");
        jButton2.setMaximumSize(new java.awt.Dimension(300, 30));
        jButton2.setMinimumSize(new java.awt.Dimension(300, 30));
        jButton2.setPreferredSize(new java.awt.Dimension(300, 30));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_add_24px.png"))); // NOI18N
        jButton3.setText("Ajouter");
        jButton3.setMaximumSize(new java.awt.Dimension(300, 30));
        jButton3.setMinimumSize(new java.awt.Dimension(300, 30));
        jButton3.setPreferredSize(new java.awt.Dimension(300, 30));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                .addGap(110, 110, 110)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                .addGap(111, 111, 111)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addGap(69, 69, 69))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPnl1Layout = new javax.swing.GroupLayout(jPnl1);
        jPnl1.setLayout(jPnl1Layout);
        jPnl1Layout.setHorizontalGroup(
            jPnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnl1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPnl1Layout.setVerticalGroup(
            jPnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnl1Layout.createSequentialGroup()
                .addGroup(jPnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPnl1Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6)))
                .addGap(0, 0, 0)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel10.add(jPnl1, "card2");

        jPanel16.setMaximumSize(new java.awt.Dimension(367, 455));
        jPanel16.setMinimumSize(new java.awt.Dimension(367, 455));
        jPanel16.setPreferredSize(new java.awt.Dimension(367, 455));

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtrage par", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 14), new java.awt.Color(102, 102, 102))); // NOI18N

        jLabel10.setText("Type de chambre");

        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setMaximumSize(new java.awt.Dimension(200, 14));
        jLabel11.setMinimumSize(new java.awt.Dimension(200, 14));
        jLabel11.setPreferredSize(new java.awt.Dimension(200, 14));

        jLabel22.setMaximumSize(new java.awt.Dimension(25, 25));
        jLabel22.setMinimumSize(new java.awt.Dimension(25, 25));
        jLabel22.setPreferredSize(new java.awt.Dimension(25, 25));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_search_19px.png"))); // NOI18N
        jButton5.setMaximumSize(new java.awt.Dimension(50, 25));
        jButton5.setMinimumSize(new java.awt.Dimension(50, 25));
        jButton5.setPreferredSize(new java.awt.Dimension(50, 25));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel23.setText("Numéro de chambre");

        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });

        jLabel24.setForeground(new java.awt.Color(255, 0, 0));
        jLabel24.setMaximumSize(new java.awt.Dimension(200, 14));
        jLabel24.setMinimumSize(new java.awt.Dimension(200, 14));
        jLabel24.setPreferredSize(new java.awt.Dimension(200, 14));

        jLabel25.setMaximumSize(new java.awt.Dimension(25, 25));
        jLabel25.setMinimumSize(new java.awt.Dimension(25, 25));
        jLabel25.setName(""); // NOI18N
        jLabel25.setPreferredSize(new java.awt.Dimension(25, 25));

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_search_19px.png"))); // NOI18N
        jButton9.setMaximumSize(new java.awt.Dimension(50, 25));
        jButton9.setMinimumSize(new java.awt.Dimension(50, 25));
        jButton9.setPreferredSize(new java.awt.Dimension(50, 25));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField4)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18))))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(3, 3, 3)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(1, 1, 1)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel23)
                .addGap(3, 3, 3)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField4)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chambres disponibles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 14), new java.awt.Color(102, 102, 102))); // NOI18N

        jPanel21.setBackground(new java.awt.Color(204, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel21.setMaximumSize(new java.awt.Dimension(100, 50));
        jPanel21.setMinimumSize(new java.awt.Dimension(100, 50));
        jPanel21.setPreferredSize(new java.awt.Dimension(100, 50));

        jLabel18.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Single");
        jLabel18.setMaximumSize(new java.awt.Dimension(100, 20));
        jLabel18.setMinimumSize(new java.awt.Dimension(100, 20));
        jLabel18.setPreferredSize(new java.awt.Dimension(100, 20));

        jLabel29.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setMaximumSize(new java.awt.Dimension(100, 28));
        jLabel29.setMinimumSize(new java.awt.Dimension(100, 28));
        jLabel29.setPreferredSize(new java.awt.Dimension(100, 28));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel22.setBackground(new java.awt.Color(204, 255, 204));
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel22.setMaximumSize(new java.awt.Dimension(100, 50));
        jPanel22.setMinimumSize(new java.awt.Dimension(100, 50));
        jPanel22.setPreferredSize(new java.awt.Dimension(100, 50));

        jLabel20.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Queen");
        jLabel20.setMaximumSize(new java.awt.Dimension(100, 20));
        jLabel20.setMinimumSize(new java.awt.Dimension(100, 20));
        jLabel20.setPreferredSize(new java.awt.Dimension(100, 20));

        jLabel30.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setMaximumSize(new java.awt.Dimension(100, 28));
        jLabel30.setMinimumSize(new java.awt.Dimension(100, 28));
        jLabel30.setPreferredSize(new java.awt.Dimension(100, 28));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel23.setBackground(new java.awt.Color(255, 204, 204));
        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel23.setMaximumSize(new java.awt.Dimension(100, 50));
        jPanel23.setMinimumSize(new java.awt.Dimension(100, 50));
        jPanel23.setPreferredSize(new java.awt.Dimension(100, 50));

        jLabel21.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("2-Bedroom");
        jLabel21.setMaximumSize(new java.awt.Dimension(100, 20));
        jLabel21.setMinimumSize(new java.awt.Dimension(100, 20));
        jLabel21.setPreferredSize(new java.awt.Dimension(100, 20));

        jLabel31.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setMaximumSize(new java.awt.Dimension(100, 28));
        jLabel31.setMinimumSize(new java.awt.Dimension(100, 28));
        jLabel31.setPreferredSize(new java.awt.Dimension(100, 28));

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel26.setBackground(new java.awt.Color(255, 255, 204));
        jPanel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel26.setMaximumSize(new java.awt.Dimension(100, 50));
        jPanel26.setMinimumSize(new java.awt.Dimension(100, 50));
        jPanel26.setPreferredSize(new java.awt.Dimension(100, 50));

        jLabel27.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("King");
        jLabel27.setMaximumSize(new java.awt.Dimension(100, 20));
        jLabel27.setMinimumSize(new java.awt.Dimension(100, 20));
        jLabel27.setPreferredSize(new java.awt.Dimension(100, 20));

        jLabel33.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setMaximumSize(new java.awt.Dimension(100, 28));
        jLabel33.setMinimumSize(new java.awt.Dimension(100, 28));
        jLabel33.setPreferredSize(new java.awt.Dimension(100, 28));

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel27.setBackground(new java.awt.Color(255, 153, 255));
        jPanel27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel27.setMaximumSize(new java.awt.Dimension(100, 50));
        jPanel27.setMinimumSize(new java.awt.Dimension(100, 50));
        jPanel27.setPreferredSize(new java.awt.Dimension(100, 50));

        jLabel28.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Suite");
        jLabel28.setMaximumSize(new java.awt.Dimension(100, 20));
        jLabel28.setMinimumSize(new java.awt.Dimension(100, 20));
        jLabel28.setPreferredSize(new java.awt.Dimension(100, 20));

        jLabel34.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setMaximumSize(new java.awt.Dimension(100, 28));
        jLabel34.setMinimumSize(new java.awt.Dimension(100, 28));
        jLabel34.setPreferredSize(new java.awt.Dimension(100, 28));

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel24.setBackground(new java.awt.Color(204, 204, 255));
        jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel24.setMaximumSize(new java.awt.Dimension(100, 50));
        jPanel24.setMinimumSize(new java.awt.Dimension(100, 50));
        jPanel24.setPreferredSize(new java.awt.Dimension(100, 50));

        jLabel26.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Double");
        jLabel26.setMaximumSize(new java.awt.Dimension(100, 20));
        jLabel26.setMinimumSize(new java.awt.Dimension(100, 20));
        jLabel26.setPreferredSize(new java.awt.Dimension(100, 20));

        jLabel32.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setMaximumSize(new java.awt.Dimension(100, 28));
        jLabel32.setMinimumSize(new java.awt.Dimension(100, 28));
        jLabel32.setPreferredSize(new java.awt.Dimension(100, 28));

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel25.setBackground(new java.awt.Color(204, 204, 204));
        jPanel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel25.setMaximumSize(new java.awt.Dimension(164, 35));
        jPanel25.setMinimumSize(new java.awt.Dimension(164, 35));

        jLabel35.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("TOTAL :");
        jLabel35.setMaximumSize(new java.awt.Dimension(85, 33));
        jLabel35.setMinimumSize(new java.awt.Dimension(85, 33));
        jLabel35.setPreferredSize(new java.awt.Dimension(85, 33));

        jLabel36.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setMaximumSize(new java.awt.Dimension(42, 33));
        jLabel36.setMinimumSize(new java.awt.Dimension(42, 33));
        jLabel36.setPreferredSize(new java.awt.Dimension(42, 33));

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel18.setMaximumSize(new java.awt.Dimension(868, 449));
        jPanel18.setMinimumSize(new java.awt.Dimension(868, 449));
        jPanel18.setPreferredSize(new java.awt.Dimension(868, 449));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numéro de chambre", "Statut", "Téléphone", "Type de chambre", "Identifiant de l'hôtel"
            }
        ));
        jTable2.setFocusable(false);
        jTable2.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable2.setRowHeight(25);
        jTable2.setSelectionBackground(new java.awt.Color(0, 204, 0));
        jTable2.setShowVerticalLines(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
        );

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_update_left_rotation_24px.png"))); // NOI18N
        jButton10.setText("Modifier");
        jButton10.setMaximumSize(new java.awt.Dimension(300, 30));
        jButton10.setMinimumSize(new java.awt.Dimension(300, 30));
        jButton10.setPreferredSize(new java.awt.Dimension(300, 30));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_remove_24px.png"))); // NOI18N
        jButton11.setText("Supprimer");
        jButton11.setMaximumSize(new java.awt.Dimension(300, 30));
        jButton11.setMinimumSize(new java.awt.Dimension(300, 30));
        jButton11.setPreferredSize(new java.awt.Dimension(300, 30));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_add_24px.png"))); // NOI18N
        jButton12.setText("Ajouter");
        jButton12.setMaximumSize(new java.awt.Dimension(300, 30));
        jButton12.setMinimumSize(new java.awt.Dimension(300, 30));
        jButton12.setPreferredSize(new java.awt.Dimension(300, 30));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addGap(110, 110, 110)
                .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addGap(111, 111, 111)
                .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                .addGap(69, 69, 69))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPnl2Layout = new javax.swing.GroupLayout(jPnl2);
        jPnl2.setLayout(jPnl2Layout);
        jPnl2Layout.setHorizontalGroup(
            jPnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPnl2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPnl2Layout.setVerticalGroup(
            jPnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnl2Layout.createSequentialGroup()
                .addGroup(jPnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPnl2Layout.createSequentialGroup()
                        .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6)))
                .addGap(0, 0, 0)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel10.add(jPnl2, "card3");

        jPanel32.setMaximumSize(new java.awt.Dimension(367, 455));
        jPanel32.setMinimumSize(new java.awt.Dimension(367, 455));

        jPanel33.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtrage par", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 14), new java.awt.Color(102, 102, 102))); // NOI18N
        jPanel33.setMaximumSize(new java.awt.Dimension(354, 284));
        jPanel33.setMinimumSize(new java.awt.Dimension(354, 284));

        jLabel48.setText("Nom commence par");
        jLabel48.setMaximumSize(new java.awt.Dimension(96, 14));
        jLabel48.setMinimumSize(new java.awt.Dimension(96, 14));
        jLabel48.setPreferredSize(new java.awt.Dimension(96, 14));

        jLabel49.setForeground(new java.awt.Color(255, 0, 0));
        jLabel49.setMaximumSize(new java.awt.Dimension(200, 14));
        jLabel49.setMinimumSize(new java.awt.Dimension(200, 14));
        jLabel49.setPreferredSize(new java.awt.Dimension(200, 14));

        jLabel50.setMaximumSize(new java.awt.Dimension(25, 25));
        jLabel50.setMinimumSize(new java.awt.Dimension(25, 25));
        jLabel50.setName(""); // NOI18N
        jLabel50.setPreferredSize(new java.awt.Dimension(25, 25));

        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_search_19px.png"))); // NOI18N
        jButton19.setMaximumSize(new java.awt.Dimension(50, 25));
        jButton19.setMinimumSize(new java.awt.Dimension(50, 25));
        jButton19.setPreferredSize(new java.awt.Dimension(50, 25));
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jLabel52.setText("Numéro de client");

        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField7KeyPressed(evt);
            }
        });

        jLabel53.setForeground(new java.awt.Color(255, 0, 0));
        jLabel53.setMaximumSize(new java.awt.Dimension(200, 14));
        jLabel53.setMinimumSize(new java.awt.Dimension(200, 14));
        jLabel53.setPreferredSize(new java.awt.Dimension(200, 14));

        jLabel54.setMaximumSize(new java.awt.Dimension(25, 25));
        jLabel54.setMinimumSize(new java.awt.Dimension(25, 25));
        jLabel54.setName(""); // NOI18N
        jLabel54.setPreferredSize(new java.awt.Dimension(25, 25));

        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_search_19px.png"))); // NOI18N
        jButton20.setMaximumSize(new java.awt.Dimension(50, 25));
        jButton20.setMinimumSize(new java.awt.Dimension(50, 25));
        jButton20.setPreferredSize(new java.awt.Dimension(50, 25));
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton13.setText("Afficher tout");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel33Layout.createSequentialGroup()
                                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField6)
                                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                                .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel33Layout.createSequentialGroup()
                                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField7)
                                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton13)
                .addGap(18, 18, 18)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jLabel52)
                .addGap(3, 3, 3)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField7)
                    .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField6)
                    .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel34.setMaximumSize(new java.awt.Dimension(868, 449));
        jPanel34.setMinimumSize(new java.awt.Dimension(868, 449));
        jPanel34.setPreferredSize(new java.awt.Dimension(868, 449));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Identité du client", "Prénom", "Nom", "Adress", "Ville", "Code postal", "Téléphone", "Email"
            }
        ));
        jTable4.setFocusable(false);
        jTable4.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable4.setRowHeight(25);
        jTable4.setSelectionBackground(new java.awt.Color(0, 204, 0));
        jTable4.setShowVerticalLines(false);
        jTable4.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(jTable4);

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
        );

        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_update_left_rotation_24px.png"))); // NOI18N
        jButton21.setText("Modifier");
        jButton21.setMaximumSize(new java.awt.Dimension(300, 30));
        jButton21.setMinimumSize(new java.awt.Dimension(300, 30));
        jButton21.setPreferredSize(new java.awt.Dimension(300, 30));
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_remove_24px.png"))); // NOI18N
        jButton22.setText("Supprimer");
        jButton22.setMaximumSize(new java.awt.Dimension(300, 30));
        jButton22.setMinimumSize(new java.awt.Dimension(300, 30));
        jButton22.setPreferredSize(new java.awt.Dimension(300, 30));
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_add_24px.png"))); // NOI18N
        jButton23.setText("Ajouter");
        jButton23.setMaximumSize(new java.awt.Dimension(300, 30));
        jButton23.setMinimumSize(new java.awt.Dimension(300, 30));
        jButton23.setPreferredSize(new java.awt.Dimension(300, 30));
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addGap(110, 110, 110)
                .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addGap(111, 111, 111)
                .addComponent(jButton23, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                .addGap(69, 69, 69))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPnl3Layout = new javax.swing.GroupLayout(jPnl3);
        jPnl3.setLayout(jPnl3Layout);
        jPnl3Layout.setHorizontalGroup(
            jPnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPnl3Layout.createSequentialGroup()
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, 869, Short.MAX_VALUE))
            .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPnl3Layout.setVerticalGroup(
            jPnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnl3Layout.createSequentialGroup()
                .addGroup(jPnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPnl3Layout.createSequentialGroup()
                        .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6)))
                .addGap(0, 0, 0)
                .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel10.add(jPnl3, "card4");

        jPnl4.setPreferredSize(new java.awt.Dimension(1248, 510));

        jPanel39.setMaximumSize(new java.awt.Dimension(367, 455));
        jPanel39.setMinimumSize(new java.awt.Dimension(367, 455));

        jPanel40.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtrage par", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 14), new java.awt.Color(102, 102, 102))); // NOI18N
        jPanel40.setMaximumSize(new java.awt.Dimension(354, 284));
        jPanel40.setMinimumSize(new java.awt.Dimension(354, 284));

        jTextField8.setMaximumSize(new java.awt.Dimension(200, 25));
        jTextField8.setMinimumSize(new java.awt.Dimension(200, 25));
        jTextField8.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel51.setText("Nom de famille");
        jLabel51.setMaximumSize(new java.awt.Dimension(96, 14));
        jLabel51.setMinimumSize(new java.awt.Dimension(96, 14));
        jLabel51.setPreferredSize(new java.awt.Dimension(96, 14));

        jLabel55.setForeground(new java.awt.Color(255, 0, 0));
        jLabel55.setMaximumSize(new java.awt.Dimension(200, 14));
        jLabel55.setMinimumSize(new java.awt.Dimension(200, 14));
        jLabel55.setPreferredSize(new java.awt.Dimension(200, 14));

        jLabel56.setMaximumSize(new java.awt.Dimension(25, 25));
        jLabel56.setMinimumSize(new java.awt.Dimension(25, 25));
        jLabel56.setName(""); // NOI18N
        jLabel56.setPreferredSize(new java.awt.Dimension(25, 25));

        jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_search_19px.png"))); // NOI18N
        jButton24.setMaximumSize(new java.awt.Dimension(50, 25));
        jButton24.setMinimumSize(new java.awt.Dimension(50, 25));
        jButton24.setPreferredSize(new java.awt.Dimension(50, 25));
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jLabel57.setText("Numéro d'utilisateur");

        jTextField9.setMaximumSize(new java.awt.Dimension(200, 25));
        jTextField9.setMinimumSize(new java.awt.Dimension(200, 25));
        jTextField9.setPreferredSize(new java.awt.Dimension(200, 25));
        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField9KeyPressed(evt);
            }
        });

        jLabel58.setForeground(new java.awt.Color(255, 0, 0));
        jLabel58.setMaximumSize(new java.awt.Dimension(200, 14));
        jLabel58.setMinimumSize(new java.awt.Dimension(200, 14));
        jLabel58.setPreferredSize(new java.awt.Dimension(200, 14));

        jLabel59.setMaximumSize(new java.awt.Dimension(25, 25));
        jLabel59.setMinimumSize(new java.awt.Dimension(25, 25));
        jLabel59.setName(""); // NOI18N
        jLabel59.setPreferredSize(new java.awt.Dimension(25, 25));

        jButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_search_19px.png"))); // NOI18N
        jButton25.setMaximumSize(new java.awt.Dimension(50, 25));
        jButton25.setMinimumSize(new java.awt.Dimension(50, 25));
        jButton25.setPreferredSize(new java.awt.Dimension(50, 25));
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jLabel60.setText("Utilisateur est");

        jLabel61.setForeground(new java.awt.Color(255, 0, 0));
        jLabel61.setMaximumSize(new java.awt.Dimension(200, 14));
        jLabel61.setMinimumSize(new java.awt.Dimension(200, 14));
        jLabel61.setPreferredSize(new java.awt.Dimension(200, 14));

        jLabel62.setMaximumSize(new java.awt.Dimension(25, 25));
        jLabel62.setMinimumSize(new java.awt.Dimension(25, 25));
        jLabel62.setName(""); // NOI18N
        jLabel62.setPreferredSize(new java.awt.Dimension(25, 25));

        jButton29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_search_19px.png"))); // NOI18N
        jButton29.setMaximumSize(new java.awt.Dimension(50, 25));
        jButton29.setMinimumSize(new java.awt.Dimension(50, 25));
        jButton29.setPreferredSize(new java.awt.Dimension(50, 25));
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Réception" }));
        jComboBox3.setMaximumSize(new java.awt.Dimension(200, 25));
        jComboBox3.setMinimumSize(new java.awt.Dimension(200, 25));
        jComboBox3.setPreferredSize(new java.awt.Dimension(200, 25));

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(101, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel40Layout.createSequentialGroup()
                                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel40Layout.createSequentialGroup()
                                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel40Layout.createSequentialGroup()
                                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel40Layout.createSequentialGroup()
                                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(32, 32, 32)
                                .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(19, 19, 19))))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel60)
                .addGap(3, 3, 3)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jLabel57)
                .addGap(3, 3, 3)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel41.setMaximumSize(new java.awt.Dimension(868, 449));
        jPanel41.setMinimumSize(new java.awt.Dimension(868, 449));

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Identifiant", "Pénom", "Nom", "Téléphone", "Email", "Date naissance", "Est admin?", "Mot de pass", "Qcheck", "Admin_id"
            }
        ));
        jTable5.setFocusable(false);
        jTable5.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable5.setRowHeight(25);
        jTable5.setSelectionBackground(new java.awt.Color(0, 204, 0));
        jTable5.setShowVerticalLines(false);
        jTable5.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(jTable5);

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5)
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
        );

        jButton26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_update_left_rotation_24px.png"))); // NOI18N
        jButton26.setText("Modifier");
        jButton26.setMaximumSize(new java.awt.Dimension(300, 30));
        jButton26.setMinimumSize(new java.awt.Dimension(300, 30));
        jButton26.setPreferredSize(new java.awt.Dimension(300, 30));
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jButton27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_remove_24px.png"))); // NOI18N
        jButton27.setText("Supprimer");
        jButton27.setMaximumSize(new java.awt.Dimension(300, 30));
        jButton27.setMinimumSize(new java.awt.Dimension(300, 30));
        jButton27.setPreferredSize(new java.awt.Dimension(300, 30));
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jButton28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_add_24px.png"))); // NOI18N
        jButton28.setText("Ajouter");
        jButton28.setMaximumSize(new java.awt.Dimension(300, 30));
        jButton28.setMinimumSize(new java.awt.Dimension(300, 30));
        jButton28.setPreferredSize(new java.awt.Dimension(300, 30));
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jButton26, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addGap(110, 110, 110)
                .addComponent(jButton27, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                .addGap(111, 111, 111)
                .addComponent(jButton28, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                .addGap(69, 69, 69))
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPnl4Layout = new javax.swing.GroupLayout(jPnl4);
        jPnl4.setLayout(jPnl4Layout);
        jPnl4Layout.setHorizontalGroup(
            jPnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnl4Layout.createSequentialGroup()
                .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel42, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPnl4Layout.setVerticalGroup(
            jPnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnl4Layout.createSequentialGroup()
                .addGroup(jPnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPnl4Layout.createSequentialGroup()
                        .addComponent(jPanel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6)))
                .addGap(0, 0, 0)
                .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel10.add(jPnl4, "card5");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1253, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        // TODO add your handling code here:
        clickedColor(jPanel4);
        resetColor(jPanel7);
        resetColor(jPanel13);
        resetColor(jPanel36);

        jPanel10.removeAll();
        jPanel10.add(jPnl1);
        jPanel10.repaint();
        jPanel10.revalidate();
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        clickedColor(jPanel7);
        resetColor(jPanel4);
        resetColor(jPanel13);
        resetColor(jPanel36);

        jPanel10.removeAll();
        jPanel10.add(jPnl2);
        jPanel10.repaint();
        jPanel10.revalidate();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseEntered
        // TODO add your handling code here:
        setColor(jPanel11);
    }//GEN-LAST:event_jLabel17MouseEntered

    private void jLabel17MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseExited
        // TODO add your handling code here:
        resetColor(jPanel11);
    }//GEN-LAST:event_jLabel17MouseExited

    private void jLabel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseEntered
        // TODO add your handling code here:
        setColor(jPanel6);
    }//GEN-LAST:event_jLabel5MouseEntered

    private void jLabel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseExited
        // TODO add your handling code here:
        resetColor(jPanel6);
    }//GEN-LAST:event_jLabel5MouseExited

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        clickedColor(jPanel13);
        resetColor(jPanel4);
        resetColor(jPanel7);
        resetColor(jPanel36);

        jPanel10.removeAll();
        jPanel10.add(jPnl3);
        jPanel10.repaint();
        jPanel10.revalidate();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseEntered
        // TODO add your handling code here:
        setColor(jPanel9);
    }//GEN-LAST:event_jLabel6MouseEntered

    private void jLabel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseExited
        // TODO add your handling code here:
        resetColor(jPanel9);
    }//GEN-LAST:event_jLabel6MouseExited

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered
        setColor(jPanel14);
    }//GEN-LAST:event_jLabel3MouseEntered

    private void jLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseExited
        jPanel14.setBackground(new Color(102, 102, 102));
    }//GEN-LAST:event_jLabel3MouseExited

    private void jLabel37MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37MouseEntered
        setColor(jPanel28);
    }//GEN-LAST:event_jLabel37MouseEntered

    private void jLabel37MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37MouseExited
        resetColor(jPanel28);
    }//GEN-LAST:event_jLabel37MouseExited

    private void jLabel37MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37MouseClicked
        // TODO add your handling code here:

        try {
            String queryy = "select * from users where active=1 and isadmin=1";
            st = con.createStatement();
            rs = st.executeQuery(queryy);
            if (rs.next()) {
                clickedColor(jPanel36);
                resetColor(jPanel4);
                resetColor(jPanel13);
                resetColor(jPanel7);

                jPanel10.removeAll();
                jPanel10.add(jPnl4);
                jPanel10.repaint();
                jPanel10.revalidate();
            } else {
                JOptionPane.showMessageDialog(null, "Vous n'avez pas l'autorisation d'accéder \npour plus d'information contactez l'administrateur");
            }
        } catch (SQLException e) {
        }

    }//GEN-LAST:event_jLabel37MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Reservation rsv = new Reservation();
        rsv.setVisible(true);
        rsv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        String sql2 = "update users set active = 0 where active=1";
        try {

            pst3 = con.prepareStatement(sql2);
            int rss = pst3.executeUpdate();
            if (rss == 0) {

            } else {

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "info " + e);
        }

    }//GEN-LAST:event_formWindowClosing

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus

    }//GEN-LAST:event_formWindowGainedFocus

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            if (jTable1.getSelectedRowCount() == 1) {
                int row = jTable1.getSelectedRow();
                String cell = jTable1.getModel().getValueAt(row, 0).toString();
                String romnum = jTable1.getModel().getValueAt(row, 5).toString();
                pst = con.prepareStatement("delete from reservation where res_id =?");
                pst.setString(1, cell);
                int res = pst.executeUpdate();

                pst2 = con.prepareStatement("update rooms set status=0 where room_id=?");
                pst2.setString(1, romnum);
                int rres = pst2.executeUpdate();
                if (res == 0) {
                    JOptionPane.showMessageDialog(null, "détails de réservation non supprimés");
                } else {
                    JOptionPane.showMessageDialog(null, "détails de réservation supprimés");
                    printDataRes();
                }
            } else if (jTable1.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "La table est vide");
            } else if (jTable1.getSelectedRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner une ligne à supprimer");
            } else {
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner une seule ligne à supprimer.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            try {
                if (jTable1.getSelectedRowCount() == 1) {
                    int index = jTable1.getSelectedRow();

                    TableModel model = jTable1.getModel();

                    String r_id = model.getValueAt(index, 0).toString();
                    Date datei = new SimpleDateFormat("dd-MMM-yyyy").parse((String) model.getValueAt(index, 1));
                    Date dateo = new SimpleDateFormat("dd-MMM-yyyy").parse((String) model.getValueAt(index, 2));
                    String clientid = model.getValueAt(index, 3).toString();
                    String romnum = model.getValueAt(index, 5).toString();

                    pst1 = con.prepareStatement("update rooms set status=0 where room_id=?");
                    pst1.setString(1, romnum);
                    int rres = pst1.executeUpdate();
                    if (rres == 0) {
                        JOptionPane.showMessageDialog(null, "chambre error");
                    }
                    rsvup.setVisible(true);
                    rsvup.pack();
                    rsvup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                    rsvup.jTextField3.setText(r_id);
                    rsvup.jDateChooser1.setDate(datei);
                    rsvup.jDateChooser2.setDate(dateo);
                    rsvup.jTextField1.setText(clientid);

                } else if (jTable1.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "La table est vide.");
                } else if (jTable1.getSelectedRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner une ligne à modifier.");
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner une seule ligne à modifier.");
                }
            } catch (ParseException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String ro = jComboBox1.getSelectedItem().toString().trim();
        dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);
        try {
            st = con.createStatement();
            rs3 = st.executeQuery("select * from reservation where roomtype_id=get_rtype_id('" + ro + "')");
            while (rs3.next()) {
                dtm.addRow(new Object[]{rs3.getString(1), rs3.getString(2), rs3.getString(3), rs3.getString(4), rs3.getString(6), rs3.getString(7), rs3.getString(5)});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        printDataRes();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        String roo = jTextField2.getText().trim();
        dtm = (DefaultTableModel) jTable1.getModel();

        if (jTextField2.getText().isEmpty()) {
            jLabel14.setText("le champ est vide");
            jLabel19.setIcon(new ImageIcon("src\\Icons\\error.png"));
        } else {
            jLabel14.setText("");
            jLabel19.setIcon(new ImageIcon(""));
            try {

                st = con.createStatement();
                rs3 = st.executeQuery("select res_id,to_char(date_in,'dd-MON-yyyy'),"
                        + "to_char(date_out,'dd-MON-yyyy'),customer_id,user_id,"
                        + "roomtype_id,room_id from reservation where room_id=" + roo + "");
                if (rs3.next()) {
                    dtm.setRowCount(0);
                    jLabel14.setText("");
                    jLabel19.setIcon(new ImageIcon(""));
                    dtm.addRow(new Object[]{rs3.getString(1), rs3.getString(2), rs3.getString(3), rs3.getString(4), rs3.getString(6), rs3.getString(7), rs3.getString(5)});
                } else {
                    jLabel14.setText("chambre id n'exist pas");
                    jLabel19.setIcon(new ImageIcon("src\\Icons\\error.png"));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String rooo = jTextField1.getText().trim();
        dtm = (DefaultTableModel) jTable1.getModel();

        if (jTextField1.getText().isEmpty()) {
            jLabel12.setText("le champ est vide");
            jLabel15.setIcon(new ImageIcon("src\\Icons\\error.png"));
        } else {
            jLabel12.setText("");
            jLabel15.setIcon(new ImageIcon(""));
            try {

                st = con.createStatement();
                rs3 = st.executeQuery("select res_id,to_char(date_in,'dd-MON-yyyy'),"
                        + "to_char(date_out,'dd-MON-yyyy'),customer_id,user_id,"
                        + "roomtype_id,room_id from reservation where customer_id=" + rooo + "");
                st2 = con.createStatement();
                rs2 = st2.executeQuery("select * from customers where customer_id=" + rooo + "");
                if (rs2.next()) {
                    jLabel12.setText("");
                    jLabel15.setIcon(new ImageIcon(""));
                    dtm.setRowCount(0);
                    while (rs3.next()) {
                        dtm.addRow(new Object[]{rs3.getString(1), rs3.getString(2), rs3.getString(3), rs3.getString(4), rs3.getString(6), rs3.getString(7), rs3.getString(5)});
                    }
                } else {
                    jLabel12.setText("Client id n'exist pas");
                    jLabel15.setIcon(new ImageIcon("src\\Icons\\error.png"));
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        Client cl = new Client();
        cl.setVisible(true);
        cl.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        User ur = new User();
        ur.setVisible(true);
        ur.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        Rooms rms = new Rooms();
        rms.setVisible(true);
        rms.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        //throw user information to update
        try {
            if (jTable5.getSelectedRowCount() == 1) {
                int index = jTable5.getSelectedRow();

                TableModel model = jTable5.getModel();

                String user_id = model.getValueAt(index, 0).toString();
                String f_name = model.getValueAt(index, 1).toString();
                String l_name = model.getValueAt(index, 2).toString();
                String phone = model.getValueAt(index, 3).toString();
                String email = model.getValueAt(index, 4).toString();
                Date datee = new SimpleDateFormat("dd-MMM-yyyy").parse((String) model.getValueAt(index, 5));
                String pass = model.getValueAt(index, 7).toString();
                String qcheck = model.getValueAt(index, 8).toString();

                urup.setVisible(true);
                urup.pack();
                urup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                urup.jTextField5.setText(user_id);
                urup.jTextField1.setText(f_name);
                urup.jTextField2.setText(l_name);
                urup.jTextField3.setText(email);
                urup.jDateChooser1.setDate(datee);
                urup.jPasswordField1.setText(pass);
                urup.jPasswordField2.setText(pass);
                urup.jTextField4.setText(qcheck);
                urup.jTextField6.setText(phone);
                if (model.getValueAt(index, 9) == null) {
                    urup.jTextField7.setText("");
                } else {
                    urup.jTextField7.setText(model.getValueAt(index, 9).toString());
                }

            } else if (jTable5.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "La table est vide.");
            } else if (jTable5.getSelectedRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner une ligne à modifier.");
            } else {
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner une seule ligne à modifier.");
            }
        } catch (ParseException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        dtm2 = (DefaultTableModel) jTable2.getModel();
        dtm2.setRowCount(0);
        String rmtype = jComboBox2.getSelectedItem().toString();
        try {
            String q = "select room_id,decode(status,0,'disponible',1,'occupé'),phone_number,"
                    + "roomtype_id,hotel_id from rooms where roomtype_id=get_rtype_id('" + rmtype + "')";
            st = con.createStatement();
            rs7 = st.executeQuery(q);
            while (rs7.next()) {
                dtm2.addRow(new Object[]{rs7.getString(1), rs7.getString(2), rs7.getString(3),
                    rs7.getString(4), rs7.getString(5)});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        dtm2 = (DefaultTableModel) jTable2.getModel();

        String rmid = jTextField4.getText().trim();
        if (jTextField4.getText().isEmpty()) {
            jLabel24.setText("le champ est vide!!");
            jLabel25.setIcon(new ImageIcon("src\\Icons\\error.png"));
        } else {
            jLabel24.setText("");
            jLabel25.setIcon(new ImageIcon(""));
            try {
                String qq = "select * from rooms where room_id=?";
                pst4 = con.prepareStatement(qq);
                pst4.setString(1, rmid);
                rs8 = pst4.executeQuery();
                if (rs8.next()) {
                    dtm2.setRowCount(0);
                    jLabel24.setText("");
                    String q = "select room_id,decode(status,0,'disponible',1,'occupé'),phone_number,"
                            + "roomtype_id,hotel_id from rooms where room_id=" + rmid + "";
                    st = con.createStatement();
                    rs7 = st.executeQuery(q);
                    while (rs7.next()) {
                        dtm2.addRow(new Object[]{rs7.getString(1), rs7.getString(2), rs7.getString(3),
                            rs7.getString(4), rs7.getString(5)});
                    }
                } else {
                    jLabel24.setText("room id n'exist pas");
                    jLabel25.setIcon(new ImageIcon("src\\Icons\\error.png"));
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }//GEN-LAST:event_jButton9ActionPerformed

    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            jTextField4.setEditable(false);
            jLabel24.setText("Seulement les chiffres");
            jLabel25.setIcon(new ImageIcon("src\\Icons\\error.png"));
        } else {
            jTextField4.setEditable(true);
            jLabel24.setText("");
            jLabel25.setIcon(new ImageIcon(""));
        }
    }//GEN-LAST:event_jTextField4KeyPressed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        try {
            if (jTable5.getSelectedRowCount() == 1) {
                int row = jTable5.getSelectedRow();
                String cell = jTable5.getModel().getValueAt(row, 0).toString();
                pst = con.prepareStatement("delete from users where user_id =?");
                pst.setString(1, cell);
                int res = pst.executeUpdate();

                if (res == 0) {
                    JOptionPane.showMessageDialog(null, "utilisateur non supprimés");

                } else {
                    JOptionPane.showMessageDialog(null, "utilisateur supprimés");
                    printDataUser();
                }
            } else if (jTable5.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "La table est vide");
            } else if (jTable5.getSelectedRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner une ligne à supprimer");
            } else {
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner une seule ligne à supprimer.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed

        if (jTable4.getSelectedRowCount() == 1) {
            int index = jTable4.getSelectedRow();

            TableModel model = jTable4.getModel();

            String client_id = model.getValueAt(index, 0).toString();
            String f_name = model.getValueAt(index, 1).toString();
            String l_name = model.getValueAt(index, 2).toString();
            String adress = model.getValueAt(index, 3).toString();
            String ville = model.getValueAt(index, 4).toString();
            String zip = model.getValueAt(index, 5).toString();
            String phone = model.getValueAt(index, 6).toString();
            String mail = model.getValueAt(index, 7).toString();

            clup.setVisible(true);
            clup.pack();
            clup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            clup.jTextField8.setText(client_id);
            clup.jTextField1.setText(f_name);
            clup.jTextField2.setText(l_name);
            clup.jTextField3.setText(adress);
            clup.jTextField4.setText(ville);
            clup.jTextField5.setText(zip);
            clup.jTextField6.setText(phone);
            clup.jTextField7.setText(mail);

        } else if (jTable4.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "La table est vide.");
        } else if (jTable4.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner une ligne à modifier.");
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner une seule ligne à modifier.");
        }

    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        try {
            if (jTable4.getSelectedRowCount() == 1) {
                int row = jTable4.getSelectedRow();
                String cell = jTable4.getModel().getValueAt(row, 0).toString();
                pst = con.prepareStatement("delete from customers where customer_id =?");
                pst.setString(1, cell);
                int res = pst.executeUpdate();

                if (res == 0) {
                    JOptionPane.showMessageDialog(null, "Client non supprimés");
                } else {
                    JOptionPane.showMessageDialog(null, "Client supprimés");
                    printDataClient();
                }
            } else if (jTable4.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "La table est vide");
            } else if (jTable4.getSelectedRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner une ligne à supprimer");
            } else {
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner une seule ligne à supprimer.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        if (jTable2.getSelectedRowCount() == 1) {
            int index = jTable2.getSelectedRow();

            TableModel model = jTable2.getModel();

            String room_id = model.getValueAt(index, 0).toString();
            String phone = model.getValueAt(index, 2).toString();

            rmup.setVisible(true);
            rmup.pack();
            rmup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            rmup.jTextField1.setText(phone);
            rmup.jTextField2.setText(room_id);

        } else if (jTable2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "La table est vide.");
        } else if (jTable2.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner une ligne à modifier.");
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner une seule ligne à modifier.");
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        try {
            if (jTable2.getSelectedRowCount() == 1) {
                int row = jTable2.getSelectedRow();
                String cell = jTable2.getModel().getValueAt(row, 0).toString();
                pst = con.prepareStatement("delete from rooms where room_id =?");
                pst.setString(1, cell);
                int res = pst.executeUpdate();

                if (res == 0) {
                    JOptionPane.showMessageDialog(null, "Chambre non supprimés");
                } else {
                    JOptionPane.showMessageDialog(null, "Chambre supprimés");
                    printDataRooms();
                }
            } else if (jTable2.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "La table est vide");
            } else if (jTable2.getSelectedRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner une ligne à supprimer");
            } else {
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner une seule ligne à supprimer.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        dtm3 = (DefaultTableModel) jTable4.getModel();
        String client_id = jTextField7.getText().trim();
        if (jTextField7.getText().isEmpty()) {
            jLabel53.setText("le champ est vide");
            jLabel54.setIcon(new ImageIcon("src\\Icons\\error.png"));
        } else {
            jLabel53.setText("");
            jLabel54.setIcon(new ImageIcon(""));
            try {
                String query3 = "select * from customers where CUSTOMER_ID=" + client_id + "";
                st = con.createStatement();
                rs6 = st.executeQuery(query3);
                if (rs6.next()) {
                    dtm3.setRowCount(0);
                    jLabel53.setText("");
                    jLabel54.setIcon(new ImageIcon(""));
                    dtm3.addRow(new Object[]{rs6.getString(1), rs6.getString(2), rs6.getString(3),
                        rs6.getString(4), rs6.getString(5), rs6.getString(6), rs6.getString(7),
                        rs6.getString(8)});
                } else {
                    jLabel53.setText("Client id n'exist pas");
                    jLabel54.setIcon(new ImageIcon("src\\Icons\\error.png"));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        dtm3 = (DefaultTableModel) jTable4.getModel();
        String client_lname = jTextField6.getText().trim();
        if (jTextField6.getText().isEmpty()) {
            jLabel49.setText("le champ est vide");
            jLabel50.setIcon(new ImageIcon("src\\Icons\\error.png"));
        } else {
            jLabel49.setText("");
            jLabel50.setIcon(new ImageIcon(""));
            try {
                String query3 = "select * from customers where LAST_NAME like initcap('" + client_lname + "%')";
                st = con.createStatement();
                rs6 = st.executeQuery(query3);
                dtm3.setRowCount(0);

                while (rs6.next()) {
                    dtm3.addRow(new Object[]{rs6.getString(1), rs6.getString(2), rs6.getString(3),
                        rs6.getString(4), rs6.getString(5), rs6.getString(6), rs6.getString(7),
                        rs6.getString(8)});
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jTextField7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyPressed
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            jTextField7.setEditable(false);
            jLabel53.setText("Seulement les chiffres");
            jLabel54.setIcon(new ImageIcon("src\\Icons\\error.png"));
        } else {
            jTextField7.setEditable(true);
            jLabel53.setText("");
            jLabel54.setIcon(new ImageIcon(""));
        }

    }//GEN-LAST:event_jTextField7KeyPressed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        printDataClient();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        String isadmin = jComboBox3.getSelectedItem().toString();
        String admin;
        if (isadmin.equals("Admin")) {
            admin = "1";
        } else {
            admin = "0";
        }
        dtm4 = (DefaultTableModel) jTable5.getModel();
        dtm4.setRowCount(0);
        try {
            String query4 = "select user_id,first_name,last_name,phone_number,email,"
                    + "to_char(dateofbirth,'dd-MON-yyyy'),decode(isadmin,0,'Réception',1,'Admin'),password,qcheck,admin_id"
                    + " from users where isadmin=" + admin + "";
            st = con.createStatement();
            rs5 = st.executeQuery(query4);
            while (rs5.next()) {
                dtm4.addRow(new Object[]{rs5.getString(1), rs5.getString(2), rs5.getString(3),
                    rs5.getString(4), rs5.getString(5), rs5.getString(6), rs5.getString(7),
                    rs5.getString(8), rs5.getString(9), rs5.getString(10)});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        dispose();
        Login lg = new Login();
        lg.setVisible(true);
        String sql2 = "update users set active = 0 where active=1";
        try {

            pst3 = con.prepareStatement(sql2);
            int rss = pst3.executeUpdate();
            if (rss == 0) {

            } else {

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "info " + e);
        }
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel41MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel41MouseClicked
        printAll();
    }//GEN-LAST:event_jLabel41MouseClicked

    private void jLabel41MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel41MouseEntered
        setColor(jPanel44);
    }//GEN-LAST:event_jLabel41MouseEntered

    private void jLabel41MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel41MouseExited
        resetColor(jPanel44);
    }//GEN-LAST:event_jLabel41MouseExited

    private void jTextField9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyPressed
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            jTextField9.setEditable(false);
            jLabel58.setText("Seulement les chiffres");
            jLabel59.setIcon(new ImageIcon("src\\Icons\\error.png"));
        } else {
            jTextField9.setEditable(true);
            jLabel58.setText("");
            jLabel59.setIcon(new ImageIcon(""));
        }
    }//GEN-LAST:event_jTextField9KeyPressed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        dtm4 = (DefaultTableModel) jTable5.getModel();
        String us_id = jTextField9.getText().trim();
        if (jTextField9.getText().isEmpty()) {
            jLabel58.setText("le champ est vide");
            jLabel59.setIcon(new ImageIcon("src\\Icons\\error.png"));
        } else {
            try {
                jLabel58.setText("");
                jLabel59.setIcon(new ImageIcon(""));
                String query4 = "select user_id,first_name,last_name,phone_number,email,"
                        + "to_char(dateofbirth,'dd-MON-yyyy'),decode(isadmin,0,'Réception',1,'Admin'),password,qcheck,admin_id"
                        + " from users where user_id=" + us_id + "";
                st = con.createStatement();
                rs5 = st.executeQuery(query4);
                if (rs5.next()) {
                    jLabel58.setText("");
                    jLabel59.setIcon(new ImageIcon(""));
                    dtm4.setRowCount(0);
                    dtm4.addRow(new Object[]{rs5.getString(1), rs5.getString(2), rs5.getString(3),
                        rs5.getString(4), rs5.getString(5), rs5.getString(6), rs5.getString(7),
                        rs5.getString(8), rs5.getString(9), rs5.getString(10)});
                } else {
                    jLabel58.setText("User id n'exist pas");
                    jLabel59.setIcon(new ImageIcon("src\\Icons\\error.png"));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        dtm4 = (DefaultTableModel) jTable5.getModel();
        String us_lname = jTextField8.getText().trim();

        if (jTextField8.getText().isEmpty()) {
            jLabel55.setText("le champ est vide");
            jLabel56.setIcon(new ImageIcon("src\\Icons\\error.png"));
        } else {
            jLabel55.setText("");
            jLabel56.setIcon(new ImageIcon(""));
            try {
                String query4 = "select user_id,first_name,last_name,phone_number,email,"
                        + "to_char(dateofbirth,'dd-MON-yyyy'),decode(isadmin,0,'Réception',1,'Admin'),password,qcheck,admin_id"
                        + " from users where last_name=initcap('" + us_lname + "')";
                st = con.createStatement();
                rs5 = st.executeQuery(query4);
                if (rs5.next()) {
                    jLabel55.setText("");
                    jLabel56.setIcon(new ImageIcon(""));
                    dtm4.setRowCount(0);
                    dtm4.addRow(new Object[]{rs5.getString(1), rs5.getString(2), rs5.getString(3),
                        rs5.getString(4), rs5.getString(5), rs5.getString(6), rs5.getString(7),
                        rs5.getString(8), rs5.getString(9), rs5.getString(10)});
                } else {
                    jLabel55.setText("User n'exist pas");
                    jLabel56.setIcon(new ImageIcon("src\\Icons\\error.png"));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            jTextField2.setEditable(false);
            jLabel14.setText("Seulement les chiffres");
            jLabel19.setIcon(new ImageIcon("src\\Icons\\error.png"));
        } else {
            jTextField2.setEditable(true);
            jLabel14.setText("");
            jLabel19.setIcon(new ImageIcon(""));
        }
    }//GEN-LAST:event_jTextField2KeyPressed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            jTextField1.setEditable(false);
            jLabel12.setText("Seulement les chiffres");
            jLabel15.setIcon(new ImageIcon("src\\Icons\\error.png"));
        } else {
            jTextField1.setEditable(true);
            jLabel12.setText("");
            jLabel15.setIcon(new ImageIcon(""));
        }
    }//GEN-LAST:event_jTextField1KeyPressed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
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
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPnl1;
    private javax.swing.JPanel jPnl2;
    private javax.swing.JPanel jPnl3;
    private javax.swing.JPanel jPnl4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
