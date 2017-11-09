/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import views.*;
import models.*;
import controllers.*;

public class Main {

    public static void main(String[] args) {
        ModelClientes model_cliente = new ModelClientes();
        ViewClientes view_cliente = new ViewClientes();

        ModelPeliculas model_pelicula = new ModelPeliculas();
        ViewPeliculas view_peliculas = new ViewPeliculas();

        ModelRentas model_rentas = new ModelRentas();
        ViewRentas view_rentas = new ViewRentas();

        ModelPrincipal model_principal = new ModelPrincipal();
        ViewPrincipal view_principal = new ViewPrincipal();

        Object model[] = new Object[4];
        Object view[] = new Object[4];

        model[0] = model_principal;
        model[1] = model_cliente;
        model[2] = model_pelicula;
        model[3] = model_rentas;

        view[0] = view_principal;
        view[1] = view_cliente;
        view[2] = view_peliculas;
        view[3] = view_rentas;

        ControllerClientes controller_cliente = new ControllerClientes(model, view);
        ControllerPeliculas controller_peliculas = new ControllerPeliculas(model, view);
        ControllerRentas controller_rentas = new ControllerRentas(model, view);
        ControllerPrincipal Controller_princopal = new ControllerPrincipal(model, view);

    }

}
