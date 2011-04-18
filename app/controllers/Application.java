package controllers;

import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        if(Authentication.isLoggedIn()) {
            User user = User.findByEmail(Authentication.getEmail());
            if(user == null) {
                user = new User(Authentication.getEmail());
                user.created = new Date();
                user.insert();

                return;
            }
        }
        if (Authentication.isLoggedIn()) {
            User user = User.findByEmail(Authentication.getEmail());
            renderArgs.put("user", user.name);
        }

        List<BackHaul> backHauls = BackHaul.findByDate(new Date());
        for(BackHaul b : backHauls) {
            if (b.user != null) {
                b.user.get();
            }
        }

        render(backHauls);
    }

    public static void login() {
        Authentication.login("Application.index");
    }

    public static void logout() {
        Authentication.logout("Application.index");
    }
}
