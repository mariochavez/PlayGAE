package models;

import siena.*;
import java.util.Date;

public class User extends Model {
    @Id
    public Long id;

    @NotNull
    public String email;
    public String name;

    public Date created;
    public Date updated;

    public User() {
        super();
    }

    public User(String email, String name) {
        this.email  = email;
        this.name = name;
    }

    public User(String email ) {
        this.email  = email;
    }

    static Query<User> all() {
        return Model.all(User.class);
    }

    public static User findById(Long id) {
        return all().filter("id", id).get();
    }

    public static User findByEmail(String email) {
        return all().filter("email", email).get();
    }
}
