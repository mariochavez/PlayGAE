package controllers;

import models.*;
import play.*;
import play.mvc.*;
import java.util.Date;

import play.Logger;

public class Users extends Application {

    @Before
    static void checkConnected() {
        if(Authentication.getUser() == null) {
            Application.login();
        } else {
            renderArgs.put("user", Authentication.getEmail());
        }
    }
    
    public static void index() {
        User user = User.findByEmail(Authentication.getEmail());

        render(user);
    }

    public static void edit() {
        User user = User.findByEmail(Authentication.getEmail());

        render(user);
    }

    public static void update(Long id, String name) {
        User user = User.findById(id);
        user.name = name;
        user.updated = new Date();
        user.update();

        flash.put("notice", "El usuario ha sido actualizado");
        index();
    }
}
