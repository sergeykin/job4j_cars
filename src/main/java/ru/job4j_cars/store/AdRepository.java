package ru.job4j_cars.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j_cars.model.Announcement;
import ru.job4j_cars.model.CarBrand;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class AdRepository {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    public static void main(String[] args) {
        AdRepository adRepository = new AdRepository();
        System.out.println(adRepository.showAnLastDay());

        System.out.println(adRepository.showAnWithPhoto());
        System.out.println(adRepository.showAnBrand("Tesla"));

    }

    public List<Announcement> showAnLastDay() {
        return this.tx(
                session -> {
                    Date date = new Date();
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    String s=df.format(date);
                    try {
                        date = df.parse(s);
                    } catch (ParseException ex) {
                    }
                    return session.createQuery("select an " +
                            "from ru.job4j_cars.model.Announcement an " +
                            "join fetch an.bodyType bt " +
                            "join fetch an.carBrand cb " +
                            "join fetch an.user u " +
                            "where an.created>=:pdate").setParameter("pdate", date).list();
                }
        );
    }

    public List<Announcement> showAnWithPhoto() {
        return this.tx(
                session -> {
                    return session.createQuery("select an " +
                            "from ru.job4j_cars.model.Announcement an " +
                            "join fetch an.bodyType bt " +
                            "join fetch an.carBrand cb " +
                            "join fetch an.user u " +
                            "where an.pathImage != null").list();
                }
        );
    }

    public List<Announcement> showAnBrand(String carBrandName) {
        return this.tx(
                session -> {
                    return session.createQuery("select an " +
                            "from ru.job4j_cars.model.Announcement an " +
                            "join fetch an.bodyType bt " +
                            "join fetch an.carBrand cb " +
                            "join fetch an.user u " +
                            "where cb.name = :pcarbrandName").setParameter("pcarbrandName", carBrandName).list();
                }
        );
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
