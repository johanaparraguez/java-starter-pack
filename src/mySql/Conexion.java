/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mySql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author aNiG
 */
public class Conexion {
    
        Connection conect = null;

       // <editor-fold defaultstate="collapsed" desc="Conexión a MySQL con mensaje">  
       public Connection conexionMsg()
        {
          try {

               //Cargamos el Driver MySQL
               Class.forName("org.gjt.mm.mysql.Driver");
               conect = DriverManager.getConnection("jdbc:mysql://localhost/veterinaria","root","abcdef");
            return conect;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Error "+e);
            }
            JOptionPane.showMessageDialog(null, "Error en la conexion a la base de datos");
            return null;
        }// </editor-fold>


       // <editor-fold defaultstate="collapsed" desc="Conexión a MySQL"> 
       public Connection conexion()
        {
          try {

               //Cargamos el Driver MySQL
               Class.forName("org.gjt.mm.mysql.Driver");
               conect = DriverManager.getConnection("jdbc:mysql://localhost/veterinaria","root","abcdef");
            } catch (Exception e) {
                System.out.println("No se puede conectar "+e);
            }
            return conect;
        }// </editor-fold>
        
}
    
