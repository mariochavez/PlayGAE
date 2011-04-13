package controllers;

import play.modules.gae.*;
import com.google.appengine.api.users.*;

public class Authentication {
    private static User currentUser = null;
    public static void setUser() {
        currentUser = new User("fake@mail.com", "mail.com");
    }

    public static void clearUser() {
        currentUser = null;
    }

    public static boolean isLoggedIn() {
        return GAE.isLoggedIn();
    }

    public static String getEmail() {
        if(currentUser != null)
            return currentUser.getEmail();

        return GAE.getUser().getEmail();
    }

    public static User getUser() {
        if(currentUser != null)
            return currentUser;

        return GAE.getUser();
    }

    public static void login(String action) {
        GAE.login(action);
    }

    public static void logout(String action) {
        GAE.logout(action);
    }
}
