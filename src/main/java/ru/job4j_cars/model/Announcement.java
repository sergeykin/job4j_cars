package ru.job4j_cars.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "announcement")
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "car_brand_id")
    private CarBrand carBrand;

    @ManyToOne
    @JoinColumn(name = "body_type_id")
    private BodyType bodyType;

    private String pathImage;

    private boolean done;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Announcement(String description, boolean done, User user) {
        this.description = description;
        this.done = done;
        this.user = user;
    }

    public Announcement() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CarBrand getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(CarBrand carBrand) {
        this.carBrand = carBrand;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Announcement that = (Announcement) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", carBrand=" + carBrand +
                ", bodyType=" + bodyType +
                ", pathImage='" + pathImage + '\'' +
                ", done=" + done +
                ", user=" + user +
                '}';
    }
}
