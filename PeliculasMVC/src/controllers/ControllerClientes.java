/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.ModelClientes;
import views.ViewClientes;

public class ControllerClientes {

    ModelClientes model_cliente;
    ViewClientes view_cliente;

    public ControllerClientes(Object model[], Object view[]) {
        this.model_cliente = (ModelClientes) model[1];
        this.view_cliente = (ViewClientes) view[1];
        view_cliente.jbtn_nuevo.addActionListener(e -> clic_jbtn_nuevo());
        view_cliente.jbtn_agregar.addActionListener(e -> clic_jbtn_agregar());
        view_cliente.jbtn_modificar.addActionListener(e -> clic_jbtn_modificar());
        view_cliente.jbtn_eliminar.addActionListener(e -> clic_jbtn_eliminar());
        view_cliente.jbtn_primero.addActionListener(e -> clic_jbtn_primero());
        view_cliente.jbtn_anterior.addActionListener(e -> clic_jbtn_anterior());
        view_cliente.jbtn_siguiente.addActionListener(e -> clic_jbtn_siguiente());
        view_cliente.jbtn_ultimo.addActionListener(e -> clic_jbtn_ultimo());
        initView();
    }

    public void initView() {
        model_cliente.Conectar();
        model_cliente.seleccionarTodos();
        model_cliente.moverPrimero();
        getValores();
    }

    public void setValores() {
        if (!view_cliente.jtf_id_cliente.getText().equals("")) {
            model_cliente.setId_cliente(Integer.parseInt(view_cliente.jtf_id_cliente.getText()));
        }
        model_cliente.setNombre(view_cliente.jtf_nombre.getText());
        model_cliente.setTelefono(view_cliente.jtf_telefono.getText());
        model_cliente.setEmail(view_cliente.jtf_email.getText());
        model_cliente.setDireccion(view_cliente.jtf_direccion.getText());
    }

    public void getValores() {
        view_cliente.jtf_id_cliente.setText(String.valueOf(model_cliente.getId_cliente()));
        view_cliente.jtf_nombre.setText(model_cliente.getNombre());
        view_cliente.jtf_telefono.setText(model_cliente.getTelefono());
        view_cliente.jtf_email.setText(model_cliente.getEmail());
        view_cliente.jtf_direccion.setText(model_cliente.getDireccion());

    }

    public void clic_jbtn_nuevo() {
        view_cliente.jtf_id_cliente.setText("");
        view_cliente.jtf_nombre.setText("");
        view_cliente.jtf_telefono.setText("");
        view_cliente.jtf_email.setText("");
        view_cliente.jtf_direccion.setText("");
    }

    public void clic_jbtn_agregar() {
        setValores();
        model_cliente.insertar();
        model_cliente.seleccionarTodos();
        getValores();
    }

    public void clic_jbtn_modificar() {
        setValores();
        model_cliente.actualizar();
        model_cliente.seleccionarTodos();
        getValores();
    }

    public void clic_jbtn_eliminar() {
        model_cliente.borrar(Integer.parseInt(view_cliente.jtf_id_cliente.getText()));
        model_cliente.seleccionarTodos();
        getValores();
    }

    public void clic_jbtn_primero() {
        model_cliente.moverPrimero();
        getValores();
    }

    public void clic_jbtn_anterior() {
        model_cliente.moverAnterior();
        getValores();
    }

    public void clic_jbtn_siguiente() {
        model_cliente.moverSiguiente();
        getValores();
    }

    public void clic_jbtn_ultimo() {
        model_cliente.moverUltimo();
        getValores();
    }

}
