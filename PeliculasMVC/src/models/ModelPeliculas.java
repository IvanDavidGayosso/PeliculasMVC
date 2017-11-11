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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Ivan David
 */
public class ModelPeliculas {

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public int getSegundo() {
        return segundo;
    }

    public void setSegundo(int segundo) {
        this.segundo = segundo;
    }

    Connection conexion;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
    private int hora = 0;
    private int minuto = 0;
    private int segundo = 0;
    private int id_pelicula;
    private String pelicula;
    private String formato;
    private String duracion;
    private String descripcion;

    public int getId_pelicula() {
        return id_pelicula;
    }

    public void setId_pelicula(int id_pelicula) {
        this.id_pelicula = id_pelicula;
    }

    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void conectar() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/blockbooster", "root", "12345678");
        } catch (SQLException ex) {
            Logger.getLogger(ModelPeliculas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void llenarValores() {
        try {
            String hora = "", minuto = "", segundo = "";
            setId_pelicula(rs.getInt("id_pelicula"));
            setPelicula(rs.getString("pelicula"));
            setFormato(rs.getString("formato"));
            setDuracion(rs.getString("duracion"));
            for (int i = 0; i < duracion.length(); i++) {
                if (!(duracion.charAt(i) == ':') && i < 2) {
                    hora = hora + duracion.charAt(i);
                } else if (!(duracion.charAt(i) == ':') && i >= 2 && i < 5) {
                    minuto = minuto + duracion.charAt(i);
                } else if (!(duracion.charAt(i) == ':') && i >= 5) {
                    segundo = segundo + duracion.charAt(i);
                }
            }
            setHora(Integer.parseInt(hora));
            setMinuto(Integer.parseInt(minuto));
            setSegundo(Integer.parseInt(segundo));
            setDescripcion(rs.getString("descripcion"));
        } catch (SQLException ex) {
            Logger.getLogger(ModelPeliculas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void moverPrimero() {
        try {
            rs.first();
            llenarValores();
        } catch (SQLException ex) {
            Logger.getLogger(ModelPeliculas.class.getName()).log(Level.SEVERE, null, ex);
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
            sql = "select * from peliculas;";
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            moverPrimero();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 107 " + ex + "");
        }
    }

    public void mostrarTodos() {
        try {
            sql = "select * from peliculas;";
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 107 " + ex + "");
        }
    }

    public void insertar() {
        try {
            setDuracion(String.valueOf(hora) + ":" + String.valueOf(minuto) + ":" + String.valueOf(segundo));
            sql = "INSERT INTO peliculas (pelicula,formato, duracion, descripcion) VALUES (?,?,?,?);";
            ps = conexion.prepareStatement(sql);
            ps.setString(1, pelicula);
            ps.setString(2, formato);
            ps.setString(3, duracion);
            ps.setString(4, descripcion);
            ps.executeUpdate();
            moverPrimero();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 108 " + ex + "");
        }
    }

    public void borrar(int id_pelicula) {
        try {
            sql = "DELETE FROM peliculas WHERE id_pelicula=?;";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id_pelicula);
            ps.executeUpdate();
            moverPrimero();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 109 " + ex + "");
        }
    }

    public void actualizar() {
        try {
            setDuracion(String.valueOf(hora) + ":" + String.valueOf(minuto) + ":" + String.valueOf(segundo));
            sql = "UPDATE peliculas SET pelicula=?, formato=?, duracion=?, descripcion=? WHERE id_pelicula=?;";
            ps = conexion.prepareStatement(sql);
            ps.setString(1, pelicula);
            ps.setString(2, formato);
            ps.setString(3, duracion);
            ps.setString(4, descripcion);
            ps.setInt(5, id_pelicula);
            ps.executeUpdate();
            moverPrimero();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 110 " + ex + "");
        }
    }
}
