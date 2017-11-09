/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import views.ViewPeliculas;
import models.ModelPeliculas;

public class ControllerPeliculas {

    ViewPeliculas view_peliculas;
    ModelPeliculas model_peliculas;

    public ControllerPeliculas(Object[] model, Object[] view) {
        this.model_peliculas = (ModelPeliculas) model[2];
        this.view_peliculas = (ViewPeliculas) view[2];
        view_peliculas.jbtn_nuevo.addActionListener(e -> clic_jbtn_nuevo());
        view_peliculas.jbtn_agregar.addActionListener(e -> clic_jbtn_agregar());
        view_peliculas.jbtn_modificar.addActionListener(e -> clic_jbtn_modificar());
        view_peliculas.jbtn_eliminar.addActionListener(e -> clic_jbtn_eliminar());
        view_peliculas.jbtn_primero.addActionListener(e -> clic_jbtn_primero());
        view_peliculas.jbtn_anterior.addActionListener(e -> clic_jbtn_anterior());
        view_peliculas.jbtn_siguiente.addActionListener(e -> clic_jbtn_siguiente());
        view_peliculas.jbtn_ultimo.addActionListener(e -> clic_jbtn_ultimo());
        initView();
    }

    public void initView() {
        model_peliculas.conectar();
        model_peliculas.seleccionarTodos();
        model_peliculas.moverPrimero();
        getValores();
    }

    public void setValores() {
        if (!view_peliculas.jtf_id_pelicula.getText().equals("")) {
            model_peliculas.setId_pelicula(Integer.parseInt(view_peliculas.jtf_id_pelicula.getText()));
        }
        model_peliculas.setPelicula(view_peliculas.jtf_pelicula.getText());
        model_peliculas.setFormato((String) view_peliculas.jcb_formato.getSelectedItem());
        model_peliculas.setHora((int)view_peliculas.js_hora.getValue());
        model_peliculas.setMinuto((int) view_peliculas.js_minuto.getValue());
        model_peliculas.setSegundo((int) view_peliculas.js_segundo.getValue());
        model_peliculas.setDescripcion(view_peliculas.jta_descripcion.getText());
    }

    public void getValores() {
        view_peliculas.jtf_id_pelicula.setText(String.valueOf(model_peliculas.getId_pelicula()));
        view_peliculas.jtf_pelicula.setText(model_peliculas.getPelicula());
        view_peliculas.jcb_formato.setSelectedItem(model_peliculas.getFormato());
        view_peliculas.js_hora.setValue(model_peliculas.getHora());
        view_peliculas.js_minuto.setValue(model_peliculas.getMinuto());
        view_peliculas.js_segundo.setValue(model_peliculas.getSegundo());
        view_peliculas.jta_descripcion.setText(model_peliculas.getDescripcion());

    }

    public void clic_jbtn_nuevo() {
        view_peliculas.jtf_id_pelicula.setText("");
        view_peliculas.jtf_pelicula.setText("");
        view_peliculas.jta_descripcion.setText("");
    }

    public void clic_jbtn_agregar() {
        setValores();
        model_peliculas.insertar();
        model_peliculas.seleccionarTodos();
        getValores();
    }

    public void clic_jbtn_modificar() {
        setValores();
        model_peliculas.actualizar();
        model_peliculas.seleccionarTodos();
        getValores();
    }

    public void clic_jbtn_eliminar() {
        model_peliculas.borrar(Integer.parseInt(view_peliculas.jtf_id_pelicula.getText()));
        model_peliculas.seleccionarTodos();
        getValores();
    }

    public void clic_jbtn_primero() {
        model_peliculas.moverPrimero();
        getValores();
    }

    public void clic_jbtn_anterior() {
        model_peliculas.moverAnterior();
        getValores();
    }

    public void clic_jbtn_siguiente() {
        model_peliculas.moverSiguiente();
        getValores();
    }

    public void clic_jbtn_ultimo() {
        model_peliculas.moverUltimo();
        getValores();
    }

}
