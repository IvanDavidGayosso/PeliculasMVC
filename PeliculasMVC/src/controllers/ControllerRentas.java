/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import views.ViewRentas;
import models.ModelRentas;

public class ControllerRentas {

    ViewRentas view_rentas;
    ModelRentas model_rentas;

    public ControllerRentas(Object[] model, Object[] view) {
        this.model_rentas = (ModelRentas) model[3];
        this.view_rentas = (ViewRentas) view[3];
        view_rentas.jbtn_agregar.addActionListener(e -> clic_jbtn_agregar());
        initView();
    }

    public void initView() {
        model_rentas.Conectar();
        model_rentas.seleccionarTodos();
        model_rentas.moverPrimero();
        getValores();
    }

    public void setValores() {
    
        model_rentas.setId_cliente((int) view_rentas.jcb_cliente.getSelectedItem());
        model_rentas.setId_pelicula((int) view_rentas.jcb_pelicula.getSelectedItem());
        model_rentas.setFormato((String) view_rentas.jcb_formato.getSelectedItem());
        model_rentas.setCosto_dia(Integer.parseInt(view_rentas.jtf_costo_dia.getText()));
        model_rentas.setDias(Integer.parseInt( view_rentas.jtf_dias.getText()));
        model_rentas.setTotal_renta(Integer.parseInt(view_rentas.jtf_total_renta.getText()));
        
    }

    public void getValores() {
        view_rentas.jcb_id_cliente.setSelectedItem(model_rentas.getId_cliente());
        view_rentas.jcb_id_pelicula.setSelectedItem(model_rentas.getId_pelicula());
        view_rentas.jcb_formato.setSelectedItem(model_rentas.getFormato());
        view_rentas.jtf_costo_dia.setText(String.valueOf(model_rentas.getCosto_dia()));
        view_rentas.jtf_dias.setText(String.valueOf(model_rentas.getDias()));
        view_rentas.jtf_total_renta.setText(String.valueOf(model_rentas.getTotal_renta()));
    }

 

    public void clic_jbtn_agregar() {/*
        setValores();
        model_peliculas.insertar();
        model_peliculas.seleccionarTodos();
        getValores();*/
    }

   
}
