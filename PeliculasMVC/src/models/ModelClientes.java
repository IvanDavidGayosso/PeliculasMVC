/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ModelClientes {

    private Connection conexion;
    private ResultSet rs;
    private PreparedStatement ps;
    private String sql;

    private int id_cliente;
    private String nombre;
    private String telefono;
    private String email;
    private String direccion;

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void Conectar() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/blockbooster", "root", "12345678");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 101" + ex);
        }
    }

    public void llenarValores() {
        try {
            setId_cliente(rs.getInt("id_cliente"));
            setNombre(rs.getString("nombre"));
            setTelefono(rs.getString("telefono"));
            setEmail(rs.getString("email"));
            setDireccion(rs.getString("direccion"));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 102" + ex);
        }
    }

    public void moverPrimero() {
        try {
            rs.first();
            llenarValores();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 103" + ex);
        }
    }

    public void moverUltimo() {
        try {
            rs.last();
            llenarValores();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 104");
        }
    }

    public boolean moverSiguiente() {
        try {
            if (rs.isLast() == false) {
                rs.next();
                llenarValores();
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 105");
        }
        return false;
    }

    public void moverAnterior() {
        try {
            if (rs.isFirst() == false) {
                rs.previous();
                llenarValores();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 106");
        }
    }

    public void seleccionarTodos() {

        try {
            sql = "select * from clientes;";
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            moverPrimero();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 107 " + ex + "");
        }
    }

    public void mostrarTodos() {

        try {
            sql = "select * from clientes;";
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 107 " + ex + "");
        }
    }

    public void insertar() {
        try {
            sql = "INSERT INTO clientes (nombre,telefono,email,direccion) VALUES (?,?,?,?);";
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, telefono);
            ps.setString(3, email);
            ps.setString(4, direccion);
            ps.executeUpdate();
            moverPrimero();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 108 " + ex + "");
        }
    }

    public void borrar(int cliente) {
        try {
            sql = "DELETE FROM clientes WHERE id_cliente=?;";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id_cliente);
            ps.executeUpdate();
            moverPrimero();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 109 " + ex + "");
        }
    }

    public void actualizar() {
        try {
            sql = "UPDATE clientes SET nombre=?, telefono=?, email=?, direccion=? WHERE id_cliente=?;";
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, telefono);
            ps.setString(3, email);
            ps.setString(4, direccion);
            ps.setInt(5, id_cliente);
            ps.executeUpdate();
            moverPrimero();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 110 " + ex + "");
        }
    }
}
