/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mySql;

import claseVeterinaria.ClaseVeterinaria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author aNiG
 */
public class Crud {

    //instaciamos la clase de conexion
    Conexion con = new Conexion();

    public void insertar(ClaseVeterinaria vet) throws SQLException {

        Connection conn = con.conexion();

        try {
            String sql = "INSERT INTO `duenomascota` "
                    + "(`nombre`, `apellido`, `edad`, `sexo`, `nombreMascota`, `sexoMascota`) "
                    + "VALUES ('" + vet.getNombre() + "', '" + vet.getApellido() + "', '" + vet.getEdad() + "', "
                    + "'" + vet.getSexo() + "', '" + vet.getNombreMascota() + "', '" + vet.getSexoMascota() + "')";

            System.out.println(sql);
            PreparedStatement stm = conn.prepareStatement(sql);
            //Ejecutamos el insert
            int c = stm.executeUpdate();
            //si es mayor a 0 es por que se ejecuro correctamente en caso contrario hubo un error
            if (c > 0) {
                JOptionPane.showMessageDialog(null, "Exito al grabar");
            } else {
                JOptionPane.showMessageDialog(null, "Error al grabar Revisar la consola");
            }
            conn.close();

        } catch (SQLException ex) {

            System.out.println(ex);
        }

    }

    public ClaseVeterinaria buscar(String nombreApellido) throws SQLException {

        Connection conn = con.conexion();
        Statement stmt = conn.createStatement();
        //Ejecutamos la consulta
        ResultSet rs = stmt.executeQuery("SELECT * FROM `duenomascota` WHERE `nombre` LIKE '" + nombreApellido + "' or `apellido` LIKE '" + nombreApellido + "'");
        if (rs.next()) {
            //Recatamos cada uno de los campos obtenido de la consulta
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String edad = rs.getString("edad");
            String sexo = rs.getString("sexo");
            String nombreMascota = rs.getString("nombreMascota");
            String sexoMascota = rs.getString("sexoMascota");

            ClaseVeterinaria vet = new ClaseVeterinaria(nombre, apellido, edad, sexo, nombreMascota, sexoMascota);
            conn.close();
            return vet;
        }

        conn.close();
        return null;
    }

    public boolean eliminar(ClaseVeterinaria vet) {

        Connection conn = con.conexion();
        try {

            String sql = "DELETE FROM `duenomascota` WHERE "
                    + "`nombre` like '" + vet.getNombre() + "' and apellido like '" + vet.getApellido() + "' and edad = " + vet.getEdad() + " "
                    + "and sexo like '" + vet.getSexo() + "' and nombreMascota "
                    + "like '" + vet.getNombreMascota() + "' and sexoMascota like '" + vet.getSexoMascota() + "'";

            PreparedStatement stm = conn.prepareStatement(sql);
            //Ejecutamos la consulta
            stm.executeUpdate();
            conn.close();
            //Retornamos un verdadero si se elimino exitosamente
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<ClaseVeterinaria> lista() {

        ArrayList<ClaseVeterinaria> listaVet = new ArrayList<>();
        try {
            Connection conn = con.conexion();

            Statement stmt = conn.createStatement();
            //Ejecutamos la consulta

            ResultSet rs = stmt.executeQuery("select * from duenomascota");
            while (rs.next()) {

                //Recatamos cada uno de los campos obtenido de la consulta
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String edad = rs.getString("edad");
                String sexo = rs.getString("sexo");
                String nombreMascota = rs.getString("nombreMascota");
                String sexoMascota = rs.getString("sexoMascota");
                ClaseVeterinaria vet = new ClaseVeterinaria(nombre, apellido, edad, sexo, nombreMascota, sexoMascota);

                listaVet.add(vet);

            }
            return listaVet;

        } catch (SQLException ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
