package ru.job4j_cars.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j_cars.model.BodyType;
import ru.job4j_cars.model.CarBrand;
import ru.job4j_cars.model.User;

public class StartHib {
    public static void main(String[] args) {
//
//        User user = new User("User1");
//        CarBrand carBrand = new CarBrand("CarBrand1");
//        BodyType bodyType = new
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf
                    .withOptions()
                    .openSession();
            session.beginTransaction();


            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
