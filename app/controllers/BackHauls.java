package controllers;

import models.*;
import play.*;
import play.mvc.*;
import play.data.validation.*;
import java.util.Date;

import play.Logger;

public class BackHauls extends Application {

    @Before
    static void checkConnected() {
        if(Authentication.getUser() == null) {
            Application.login();
        } else {
            User user = User.findByEmail(Authentication.getEmail());
            renderArgs.put("user", user.name);
        }
    }

    public static void newBackHaul() {
        BackHaul backhaul = new BackHaul();
        backhaul.originDate = new Date();

        render(backhaul);
    }

    public static void edit(Long id) {
        BackHaul backhaul = BackHaul.findById(id);
        User user = User.findByEmail(Authentication.getEmail());

        if(!backhaul.user.id.equals(user.id))
        {
            flash.put("error", "Usted no puede modificar este registro");
            Application.index();
            return;
        }

        if(backhaul == null) {
            flash.put("error", "No existe el registro");
            Application.index();
            return;
        }

        render(backhaul);
    }

    public static void create(@Required String origin, @Required String destination, @Required String contact, @InFuture() Date originDate, @Min(1) double cargoArea, @Min(1) double costByArea, @Min(1) double costByWeight) {

        User user = User.findByEmail(Authentication.getEmail());
        BackHaul backhaul = new BackHaul(origin, destination, contact, originDate, cargoArea, costByArea, costByWeight, user);

        if(validation.hasErrors()) {
            flash.put("error", "Tenemos errores");
            render("BackHauls/newBackHaul.html", backhaul);
            return;
        }

        backhaul.insert();

        flash.put("notice", "La oferta fue creada");
        Application.index();
    }


    public static void update(Long id, @Required String origin, @Required String destination, @Required String contact, @InFuture() Date originDate, @Min(1) double cargoArea, @Min(1) double costByArea, @Min(1) double costByWeight) {
        BackHaul backhaul = BackHaul.findById(id);

        if(backhaul == null) {
            flash.put("error", "No existe el registro");
            Application.index();
            return;
        }

        backhaul.origin = origin;
        backhaul.destination = destination;
        backhaul.contact = contact;
        backhaul.originDate = originDate;
        backhaul.cargoArea = cargoArea;
        backhaul.costByArea = costByArea;
        backhaul.costByWeight = costByWeight;

        if(validation.hasErrors()) {
            flash.put("error", "Tenemos errores");
            render("BackHauls/edit.html", backhaul);
            return;
        }

        backhaul.update();

        flash.put("notice", "La oferta fue actualizada");
        Application.index();
    }
}
