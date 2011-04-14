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
            renderArgs.put("user", Authentication.getEmail());
        }
    }

    public static void newBackHaul() {
        BackHaul backhaul = new BackHaul();
        backhaul.originDate = new Date();

        render(backhaul);
    }

    public static void edit(Long id) {
        BackHaul backhaul = BackHaul.findById(id);
        if(backhaul == null) {
            flash.put("error", "No existe el registro");
            Application.index();
            return;
        }

        render(backhaul);
    }

    public static void create(@Required String origin, @Required String destination, @Required String contact, @InFuture() Date originDate, @Min(1) double cargoArea, @Min(1) double costByArea, @Min(1) double costByWeight) {

        BackHaul backhaul = new BackHaul(origin, destination, contact, originDate, cargoArea, costByArea, costByWeight);

        if(validation.hasErrors()) {
            flash.put("error", "Tenemos errores");
            render("BackHauls/newBackHaul.html", backhaul);
            return;
        }

        backhaul.insert();

        Application.index();
    }
}
