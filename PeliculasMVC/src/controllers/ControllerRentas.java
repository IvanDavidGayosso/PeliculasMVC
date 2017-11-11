/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import views.ViewRentas;
import models.*;

public class ControllerRentas {

    ArrayList<String> clientes = new ArrayList<>();
    ArrayList<String> peliculas = new ArrayList<>();
    ViewRentas view_rentas;
    ModelRentas model_rentas;
    ModelClientes model_clientes;
    ModelPeliculas model_peliculas;

    public ControllerRentas(Object[] model, Object[] view) {
        this.model_clientes = (ModelClientes) model[1];
        this.model_peliculas = (ModelPeliculas) model[2];
        this.model_rentas = (ModelRentas) model[3];
        this.view_rentas = (ViewRentas) view[3];
        view_rentas.jbtn_agregar.addActionListener(e -> clic_jbtn_agregar());
        view_rentas.jcb_formato.addActionListener(e -> llenarCostos());
        view_rentas.js_dias.addChangeListener(e -> llenarCostos());
        initView();
    }

    public void initView() {
        model_rentas.Conectar();
        model_rentas.seleccionarTodos();
        llenarCombo();
        llenarCostos();

    }

    public void setValores() {

        model_rentas.setId_cliente(Integer.parseInt(clientes.get(view_rentas.jcb_cliente.getSelectedIndex())));
        model_rentas.setId_pelicula(Integer.parseInt(peliculas.get(view_rentas.jcb_pelicula.getSelectedIndex())));
        model_rentas.setFormato((String) view_rentas.jcb_formato.getSelectedItem());
        model_rentas.setCosto_dia(Integer.parseInt(view_rentas.jtf_costo_dia.getText()));
        model_rentas.setDias((int) view_rentas.js_dias.getValue());
        model_rentas.setTotal_renta(Integer.parseInt(view_rentas.jtf_total_renta.getText()));

    }

    public void getValores() {
        // view_rentas.jcb_id_cliente.setSelectedItem(model_rentas.getId_cliente());
        //view_rentas.jcb_id_pelicula.setSelectedItem(model_rentas.getId_pelicula());
        view_rentas.jcb_formato.setSelectedItem(model_rentas.getFormato());
        view_rentas.jtf_costo_dia.setText(String.valueOf(model_rentas.getCosto_dia()));
        view_rentas.js_dias.setValue(String.valueOf(model_rentas.getDias()));
        view_rentas.jtf_total_renta.setText(String.valueOf(model_rentas.getTotal_renta()));
    }

    public void clic_jbtn_agregar() {
        setValores();
        model_rentas.insertar();
        model_rentas.seleccionarTodos();
        getValores();
    }

    public boolean llenarCombo() {

        view_rentas.jcb_cliente.removeAllItems();
        view_rentas.jcb_pelicula.removeAllItems();
        model_clientes.mostrarTodos();
        model_peliculas.mostrarTodos();
        while (model_clientes.moverSiguiente() == true) {
            view_rentas.jcb_cliente.addItem(model_clientes.getNombre());
            clientes.add(String.valueOf(model_clientes.getId_cliente()));
        }
        while (model_peliculas.moverSiguiente() == true) {
            view_rentas.jcb_pelicula.addItem(model_peliculas.getPelicula());
            peliculas.add(String.valueOf(model_peliculas.getId_pelicula()));
        }
        return false;
    }

    public void llenarCostos() {
        model_rentas.setFormato((String) view_rentas.jcb_formato.getSelectedItem());
        model_rentas.setDias((int) view_rentas.js_dias.getValue());
        model_rentas.operacionesAutomaticas();
        view_rentas.jtf_costo_dia.setText(String.valueOf(model_rentas.getCosto_dia()));
        view_rentas.jtf_total_renta.setText(String.valueOf(model_rentas.getTotal_renta()));
    }

}
