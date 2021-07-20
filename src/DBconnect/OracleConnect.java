/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Medkaffouh
 */
public class OracleConnect {
    public Connection getConnect(){
        Connection conn=null;
        try {
            // registering Oracle driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // getting connection
            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                    "hotelmanagement", "ntic");

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Impossible de trouver le pilote de base de données "+e);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Impossible de se connecter à la base de données "+e);
        }
        
        return conn;
    }
}