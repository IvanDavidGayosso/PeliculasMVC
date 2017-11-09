/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.ModelPrincipal;
import views.*;

public class ControllerPrincipal {

    ModelPrincipal model_principal;
    ViewPrincipal view_principal;
    ViewClientes view_clientes;
    ViewPeliculas view_peliculas;
    ViewRentas view_rentas;

    public ControllerPrincipal(Object[] model, Object[] view) {
        this.view_clientes = (ViewClientes) view[1];
        this.view_peliculas = (ViewPeliculas) view[2];
        this.view_rentas = (ViewRentas) view[3];
        this.model_principal = (ModelPrincipal) model[0];
        this.view_principal = (ViewPrincipal) view[0];
        view_principal.jmi_clientes.addActionListener(e -> clic_jmi_clientes());
        view_principal.jmi_peliculas.addActionListener(e -> clic_jmi_peliculas());
        view_principal.jmi_rentas.addActionListener(e-> clic_jmi_rentas());
        view_principal.jmi_salir.addActionListener(e -> clic_jmi_salir());
        initView();
    }

    private void clic_jmi_clientes() {
        view_principal.setContentPane(view_clientes);
        view_principal.revalidate();
        view_principal.repaint();
    }

    private void clic_jmi_peliculas() {
        view_principal.setContentPane(view_peliculas);
        view_principal.revalidate();
        view_principal.repaint();
    }

    private void clic_jmi_rentas() {
        view_principal.setContentPane(view_rentas);
        view_principal.revalidate();
        view_principal.repaint();
    }

    private void clic_jmi_salir() {
        System.exit(0);
    }

    private void initView() {
        view_principal.setVisible(true);
    }

}
