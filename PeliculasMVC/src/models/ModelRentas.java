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

public class ModelRentas {

    private Connection conexion;
    private ResultSet rs;
    private PreparedStatement ps;
    private String sql;

    private int id_renta;
    private int id_cliente;
    private int id_pelicula;
    private String formato;
    private int costo_dia;
    private int dias;
    private int total_renta;

    public int getId_renta() {
        return id_renta;
    }

    public void setId_renta(int id_renta) {
        this.id_renta = id_renta;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_pelicula() {
        return id_pelicula;
    }

    public void setId_pelicula(int id_pelicula) {
        this.id_pelicula = id_pelicula;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public int getCosto_dia() {
        return costo_dia;
    }

    public void setCosto_dia(int costo_dia) {
        this.costo_dia = costo_dia;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public int getTotal_renta() {
        return total_renta;
    }

    public void setTotal_renta(int total_renta) {
        this.total_renta = total_renta;
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
            setId_renta(rs.getInt("id_renta"));
            setId_cliente(rs.getInt("id_cliente"));
            setId_pelicula(rs.getInt("id_pelicula"));
            setFormato(rs.getString("formato"));
            setCosto_dia(rs.getInt("costo_dia"));
            setDias(rs.getInt("dias"));
            setTotal_renta(rs.getInt("total_renta"));
            
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

    public void moverSiguiente() {
        try {
            if (rs.isLast() == false) {
                rs.next();
                llenarValores();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 105");
        }
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
            sql = "select * from rentas;";
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            moverPrimero();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 107 " + ex + "");
        }
    }

    public void insertar() {
        try {
            sql = "insert into rentas(id_cliente,id_pelicula ,formato,costo_dia,dias,total_renta) values (?,?,?,?,?,?);";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id_cliente);
            ps.setInt(2, id_pelicula);
            ps.setString(3, formato);
            ps.setInt(4, costo_dia);
            ps.setInt(5, dias);
            ps.setInt(6, total_renta);
            ps.executeUpdate();
            moverPrimero();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 108 " + ex + "");
        }
    }

    public void borrar() {
        try {
            sql = "DELETE FROM rentas WHERE id_renta=?;";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, getId_renta());
            ps.executeUpdate();
            moverPrimero();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 109 " + ex + "");
        }
    }

    public void actualizar() {
        try {
            sql = "UPDATE rentas SET id_cliente=?, id_pelicula=?, formato=?, costo_dia=? ,dias=? , total_renta=? "
                    + "WHERE id_renta=?;";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id_cliente);
            ps.setInt(2, id_pelicula);
            ps.setString(3, formato);
            ps.setInt(4, costo_dia);
            ps.setInt(5, dias);
            ps.setInt(6, total_renta);
            ps.setInt(7, id_renta);
            ps.executeUpdate();
            moverPrimero();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 110 " + ex + "");
        }
    }
    
    public void operacionesAutomaticas(){
        if(formato.equals("DVD")){
            setCosto_dia(10);
        }else{
            setCosto_dia(15);
        }
        this.setTotal_renta(getCosto_dia()*getDias());
    }
   
}
