package models;

import siena.*;
import java.util.*;

public class BackHaul extends Model {
    @Id(Generator.AUTO_INCREMENT)
    public Long id;

    @NotNull
    public String origin;
    @NotNull
    public String destination;
    @NotNull
    public String contact;

    public Date originDate;
    public double cargoArea;
    public double costByArea;
    public double costByWeight;

    @Index("user_index")
    @Column("user")
    public User user;

    public BackHaul() {
        super();
    }


    public BackHaul(String origin, String destination, String contact, Date originDate, double cargoArea, double costByArea, double costByWeight, User user) {
        this.origin = origin;
        this.destination = destination;
        this.contact = contact;
        this.originDate = originDate;
        this.cargoArea = cargoArea;
        this.costByArea = costByArea;
        this.costByWeight = costByWeight;
        this.user = user;
    }

    static Query<BackHaul> all() {
        return Model.all(BackHaul.class);
    }

    public static BackHaul findById(Long id) {
        return all().filter("id", id).get();
    }

    public static List<BackHaul> findByOrigin(String origin) {
        return all().filter("origin", origin).fetch();
    }

    public static List<BackHaul> findByDestination(String destination) {
        return all().filter("destination", destination).fetch();
    }

    public static List<BackHaul> findByDate(Date date) {
        return all().filter("originDate >=", date).fetch();
    }

    public static List<BackHaul> findByUser(User user) {
        return all().filter("user", user).fetch();
    }
}
